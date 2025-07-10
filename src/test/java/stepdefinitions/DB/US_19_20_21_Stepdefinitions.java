package stepdefinitions.DB;

import config_Requirements.ConfigReader;
import helperDB.*;
import io.cucumber.java.en.*;
import io.cucumber.java.en.Then;
import manage.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import static helperDB.CommonData.rowCount;
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

    private String lastInsertedUuid; // Son eklenen UUID'yi takip etmek için

    @Given("Database connection established for LE's Scenario.")
    public void database_connection_established_for_le_s_scenario() {
        createConnection();
        // Test başlamadan önce mevcut test verilerini temizle
        cleanupTestData();
    }
    
    @Given("Add {int} data to failed_jobs table's {string},{string},{string},{string},{string} columns at the same time.")
    public void add_data_to_failed_jobs_table_s_columns_at_the_same_time(Integer int1, String uuid, String connection, String queue, String payload, String exception) {
        query = getFailed_jobs_dynamic_data(uuid, connection, queue, payload, exception);
        try {
            System.out.println("Executing query: " + query);
            rowCount = getStatement().executeUpdate(query);
            System.out.println("Row count: " + rowCount);
            lastInsertedUuid = uuid;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    @Given("Check that the values are added into the table.")
    public void check_that_the_values_are_added_into_the_table() {
        try {
            // Her adımda 1 veri eklendiği için rowCount kontrolü yeterli
            assertEquals("Veri eklenmemiş", 1, rowCount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Given("Database connection is closed for LE's Scenario.")
    public void database_connection_is_closed_for_le_s_scenario() {
        // Test sonunda eklenen veriyi sil
        cleanupInsertedData();

        closeConnection();
    }

    // Test verilerini temizleme metodu
    private void cleanupTestData() {
        try {
            String deleteQuery = "DELETE FROM failed_jobs WHERE uuid LIKE 'deneme_mySQL_uuid_%'";
            int deletedRows = getStatement().executeUpdate(deleteQuery);
            if (deletedRows > 0) {
                System.out.println("Temizlenen test verisi sayısı: " + deletedRows);
            }
        } catch (SQLException e) {
            System.err.println("Test verisi temizleme hatası: " + e.getMessage());
        }
    }

    // Eklenen veriyi silme metodu
    private void cleanupInsertedData() {
        try {
            if (lastInsertedUuid != null) {
                String deleteQuery = "DELETE FROM failed_jobs WHERE uuid = '" + lastInsertedUuid + "'";
                getStatement().executeUpdate(deleteQuery);
                System.out.println("Eklenen veri silindi: " + lastInsertedUuid);
            }
        } catch (SQLException e) {
            System.err.println("Veri silme hatası: " + e.getMessage());
        }
    }

}
