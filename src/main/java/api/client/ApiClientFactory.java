package api.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApiClientFactory {
    private static final Map<String, ApiClient> clientMap = new ConcurrentHashMap<>();

    public static ApiClient getClient(String baseUrl) {
        return clientMap.computeIfAbsent(baseUrl, k -> new ApiClient(baseUrl));
    }
}