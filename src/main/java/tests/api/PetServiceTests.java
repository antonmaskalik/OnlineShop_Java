package tests.api;

import api.client.ApiClient;
import api.client.ApiClientFactory;
import api.models.Pet;
import api.services.PetService;
import configs.ConfigReader;
import enums.HttpStatusCode;
import helpers.RandomHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PetServiceTests extends BaseTest {
    private static final ThreadLocal<PetService> petServiceThreadLocal = new ThreadLocal<>();

    @BeforeEach
    public void setUp() {
        ApiClient apiClient = ApiClientFactory.getClient(ConfigReader.getApiConfig().getBaseUrl());
        petServiceThreadLocal.set(new PetService(apiClient));
    }

    @ParameterizedTest
    @ValueSource(strings = {"available", "pending", "sold"})
    public void testCreateAndGetPet(String status) {
        Pet pet = new Pet();
        pet.setId(RandomHelper.getRandomInt(1, 100000));
        pet.setName(RandomHelper.getRandomString(10));
        pet.setStatus(status);
        PetService petService = petServiceThreadLocal.get();

        Response createResponse = petService.createPet(pet);
        Assertions.assertEquals(HttpStatusCode.OK.getCode(), createResponse.getStatusCode());

        Response getResponse = petService.getPetById(pet.getId());
        Assertions.assertEquals(HttpStatusCode.OK.getCode(), getResponse.getStatusCode());

        Pet retrievedPet = getResponse.getBody().as(Pet.class);
        Assertions.assertEquals(pet, retrievedPet);

        Response deleteResponse = petService.deletePet(pet.getId());
        Assertions.assertEquals(HttpStatusCode.OK.getCode(), deleteResponse.getStatusCode());
    }

    @AfterEach
    public void tearDown() {
        petServiceThreadLocal.remove();
    }
}
