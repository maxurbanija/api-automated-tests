package client;

import config.APIConstants;
import io.restassured.common.mapper.TypeRef;
import models.response.pet.Pet;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetClient {
    public List<Pet> findPetByStatus(String status) {
        return given()
                .when()
                .queryParam("status", status)
                .get(APIConstants.PET_FIND_BY_STATUS)
                .then()
                .extract()
                .as(new TypeRef<List<Pet>>() {
                });
    }
}
