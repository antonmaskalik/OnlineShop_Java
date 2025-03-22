package configs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiConfigModel {

    @JsonProperty("baseUrl")
    private String baseUrl;


    public ApiConfigModel() {
    }

    public String getBaseUrl() { return baseUrl; }
}