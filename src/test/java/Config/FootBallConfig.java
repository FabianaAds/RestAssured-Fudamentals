package Config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;

public class FootBallConfig {
    @BeforeEach

    public  void setup(){
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.football-data.org")
                .setBasePath ("v4/")
                .addHeader("X-Auth-Token","d0bbae75e5394fc5a2acb41a7a701544")
                .addHeader("X-Response-Control","minified")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                   .expectStatusCode(200)
                   .build();
    }
}
