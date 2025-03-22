package api.services;

import api.client.ApiClient;

public abstract class BaseService {
    protected final ApiClient apiClient;

    public BaseService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
}
