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

public class ProductCategories_Stepdefinitions {

    Response response;
    JsonPath jsonPath;
    String exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();
    JSONObject jsonObjectBody = new JSONObject();
    HashMap<String, Object> hashMapBody = new HashMap<>();
    TestData testData = new TestData();


    @Given("The api user verifies the null, {string}, null, null, {int}, {int}, {string}, {string} information of the item at {int} in the response body.")
    public void the_api_user_verifies_the_null_null_null_information_of_the_item_at_in_the_response_body(String icon, int id, int product_category_id, String locale, String translations_title, int dataIndex) {

        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/productCategories");
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        response.prettyPrint();

        jsonPath = response.jsonPath();


        Assert.assertEquals(icon, jsonPath.getString("data.categories[" + dataIndex + "].icon"));
        Assert.assertEquals(id, jsonPath.getInt("data.categories[" + dataIndex + "].id"));
        Assert.assertEquals(product_category_id, jsonPath.getInt("data.categories[" + dataIndex + "].translations[0].product_category_id"));
        Assert.assertEquals(locale, jsonPath.getString("data.categories[" + dataIndex + "].translations[0].locale"));
        Assert.assertEquals(translations_title, jsonPath.getString("data.categories[" + dataIndex + "].translations[0].title"));

        Assert.assertNull(jsonPath.get("data.categories[" + dataIndex + "].parent_id"));
        Assert.assertNull(jsonPath.get("data.categories[" + dataIndex + "].order"));
        Assert.assertNull(jsonPath.get("data.categories[" + dataIndex + "].title"));

    }
}
