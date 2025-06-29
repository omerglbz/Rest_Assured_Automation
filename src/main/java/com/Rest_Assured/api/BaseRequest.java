package com.Rest_Assured.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseRequest {

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String API_KEY = "282df3c1179b9c98f5aadec439bb78f";
    private static final String TOKEN = "8e61d5f4e0453a928a180d93e5ee4d2230622";

    protected RequestSpecification requestSpec() {
        return given()
                .baseUri(BASE_URL)
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .header("Content-Type", "application/json");
    }
}
