package stepdefinitions;

import config_Requirements.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utilities.API_Methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class ProductSteps {

    private String productData;
    private int productId;
    JsonPath jsonPath;

    HashMap<String,Object> map;

    @Given("a valid product data")
    public void aValidProductData() {
        JSONObject product = new JSONObject();
        product.put("name", "John Brow");
        product.put("email", "jbrown@gmail.com");
        product.put("phone", 12024569875L);
        product.put("subject", "Test Contact Subject");
        product.put("message", "Test Contact Message");

        productData = product.toString();
        System.out.println("productData : " + productData);
    }

    @When("I send a POST request to {string}")
    public void iSendAPostRequestTo(String url) throws Exception {
        API_Methods.sendRequest("POST", productData);
    }

    @Then("the product should be created successfully")
    public void theProductShouldBeCreatedSuccessfully() {

        map = API_Methods.response.as(HashMap.class);


        productId = (int) map.get("Added Contact Message ID");

        System.out.println("productId : " + productId);
    }

    @And("the product should be present in the database")
    public void theProductShouldBePresentInTheDatabase() throws Exception {
        String jdbcUrl = ConfigReader.getProperty("URL","db");
        String username = ConfigReader.getProperty("USERNAME","db");
        String password = ConfigReader.getProperty("PASSWORD","db");

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM contacts WHERE id = " + productId;
        ResultSet resultSet = statement.executeQuery(query);

        Assert.assertTrue(resultSet.next());
        Assert.assertEquals("John Brow", resultSet.getString("name"));

        resultSet.close();
        statement.close();
        connection.close();
    }
}

