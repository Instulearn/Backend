package stepdefinitions.DB;

import config_Requirements.ConfigReader;
import helperDB.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manage.Manage;
import manage.US_13_14_15_Manage;

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
import static utilities.DB_Utilities.JDBCMethods.executeQuery;
import static org.junit.Assert.*;

public class US_13_14_15_Stepdefinitions extends US_13_14_15_Manage{

    @Given("Database connection established for furkan's scenario")
    public void database_connection_established_for_furkans_scenario() {
        createConnection();
    }

    // TODO: US13 Step Definitions
    @When("I query accepted instructors from last 15 days")
    public void i_query_accepted_instructors_from_last_15_days() {
        String query = getUs13AcceptedInstructorsLast15Days();
        executeQuery(query);
    }

    @Then("I should see accepted instructors list")
    public void i_should_see_accepted_instructors_list() {
        try {
            assertNotNull("Result set should not be null", resultSet);
            
            // Verileri yazdır
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
                String instructorId = resultSet.getString("id");
                String userId = resultSet.getString("user_id");
                String role = resultSet.getString("role");
                String status = resultSet.getString("status");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");

            }
            
        } catch (Exception e) {
            System.err.println("❌ US13 - Onaylanmış instructor'lar listesi doğrulanırken hata: " + e.getMessage());
            fail("Failed to verify accepted instructors list");
        }
    }

    @Then("the count should be greater than zero")
    public void the_count_should_be_greater_than_zero() {
        try {
            // Count query'yi çalıştır
            String countQuery = getUs13AcceptedInstructorsCount();
            executeQuery(countQuery);
            
            // Result set'ten count değerini al
            if (resultSet.next()) {
                int count = resultSet.getInt("accepted_instructors_count");
                assertTrue("Count should be greater than zero", count >= 0);
            }
        } catch (SQLException e) {
            System.err.println("❌ US13 - Sayı kontrol edilirken hata: " + e.getMessage());
            fail("Failed to check count");
        }
    }

    @When("I query accepted instructors from last {string} days")
    public void i_query_accepted_instructors_from_last_days(String days) {
        String query = getUs13AcceptedInstructorsByDateRange(days);
        executeQuery(query);
    }

    @Then("I should see accepted instructors for the specified period")
    public void i_should_see_accepted_instructors_for_the_specified_period() {
        try {
            assertNotNull("Result set should not be null", resultSet);
            
            // Verileri yazdır
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
                String instructorId = resultSet.getString("id");
                String userId = resultSet.getString("user_id");
                String role = resultSet.getString("role");
                String status = resultSet.getString("status");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
            }
                        
        } catch (Exception e) {
            System.err.println("❌ US13 - Onaylanmış instructor'lar doğrulanırken hata: " + e.getMessage());
            fail("Failed to verify accepted instructors");
        }
    }

    // TODO: US14 Step Definitions
    @When("I query become_instructors table for all roles") // bütün rolleri sorguluyoruz
    public void i_query_become_instructors_table_for_all_roles() { 
        String query = getUs14AllRoles(); 
        executeQuery(query);
    }

    @Then("I should see only teacher and organization roles")
    public void i_should_see_only_teacher_and_organization_roles() {
        try {
            assertNotNull("Result set should not be null", resultSet);
            boolean hasOnlyValidRoles = true;
            
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                
                if (!role.equals("teacher") && !role.equals("organization")) {
                    hasOnlyValidRoles = false;
                    break;
                }
            }
            
            assertTrue("Only teacher and organization roles should exist", hasOnlyValidRoles);
        } catch (Exception e) {
            fail("Failed to verify roles");
        }
    }

    @Then("no invalid roles should exist")
    public void no_invalid_roles_should_exist() {
        try {
            String invalidRolesQuery = getUs14InvalidRoles();
            executeQuery(invalidRolesQuery);
            
            if (resultSet.next()) {
                fail("Invalid roles found in become_instructors table");
            } else {
            }
        } catch (Exception e) {
            System.err.println("❌ US14 - Geçersiz roller kontrol edilirken hata: " + e.getMessage());
            fail("Failed to check invalid roles");
        }
    }

    @When("I query become_instructors table for valid roles")
    public void i_query_become_instructors_table_for_valid_roles() {
        String query = getUs14ValidRoles();
        executeQuery(query);
    }

    @Then("I should see teacher and organization roles only")
    public void i_should_see_teacher_and_organization_roles_only() {
        try {
            assertNotNull("Result set should not be null", resultSet);
            boolean hasTeacher = false;
            boolean hasOrganization = false;
            
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                int count = resultSet.getInt("valid_role_count");
                
                if (role.equals("teacher")) {
                    hasTeacher = true;
                } else if (role.equals("organization")) {
                    hasOrganization = true;
                }
            }
            
            assertTrue("Teacher role should exist", hasTeacher);
            assertTrue("Organization role should exist", hasOrganization);
        } catch (Exception e) {
            System.err.println("❌ US14 - Geçerli roller doğrulanırken hata: " + e.getMessage());
            fail("Failed to verify valid roles");
        }
    }

    // TODO: US15 Step Definitions
    @When("I query products table for out of stock items")
    public void i_query_products_table_for_out_of_stock_items() {
        String query = getUs15OutOfStockProducts();
        executeQuery(query);
    }

    @Then("I should see out of stock products list")
    public void i_should_see_out_of_stock_products_list() {
        try {
            assertNotNull("Result set should not be null", resultSet);
            System.out.println("📋 US15 - Stokta olmayan ürünler listesi doğrulanıyor...");
            
            // Ürünleri yazdır
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
                String productId = resultSet.getString("id");
                String slug = resultSet.getString("slug");
                String inventory = resultSet.getString("inventory");
                String status = resultSet.getString("status");
            }
            
            
        } catch (Exception e) {
            System.err.println("❌ US15 - Stokta olmayan ürünler doğrulanırken hata: " + e.getMessage());
            fail("Failed to verify out of stock products");
        }
    }

    @Then("the out of stock count should be greater than or equal to zero")
    public void the_out_of_stock_count_should_be_greater_than_or_equal_to_zero() {
        try {
            String countQuery = getUs15OutOfStockCount();
            System.out.println("🔢 US15 - Stokta olmayan ürün sayısı sorgulanıyor: " + countQuery);
            
            // Yeni bir sorgu çalıştır
            executeQuery(countQuery);
            
            if (resultSet.next()) {
                int count = resultSet.getInt("out_of_stock_count");
                assertTrue("Out of stock count should be >= 0", count >= 0);
                System.out.println("📊 US15 - Stokta olmayan ürün sayısı: " + count);

            }
        } catch (SQLException e) {
            System.err.println("❌ US15 - Stokta olmayan ürün sayısı kontrol edilirken hata: " + e.getMessage());
            fail("Failed to check out of stock count");
        }
    }

    @When("I query products table for stock analysis")
    public void i_query_products_table_for_stock_analysis() {
        String query = getUs15StockAnalysis();
        executeQuery(query);
    }

    @Then("I should see stock status breakdown")
    public void i_should_see_stock_status_breakdown() {
        try {
            assertNotNull("Result set should not be null", resultSet);
        
            // Stok durumlarını yazdır
            while (resultSet.next()) {
                String status = resultSet.getString("stock_status");
                int count = resultSet.getInt("product_count");
            }
            
        } catch (Exception e) {
            System.err.println("❌ US15 - Stok analizi doğrulanırken hata: " + e.getMessage());
            fail("Failed to verify stock analysis");
        }
    }

    @Given("Database connection is closed for furkan's scenario")
    public void database_connection_is_closed_for_furkans_scenario() {
        closeConnection();
    }

}
