import Config.FootBallConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class GpathJsonTests  extends FootBallConfig {


    @Test

    public void extractMapOfElementsWithFind() {
        Response response =  get("competitions/2021/teams");

        Map < String, ? > allTeamDataForSingleteam = response.path("teams.find { it.name == ' Manchester United FC' }");
        System.out.println("Map  of teams data =  " +  allTeamDataForSingleteam);




    }

    @Test
     public  void extractSingleValueWithFind() {
         Response response = get("teams/57");
        String certainPlayer =  response.path("squad.find { it.id ==  7784 }.name ");
        System.out.println("Name of Player" + certainPlayer);

    }

    @Test
      public  void extractListOfValueWithFindAll(){
          Response response =  get("teams/57");
        List<String> playerNames = response.path("squad.findAll { it.id >= 7784}.name");
        System.out.println("List of  players: " + playerNames);

    }


    @Test
      public  void   extractSingleValueWhitHighestNumber() {
            Response response = get("teams/57");
             String  playerName =  response.path("squad.max {it.id }.name");
            System.out.println("Player with Highest  : id " + playerName);


    }

    @Test
      public  void  extractMultipleValuesAndSumThem(){
              Response response = get("teams/57");
               int  sumOfIds = response.path("squad.collect { it.id }.sum()");
          System.out.println(" Sum of  all Ids:  " + sumOfIds);


    }

    @Test
     public  void   extractMapWhitFindAndFindAllWithParameters () {

         String position  = "Offence";
         String nationality = "England";

        Response response = get("teams/57");

          Map<String, ? > playerOfCertainPosition = response.path(
                  " squad.findAll { it.position == '%s'}.find { it.nationality == '%s' }", position, nationality
          );
              System.out.println(" Details of player : " + playerOfCertainPosition);


    }

    @Test
       public  void  extractMultiplePlayers () {

        String position = "Offence";
        String nationality = "England";

        Response response = get("teams/57");

        ArrayList<Map<String, ?>> allPlayerOfCertainPosition = response.path(
                " squad.findAll { it.position == '%s'}.findAll { it.nationality == '%s' }", position, nationality
        );
        System.out.println(" Details of players : " + allPlayerOfCertainPosition);


    }
}
