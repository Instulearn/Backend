package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Blogs_Stepdefinitions {

    Response response;
    JsonPath jsonPath;


    @Given("Api kullanıcısı {string} token ile base url'i oluşturur")
    public void api_kullanıcısı_token_ile_base_url_i_oluşturur(String string) {
      HooksAPI.setUpApi("admin");
    }


    @Given("Api kullanicisi GET request gonderir ve donen responsu kaydeder")
    public void api_kullanicisi_get_request_gonderir_ve_donen_responsu_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.prettyPrint();
    }
    @Given("Api kullanicisi status code'un {int} oldugunu dogrular")
    public void api_kullanicisi_status_code_un_oldugunu_dogrular(Integer int1) {
        response.then().assertThat().statusCode(200);
    }

    @Given("Api kullanicisi response body'deki {string} bilgisinin {string} oldugunu dogrular")
    public void api_kullanicisi_response_body_deki_bilgisinin_oldugunu_dogrular(String key, String value) {
        response.then()
                .assertThat()
                .body(key,equalTo(value));
    }

    @Given("Api kullanıcısı response body icindeki {int}, {int}, {string}, {string}, {int}, {int}, {string}, {int}, {int}, {int}  {int}  bilgilerini doğrular.")
    public void api_kullanıcısı_response_body_icindeki_bilgilerini_doğrular( int category_id, int author_id, String slug, String image, int visit_count, int enable_comment, String status, int created_at, int updated_at, int comments_count, int dataIndex) {

        jsonPath = response.jsonPath();

        Assert.assertEquals((category_id),jsonPath.getInt("data.blog[" + dataIndex + "].category_id"));
        Assert.assertEquals((author_id), jsonPath.getInt("data.blog[" + dataIndex + "].author_id"));
        Assert.assertEquals((slug), jsonPath.getString("data.blog[" + dataIndex + "].slug"));
        Assert.assertEquals((image), jsonPath.getString("data.blog[" + dataIndex + "].image"));
        Assert.assertEquals((enable_comment), jsonPath.getInt("data.blog[" + dataIndex + "].enable_comment"));
        Assert.assertEquals((status), jsonPath.getString("data.blog[" + dataIndex + "].status"));
        Assert.assertEquals((created_at), jsonPath.getInt("data.blog[" + dataIndex + "].created_at"));
        Assert.assertEquals((updated_at), jsonPath.getInt("data.blog[" + dataIndex + "].updated_at"));
        Assert.assertEquals((comments_count), jsonPath.getInt("data.blog[" + dataIndex + "].comments_count"));
    }

    @Given("Api kullanicisi {string} token ile base urli oluşturur")
    public void api_kullanicisi_token_ile_base_urli_oluşturur(String userCan) {
        HooksAPI.setUpApi("userCan");
    }
    @Given("Api kullanicisi {string} path parametrelerini oluşturur")
    public void api_kullanicisi_path_parametrelerini_oluşturur(String params) {
        API_Methods.pathParam("params");
    }
    @Given("Api kullanicisi GET request gönderir ve dönen responsu kaydeder")
    public void api_kullanicisi_get_request_gönderir_ve_dönen_responsu_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
    }
    @Given("Api kullanicisi response bodydeki {string} bilgisinin {string} oldugunu dogrular")
    public void api_kullanicisi_response_bodydeki_message_bilgisinin_oldugunu_dogrular(String key, String value) {
        response.then().assertThat().body(key,equalTo(value));
    }

}
