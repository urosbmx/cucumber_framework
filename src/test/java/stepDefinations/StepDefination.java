package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static resource.addBook.*;
import static resource.randomGenerators.generateRandomString;


public class StepDefination  {
    private String apiUrl;
    private String resource;
    private Response response;
    private  Response newResponse;


    private static String newBookisbn = generateRandomString(5);


    @Given("I have the API endpoint {string} and resource {string}")
    public void iHaveTheAPIEndpointAndResource(String arg0, String arg1) {
        apiUrl = arg0+arg1;
        response = expect()
                .given()
                .baseUri(apiUrl)
                .header("Content-Type","application/json")
                .body(addNewBook(generateRandomString(12),generateRandomString(12),newBookisbn,generateRandomString(5)))
                .post()
                .then()
                .extract()
                .response();
    }

    @When("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
       int responseCode = response.statusCode();
        Assert.assertEquals(statusCode,responseCode);
        System.out.println("Response code is " + responseCode);

    }


    @Then("Response should contain message {}")
    public void responseSholudContainMessgae(String message) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String expectedMessage = jsonPathEvaluator.get("Msg");
        Assert.assertEquals(message,expectedMessage);
        System.out.println("Response message is " + expectedMessage);
    }


    //Add existing book

    @Given("User call {string} for creating new book and doesn't provide bookID existing {string}")
    public void userCallForCreatingNewBookAndDoesnTProvideBookIDExisting(String arg0, String arg1) {
        newResponse = given()
                .baseUri(arg0)
                .header("Content-Type","application/json")
                .body(addNewBook(arg1,arg1,arg1,arg1))
                .post()
                .then()
                .extract()
                .response();

        System.out.println(newResponse.asString());

    }

    @When("Response return status code should be {int}")
    public void responseReturnStatusCodeShouldBe(int arg0) {
        int responseCod = newResponse.statusCode();
        Assert.assertEquals(arg0,responseCod);
        System.out.println("Response code is " + responseCod);

    }

    @Then("Response meesage sholud be {string}")
    public void responseMeesageSholudBe(String arg0) {
        JsonPath jsonPathEvaluators = newResponse.jsonPath();
        String expectedMessages = jsonPathEvaluators.get("ID");
        System.out.println(arg0);
        Assert.assertEquals(arg0,expectedMessages);
        System.out.println("Response message is " + expectedMessages);

    }


}