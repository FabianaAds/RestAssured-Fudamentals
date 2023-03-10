import Config.VideoGameConfig;
import Config.VideoGameEndPoints;
import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import objects.VideoGame;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;


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

        given().when().get(VideoGameEndPoints.ALL_VIDEO_GAMES).then();
     }

     @Test
    public  void  createNewGameByJson(){


                      given()
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

         @Test
          public  void convertJsonPojo() {
             Response response =  RestAssured.given()
                                 .pathParam("videoGameId",5)
                                 .when()
                              .get(VideoGameEndPoints.SINGLE_VIDEO_GAME);

                    VideoGame videoGame =  response.getBody().as(VideoGame.class);
             System.out.println(videoGame.toString());


         }

       @Test
       public  void  TestVideoGameSchemaXML(){
                       RestAssured
                               .given()
                               .pathParam("videoGameId", 5)
                               .accept("application/xml")
                               .when()
                               .get(VideoGameEndPoints.SINGLE_VIDEO_GAME)
                               .then()
                               .body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameXsd.xsd"));


       }
    @Test
    public void testVideoGameSchemaJSON(){

                 given()
                .pathParam("videoGameId", 5)
                .accept("application/json")
                .when()
                .get(VideoGameEndPoints.SINGLE_VIDEO_GAME)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }
           @Test
          public  void  captureResponseTime () {
                      long responseTime =  RestAssured.get (VideoGameEndPoints.ALL_VIDEO_GAMES).time();
                      System.out.println("Response time in MS: " + responseTime);


           }

           @Test
          public  void  assertOnResponseTime() {
                       RestAssured
                               .get(VideoGameEndPoints.ALL_VIDEO_GAMES)
                               .then().time(Matchers.lessThan(1000L));



           }




}
