package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BlogCategories_Stepdefinitions {

    Response response;
    JsonPath jsonPath;
    ConfigLoader configLoader = new ConfigLoader();
    String exceptionMesaj;
    Map<String, Object> requestBody = new HashMap<>();
    JSONObject jsonObjectBody = new JSONObject();


    @Given("Api kullanicisi {string} token ile base urli olusturur")
    public void api_kullanicisi_token_ile_base_urli_olusturur(String userType) {

        HooksAPI.setUpApi(userType);
    }

    @Given("Api kullanicisi {string} path parametrelerini olusturur")
    public void api_kullanicisi_path_parametrelerini_olusturur(String pathParam) {

        API_Methods.pathParam(pathParam);
    }

    @Given("Api kullanicisi GET request gonderir ve donen responsei kaydeder")
    public void api_kullanicisi_get_request_gonderir_ve_donen_responsei_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.prettyPrint();
    }

    @Given("Api kullanicisi status codeun {int} oldugunu dogrular")
    public void api_kullanicisi_status_codeun_oldugunu_dogrular(int code) {
        response.then()
                .assertThat()
                .statusCode(code);
    }

    @Given("Api kullanicisi response bodydeki {string} bilgisinin {string} oldugunu dogrular")
    public void api_kullanicisi_response_bodydeki_bilgisinin_oldugunu_dogrular(String key, String value) {
        jsonPath = response.jsonPath();
        String actualValue = jsonPath.getString(key);
        Assert.assertEquals(value, actualValue);
    }

    @Given("Api kullanicisi response bodydeki dataların {string}, {string}, {string}, {int},{string},{string}, {int} içeriklerini doğrular.")
    public void api_kullanicisi_response_bodydeki_dataların_içeriklerini_doğrular(String data, String pageTitle, String blogCategories, int id, String title, String slug, int blog_count) {

        jsonPath = response.jsonPath();
        if (data.equals("not null")) {
            Assert.assertNotNull(jsonPath.get("data"));

            Assert.assertEquals(pageTitle, jsonPath.getString("data.pageTitle"));

            if (blogCategories.equals("not empty")) {
                Assert.assertFalse(jsonPath.getList("data.blogCategories").isEmpty());
            }
            boolean found = false;
            List<Map<String, Object>> categories = jsonPath.getList("data.blogCategories");

            for (Map<String, Object> category : categories) {
                if ((int) category.get("id") == id) {
                    Assert.assertEquals(title, category.get("title"));
                    Assert.assertEquals(slug, category.get("slug"));
                    Assert.assertEquals(blog_count, category.get("blog_count"));
                    found = true;
                    break;
                }
            }

            Assert.assertTrue("Belirtilen id ile blog category bulunamadi!", found);
        }

    }

    @Given("Api kullanicisi response bodydeki dataların {int}, {string}, {string} içeriklerini doğrular")
    public void api_kullanicisi_response_bodydeki_dataların_içeriklerini_doğrular(int id, String title, String slug) {

        jsonPath = response.jsonPath();
        Assert.assertEquals(id, jsonPath.getInt("data.id"));
        Assert.assertEquals(title, jsonPath.getString("data.title"));
        Assert.assertEquals(slug, jsonPath.getString("data.slug"));
    }

    @Given("Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun {int} ve {string} bilgisinin {string} oldugunu dogrular")
    public void api_kullanicisi_get_request_gonderir_donen_responsei_kaydeder_status_codeun_ve_bilgisinin_oldugunu_dogrular(int code, String key, String expectedValue) {

        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.then()
                .assertThat()
                .statusCode(code);

        jsonPath = response.jsonPath();
        Assert.assertEquals(expectedValue, jsonPath.getString(key));

    }

    @Given("Api kullanıcısı {string} token ile base urli oluşturur")
    public void api_kullanıcısı_token_ile_base_urli_oluşturur(String userType1) {

        HooksAPI.setUpApi(userType1);
    }
    @Given("Api kullanıcısı {string} path parametrelerini oluşturur")
    public void api_kullanıcısı_path_parametrelerini_oluşturur(String pathParam1) {

        API_Methods.pathParam(pathParam1);
    }
    @Given("Api kullanıcısı addBlogCategory endpoint’ine gönderilmek üzere geçerli bir post request body hazırlar")
    public void api_kullanıcısı_add_blog_category_endpoint_ine_gönderilmek_üzere_geçerli_bir_post_request_body_hazırlar() {
        requestBody.put("title", "Deneme1234");
    }
    @Given("Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder")
    public void api_kullanıcısı_post_request_gönderir_ve_dönen_response_ı_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(API_Methods.fullPath);

        response.prettyPrint();
    }
    @Given("Api kullanıcısı status code’un {int} olduğunu doğrular")
    public void api_kullanıcısı_status_code_un_olduğunu_doğrular(int code) {
        response.then().statusCode(code);
    }
    @Given("Api kullanıcısı response body’deki {string} bilgisinin {string} olduğunu doğrular")
    public void api_kullanıcısı_response_body_deki_bilgisinin_olduğunu_doğrular(String key, String expectedValue) {
        jsonPath = response.jsonPath();
        String actualValue = jsonPath.getString(key);
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Given("Api kullanıcısı title içermeyen bir post request body hazırlar")
    public void api_kullanıcısı_title_içermeyen_bir_post_request_body_hazırlar() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .body(requestBody) //atama yapmadigim icin bos
                .when()
                .post(API_Methods.fullPath);
    }
    @Given("Api kullanıcısı daha önce kullanılmış bir title ile post request body hazırlar")
    public void api_kullanıcısı_daha_önce_kullanılmış_bir_title_ile_post_request_body_hazırlar() {
        requestBody.put("title", "Online11 Education");

    }
    @Given("Api kullanıcısı geçerli title içeren bir post request body hazırlar")
    public void api_kullanıcısı_geçerli_title_içeren_bir_post_request_body_hazırlar() {
        requestBody.put("title", "Online11111 Education");

    }
}









