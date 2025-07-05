package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.notNullValue;

public class Product_Stepdefinitions {

    Response response;
    JsonPath jsonPath;
    String exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();
    JSONObject jsonObjectBody = new JSONObject();
    HashMap<String, Object> hashMapBody = new HashMap<>();
    TestData testData = new TestData();

    @Given("The api user verifies that the {string} information in the response body exists.")
    public void the_api_user_verifies_that_the_information_in_the_response_body_exists(String field) {
        // Response'u kendisi oluştur
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        
        jsonPath = response.jsonPath();
        
        // Field path'ini belirle
        String fieldPath;
        switch (field) {
            case "product_id":
            case "locale":
            case "title":
            case "seo_description":
            case "summary":
            case "description":
                // Bu alanlar translations array'inde
                fieldPath = "data.translations[0]." + field;
                break;
            default:
                // Diğer alanlar data objesinde
                fieldPath = "data." + field;
                break;
        }
        
        Assert.assertNotNull("Field " + field + " should not be null", jsonPath.get(fieldPath));
    }

    @Given("The api user verifies that the message information in the response body is {string}.")
    public void the_api_user_verifies_that_the_message_information_in_the_response_body_is(String value) {
        // Response zaten önceki step'te oluşturulmuş olmalı
        // Sadece assertion yap
        response.then()
                .assertThat()
                .body("data.message", equalTo(value));
    }

}
