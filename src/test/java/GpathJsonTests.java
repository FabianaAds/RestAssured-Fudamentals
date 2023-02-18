import Config.FootBallConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

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

}
