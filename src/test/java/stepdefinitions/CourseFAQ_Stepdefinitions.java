package stepdefinitions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourseFAQ_Stepdefinitions<addCoursefaqPojo> {

    Response response;
    JsonPath jsonPath;
    String exceptionMesaj;
    private int id;
    ConfigLoader configLoader = new ConfigLoader();
    JSONObject jsonObjectBody = new JSONObject();
    HashMap<String, Object> hashMapBody = new HashMap<>();
    TestData testData = new TestData();
    boolean sendEmptyBody = false;

    @Given("The api user verifies the {int}, {int}, {int}, {int},information of the item at {int} in the response body.")
    public void the_api_user_verifies_the_information_of_the_item_at_in_the_response_body(int creator_id, int webinar_id, int created_at, int updated_at, int  dataIndex) {
        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/coursefaqs");
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        response.prettyPrint();

        jsonPath = response.jsonPath();

        Assert.assertEquals(creator_id, jsonPath.getInt("data.coursefaqs[" + dataIndex + "].creator_id"));
        Assert.assertEquals(webinar_id, jsonPath.getInt("data.coursefaqs[" + dataIndex + "].webinar_id"));
        Assert.assertEquals(created_at, jsonPath.getInt("data.coursefaqs[" + dataIndex + "].created_at"));
        Assert.assertEquals(updated_at, jsonPath.getInt("data.coursefaqs[" + dataIndex + "].updated_at"));
    }
    @Given("The api user verifies the {int}, {int}, {int}, {int}, contents in the response body data.")
    public void the_api_user_verifies_the_contents_in_the_response_body_data(int data_id, int created_id, int webinar_id, int created_at, int updated_at) {
        response.then()
                .assertThat()
                .body("data.id", equalTo(14),
                        "data.created_id", equalTo(1016),
                        "data.webinar_id", equalTo(1995),
                        "data.created_at", equalTo(1624908812),
                        "data.updated_at", equalTo(1718059480),
                        "data.translations[0].id", equalTo(0));
    }


    @Given("The api user prepares a post request without any data to send to the api add Coursefaq endpoint.")
    public void the_api_user_prepares_a_post_request_without_any_data_to_send_to_the_api_add_Coursefaq_endpoint() {
    }
    @Given("The api user verifies that the status code is {int} for Coursefaq.")
    public void the_api_user_verifies_that_the_status_code_is_for_product_faq(int code) {
        response.then()
                .assertThat()
                .statusCode(code);
    }

    @Given("The api user verifies that the {string} information in the response body is {string} for Coursefaq.")
    public void the_api_user_verifies_that_the_information_in_the_response_body_is_for_product_faq(String key, String value) {
        response.then()
                .assertThat()
                .body(key, equalTo(value));
    }

    @Given("The api user prepares a post request without any data to send to the api addCategory endpoint for Coursefaq.")
    public void the_api_user_prepares_a_post_request_without_any_data_to_send_to_the_api_add_category_endpoint_for_course_faq() {

    }

    @Given("The api user prepares a patch request body to send to the api updateCourseFAQ {int} endpoint with the {string}, {string}.")
    public void the_api_user_prepares_a_patch_request_body_to_send_to_the_api_update_course_faq_endpoint_with_the(int id, String title, String answer) {
        this.id = id;
        jsonObjectBody.put("title", title);
        jsonObjectBody.put("answer", answer);
        //jsonObjectBody.put("course_id", id);
    }


    @Given("The api user sends a PATCH request and saves the returned response for Coursefaq.")
    public void the_api_user_sends_a_patch_request_and_saves_the_returned_response_for_Course_faq() {

        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/updateCoursefaq/" + id);
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObjectBody.toString())
                .when()
                .patch(API_Methods.fullPath);

        response.prettyPrint();
        jsonPath = response.jsonPath();
    }

    @Given("The api user prepares an empty patch request body to send to the api updateCoursefaq {int} endpoint")
    public void the_api_user_prepares_an_empty_patch_request_body_to_send_to_the_api_update_category_endpoint(int id) {
        this.id = id;
        jsonObjectBody = new JSONObject();
        //sendEmptyBody = true;
    }

    @Given("The api user sends a PATCH request with invalid token and verifies that the status code is {string} with the reason phrase Unauthorized.")
    public void the_api_user_sends_a_patch_request_with_invalid_token_and_verifies_that_the_status_code_is_with_the_reason_phrase_unauthorized(String code) {

        HooksAPI.setUpApi("invalid");
        API_Methods.pathParam("api/updateCoursefaq/" + id);
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObjectBody.toString())
                .when()
                .patch(API_Methods.fullPath);

        response.prettyPrint();

        Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"), API_Methods.tryCatchRequest("PATCH", hashMapBody));

    }

    @Given("The api user sends a PATCH request with invalid token and saves the returned response for Coursefaq.")
    public void the_api_user_sends_a_patch_request_with_invalid_token_and_saves_the_returned_response_for_coursefaq() {
        HooksAPI.setUpApi("invalid");
        API_Methods.pathParam("api/updateCoursefaq/" + id);
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObjectBody.toString())
                .when()
                .patch(API_Methods.fullPath);

        response.prettyPrint();
    }
    @Given("The api user verifies that the status code is {int} and the {string} information in the response body is {string}.")
    public void the_api_user_verifies_that_the_status_code_is_and_the_information_in_the_response_body_is(Integer code, String key, String value) {
        response.then()
                .assertThat()
                .statusCode(code);

        response.then()
                .assertThat()
                .body(key, equalTo(value));

    }

}