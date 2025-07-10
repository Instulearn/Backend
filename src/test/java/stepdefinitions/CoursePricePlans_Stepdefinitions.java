package stepdefinitions;

import config_Requirements.ConfigLoader;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.CoursPricePlanPojo_RequestBody;
import utilities.API_Utilities.API_Methods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class CoursePricePlans_Stepdefinitions {

    JsonPath jsonPath;
    Response response;
    CoursPricePlanPojo_RequestBody coursPricePlanPojoRequestBody;
    boolean sendEmptyBody = false;
    public static int createdPricePlanId;
    public static int updatePricePlanId;
    public static String exceptionMesaj;
    ConfigLoader configLoader = new ConfigLoader();
    public static int deleteId;


    // ********************** GET *************************


    @Given("The api user verifies the {int}, {int}, {int}, {int}, {int}, {int}, {int}, {int}, {int}, {string} and {string} information of the item at {int} in the response body.")
    public void the_api_user_verifies_the_and_information_of_the_item_at_in_the_response_body(Integer id,
                                                                                              Integer creator_id,
                                                                                              Integer webinar_id,
                                                                                              Integer start_date,
                                                                                              Integer end_date,
                                                                                              Integer discount,
                                                                                              Integer capacity,
                                                                                              Integer created_at,
                                                                                              Integer ticket_id,
                                                                                              String locale,
                                                                                              String title,
                                                                                              Integer dataIndex) {

        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/pricePlans");
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        jsonPath = response.jsonPath();

        assertEquals(id.intValue(), jsonPath.getInt("data[" + dataIndex + "].id"));
        assertEquals(creator_id.intValue(), jsonPath.getInt("data[" + dataIndex + "].creator_id"));
        assertEquals(webinar_id.intValue(), jsonPath.getInt("data[" + dataIndex + "].webinar_id"));
        assertEquals(start_date.longValue(), jsonPath.getLong("data[" + dataIndex + "].start_date"));
        assertEquals(end_date.longValue(), jsonPath.getLong("data[" + dataIndex + "].end_date"));
        assertEquals(discount.intValue(), jsonPath.getInt("data[" + dataIndex + "].discount"));
        assertEquals(capacity.intValue(), jsonPath.getInt("data[" + dataIndex + "].capacity"));
        assertEquals(created_at.longValue(), jsonPath.getLong("data[" + dataIndex + "].created_at"));
        assertEquals(ticket_id.intValue(), jsonPath.getInt("data[" + dataIndex + "].translations[0].ticket_id"));
        assertEquals(locale, jsonPath.getString("data[" + dataIndex + "].translations[0].locale"));
        assertEquals(title, jsonPath.getString("data[" + dataIndex + "].translations[0].title"));
        assertNull(jsonPath.get("data[" + dataIndex + "].bundle_id"));
        assertNull(jsonPath.get("data[" + dataIndex + "].order"));
        assertNull(jsonPath.get("data[" + dataIndex + "].updated_at"));
        assertNull(jsonPath.get("data[" + dataIndex + "].deleted_at"));

    }

    // *********************** GET/ID ******************************

    @Given("And The api user verifies the list data with id {int}, creator_id {int}, webinar_id {int}, bundle_id null, start_date {int}, end_date {int}, discount {int}, capacity {int}, order null, created_at {int}, updated_at null, deleted_at null, ticket_id {int}, locale {string}, title {string} in the response body.")
    public void and_the_api_user_verifies_the_list_data_with_id_creator_id_webinar_id_bundle_id_null_start_date_end_date_discount_capacity_order_null_created_at_updated_at_null_deleted_at_null_title_ticket_id_locale_title_in_the_response_body(Integer id,
                                                                                                                                                                                                                                                   Integer creator_id,
                                                                                                                                                                                                                                                   Integer webinar_id,
                                                                                                                                                                                                                                                   Integer start_date,
                                                                                                                                                                                                                                                   Integer end_date,
                                                                                                                                                                                                                                                   Integer discount,
                                                                                                                                                                                                                                                   Integer capacity,
                                                                                                                                                                                                                                                   Integer created_at,
                                                                                                                                                                                                                                                   Integer ticket_id,
                                                                                                                                                                                                                                                   String locale, String title) {
        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/pricePlan/" + id);
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);
        jsonPath = response.jsonPath();
        // Doğrulamalar

        assertEquals(id, Integer.valueOf(jsonPath.getInt("data.id")));
        assertEquals(creator_id, Integer.valueOf(jsonPath.getInt("data.creator_id")));
        assertEquals(webinar_id, Integer.valueOf(jsonPath.getInt("data.webinar_id")));
        assertNull(jsonPath.get("data.bundle_id")); // bundle_id null
        assertEquals(Long.valueOf(start_date), Long.valueOf(jsonPath.getLong("data.start_date")));
        assertEquals(Long.valueOf(end_date), Long.valueOf(jsonPath.getLong("data.end_date")));
        assertEquals(discount, Integer.valueOf(jsonPath.getInt("data.discount")));
        assertEquals(capacity, Integer.valueOf(jsonPath.getInt("data.capacity")));
        assertNull(jsonPath.get("data.order")); // order null
        assertEquals(Long.valueOf(created_at), Long.valueOf(jsonPath.getLong("data.created_at")));
        assertNull(jsonPath.get("data.updated_at")); // updated_at null
        assertNull(jsonPath.get("data.deleted_at")); // deleted_at null

        // Translations içindeki ticket_id, locale ve title kontrolü
        assertEquals(Integer.valueOf(ticket_id), Integer.valueOf(jsonPath.getInt("data.translations[0].ticket_id")));
        assertEquals(locale, jsonPath.getString("data.translations[0].locale"));
        assertEquals(title, jsonPath.getString("data.translations[0].title"));
    }


    // *********************** POST ****************************
    @Given("The API user prepares a POST request body to send to the addPricePlan endpoint with the {string} {string}, {string} {string}, {string} {int}, {string} {int}, and {string} {int}.")
    public void the_apı_user_prepares_a_post_request_body_to_send_to_the_add_PricePlan_endpoint_with_the_and(String title, String titleAttribute, String dateRange, String dateAttribute, String discount, int discountAttribute, String capacity, int capacityAttribute, String webinarID, int webinarAttribute) {

        coursPricePlanPojoRequestBody = new CoursPricePlanPojo_RequestBody(titleAttribute, dateAttribute, discountAttribute, capacityAttribute, webinarAttribute);

        System.out.println("Pojo Request Body: " + coursPricePlanPojoRequestBody.toString());

    }


    @Given("The API user sends a POST request to coursplanprice and records the returned response.")
    public void the_apı_user_sends_a_post_request_to_coursplanprice_and_records_the_returned_response() {

        if (sendEmptyBody) {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .body("{}") // boş body gönder
                    .when()
                    .post(API_Methods.fullPath);
        } else {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .body(coursPricePlanPojoRequestBody) // dolu POJO gönder
                    .when()
                    .post(API_Methods.fullPath);
        }

        response.prettyPrint();
        jsonPath = response.jsonPath();

        if (!sendEmptyBody && response.getStatusCode() == 200) {
            createdPricePlanId = jsonPath.getInt("\"Added Price Plans ID\"");  // response’daki gerçek id alanı burada "id" olmalı
            System.out.println("Kayıt edilen Price Plan ID: " + createdPricePlanId);
        } else {
            System.out.println("Boş body gönderildi veya hata döndü, ID alınmadı.");
        }
    }


    @Given("The API user verifies that the status code for courspriceplan is {int}.")
    public void the_apı_user_verifies_that_the_status_code_for_courspriceplan_is(int statusCode) {
        response.then()
                .statusCode(statusCode);

        System.out.println("Status Code Doğrulandı. Status Code : " + statusCode);
    }


    @Given("API user verifies that the {string} information in the response body for courspriceplan is {string}.")
    public void apı_user_verifies_that_the_information_in_the_response_body_for_courspriceplan_is(String value, String dataAttribute) {
        response.then()
                .assertThat()
                .body(value, equalTo(dataAttribute));

        System.out.println("Doğru mesaj görüntülendi. MESAJ: " + dataAttribute);

    }


    @Given("API user verifies that the {string} in the response body for courspriceplan is {string}.")
    public void apı_user_verifies_that_the_in_the_response_body_for_courspriceplan_is(String Message, String MessageAttribute) {
        response.then()
                .assertThat()
                .body(Message, equalTo(MessageAttribute));

        System.out.println("Doğru mesaj görüntülendi. MESAJ : " + MessageAttribute);
    }


    // bu step boş bir post request gönderir
    @Given("The api user prepares a post request without any data to send to the api addPricePlan endpoint.")
    public void the_api_user_prepares_a_post_request_without_any_data_to_send_to_the_api_add_price_plan_endpoint() {
        sendEmptyBody = true;
    }


    @Given("The API user enters the id value she post into the {string} parameter.")
    public void the_apı_user_enters_the_id_value_she_post_into_the_parameter(String params) {
        HooksAPI.setUpApi("admin");
        API_Methods.pathParam(params + "/" + createdPricePlanId); //151. satırda kayıt ettiğimiz Id
                                                                    // burada doğrulama için kullanıyoruz
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.prettyPrint();
    }


    // ************************ PATCH ********************************
    @Given("The API user sends a PATCH request for id {int} to coursplanprice and records the returned response.")
    public void the_apı_user_sends_a_patch_request_for_id_to_coursplanprice_and_records_the_returned_response(int patchId) {

        API_Methods.pathParam("api/updatePricePlan/" + patchId);
        if (sendEmptyBody) {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .body("{}") // boş gönder
                    .patch(API_Methods.fullPath);
        } else {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .body(coursPricePlanPojoRequestBody) // dolu gönder
                    .patch(API_Methods.fullPath);
        }

        response.prettyPrint();
    }


    @Given("The api user verifies that the {string} in the response body for courspriceplan is the same as the {int} id path parameter.")
    public void the_api_user_verifies_that_the_information_in_the_response_body_for_courspriceplan_is_the_same_as_the_id_path_parameter_in_the_endpoint(String patchIdData, int patchId) {

        jsonPath = response.jsonPath();
        updatePricePlanId = (Integer) jsonPath.getInt("\"" + patchIdData + "\""); //id'yi, patch ettiğimiz data'yıson TC'de
                                                                                    // stepte doğrulamak için kaydediyoruz
        System.out.println("Kayıt edilen Price Plan ID: " + updatePricePlanId);
        Assert.assertEquals(patchId, updatePricePlanId);
        System.out.println("Update edilen data'nın, Response body'de id'si doğrulanır.");
    }


    @Given("The API user sends a PATCH request for not contain id to coursplanprice and records the returned response.")
    public void the_apı_user_sends_a_patch_request_for_not_contain_id_to_coursplanprice_and_records_the_returned_response() {

        API_Methods.pathParam("api/updatePricePlan");
        if (sendEmptyBody) {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .body("{}") // boş gönder
                    .patch(API_Methods.fullPath);
        } else {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .body(coursPricePlanPojoRequestBody) // dolu gönder
                    .patch(API_Methods.fullPath);
        }

        response.prettyPrint();
    }


    @Given("The API user sends a PATCH request to courspriceplan, records the returned response, and verifies that the status code is {string} with the reason Unauthorized.")
    public void the_apı_user_sends_a_patch_request_to_courspriceplan_records_the_returned_response_and_verifies_that_the_status_code_is_with_the_reason_unauthorized(String expectedMessageFromConfig) {

        HooksAPI.setUpApi("invalid");
        API_Methods.pathParam("api/updatePricePlan/125");

        CoursPricePlanPojo_RequestBody coursPricePlanPojoRequestBody = new CoursPricePlanPojo_RequestBody(
                "Change_Api",
                "2025-07-07 - 2025-08-02",
                5,
                200,
                2002
        );

        Response response = null;
        String actualResponseAsString = null;

        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .body(coursPricePlanPojoRequestBody)
                    .patch(API_Methods.fullPath);
        } catch (Exception e) {
            // Exception mesajı genelde şöyle: "status code: 401, reason phrase: Unauthorized"
            actualResponseAsString = e.getMessage();
        }

        expectedMessageFromConfig = configLoader.getApiConfig("unauthorizedExceptionMessage");

        System.out.println("Beklenen mesaj: " + expectedMessageFromConfig);
        System.out.println("Gelen mesaj: " + actualResponseAsString);

        Assert.assertEquals(expectedMessageFromConfig, actualResponseAsString);
    }

    @Given("The API user enters the id value she patch into the {string} parameter.")
    public void the_apı_user_enters_the_id_value_she_patch_into_the_parameter(String params) {
        HooksAPI.setUpApi("admin");
        API_Methods.pathParam(params + "/" + updatePricePlanId); // 236. satırda kayıt ettiğimiz id
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.prettyPrint();
    }

    //********************** DELETE *************************
    @Given("The API user sets {string} path parameters  sends a DELETE request for id {int} to coursplanprice and records the returned response.")
    public void the_apı_user_sets_path_parameters_sends_a_delete_request_for_id_to_coursplanprice_and_records_the_returned_response(String pathParam, int deletePathid) {

        deleteId = deletePathid;

        HooksAPI.setUpApi("admin");
        API_Methods.pathParam(pathParam + "/" + deletePathid);
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .delete(API_Methods.fullPath);

        response.prettyPrint();
    }

    @Given("The API user verifies that the {string} in the response body for coursPricePlan is the same as the id path parameter in the endpoint.")
    public void the_apı_user_verifies_that_the_in_the_response_body_for_cours_price_plan_is_the_same_as_the_id_path_parameter_in_the_endpoint(String deleteMessageData) {

        jsonPath = response.jsonPath();
        assertEquals(deleteId, jsonPath.getInt("\"" + deleteMessageData + "\""));
        System.out.println(deleteId + "id'ye sahip data silinme işlemi doğrulanmıştır. ");
    }


    @Given("The API user enters the data of the deleted ID into the {string} parameter with the Get method.")
    public void the_apı_user_enters_the_data_of_the_deleted_ıd_into_the_parameter_with_the_get_method(String pathParam) {
        HooksAPI.setUpApi("admin");
        API_Methods.pathParam(pathParam + "/" + deleteId); //320. satırda kayıt ettiğimiz id'ye sahip data
        response = given()
                .spec(HooksAPI.spec)
                .when()
                .get(API_Methods.fullPath);

        response.prettyPrint();

        System.out.println("Silinen id'li data, GET methodu ile doğrulandı");

    }


}




















