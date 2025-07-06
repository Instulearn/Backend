package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class Course_Stepdefinitions {

    Response response;
    JsonPath jsonPath;
    String exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();
    JSONObject jsonObjectBody = new JSONObject();
    HashMap<String, Object> hashMapBody = new HashMap<>();
    TestData testData = new TestData();

    @Given("The api user verifies the {int}, {int}, {int}, {string}, {int}, {string}, {int}, {string} information of the item at {int} in the response body.")
    public void the_api_user_verifies_the_null_information_of_the_item_at_in_the_response_body(int teacher_id, int creator_id, int category_id, String type, int isPrivate, String slug, int duration, String timezone, int dataIndex) {

        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/courses");
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.prettyPrint();

        jsonPath = response.jsonPath();

        Assert.assertEquals(teacher_id, jsonPath.getInt("AddedCourseID.webinars[" + dataIndex + "].teacher_id"));
        Assert.assertEquals(creator_id, jsonPath.getInt("AddedCourseID.webinars[" + dataIndex + "].creator_id"));
        Assert.assertEquals(category_id, jsonPath.getInt("AddedCourseID.webinars[" + dataIndex + "].category_id"));
        Assert.assertEquals(type, jsonPath.getString("AddedCourseID.webinars[" + dataIndex + "].type"));
        Assert.assertEquals(isPrivate, jsonPath.getInt("AddedCourseID.webinars[" + dataIndex + "].private"));
        Assert.assertEquals(slug, jsonPath.getString("AddedCourseID.webinars[" + dataIndex + "].slug"));
        Assert.assertEquals(duration, jsonPath.getInt("AddedCourseID.webinars[" + dataIndex + "].duration"));
        Assert.assertEquals(timezone, jsonPath.getString("AddedCourseID.webinars[" + dataIndex + "].timezone"));
        Assert.assertNull(jsonPath.get("AddedCourseID.webinars[" + dataIndex + "].start_date"));
    }


}
