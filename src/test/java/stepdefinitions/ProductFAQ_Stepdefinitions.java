package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;

import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;
import utilities.API_Utilities.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductFAQ_Stepdefinitions {

    Response response;
    JsonPath jsonPath;
    String exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();
    JSONObject jsonObjectBody = new JSONObject();
    HashMap<String, Object> hashMapBody = new HashMap<>();
    TestData testData = new TestData();

    @Given("The api user verifies the {int}, {int}, {string}, {int}, {string}, {string}, {int}, {int}, {string}, {string} and {string} information of the item at {int} in the response body.")
    public void the_api_user_verifies_the_and_information_of_the_item_at_in_the_response_body(int creator_id, int product_id, String order, int created_at, String title, String answer, int translations_id, int product_faq_id, String locale, String translations_title, String translations_answer, int dataIndex) {

        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/productfaqs");
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);


        jsonPath = response.jsonPath();

        Assert.assertEquals(creator_id, jsonPath.getInt("data[" + dataIndex + "].creator_id"));
        Assert.assertEquals(product_id, jsonPath.getInt("data[" + dataIndex + "].product_id"));
        Assert.assertEquals(created_at, jsonPath.getInt("data[" + dataIndex + "].created_at"));
        Assert.assertEquals(translations_id, jsonPath.getInt("data["+ dataIndex + "].translations[0].id"));
        Assert.assertEquals(product_faq_id, jsonPath.getInt("data[" + dataIndex + "].translations[0].product_faq_id"));
        Assert.assertEquals(locale, jsonPath.getString("data[" + dataIndex + "].translations[0].locale"));
        Assert.assertEquals(translations_title, jsonPath.getString("data[" + dataIndex + "].translations[0].title"));
        Assert.assertEquals(translations_answer, jsonPath.getString("data[" + dataIndex + "].translations[0].answer"));

        Assert.assertNull(jsonPath.get("data[" + dataIndex + "].order"));
        Assert.assertNull(jsonPath.get("data[" + dataIndex + "].title"));
        Assert.assertNull(jsonPath.get("data[" + dataIndex + "].answer"));

    }

    @Given("The api user verifies the {int}, {int}, {int}, {string}, {int}, {string}, {string}, {int}, {int}, {string}, {string} and {string} contents in the response body data.")
    public void the_api_user_verifies_the_and_contents_in_the_response_body_data(int id, int creator_id, int product_id, String order, int created_at, String title, String answer, int translations_id, int product_faq_id, String locale, String translations_title, String translations_answer) {

        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/productfaq/15");
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        /*
        jsonPath = response.jsonPath();
        */

        /*

        Assert.assertEquals(id, jsonPath.getInt("data.id"));
        Assert.assertEquals(creator_id, jsonPath.getInt("data.creator_id"));
        Assert.assertEquals(product_id, jsonPath.getInt("data.product_id"));
        Assert.assertEquals(created_at, jsonPath.getInt("data.created_at"));
        Assert.assertEquals(translations_id, jsonPath.getInt("data.translations[0].id"));
        Assert.assertEquals(product_faq_id, jsonPath.getInt("data.translations[0].product_faq_id"));
        Assert.assertEquals(locale, jsonPath.getString("data.translations[0].locale"));
        Assert.assertEquals(translations_title, jsonPath.getString("data.translations[0].title"));
        Assert.assertEquals(translations_answer, jsonPath.getString("data.translations[0].answer"));

        Assert.assertNull(jsonPath.get("data.order"));
        Assert.assertNull(jsonPath.get("data.title"));
        Assert.assertNull(jsonPath.get("data.answer"));
        */

        response.then().assertThat().body("data.id", equalTo(id),
                "data.creator_id", equalTo(creator_id),
                "data.product_id", equalTo(product_id),
                "data.order", nullValue(),
                "data.created_at", equalTo(created_at),
                "data.title", nullValue(),
                "data.answer", nullValue(),
                "data.translations[0].id", equalTo(translations_id),
                "data.translations[0].product_faq_id", equalTo(product_faq_id),
                "data.translations[0].locale", equalTo(locale),
                "data.translations[0].title", equalTo(translations_title),
                "data.translations[0].answer", equalTo(translations_answer));



    }


}
