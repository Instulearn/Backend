package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.Blogs_PatchPojos;
import pojos.Blogs_PostPojo;
import utilities.API_Utilities.API_Methods;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class Blogs_Stepdefinitions {

    Response response;
    JsonPath jsonPath;
    String exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();
    Blogs_PostPojo blogsPostPojo;
    Blogs_PatchPojos blogsPatchPojos;



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
    public void api_kullanicisi_status_code_un_oldugunu_dogrular(int code) {
        response.then().assertThat().statusCode(code);
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
    public void api_kullanicisi_token_ile_base_urli_oluşturur(String token) {
        HooksAPI.setUpApi(token);
    }
    @Given("Api kullanicisi {string} path parametrelerini oluşturur")
    public void api_kullanicisi_path_parametrelerini_oluşturur(String params) {
        API_Methods.pathParam(params);
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

    @Given("Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun {string} ve reason phrase bilgisinin Unauthorized oldugunu dogrular")
    public void api_kullanicisi_get_request_gonderir_donen_responsei_kaydeder_status_codeun_ve_reason_phrase_bilgisinin_unauthorized_oldugunu_dogrular(String string) {
        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .when()
                    .get(API_Methods.fullPath);
        } catch (Exception e) {
            exceptionMesaj = e.getMessage();
        }
        System.out.println("exceptionMesaj : " + exceptionMesaj);
        Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"), exceptionMesaj);
    }


    @Given("Api kullanıcısı response body icindeki  {int}, {int}, {int}, {string}, {string}, {int}, {int}, {string}, {int}, {int}, bilgilerini doğrular.")
    public void api_kullanıcısı_response_body_icindeki_bilgilerini_doğrular(int id, int category_id, int author_id, String slug, String image, int visit_count, int enable_comment, String status, int created_at, int updated_at) {


        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.then()
                .assertThat()
                .body("data.id", equalTo(id),
                        "data.category_id", equalTo(category_id),
                        "data.author_id", equalTo(author_id),
                        "data.slug", equalTo(slug),
                        "data.image", equalTo(image),
                        "data.enable_comment", equalTo(enable_comment),
                        "data.slug", equalTo(slug),
                        "data.status", equalTo(status),
                        "data.created_at", equalTo(created_at),
                        "data.updated_at", equalTo(updated_at));

    }

    @Given("Api kullanicisi,response body'deki {string} bilgisinin {string} olduğunu doğrular")
    public void api_kullanicisi_response_body_deki_bilgisinin_olduğunu_doğrular(String data1, String data2) {
        response
                .then()
                .assertThat()
                .body(data1,equalTo(data2));
    }

    @Given("Api kullanıcısı addBlog endpointine göndermek için post request body hazırlar")
    public void api_kullanıcısı_add_blog_endpointine_göndermek_için_post_request_body_hazırlar() {
        blogsPostPojo = new Blogs_PostPojo(
                "The Growing Impact of Online Education",
                34,
                "Online education has rapidly evolved from a niche alternative to a mainstream method of learning. This transformation has been driven by technological advancements, changing perceptions, and the need for flexible learning options.",
                "Accessibility Through MOOCs:Massive Open Online Courses (MOOCs) pioneered accessible online learning, offering high-quality courses from prestigious universities to a global audience. Platforms like Coursera and edX made education more inclusive, breaking down geographical and financial barriers.Personalized Learning:" +
                        "Advancements in adaptive learning technologies and AI have enabled personalized learning experiences. Online platforms can now tailor content and assessments to individual learners, improving engagement and outcomes.Accredited Online Degrees:The credibility of online degrees has grown, with many accredited universities offering fully online programs. " +
                        "These programs provide flexibility without sacrificing quality, making higher education more accessible to diverse learners.Blended Learning:Blended learning models, combining online and face-to-face instruction, have become popular. This approach maintains the benefits of traditional classrooms while leveraging online resources for enhanced learning experiences.");
    }

    @Given("Api kullanicisi POST request gonderir ve donen responsu kaydeder")
    public void api_kullanicisi_post_request_gonderir_ve_donen_responsu_kaydeder() {
       response = given()
               .spec(HooksAPI.spec)
               .contentType(ContentType.JSON)
               .body(blogsPostPojo)
               .when()
               .post(API_Methods.fullPath);
       response.prettyPrint();
    }

    @Given("Api kullanicisi data içermeyen POST request gonderir ve donen responsu kaydeder")
    public void api_kullanicisi_data_içermeyen_post_request_gonderir_ve_donen_responsu_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .post(API_Methods.fullPath);

    }

    @Given("Api kullanıcısı updateBlog endpointine göndermek için post request body hazırlar")
    public void api_kullanıcısı_update_blog_endpointine_göndermek_için_post_request_body_hazırlar() {
        blogsPatchPojos =  new Blogs_PatchPojos(
                "Online Education",
                34,
                "Online education has rapidly evolved from a niche alternative to a mainstream method of learning.",
                "Accessibility Through MOOCs:Massive Open Online Courses (MOOCs) pioneered accessible online learning, offering high-quality courses from prestigious universities to a global audience." +
                        " Platforms like Coursera and edX made education more inclusive, breaking down geographical and financial barriers."
        );
    }
    @Given("Api kullanicisi PATCH request gönderir ve dönen responsu kaydeder")
    public void api_kullanicisi_patch_request_gönderir_ve_dönen_responsu_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .body(blogsPatchPojos)
                .when()
                .patch(API_Methods.fullPath);

        response.prettyPrint();
    }

    @Given("Api kullanicisi data içerme PATCH request gönderir ve dönen responsu kaydeder")
    public void api_kullanicisi_data_içerme_patch_request_gönderir_ve_dönen_responsu_kaydeder() {

        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .patch(API_Methods.fullPath);
    }

    @Given("Api kullanicisi PATCH request gonderir, donen responsei kaydeder, status codeun {string} ve reason phrase bilgisinin Unauthorized oldugunu dogrular")
    public void api_kullanicisi_patch_request_gonderir_donen_responsei_kaydeder_status_codeun_ve_reason_phrase_bilgisinin_unauthorized_oldugunu_dogrular(String string) {
        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .body(blogsPatchPojos)
                    .when()
                    .patch(API_Methods.fullPath);
        } catch (Exception e) {
            exceptionMesaj = e.getMessage();
        }
        System.out.println("exceptionMesaj : " + exceptionMesaj);
        Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"), exceptionMesaj);
    }


    @Given("Api kullanicisi {string} ile endpoint'e yazılı olan {int}'nin aynı olduğunu doğrular")
    public void api_kullanicisi_ile_endpoint_e_yazılı_olan_nin_aynı_olduğunu_doğrular(String key, int value) {

        response
                .then()
                .assertThat()
                .body("Updated-Blog-Id",equalTo(value));
    }

    @Given("Api kullanicisi DELETE request gönderir ve dönen responsu kaydeder")
    public void api_kullanicisi_delete_request_gönderir_ve_dönen_responsu_kaydeder() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when()
                .get(API_Methods.fullPath);

        response.prettyPrint();


    }

    @Given("Api kullanicisi DELETE request gonderir, donen responsei kaydeder, status codeun {string} ve reason phrase bilgisinin Unauthorized oldugunu dogrular")
    public void api_kullanicisi_delete_request_gonderir_donen_responsei_kaydeder_status_codeun_ve_reason_phrase_bilgisinin_unauthorized_oldugunu_dogrular(String string) {
        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .get(API_Methods.fullPath);
        } catch (Exception e) {
            exceptionMesaj = e.getMessage();
        }
        System.out.println("exceptionMesaj : " + exceptionMesaj);
        Assert.assertEquals(configLoader.getApiConfig("unauthorizedExceptionMessage"), exceptionMesaj);
    }




}
