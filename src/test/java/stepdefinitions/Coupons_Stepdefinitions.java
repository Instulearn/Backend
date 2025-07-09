package stepdefinitions;

import hooks.HooksAPI;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.Coupons_PatchPojo;
import pojos.Coupons_PostPojo;
import utilities.API_Utilities.API_Methods;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Coupons_Stepdefinitions {

    Response response;
    JsonPath json;
    String payload;
    ObjectMapper mapper = new ObjectMapper();
    Coupons_PostPojo payloadBody;
    Coupons_PatchPojo payloadBody2;
    String generatedCode;
    Map<String,Object> map;
    int createdId;

    //*********************************************** GET METHODS **********************************************

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

    //**************************************** GET WITH ID METHODS*****************************************

    @Given("Api kullanicisi response bodydeki dataların {int}, {int}, {int}, {string}, {string}, {string}, {string}, {int}, {int}, {int}, {int} içeriklerini doğrular")
    public void api_kullanicisi_response_bodydeki_dataların_data_index_içeriklerini_doğrular(int dataIndex, int id, int creator_id, String title, String discount_type, String source, String code, int percent, int amount, int max_amount, int minimum_order) {

        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        json = response.jsonPath();

        assertEquals(id, json.getInt("data.id"));
        assertEquals(creator_id, json.getInt("data.creator_id"));
        assertEquals(title, json.getString("data.title"));
        assertEquals(discount_type, json.getString("data.discount_type"));
        assertEquals(source, json.getString("data.source"));
        assertEquals(code, json.getString("data.code"));
        assertEquals(percent, json.getInt("data.percent"));
        assertEquals(amount, json.getInt("data.amount"));
        assertEquals(max_amount, json.getInt("data.max_amount"));
        assertEquals(minimum_order, json.getInt("data.minimum_order"));

    }

    @Given("Api kullanicisi response bodydeki dataların {int}, {int}, {string}, {string}, {int}, {string}, {int}, {int} içeriklerini doğrular")
    public void api_kullanicisi_response_bodydeki_dataların_data_index_içeriklerini_doğrular(int dataIndex, int count, String user_type, String product_type, int for_first_purchase, String status, long expired_at, long created_at) {
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        json = response.jsonPath();
        assertEquals(count, json.getInt("data.count"));
        assertEquals(user_type, json.getString("data.user_type"));
        assertEquals(product_type, json.getString("data.product_type"));
        assertEquals(for_first_purchase, json.getInt("data.for_first_purchase"));
        assertEquals(status, json.getString("data.status"));
        assertEquals(expired_at, json.getInt("data.expired_at"));
        assertEquals(created_at, json.getInt("data.created_at"));

    }

    //******************************************+*****POST METHODS****************************************************

    @Given("Api kullanıcısı addCoupon endpoint’ine gönderilmek üzere geçerli bir post request body hazırlar.")
    public void api_kullanıcısı_add_coupon_endpoint_ine_gönderilmek_üzere_geçerli_bir_post_request_body_hazırlar() throws JsonProcessingException {
         generatedCode = "TST" + System.currentTimeMillis();

        payload = """
            {
                "title":"Test Coupon",
                "discount_type":"percentage",
                "source":"course",
                "code":"%s",
                "percent":15,
                "amount":10,
                "max_amount":200,
                "minimum_order":1,
                "count":50,
                "product_type":"all",
                "for_first_purchase":0,
                "expired_at":"2025-12-31"
            }""".formatted(generatedCode);

        payloadBody = mapper.readValue(payload, Coupons_PostPojo.class);
    }


    @Given("Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder.")
    public void api_kullanıcısı_post_request_gönderir_ve_dönen_response_ı_kaydeder() {

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .body(payloadBody)
                .when()
                .post(API_Methods.fullPath);

        response.prettyPrint();

    }

    @Given("Api kullanıcısı status code’un {int} olduğunu doğrular.")
    public void api_kullanıcısı_status_code_un_olduğunu_doğrular(Integer code) {
        response.then().statusCode(code);

    }

    @Given("Api kullanıcısı response body’deki {string} bilgisinin {string} olduğunu doğrular.")
    public void api_kullanıcısı_response_body_deki_bilgisinin_olduğunu_doğrular(String key, String expectedValue) {
        json = response.jsonPath();
        String actualValue = json.getString(key);
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Given("Api kullanıcısı daha önce kullanılmış bir code ile post request body hazırlar")
    public void api_kullanıcısı_daha_önce_kullanılmış_bir_code_ile_post_request_body_hazırlar() throws JsonProcessingException {
        String reusedPayload = """
        {
            "title":"Test Coupon Duplicate",
            "discount_type":"percentage",
            "source":"course",
            "code":"%s",
            "percent":15,
            "amount":10,
            "max_amount":200,
            "minimum_order":1,
            "count":50,
            "product_type":"all",
            "for_first_purchase":0,
            "expired_at":"2025-12-31"
        }""".formatted(generatedCode);

        payloadBody = mapper.readValue(reusedPayload, Coupons_PostPojo.class);
    }

    //******************************************** PATCH METHODS **********************************************
    @Given("Api kullanicisi api updateCoupon endpointine gondermek icin bir patch request body hazirlar")
    public void api_kullanicisi_api_update_coupon_endpointine_gondermek_icin_bir_patch_request_body_hazirlar() throws JsonProcessingException {

        payload = """
                {
                "title":"My Coupon Updated",
                "discount_type":"percentage",
                "source":"course",
                "percent":5,
                "amount":10,
                "max_amount":200,
                "minimum_order":1,
                "count":50,
                "product_type":"all",
                "for_first_purchase":0
                }""";

        payloadBody2 = mapper.readValue(payload, Coupons_PatchPojo.class);

    }


    @Given("Api kullanicisi PATCH request gonderir ve donen responsei kaydeder")
    public void api_kullanicisi_patch_request_gonderir_ve_donen_responsei_kaydeder() {

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .body(payloadBody2)
                .patch(API_Methods.fullPath);

        response.prettyPrint();

    }

    @Given("Api kullanıcısı response body icindeki {string} bilgisinin endpointde yazan id path parametresi ile ayni oldugunu dogrular.")
    public void api_kullanıcısı_response_body_icindeki_bilgisinin_endpointde_yazan_id_path_parametresi_ile_ayni_oldugunu_dogrular(String key) {
        map = response.as(Map.class);
        int dataKey = (int)map.get(key);
        Assert.assertEquals(dataKey, API_Methods.id);
    }

    @Given("Api kullanicisi api updateCoupon endpointine gondermek icin bos bir patch request body hazirlar")
    public void api_kullanicisi_api_update_coupon_endpointine_gondermek_icin_bos_bir_patch_request_body_hazirlar() {

        map = new HashMap<>();

    }

    @Given("Api kullanicisi PATCH request gonderir ve donen response'i kaydeder.")
    public void api_kullanicisi_patch_request_gonderir_ve_donen_response_i_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .patch(API_Methods.fullPath);
        response.prettyPrint();
        json = response.jsonPath();

    }

    @Given("Api kullanicisi GET request gonderir ve donen responsei kaydeder.")
    public void api_kullanicisi_get_request_gonderir_ve_donen_responsei_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        response.prettyPrint();
    }

    @Given("Api kullanıcısı title bilgisinin {string} olduğunu doğrular.")
    public void api_kullanıcısı_title_bilgisinin_olduğunu_doğrular(String updateTitle) {
        response.then()
                .assertThat()
                .body("data.title", equalTo(updateTitle));
    }

    //************************************************* DELETE ************************************************

    @Given("Api kullanıcısı response body icindeki {string} bilgisinin endpointde yazan id path parametresiyle ayni oldugunu dogrular..")
    public void api_kullanıcısı_response_body_icindeki_bilgisinin_endpointde_yazan_id_path_parametresiyle_ayni_oldugunu_dogrular(String responseKey) {
        map = response.as(HashMap.class);
        String dataKey = (String) map.get(responseKey);
        String endpointKey = String.valueOf(API_Methods.id);
        Assert.assertEquals(dataKey, endpointKey);
    }

    @Given("Api kullanıcısı DELETE request gönderir ve dönen response’ı kaydeder.")
    public void api_kullanıcısı_delete_request_gönderir_ve_dönen_response_ı_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .delete(API_Methods.fullPath);
        response.prettyPrint();
        json = response.jsonPath();
    }

    @Given("Api kullanıcısı response body icindeki {string} ile endpointde yazan id path parametresinin ayni oldugunu dogrular.")
    public void api_kullanıcısı_response_body_icindeki_ile_endpointde_yazan_id_path_parametresinin_ayni_oldugunu_dogrular(String key) {

        map = response.as(Map.class);

        // Response içinden key ile değeri al (örneğin "Deleted Coupon ID")
        Object responseValue = map.get(key);

        // null kontrolü yapabilirsin
        Assert.assertNotNull("Response'da " + key + " bulunamadı", responseValue);

        // String'e çevir ve Integer'a parse et
        int responseId;
        try {
            responseId = Integer.parseInt(responseValue.toString());
        } catch (NumberFormatException e) {
            Assert.fail(key + " alanı integer değil: " + responseValue.toString());
            return; // Fail sonrası devam etmesin
        }

        // Endpoint path parametresindeki id (API_Methods.id) ile karşılaştır
        Assert.assertEquals("Response içindeki " + key + " ile endpointde verilen id aynı olmalı", API_Methods.id, responseId);

    }


    //Post-Delete birarada yapan method. Böylelikle ürettigini siliyor
    @Given("Api kullanicisi yeni bir kupon olusturur ve kupon id’sini endpoint için kaydeder")
    public void api_kullanicisi_yeni_bir_kupon_olusturur_ve_kupon_id_sini_endpoint_için_kaydeder() throws JsonProcessingException {
        api_kullanıcısı_add_coupon_endpoint_ine_gönderilmek_üzere_geçerli_bir_post_request_body_hazırlar();

        payloadBody = mapper.readValue(payload, Coupons_PostPojo.class);

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .body(payloadBody)
                .when()
                .post("/api/addCoupon");

        response.prettyPrint();

        map = response.as(Map.class);
        createdId = (int)map.get("Added Coupon ID");
        API_Methods.id = createdId;
        API_Methods.fullPath = "/api/deleteCoupon/" + createdId;
    }

    @Given("Api kullanicisi GET request gonderir ve donen responseini kaydeder")
    public void api_kullanicisi_get_request_gonderir_ve_donen_responseini_kaydeder() {

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .get(API_Methods.fullPath);
        response.prettyPrint();
        json = response.jsonPath();
    }

    }
