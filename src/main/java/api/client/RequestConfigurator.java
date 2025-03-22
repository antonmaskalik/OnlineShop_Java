package api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class RequestConfigurator {
    private final RequestSpecBuilder requestSpecBuilder;

    public RequestConfigurator() {
        this.requestSpecBuilder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);
    }

    public RequestConfigurator withQueryParams(Map<String, Object> queryParams) {
        if (queryParams != null) {
            requestSpecBuilder.addQueryParams(queryParams);
        }
        return this;
    }

    public RequestConfigurator withHeaders(Map<String, String> headers) {
        if (headers != null) {
            requestSpecBuilder.addHeaders(headers);
        }
        return this;
    }

    public RequestConfigurator withBody(Object body) {
        if (body != null) {
            requestSpecBuilder.setBody(body);
        }
        return this;
    }

    public Response send(Method method, String baseUrl, String endpoint) {
        RequestSpecification spec = requestSpecBuilder.build();
        log.info("Sending request: {} {} with spec: {}", method, baseUrl + endpoint, spec);
        return given()
                .spec(spec)
                .filter(new RequestLoggingFilter())  // Логирует запрос
                .filter(new ResponseLoggingFilter())
                .when()
                .request(method, baseUrl + endpoint);
    }
}