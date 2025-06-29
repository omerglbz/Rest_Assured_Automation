import com.Rest_Assured.api.BoardAPI;
import com.Rest_Assured.api.CardAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.Rest_Assured.utils.Log;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrelloApiTest {

    BoardAPI boardAPI = new BoardAPI();
    CardAPI cardAPI = new CardAPI();

    @Test
    public void trelloFullFlowTest() {

        Response createBoardResponse = boardAPI.createBoard("Automation Test Board");
        createBoardResponse.then().statusCode(200);
        String boardId = createBoardResponse.jsonPath().getString("id");
        Log.info("Board created with ID: " + boardId);

        Response listsResponse = RestAssured
                .given()
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "YOUR_API_KEY")
                .queryParam("token", "YOUR_TOKEN")
                .when()
                .get("/boards/" + boardId + "/lists")
                .then()
                .log().all()
                .extract()
                .response();

        listsResponse.then().statusCode(200);
        String idList = listsResponse.jsonPath().getString("[0].id");
        Log.info("List ID fetched: " + idList);

        List<String> cardIds = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            Response createCardResponse = cardAPI.createCard("Test Card " + i, idList);
            createCardResponse.then().statusCode(200);
            String cardId = createCardResponse.jsonPath().getString("id");
            cardIds.add(cardId);
            Log.info("Created card " + i + " with ID: " + cardId);
        }

        Random random = new Random();
        String cardToUpdate = cardIds.get(random.nextInt(cardIds.size()));
        Response updateResponse = cardAPI.updateCard(cardToUpdate, "Updated Card Name");
        updateResponse.then().statusCode(200);
        Log.info("Updated card ID: " + cardToUpdate);

        for (String cardId : cardIds) {
            Response deleteResponse = cardAPI.deleteCard(cardId);
            deleteResponse.then().statusCode(200);
            Log.info("Deleted card ID: " + cardId);
        }

        Response deleteBoardResponse = boardAPI.deleteBoard(boardId);
        deleteBoardResponse.then().statusCode(200);
        Log.info("Board deleted with ID: " + boardId);

    }
}