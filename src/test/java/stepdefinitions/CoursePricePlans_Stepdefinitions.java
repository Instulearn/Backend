package stepdefinitions;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.API_Utilities.API_Methods;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class CoursePricePlans_Stepdefinitions {

    JsonPath jsonPath;
    Response response;

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
                                                                                                                                                                                                                                                   String locale,
                                                                                                                                                                                                                                                   String title) {
        HooksAPI.setUpApi("admin");
        API_Methods.pathParam("api/pricePlan/"+id);
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


}











