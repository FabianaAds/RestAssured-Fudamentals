import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import  static io.restassured.RestAssured.given;

public class AuthenticationTests {
     @BeforeAll
    public  static  void setup() {

         RestAssured.proxy("localhost", 8888);


     }
     @Test
    public void  basicPreemptiveAuthTests () {
            given().
                    auth().preemptive().basic("username","password").
                    when().
                     get("http://localhost/someEndpoint");


     }
      @Test
     public  void  basicChallengeAuthTests () {
           given().auth().basic("username","password").
                   when().
                   get("http://localhost/someEndpoint");


      }

      @Test
       public void oauth1Test() {

         given().auth().oauth("consumerKey","consumerSecret","consumerAcessToken","secretToken")
                 .when().get("http://localhost/someEndpoint");
         



      }
      @Test
       public  void oauth2Test() {

             given().auth().oauth2("acessToken")  .when().get("http://localhost/someEndpoint");
      }

    @Test
     public  void  relaxedHTPPSTest(){

           given().relaxedHTTPSValidation().when().get("http://localhost/someEndpoint");
    }

    @Test
     public  void  keyStoreTest(){


         given().keyStore("/pathToJKS","password").when().get("https://localhost:8080/someEndpoint");
    }
}
