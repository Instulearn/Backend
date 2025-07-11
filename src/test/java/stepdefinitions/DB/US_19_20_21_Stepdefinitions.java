package stepdefinitions.DB;

import config_Requirements.ConfigReader;
import helperDB.*;
import io.cucumber.java.en.*;
import io.cucumber.java.en.Then;
import manage.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    // US_20_Delete 1 data by entering uuid information in failed_jobs table.

    @Given("Deleted query prepared for LE's Scenario with {string}.")
    public void deleted_query_prepared_for_le_s_scenario_with(String uuidToDelete) throws SQLException {
        query = getFailed_jobs_delete_data(); // "DELETE FROM failed_jobs WHERE uuid=?"
        preparedStatement = getPraperedStatement(query);
        preparedStatement.setString(1, uuidToDelete);

        int affectedRows = preparedStatement.executeUpdate(); // 🔥 Sorguyu çalıştır

        if (affectedRows > 0) {
            System.out.println("✅ Silinen UUID: " + uuidToDelete);
        } else {
            System.out.println("⚠️ UUID bulunamadı: " + uuidToDelete);
        }


    }

    @Given("Verify the data information Result is obtained for LE's scenario with {string}.")
    public void verify_the_data_information_result_is_obtained_for_le_s_scenario_with(String uuid) throws SQLException {
        String verificationQuery = "SELECT COUNT(*) FROM failed_jobs WHERE uuid = ?;";
        PreparedStatement verificationStatement = connection.prepareStatement(verificationQuery);
        verificationStatement.setString(1, uuid);

        ResultSet resultSet = verificationStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        if (count == 0) {
            System.out.println("✅ UUID '" + uuid + "' için veri başarıyla silinmiş.");
        } else {
            throw new AssertionError("❌ UUID '" + uuid + "' için veri hâlâ tablodadır. Silme işlemi başarısız!");
        }

    }

    @Given("Add {int} data to failed_jobs table's {string},{string},{string},{string},{string} columns at the same time for US.")
    public void add_data_to_failed_jobs_table_s_columns_at_the_same_time_for_us(Integer int1, String uuid, String connection, String queue, String payload, String exception) {
        try {
            // Dinamik SQL sorgusu oluşturuluyor
            query = getFailed_jobs_dynamic_data(uuid, connection, queue, payload, exception);
            System.out.println("Executing query: " + query);

            // Sorgu çalıştırılıyor
            rowCount = getStatement().executeUpdate(query);
            lastInsertedUuid = uuid;

            // Başarı çıktısı
            System.out.println("✅ Veri eklendi: UUID = " + uuid);

        } catch (SQLException e) {
            System.err.println("⛔ SQL Insert hatası: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
//US_21

    @Given("Prepare query of users in active ban status for LE's Scenario.")
    public void prepare_query_of_users_in_active_ban_status_for_le_s_scenario() throws SQLException {
        query = getUs21banStatus_users();
        resultSet = getStatement().executeQuery(query);

    }
    @Given("Verify the ban information Results are obtained for LE's Scenario.")
    public void verify_the_ban_information_results_are_obtained_for_le_s_scenario() throws SQLException {

        List<BanStatus.BanInfo> currentlyBannedUsers = new ArrayList<>();
        List<BanStatus.BanInfo> previouslyBannedUsers = new ArrayList<>();
        List<BanStatus.BanInfo> neverBannedUsers = new ArrayList<>();
        while (resultSet.next()) {
            String banStatus = resultSet.getString("ban_status");
            int totalUsers = resultSet.getInt("total_users");
            double percentage = resultSet.getDouble("percentage");

            BanStatus.BanInfo banInfo = new BanStatus.BanInfo(banStatus, totalUsers, percentage);

            switch (banStatus) {
                case "Currently Banned":
                    currentlyBannedUsers.add(banInfo);
                    break;
                case "Previously Banned":
                    previouslyBannedUsers.add(banInfo);
                    break;
                case "Never Banned":
                    neverBannedUsers.add(banInfo);
                    break;
            }

        }
        // Listeyi ekrana yazdırma
        System.out.println("Currently Banned List (Total: " + currentlyBannedUsers.size() + "):");
        for (BanStatus.BanInfo banInfo : currentlyBannedUsers) {
            System.out.println(banInfo);
        }

        System.out.println("\nPreviously Banned List (Total: " + previouslyBannedUsers.size() + "):");
        for (BanStatus.BanInfo banInfo : previouslyBannedUsers) {
            System.out.println(banInfo);
        }

        System.out.println("\nNever Banned List (Total: " + neverBannedUsers.size() + "):");
        for (BanStatus.BanInfo banInfo : neverBannedUsers) {
            System.out.println(banInfo);
        }



    }

}
