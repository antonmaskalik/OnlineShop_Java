package api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class RequestConfigurator {
    public Response send(ApiRequest request, String baseUrl) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);

        if (request.getQueryParams() != null) requestSpecBuilder.addQueryParams(request.getQueryParams());
        if (request.getHeaders() != null) requestSpecBuilder.addHeaders(request.getHeaders());
        if (request.getBody() != null) requestSpecBuilder.setBody(request.getBody());

        RequestSpecification spec = requestSpecBuilder.build();
        return given()
                .spec(spec)
                .when()
                .request(request.getMethod(), baseUrl + request.getEndpoint());
    }
}