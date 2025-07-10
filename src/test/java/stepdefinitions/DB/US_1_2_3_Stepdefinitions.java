package stepdefinitions.DB;

import helperDB.CommonData;
import io.cucumber.java.en.Given;
import manage.US_1_2_3_Manage;
import java.sql.SQLException;
import static helperDB.JDBC_Structure_Methods.closeConnection;
import static helperDB.JDBC_Structure_Methods.createConnection;

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
    public void calculate_the_total_meeting_time_in_minutes_for_the_user_in_the_table(String string, String string2) {

    }

    @Given("Verify the information Results are obtained")
    public void verify_the_information_results_are_obtained() {

    }

    @Given("Preparation of query grouping according to statuses in the reserved meeting table.")
    public void preparation_of_query_grouping_according_to_statuses_in_the_reserved_meeting_table() {

    }

    @Given("The status should be {string}.")
    public void the_status_should_be(String string) {

    }

    @Given("The total_meetings should be {int}.")
    public void the_total_meetings_should_be(Integer int1) {

    }

    @Given("The percentage should be {double}.")
    public void the_percentage_should_be(Double double1) {

    }

    @Given("prepare query to  into the  table.")
    public void prepare_query_to_into_the_table() {

    }

    @Given("Verify results are obtained in the table.")
    public void verify_results_are_obtained_in_the_table() {

    }

}
