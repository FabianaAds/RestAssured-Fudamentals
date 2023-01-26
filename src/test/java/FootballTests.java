import Config.FootBallConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

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
}