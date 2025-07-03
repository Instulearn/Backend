package utilities.API_Utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import config_Requirements.ConfigLoader;

import static io.restassured.RestAssured.given;

public class Authentication{

    static ConfigLoader configLoader = new ConfigLoader();
    public static String generateToken() {

        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(configLoader.getApiConfig("base_url")).build();

        spec.pathParams("pp1", "api", "pp2", "token");

        JSONObject reqBody  = new JSONObject();
        reqBody.put("email", configLoader.getApiConfig("adminEmail"));
        reqBody.put("password", configLoader.getApiConfig("adminPassword"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("x-api-key", "1234")
                .when()
                .body(reqBody.toString())
                .post("/{pp1}/{pp2}");


        JsonPath repJP = response.jsonPath();

        String token = repJP.getString("data.access_token");
        System.out.println("Token : " + token);

        return token;
    }
}
