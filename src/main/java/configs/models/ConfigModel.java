package configs.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.BrowserType;

public class ConfigModel {

    @JsonProperty("browser")
    private String browser;

    @JsonProperty("remoteRun")
    private boolean isRemoteRun;

    @JsonProperty("baseUrl")
    private String baseUrl;

    @JsonProperty("remoteHubUrl")
    private String remoteHubUrl;


    public ConfigModel() {
    }

    public BrowserType getBrowser() { return BrowserType.valueOf(browser.toUpperCase()); }
    public boolean IsRemoteRun() { return isRemoteRun; }
    public String getBaseUrl() { return baseUrl; }
    public String getRemoteHubUrl() { return remoteHubUrl; }
}