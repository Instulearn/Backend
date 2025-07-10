package stepdefinitions.DB;

import config_Requirements.ConfigReader;
import helperDB.*;
import io.cucumber.java.en.*;
import io.cucumber.java.en.Then;
import manage.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import static helperDB.CommonData.*;
import static helperDB.JDBC_Structure_Methods.*;
import static helperDB.Job.generateFailedJob;
import static helperDB.Job.jobs;
import static helperDB.ProductReviewStats.assertProductReviewStats;
import static helperDB.SalesRecord.findSalesRecord;
import static helperDB.SalesRecord.salesRecords;
import static helperDB.Transactions.generateAccounts;
import static helperDB.Webinars.assertMaxCapacityWebinars;
import static helperDB.Webinars.webinars;
import static org.junit.Assert.*;


public class US_19_20_21_Stepdefinitions extends US_19_20_21_Manage {

    @Given("Database connection established for LE's Scenario.")
    public void database_connection_established_for_le_s_scenario() {
        createConnection();
    }
    @Given("Add {int} data to failed_jobs table's {string},{string},{string},{string},{string} columns at the same time.")
    public void add_data_to_failed_jobs_table_s_columns_at_the_same_time(Integer int1, String string, String string2, String string3, String string4, String string5) {
        query = getFailed_jobs_add_data();

    }
    @Given("Check that the values are added into the table.")
    public void check_that_the_values_are_added_into_the_table() {


    }
    @Given("Database connection is closed for LE's Scenario.")
    public void database_connection_is_closed_for_le_s_scenario() {
        closeConnection();
    }

}
