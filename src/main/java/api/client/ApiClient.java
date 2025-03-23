package api.client;

import api.exceptions.ApiException;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.core.ConditionTimeoutException;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import static org.awaitility.Awaitility.await;

@Slf4j
public class ApiClient {
    private final String baseUrl;
    private final RequestConfigurator requestConfigurator;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.requestConfigurator = new RequestConfigurator();
        log.info("Rest Api client is created with base URL: {}", baseUrl);
    }

    public Response execute(ApiRequest request) {
        if (request.getMethod() == null) {
            throw new IllegalArgumentException("HTTP Method cannot be null");
        }

        if (request.isUseWait()) {
            return waitForStatusCode(request);
        }

        return sendRequest(request);
    }

    private Response sendRequest(ApiRequest request) {
        try {
            log.info("Executing request: {}", request);
            return new RequestConfigurator().send(request, baseUrl);
        } catch (Exception e) {
            log.error("Request execution error: {}. Error: {}", request, e.getMessage(), e);
            throw new ApiException("Request execution error", e);
        }
    }

    private Response waitForStatusCode(ApiRequest request) {
        AtomicReference<Response> lastResponse = new AtomicReference<>();

        try {
            await()
                    .atMost(Duration.ofSeconds(request.getTimeoutSeconds()))
                    .pollInterval(Duration.ofSeconds(request.getPollingIntervalSeconds()))
                    .ignoreExceptions()
                    .until(() -> {
                        lastResponse.set(sendRequest(request)); // Вызываем sendRequest и сохраняем ответ
                        return lastResponse.get().getStatusCode() == request.getExpectedStatusCode().getCode();
                    });
        } catch (ConditionTimeoutException e) {
            log.warn("Timeout reached while waiting for status code {}. Returning last response: {}",
                    request.getExpectedStatusCode(), lastResponse.get());
        }

        return lastResponse.get();
    }
}