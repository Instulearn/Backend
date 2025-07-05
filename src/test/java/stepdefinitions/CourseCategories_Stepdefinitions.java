package stepdefinitions;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CourseCategories_Stepdefinitions {

    Response response;
    HashMap<String, Object> hashMapBody = new HashMap<>();
    TestData testData = new TestData();

    @Given("The api user sends a PATCH request to updateCategory and saves the responsed.")
    public void sendPatchRequest() {
        hashMapBody = testData.updateCategoryRequestBody();

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(hashMapBody)
                .patch(API_Methods.fullPath);

        response.prettyPrint();
    }

    @Given("The api user verifies that the {string} information in the response body is the same as the id path parameter in the endpointt.")
    public void verifyUpdatedCategoryId(String key) {
        HashMap<String, Object> responseMap = response.as(HashMap.class);
        double dataKeyDouble = (double) responseMap.get(key);
        int dataKey = (int) dataKeyDouble;
        Assert.assertEquals(dataKey, API_Methods.id);
    }



}
