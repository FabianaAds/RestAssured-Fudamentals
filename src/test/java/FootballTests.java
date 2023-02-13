import Config.FootBallConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver;

import java.sql.SQLOutput;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class FootballTests extends FootBallConfig {


    @Test
    public void getDetailsOfOneArea() {
        RestAssured.given()
                .queryParam("areas", 2076)
                .when()
                .get("/areas");

    }

    @Test
    public void getDetailsOfMultipleAreas() {

        String areaIds = "2076,2077,2088";
         RestAssured.given()
                 .urlEncodingEnabled(false)
                 .queryParam("areas",areaIds)
                 .when()
                 .get("/areas");


    }

    @Test

    public void getDateFounded (){

        RestAssured.given()
                   .when()
                .get("teams/57")
                .then()
                .body("founded", equalTo(1886));


    }

      @Test

     public void  getFirstTeamName (){

         RestAssured.given()
                   .when()
                   .get("competitions/2021/teams")
                   .then()
                   .body("teams.name[0]", equalTo("Arsenal FC"));
      }

      @Test
       public  void getAllTeamData (){

        String responseBody =  RestAssured.get("teams/57").asString();
          System.out.println(responseBody);
      }

      @Test
       public  void  getAllTeamData_DoCheckFirst(){

        Response response =
          RestAssured.given()
                     .when()
                     .get("teams/57")
                     .then()
                     .contentType(ContentType.JSON)
                     .extract().response();


                    String  jsonResponseAsString =  response.asString();
                   System.out.println(jsonResponseAsString);

      }
              @Test
                  public void  extractHeaders() {

                  Response response =   RestAssured.get("teams/57")
                          .then()
                          .extract().response();

                  String contentTypeHeader =  response.getContentType();
                  System.out.println(contentTypeHeader);

                  String  apiVersionHeader =  response.getHeader("X-API-Version");
                  System.out.println(apiVersionHeader);
              }



              @Test
                public  void    extractFirstTeamName(){

                         String firstTeamName  = RestAssured.get("competitions/2021/teams").jsonPath().getString("teams.name[0]");
                  System.out.println(firstTeamName);


              }
              @Test
               public  void   extractAllTeamNames()   {

                   Response  response =  RestAssured.get("competitions/2021/teams")
                           .then()
                           .extract().response();

                             List<String>  teamNames = response.path("teams.name");
                  for(String  teamName :  teamNames){
                                          System.out.println(teamNames);
                                      }





              }

    }


