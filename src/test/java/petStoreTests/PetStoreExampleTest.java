package petStoreTests;

import client.PetClient;
import client.UserClient;
import config.APIConstants;
import handlers.PetHandler;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.request.user.UserRequest;
import models.response.pet.Pet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static handlers.PetHandler.createIdAndNamePetTuple;

public class PetStoreExampleTest {

    @BeforeEach
    void setup() {
        RestAssured.baseURI = APIConstants.BASE_URL;
    }

    @Test
    void userCreationAndRetrieveTest() {
        UserClient userClient = new UserClient();
        String username = "maxUrbanijaTest";
        UserRequest user = UserRequest.builder()
                .username(username)
                .password("maxmaxmax")
                .userStatus(1)
                .build();
        Response userCreateResponse = userClient.createUserPost(user);
        userCreateResponse.then().statusCode(200);
        Response getUserResponse = userClient.userGetByUserName(username);
        getUserResponse.then().statusCode(200);
        System.out.println("Response JSON: " + getUserResponse.asString());
    }


    @Test
    public void getSoldPetsTest() {
        PetClient petClient = new PetClient();
        List<Pet> soldPets = petClient.findPetByStatus("sold");
        System.out.println("Response JSON: " + soldPets);
        System.out.println(createIdAndNamePetTuple(soldPets));
        PetHandler petHandler = new PetHandler(soldPets);
        System.out.println("Number of repeated pet names: "+(petHandler.getRepeatedNamesPetCount()));
        System.out.println("Repeated names with their ocurrences");
        System.out.println(petHandler.getRepeatedNamesAndOcurrences());
    }
}
