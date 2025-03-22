package api.client;

import enums.HttpStatusCode;
import enums.WaitTimeout;
import io.restassured.http.Method;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ApiRequest {
    private final Method method;
    private final String endpoint;
    private final Map<String, Object> queryParams;
    private final Map<String, String> headers;
    private final Object body;

    private final boolean useWait;
    private final HttpStatusCode expectedStatusCode;
    private final int timeoutSeconds;
    private final int pollingIntervalSeconds;

    private ApiRequest(Builder builder) {
        this.method = builder.method;
        this.endpoint = builder.endpoint;
        this.queryParams = builder.queryParams;
        this.headers = builder.headers;
        this.body = builder.body;
        this.useWait = builder.useWait;
        this.expectedStatusCode = builder.expectedStatusCode;
        this.timeoutSeconds = builder.timeoutSeconds;
        this.pollingIntervalSeconds = builder.pollingIntervalSeconds;
    }

    public static class Builder {
        private final Method method;
        private final String endpoint;
        private final Map<String, Object> queryParams = new HashMap<>();
        private final Map<String, String> headers = new HashMap<>();
        private Object body;
        private boolean useWait;
        private HttpStatusCode expectedStatusCode = HttpStatusCode.OK;
        private int timeoutSeconds = WaitTimeout.API_REQUEST.getSeconds();
        private int pollingIntervalSeconds = 1;

        public Builder(Method method, String endpoint) {
            this.method = method;
            this.endpoint = endpoint;
        }

        public Builder queryParams(Map<String, Object> queryParams) {
            if (queryParams != null) {
                this.queryParams.putAll(queryParams);
            }
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            if (headers != null) {
                this.headers.putAll(headers);
            }
            return this;
        }

        public Builder body(Object body) {
            this.body = body;
            return this;
        }

        public Builder withWait(HttpStatusCode expectedStatusCode, int timeoutSeconds, int pollingIntervalSeconds) {
            this.useWait = true;
            this.expectedStatusCode = expectedStatusCode;
            this.timeoutSeconds = timeoutSeconds;
            this.pollingIntervalSeconds = pollingIntervalSeconds;
            return this;
        }

        public Builder withWait() {
            return withWait(expectedStatusCode, timeoutSeconds, pollingIntervalSeconds);
        }

        public ApiRequest build() {
            if (method == null || endpoint == null) {
                throw new IllegalArgumentException("Method and endpoint must not be null");
            }
            return new ApiRequest(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
                """
                        API Request:
                          Method: %s
                          Endpoint: %s
                          Query Params: %s
                          Headers: %s
                          Body: %s
                        """,
                method, endpoint, queryParams, headers, body
        );
    }
}
