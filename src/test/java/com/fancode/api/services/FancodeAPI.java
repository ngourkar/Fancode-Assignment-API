package com.fancode.api.services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class FancodeAPI {

    public static ValidatableResponse getAPI(String endpoint) {
        ValidatableResponse validatableResponse = given().contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then();
        return validatableResponse;
    }
}
