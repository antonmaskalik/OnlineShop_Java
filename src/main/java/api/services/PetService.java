package api.services;

import api.client.ApiClient;
import api.client.ApiRequest;
import api.models.Pet;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.Map;

public class PetService extends BaseService {
    private static final String PET_ENDPOINT = "pet/";
    private static final Map<String, String> API_KEY = Map.of("api_key", "special-key");

    public PetService(ApiClient client) {
        super(client);
    }

    public Response createPet(Pet pet) {
        ApiRequest request = new ApiRequest.Builder(Method.POST, PET_ENDPOINT)
                .body(pet)
                .build();
        return apiClient.execute(request);
    }

    public Response getPetById(int id) {
        ApiRequest request = new ApiRequest.Builder(Method.GET, PET_ENDPOINT + id)
                .withWait()
                .build();
        return apiClient.execute(request);
    }

    public Response deletePet(int petId) {
        ApiRequest request = new ApiRequest.Builder(Method.DELETE, PET_ENDPOINT + petId)
                .headers(API_KEY)
                .withWait()
                .build();
        return apiClient.execute(request);
    }
}
