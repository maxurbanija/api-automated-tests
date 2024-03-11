package client;

import config.APIConstants;
import io.restassured.http.Header;
import io.restassured.response.Response;
import models.request.user.UserRequest;

import static io.restassured.RestAssured.given;

public class UserClient {
    public Response createUserPost(UserRequest user) {
        return given()
                .when()
                .header(new Header("content-type","application/json"))
                .body(user)
                .post(APIConstants.USER)
                .then()
                .extract()
                .response();
    }
    public Response userGetByUserName(String username) {
        return given()
                .when()
                .get(APIConstants.USER.concat("/").concat(username))
                .then()
                .extract()
                .response();
    }
}
