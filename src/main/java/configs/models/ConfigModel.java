package configs.models;

public class ConfigModel {
    private String browser;
    private String baseUrl;
    private String remoteHubUrl;

    public ConfigModel() {
    }

    public String getBrowser() { return browser; }
    public String getBaseUrl() { return baseUrl; }
    public String getRemoteHubUrl() { return remoteHubUrl; }
}
