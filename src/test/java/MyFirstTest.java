import Config.VideoGameConfig;
import Config.VideoGameEndPoints;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class MyFirstTest extends VideoGameConfig {

    @Test
    public void myFirstTest() {

        RestAssured.given().log().all()
                .when()
                .get("/videogame")
                .then()
                .log().all();


    }

    @Test
    public void MyFirstTestWithEndPoint() {

        RestAssured.get(VideoGameEndPoints.ALL_VIDEO_GAMES)
                .then() .log() .all();


    }
}
