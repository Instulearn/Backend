package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import utilities.API_Utilities.API_Methods;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Coupons_Stepdefinitions {

    Response response;
    JsonPath json;


    @Given("Api kullanicisi response bodydeki {int} indexe sahip olan dataların {int}, {string}, {string}, {string}, {string}, {int}, {int}, {int}, {int} içeriklerini doğrular")
    public void api_kullanicisi_response_bodydeki_indexe_sahip_olan_dataların_içeriklerini_doğrular(int dataIndex, int creator_id, String title, String discount_type, String source, String code, int percent, int amount, int max_amount, int minimum_order) {
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        json = response.jsonPath();

        assertEquals(creator_id, json.getInt("data.discounts[" + dataIndex + "].creator_id"));
        assertEquals(title, json.getString("data.discounts[" + dataIndex + "].title"));
        assertEquals(discount_type, json.getString("data.discounts[" + dataIndex + "].discount_type"));
        assertEquals(source, json.getString("data.discounts[" + dataIndex + "].source"));
        assertEquals(code, json.getString("data.discounts[" + dataIndex + "].code"));
        assertEquals(percent, json.getInt("data.discounts[" + dataIndex + "].percent"));
        assertEquals(amount, json.getInt("data.discounts[" + dataIndex + "].amount"));
        assertEquals(max_amount, json.getInt("data.discounts[" + dataIndex + "].max_amount"));
        assertEquals(minimum_order, json.getInt("data.discounts[" + dataIndex + "].minimum_order"));


    }
    @Given("Api kullanicisi response bodydeki {int} indexe sahip olan {int}, {string}, {string}, {int}, {string}, {int}, {int} içeriklerini doğrular")
    public void api_kullanicisi_response_bodydeki_indexe_sahip_olan_içeriklerini_doğrular(int dataIndex, int count, String user_type, String product_type, int for_first_purchase, String status, long expired_at, long created_at) {
        json = response.jsonPath();
        assertEquals(count, json.getInt("data.discounts[" + dataIndex + "].count"));
        assertEquals(user_type, json.getString("data.discounts[" + dataIndex + "].user_type"));
        assertEquals(product_type, json.getString("data.discounts[" + dataIndex + "].product_type"));
        assertEquals(for_first_purchase, json.getInt("data.discounts[" + dataIndex + "].for_first_purchase"));
        assertEquals(status, json.getString("data.discounts[" + dataIndex + "].status"));
        assertEquals(expired_at, json.getInt("data.discounts[" + dataIndex + "].expired_at"));
        assertEquals(created_at, json.getInt("data.discounts[" + dataIndex + "].created_at"));

    }

}
