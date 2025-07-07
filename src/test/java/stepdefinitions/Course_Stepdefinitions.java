package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.Course_PostPojo;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;


public class Course_Stepdefinitions {

    Response response;
    JsonPath jsonPath;
    Course_PostPojo coursePojoRequest;
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

    @Given("The api user verifies the {int}, {int}, {int}, {int}, {string}, {int}, {string}, {int}, {string} contents in the response body data.")
    public void the_api_user_verifies_the_contents_in_the_response_body_data(int id, int teacher_id, int creator_id, int category_id, String type, int isprivate, String slug, int duration, String timezone) {

        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/course/"+id);
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.then()
                .assertThat()
                .body("data.id", equalTo(id),
                        "data.teacher_id", equalTo(teacher_id),
                        "data.creator_id", equalTo(creator_id),
                        "data.category_id", equalTo(category_id),
                        "data.type", equalTo(type),
                        "data.private", equalTo(isprivate),
                        "data.slug", equalTo(slug),
                        "data.duration", equalTo(duration),
                        "data.timezone", equalTo(timezone),
                        "data.start_date", nullValue());
    }

    @Given("The api user prepares a post request body to send to the api addCourse endpoint.")
    public void prepare_post_request_body_for_add_course() {

        coursePojoRequest = new Course_PostPojo(
                "Health And Fitness4",
                "course",
                "Health-And",
                "1625081400",
                60,
                5,
                20,
                "desc",
                870  );

    }

    @Given("The api user sends a POST request with {string} token and saves the returned response.")
    public void the_api_user_sends_a_post_request_and_saves_the_returned_response(String token) {

        HooksAPI.setUpApi(token);
        API_Methods.pathParam("api/addCourse");
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(coursePojoRequest)
                .post(API_Methods.fullPath);

        response.prettyPrint();
    }

    @Given("The api user verifies that the status code is {int}")
    public void the_api_user_verifies_that_the_status_code_is(int code) {
        response.then()
                .assertThat()
                .statusCode(code);
    }
    @Given("The api user verifies that the {string} information in the response body is {string}")
    public void the_api_user_verifies_that_the_information_in_the_response_body_is(String key, String value) {
        response.then()
                .assertThat()
                .body(key, equalTo(value));
    }

    @Given("The api user prepares a post request body with missing data to send to the api addCourse endpoint.")
    public void prepare_post_request_body_for_add_course_with_missing_data() {

        coursePojoRequest = new Course_PostPojo(
                "Health And Fitness4",
                "course",
                "Health-And",
                "1625081400",
                60,
                5,
                20,
                "desc",
                0  );

    }

    @Given("The api user prepares and posts request body with no data to send to the api addCourse endpoint.")
    public void the_api_user_prepares_a_post_request_body_no_data_to_send_to_the_api_add_course_endpoint() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .post(API_Methods.fullPath);

        response.prettyPrint();

    }

    @Given("The api user prepares a patch request body to send to the api updateCourse endpoint.")
    public void the_api_user_prepares_a_patch_request_body_to_send_to_the_api_update_course_endpoint() {

    }
    @Given("The api user sends a PATCH request and saves the returned response")
    public void the_api_user_sends_a_patch_request_and_saves_the_returned_response() {

    }
    @Given("The api user verifies that the {string} information in the response body is the same as the id path parameter in the endpoint")
    public void the_api_user_verifies_that_the_information_in_the_response_body_is_the_same_as_the_id_path_parameter_in_the_endpoint(String string) {

    }

}
