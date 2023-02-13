import Config.VideoGameConfig;
import Config.VideoGameEndPoints;
import io.restassured.RestAssured;
import objects.VideoGame;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

public class VideoGameTests extends VideoGameConfig {
    String gameBodyJson = "{\n" +
            "  \"category\": \"Platform\",\n" +
            "  \"name\": \"Mario\",\n" +
            "  \"rating\": \"Mature\",\n" +
            "  \"releaseDate\": \"2023-01-25\",\n" +
            "  \"reviewScore\": 89\n" +
            "}";

     @Test
     public void getAllGames () {

         RestAssured.given().when().get(VideoGameEndPoints.ALL_VIDEO_GAMES).then();
     }

     @Test
    public  void  createNewGameByJson(){


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

       @Test
       public void updateGame(){

         RestAssured.given()
                   .body(gameBodyJson)
                   .when()
                   .put("videogame/3")
                   .then();
       }

       @Test
       public  void deleteGame(){
           RestAssured.given()
                   .accept("text/plain")
                   .when()
                   .delete("videogame/8")
                   .then();

       }
       @Test
       public void  getSingleGame(){

          RestAssured.given()
                  .pathParam("videoGameId",8)
                  .when()
                  .get(VideoGameEndPoints.SINGLE_VIDEO_GAME)
                  .then();


       }

       @Test
       public  void  testVideoGameSerializationByJson(){

              VideoGame videoGame =  new VideoGame("Shgoter", "MyAwesomeGame", "Mature", "2018-04-04", 99);

                  RestAssured.given()
                          .body(videoGame)
                          .when()
                          .post(VideoGameEndPoints.ALL_VIDEO_GAMES)
                          .then();


       }



 }
