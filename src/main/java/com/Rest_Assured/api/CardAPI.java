package com.Rest_Assured.api;

import io.restassured.response.Response;

public class CardAPI extends BaseRequest {

    public Response createCard(String name, String idList) {
        return requestSpec()
                .body("{\"name\":\"" + name + "\", \"idList\":\"" + idList + "\"}")
                .when()
                .post("/cards")
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response updateCard(String cardId, String newName) {
        return requestSpec()
                .body("{\"name\":\"" + newName + "\"}")
                .when()
                .put("/cards/" + cardId)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response deleteCard(String cardId) {
        return requestSpec()
                .when()
                .delete("/cards/" + cardId)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
