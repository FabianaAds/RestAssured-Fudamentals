import Config.VideoGameConfig;
import Config.VideoGameEndPoints;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class VideoGameTests extends VideoGameConfig {

     @Test
     public void getAllGames () {

         RestAssured.given().when().get(VideoGameEndPoints.ALL_VIDEO_GAMES).then();


     }


 }
