package stepdefinitions.DB;

import helperDB.CommonData;
import io.cucumber.java.en.Given;
import manage.US_1_2_3_Manage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static helperDB.CommonData.*;
import static helperDB.CommonData.actualPercentage;
import static helperDB.CommonData.percentageMap;
import static helperDB.CommonData.status;
import static helperDB.CommonData.totalMeetings;
import static helperDB.CommonData.totalMeetingsMap;
import static helperDB.JDBC_Structure_Methods.*;
import static helperDB.JDBC_Structure_Methods.getStatement;
import static helperDB.JDBC_Structure_Methods.query;
import static helperDB.JDBC_Structure_Methods.resultSet;
import static org.junit.Assert.*;

public class US_1_2_3_Stepdefinitions extends US_1_2_3_Manage {
    CommonData data = new CommonData();

    public US_1_2_3_Stepdefinitions() throws SQLException {
    }

    @Given("Database connection established.")
    public void database_connection_established() {
        createConnection();
    }

    @Given("Database connection is closed.")
    public void database_connection_is_closed() {
        closeConnection();
    }


    @Given("Calculate the total meeting time in minutes for the user {string} in the  {string} table")
    public void calculate_the_total_meeting_time_in_minutes_for_the_user_in_the_table(String string, String string2) throws SQLException {
        query = getUs01_calculates_the_total_meeting_time();
        //System.out.println(query);
        resultSet = getStatement().executeQuery(query);

    }

    @Given("Verify the information Results are obtained")
    public void verify_the_information_results_are_obtained() throws SQLException {
        resultSet.next();
        int count = resultSet.getInt("total_meeting_duration");
       // System.out.println(count);
        int exp_count = 0;
        assertEquals(exp_count, count);
    }

    @Given("Preparation of query grouping according to statuses in the reserved meeting table.")
    public void preparation_of_query_grouping_according_to_statuses_in_the_reserved_meeting_table() throws SQLException {
        query = getUs02_showing_the_total_number_of_meetings();
        resultSet = getStatement().executeQuery(query);

        totalMeetingsMap = new HashMap<>();
        percentageMap = new HashMap<>();

        while (resultSet.next()) {
            status = resultSet.getString("status");
            totalMeetings = resultSet.getInt("total_meetings");
            actualPercentage = resultSet.getDouble("percentage");

            totalMeetingsMap.put(status, totalMeetings);
            percentageMap.put(status, actualPercentage);
        }
    }

    @Given("The status should be {string}.")
    public void the_status_should_be(String expectedStatus) {
        currentStatus = expectedStatus;
        System.out.println("Checking for status: " + expectedStatus);
        assertTrue("Couldn't find expected status: " + expectedStatus, totalMeetingsMap.containsKey(expectedStatus));
    }

    @Given("The total_meetings should be {int}.")
    public void the_total_meetings_should_be(Object expectedTotalMeetings) {
        assertEquals("Total Meetings value is wrong: ", expectedTotalMeetings, totalMeetingsMap.getOrDefault(currentStatus,-1));
    }

    @Given("The percentage should be {double}.")
    public void the_percentage_should_be(Double expectedPercentage) {
        System.out.println("Checking percentage for status: " + currentStatus);
        assertEquals(expectedPercentage, percentageMap.get(currentStatus));
    }

    @Given("prepare query into the table.")
    public void prepare_query_to_into_the_table() throws SQLException {

        query = getUs03_gift_information_for_product_orders();
        resultSet = getStatement().executeQuery(query);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("gift_name"));        }
    }

    @Given("Verify results are obtained in the table.")
    public void verify_results_are_obtained_in_the_table() {
        List<String> productGift = new ArrayList<>();

        try {
            while (resultSet.next()) {
                productGift.add(resultSet.getString("gift_name"));
            }

            if (!productGift.isEmpty()) {
                for (String gift : productGift) {
                    System.out.println(gift);
                    assertTrue(
                            "Gift entry does not contain expected value: " + gift,
                            gift.contains("Cameron Schofield")
                    );
                }
            } else {
                fail("ResultSet returned no data for gift_name.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while retrieving gift_name", e);
        }
    }


}
