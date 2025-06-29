package com.Rest_Assured.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BoardAPI extends BaseRequest {

    public Response createBoard(String name) {
        return requestSpec()
                .queryParam("name", name)
                .when()
                .post("/boards/")
                .then()
                .log().all()
                .extract()
                .response();
    }


    public Response deleteBoard(String boardId) {
        return requestSpec()
                .when()
                .delete("/boards/" + boardId)
                .then()
                .log().all()
                .extract()
                .response();
    }

}
