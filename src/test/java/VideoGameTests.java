import Config.VideoGameConfig;
import Config.VideoGameEndPoints;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

public class VideoGameTests extends VideoGameConfig {

     @Test
     public void getAllGames () {

         RestAssured.given().when().get(VideoGameEndPoints.ALL_VIDEO_GAMES).then();
     }

     @Test
    public  void  createNewGameByJson(){

         String gameBodyJson = "{\n" +
                 "  \"category\": \"Platform\",\n" +
                 "  \"name\": \"Mario\",\n" +
                 "  \"rating\": \"Mature\",\n" +
                 "  \"releaseDate\": \"2023-01-25\",\n" +
                 "  \"reviewScore\": 89\n" +
                 "}";
             RestAssured.given()
                     .body(gameBodyJson)
                     .when()
                     .post(VideoGameEndPoints.ALL_VIDEO_GAMES)
                     .then().assertThat().statusCode(200);
     }

       @Test
       public void createNewGameXML(){

         String gameBodyxml = "<VideoGame category=\"string\">\n" +
                 "\t<id>0</id>\n" +
                 "\t<name>string</name>\n" +
                 "\t<rating>string</rating>\n" +
                 "\t<releaseDate>string</releaseDate>\n" +
                 "\t<reviewScore>0</reviewScore>\n" +
                 "</VideoGame>";
          RestAssured.given()
                  .body(gameBodyxml)
                  .contentType("application/xml")
                  .accept("application/xml")
                  .when()
                  .post(VideoGameEndPoints.ALL_VIDEO_GAMES)
                  .then();



       }

 }
