package stepdefinitions;

import config_Requirements.ConfigReader;
import helperDB.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manage.Manage;

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


public class DB_Stepdefinitions extends Manage {
    CommonData data = new CommonData();

    public DB_Stepdefinitions() throws SQLException {
    }

    @Given("Database connection established")
    public void database_connection_established() {
        createConnection();
    }

    @Given("Database connection is closed")
    public void database_connection_is_closed() {
        closeConnection();
    }

    /**
     * US_01
     */
    @Given("Calculate the total meeting time in minutes for the user {string} in the  {string} table.")
    public void calculate_the_total_meeting_time_in_minutes_for_the_user_in_the_table(String email, String table) throws SQLException {
        query = getUs01_calculates_the_total_meeting_time();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the information Results are obtained.")
    public void verify_the_information_results_are_obtained() throws SQLException {
        resultSet.next();
        int count = resultSet.getInt("total_meeting_duration");
        //System.out.println(count);
        int exp_count = 0;
        assertEquals(exp_count, count);
    }

    /**
     * US_02
     */
    @Given("Preparation of query grouping according to statuses in the reserved meeting table")
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


    @Then("The status should be {string}")
    public void the_status_should_be(String expectedStatus) throws SQLException {

        currentStatus = expectedStatus;
        System.out.println("Checking for status: " + expectedStatus);
        assertEquals("Beklenen durum bulunamadı: " + expectedStatus, true, totalMeetingsMap.containsKey(expectedStatus));

    }

    @Then("The total_meetings should be {int}")
    public void the_total_meetings_should_be(Object expectedTotalMeetings) throws SQLException {
        assertEquals("Toplam toplantı sayısı eşleşmiyor!", expectedTotalMeetings, totalMeetingsMap.getOrDefault(currentStatus, -1));

    }

    @Then("The percentage should be {double}")
    public void the_percentage_should_be(double expectedPercentage) throws SQLException {
        System.out.println("Checking percentage for status: " + currentStatus);
        Optional<Double> percentageOptional = Optional.ofNullable(percentageMap.get(currentStatus));
        double actualPercentage = percentageOptional.orElse(-1.0);
        assertEquals(expectedPercentage, actualPercentage, 0.0001);


    }

    /**
     * US 03
     **/
    @Given("prepare query to insert data entry into the gift table")
    public void prepare_query_to_insert_data_entry_into_the_gift_table() throws SQLException {
        query = getUs03_Insert_a_data_entry_into_the_gifts_table();
        rowCount = getStatement().executeUpdate(query);
    }

    @Given("Verify that {int} added to the table")
    public void verify_that_added_to_the_table(int row) throws SQLException {

        // Sonuç kümesinde satır varsa, bir girişin eklendiğini doğrula
        assertEquals("Giriş eklenemedi.", row, rowCount);
    }

    /**
     * US 04
     **/
    @Given("prepare query to  into the  table")
    public void prepare_query_to_into_the_table() throws SQLException {
        query = getUs04_gift_information_for_product_orders();
        resultSet = getStatement().executeQuery(query);


    }

    @Given("Verify results are obtained in the table")
    public void verify_results_are_obtained_in_the_table() {
        product_gift = new ArrayList<>();
        try {
            while (resultSet.next()) {
                product_gift.add(resultSet.getString("gift_name"));
            }
            if (!product_gift.isEmpty()) {
                for (int i = 0; i < product_gift.size(); i++) {
                    assertTrue(product_gift.get(i).contains("'Cameron Schofield'"));
                    System.out.println(product_gift.get(i));
                }
            } else {
                assertFalse("ResultSet is empty", resultSet.next());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * US05
     */
    @Given("In the Quizzes table, a query is prepared showing the limited questions and the number of questions.")
    public void ın_the_quizzes_table_a_query_is_prepared_showing_the_limited_questions_and_the_number_of_questions() throws SQLException {
        query = getUs05_quizzess_limited_questions();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verifies id {int} the returned result")
    public void verifies_id_the_returned_result(int expectedId) throws SQLException {
        resultSet.next();
        limitedQuestionsId = resultSet.getInt("id");
        assertEquals(expectedId, limitedQuestionsId);

    }

    /**
     * US06
     */
    @Given("In the quizzes_questions table, prepare a query that returns quiz_ids with a pass_mark value")
    public void in_the_quizzes_questions_table_prepare_a_query_that_returns_quiz_ids_with_a_pass_mark_value() throws SQLException {
        query = getUs06_quizzess_questions();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verifies {int} the returned result")
    public void verifies_the_returned_result(int expectedQuizId) throws SQLException {
        while (resultSet.next()) {
            quizId = resultSet.getInt("quiz_id");
            assertEquals(expectedQuizId, quizId);
        }
    }

    /**
     * US07
     */

    @Given("prepare query to insert data entry into the supports table")
    public void prepare_query_to_insert_data_entry_into_the_supports_table() throws SQLException {
        query = getUs07_In_the_supports_table_add_2_data_containing();
        rowCount = getStatement().executeUpdate(query);
    }

    /**
     * US08
     */
    @Given("prepare query  of the data with start_date in the webinars table")
    public void prepare_query_of_the_data_with_start_date_in_the_webinars_table() throws SQLException {
        query = getUs08_unixtime_date_validate();
        resultSet = getStatement().executeQuery(query);
    }

    /**
     * US09
     */

    @Given("prepare query  of the data entering the users table")
    public void prepare_query_of_the_data_entering_the_users_table() throws SQLException {
        query = getUs09_insert_users_table();
        preparedStatement = getPraperedStatement(query);
        //(full_name, email, password, role_name, role_id, created_at)
        preparedStatement.setString(1, data.getFull_name());
        System.out.println(data.getFull_name());
        preparedStatement.setString(2, data.getEmail());
        preparedStatement.setString(3, data.getPassword());
        System.out.println(data.getPassword());
        preparedStatement.setString(4, data.getRole_name());
        preparedStatement.setInt(5, data.getRole_id());

    }

    @Given("Verify the data information Result is obtained.")
    public void verify_the_data_information_result_is_obtained() {
        int rowCount = 0;
        try {
            rowCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, rowCount);

    }


    @Given("updated password")
    public void updated_password() throws SQLException {
        //update işleminde idyi verebilmek için önce insert ettigimiz datanın idsini kaydederiz.
        userId = getLastInsertedUserId(getUs09_selected_id_users_table(), data.getFull_name());
        //update query için prepared hazırlanır
        query = getUs09_update_password_users_table();
        preparedStatement = getPraperedStatement(query);
        //yeni password
        preparedStatement.setString(1, data.getNewPassword());
        System.out.println(data.getNewPassword());
        //User_id bilgileri girilir
        System.out.println(userId);
        preparedStatement.setInt(2, userId);
    }

    /**
     * US10
     */
    @Given("A query is prepared for the list of users with financial approval and installment approval in the Users table.")
    public void a_query_is_prepared_for_the_list_of_users_with_financial_approval_and_installment_approval_in_the_users_table() throws SQLException {
        query = getUs10_financial_and_installment_approval();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify list of users with financial and installment approvals")
    public void verify_list_of_users_with_financial_and_installment_approvals() throws SQLException {
        financial_installment_usersId_fullname = new HashMap<>();
        try {
            if (resultSet.next()) {
                // ResultSet dolu ise
                System.out.println("ResultSet is not empty. Processing the data...");

                financial_installment_usersId_fullname.put(resultSet.getInt("id"), resultSet.getString("full_name"));

                // Sonraki satırları işlemeye devam edin
                while (resultSet.next()) {
                    financial_installment_usersId_fullname.put(resultSet.getInt("id"), resultSet.getString("full_name"));
                }

                // Map içeriğini yazdırın
                for (Map.Entry<Integer, String> entry : financial_installment_usersId_fullname.entrySet()) {
                    System.out.println("ID: " + entry.getKey() + ", Full Name: " + entry.getValue());
                }
            } else {
                // ResultSet boş ise
                System.out.println("ResultSet is empty.");
                assertFalse("The ResultSet is empty.", resultSet.next());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * US11
     */

    @Given("Prepares a query that returns meetings with total payments more than {int}")
    public void prepares_a_query_that_returns_meetings_with_total_payments_more_than(Integer num) throws SQLException {
        query = getUs11_groupby_meeting_id();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verifies {int} row the returned result")
    public void verifies_row_the_returned_result(int row) throws SQLException {
        int flag = 0;
        while (resultSet.next()) {
            flag++;
        }
        assertEquals(row, flag);

    }

    /**
     * US12
     */

    @Given("prepare query to insert data entry into the users_zoom_api table")
    public void prepare_query_to_insert_data_entry_into_the_users_zoom_api_table() throws SQLException {
        query = getUs12_zoom_api_insert_data();
        preparedStatement = getPraperedStatement(query);
        //CommonDatta classında Users listesinden rastgele id donduren bir method hazırladık ve user_id ye kaydettik
        userId = getUserIdUsersTable(getUsersTable_user_id());
        // (user_id, jwt_token, 'example_api_key', 'example_api_secret', account, unix_timestamp())
        preparedStatement.setInt(1, userId);
        System.out.println(userId);
        preparedStatement.setString(2, data.getJwtToken());
        preparedStatement.setString(3, data.getApiKey());
        preparedStatement.setString(4, data.getApiSecret());
        preparedStatement.setString(5, data.getAccountId());
    }

    @Given("Updated account_id")
    public void updated_account_id() throws SQLException {
        query = getUs12_update();
        preparedStatement = getPraperedStatement(query);
        // (user_id, jwt_token, 'example_api_key', 'example_api_secret', account, unix_timestamp())
        preparedStatement.setString(1, ConfigReader.getProperty("account_id", "db"));
        preparedStatement.setInt(2, userId);
        System.out.println(userId);
    }

    @Given("prepare query to insert data entry into the  verifications table")
    public void prepare_query_to_insert_data_entry_into_the_verifications_table() throws SQLException {
        query = getUs13_insert_data_verifications_table();
        preparedStatement = getPraperedStatement(query);
        //(user_id, mobile, email, code)
        //CommonDatta classında Users listesinden rastgele id donduren bir method hazırladık ve user_id ye kaydettik
        userId = getUserIdUsersTable(getUsersTable_user_id());
        preparedStatement.setInt(1, userId);
        System.out.println(userId);

    }

    @Given("Deleted data")
    public void deleted_data() {

    }

    @Given("prepare query List the number of people classified by roles")
    public void prepare_query_list_the_number_of_people_classified_by_roles() throws SQLException {
        query = getUs14_become_instructors_table();
        resultSet = getStatement().executeQuery(query);
    }


    @Given("Verify the list results {string} {int} obtained")
    public void verify_the_list_results_obtained(String role, Integer record_count) throws SQLException {
        becomeInsGroup = new HashMap<>();
        while (resultSet.next()) {
            becomeInsGroup.put(resultSet.getString("role"), resultSet.getInt("record_count"));
        }
        assertTrue("Role not found: " + role, becomeInsGroup.containsKey(role));

        // Assert that the count matches the expected count
        assertEquals("Record count mismatch for role: " + role, record_count, becomeInsGroup.get(role));

    }

    @Given("prepare query to insert {int} datas entry into the payku_transactions table")
    public void prepare_query_to_insert_datas_entry_into_the_payku_transactions_table(Integer count) throws SQLException {
        query = getUs17_payku_translation_insert_bulk();
        preparedStatement = getPraperedStatement(query);
        /**  addBatch() yöntemi ile her hazırlanan sorgu bu sorgu yığınına eklenir.   */
        List<Transactions> accounts = generateAccounts(count);

        /**  Ardından executeBatch() yöntemi kullanılarak tüm sorguları çalıştırılır.
         * int id, String orderStatus, String order, String email, String subject, String url, Integer amount, String full_name
         */

        int flag = 0;

        for (Transactions transaction : accounts) {

            preparedStatement.setInt(1, accounts.get(flag).getId());
            preparedStatement.setString(2, accounts.get(flag).getOrderStatus());
            preparedStatement.setString(3, accounts.get(flag).getOrder());
            preparedStatement.setString(4, accounts.get(flag).getEmail());
            preparedStatement.setString(5, accounts.get(flag).getSubject());
            preparedStatement.setString(6, accounts.get(flag).getUrl());
            preparedStatement.setInt(7, accounts.get(flag).getAmount());
            preparedStatement.setString(8, accounts.get(flag).getFull_name());


            preparedStatement.addBatch();
            flag++;
            if (flag == accounts.size()) {
                result = preparedStatement.executeBatch();
            }

        }
    }

    @Given("{int} Enter the data in bulk. Check that it is added to the table.")
    public void enter_the_data_in_bulk_check_that_it_is_added_to_the_table(int rowCount) {
        /** executeBatch() yöntemi, her sorgunun etkilenen satır sayısını içeren bir int dizisi döndürür.*/

        System.out.println("Inserted " + result.length + " records successfully.");
        System.out.println(Arrays.toString(result));
        assertEquals(rowCount, result.length);
    }


    /**
     * US18
     */

    @Given("prepare query List the products in the products table")
    public void prepare_query_list_the_products_in_the_products_table() throws SQLException {
        query = getUs18_produtcs();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the list results {string} and {int} inventory obtained")
    public void verify_the_list_results_and_inventory_obtained(String exp_slug, Integer inventory) throws SQLException {
        productsMinInventory = new HashMap<>();
        try {
            while (resultSet.next()) {
                productsMinInventory.put(resultSet.getString("slug"), resultSet.getInt("inventory"));
            }

            for (String slug : productsMinInventory.keySet()) {
                System.out.println("Slug: " + slug + ", Inventory: " + productsMinInventory.get(slug));
            }
            assertEquals(exp_slug, productsMinInventory.get(slug));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * US19
     */
    @Given("prepare updated query from the products table")
    public void prepare_updated_query_from_the_products_table() throws SQLException {
        query = getUs19_productOrders();
        rowCount = getStatement().executeUpdate(query);
    }

    /**
     * US20
     */
    @Given("prepare query with the status {string} and using the {string} method")
    public void prepare_query_with_the_status_and_using_the_method(String string, String string2) throws SQLException {
        query = getUs20payments_methods_credit();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify total_credit_card_sales {double} obtained")
    public void verify_total_credit_card_sales_obtained(double total) throws SQLException {
        resultSet.next();

        assertEquals(total, resultSet.getInt("total_credit_card_sales"), 0.5);
    }

    /**
     * US21
     */

    @Given("prepare query Physical and Virtual Product Discounts")
    public void prepare_query_physical_and_virtual_product_discounts() throws SQLException {
        query = getUs21discounts_ph_virtual();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the list results {int} code obtained")
    public void verify_the_list_results_obtained(int code) throws SQLException {
        discountCode = new ArrayList<>();
        while (resultSet.next()) {
            discountCode.add(resultSet.getString("code"));
        }
        assertEquals(code, discountCode.size());
    }

    /**
     * US22
     */

    @Given("Add {int} data to failed_jobs table at the same time")
    public void add_data_to_failed_jobs_table_at_the_same_time(Integer count) throws SQLException {
        query = getUs22failed_jobs();
        preparedStatement = getPraperedStatement(query);
        jobs = generateFailedJob(count);
        //(uuid, connection, queue, payload, exception, failed_at)
        int flag = 0;
        for (Job field : jobs) {
            preparedStatement.setString(1, jobs.get(flag).getUuid());
            preparedStatement.setString(2, jobs.get(flag).getConnecction());
            preparedStatement.setString(3, jobs.get(flag).getQueue());
            preparedStatement.setString(4, jobs.get(flag).getPayload());
            preparedStatement.setString(5, jobs.get(flag).getException());
            preparedStatement.setTimestamp(6, jobs.get(flag).getFailed_at());

            preparedStatement.addBatch();
            flag++;
            if (flag == jobs.size()) {
                result = preparedStatement.executeBatch();
            }

        }

    }

    /**
     * US23
     ***/
    @Given("Deleted query prepare")
    public void deleted_query_prepare() throws SQLException {
        query = getUs23deleted_failed_jobs();
        preparedStatement = getPraperedStatement(query);
        String UuId = jobs.get(0).getUuid();
        System.out.println(UuId);
        preparedStatement.setString(1, UuId);
    }

    /**
     * US24
     ***/

    @Given("prepare query of users in active ban status")
    public void prepare_query_of_users_in_active_ban_status() throws SQLException {
        query = getUs24banStatus_users();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the ban information Results are obtained.")
    public void verify_the_ban_information_results_are_obtained() throws SQLException {
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

    /**
     * US25
     **/

    @Given("a list of the average signup bonus amount and the total number of users is prepared")
    public void a_list_of_the_average_signup_bonus_amount_and_the_total_number_of_users_is_prepared() throws SQLException {
        query = getUs25bonus_amount_of_users();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify list average sign-up bonus amount of users in each group")
    public void verify_list_average_sign_up_bonus_amount_of_users_in_each_group() throws SQLException {
        while (resultSet.next()) {
            String financialApproval = resultSet.getString("financial_approval");
            String installmentApproval = resultSet.getString("installment_approval");
            String enableRegistrationBonus = resultSet.getString("enable_registration_bonus");
            double avgRegistrationBonus = resultSet.getDouble("avg_registration_bonus");
            int userCount = resultSet.getInt("user_count");

            // Print the results
            System.out.println("Financial Approval: " + financialApproval);
            System.out.println("Installment Approval: " + installmentApproval);
            System.out.println("Enable Registration Bonus: " + enableRegistrationBonus);
            System.out.println("Avg Registration Bonus: " + avgRegistrationBonus);
            System.out.println("User Count: " + userCount);
            System.out.println("-------------------------------");
            // Assertions to validate the results
            assert financialApproval != null && !financialApproval.isEmpty() : "Financial approval should not be null or empty";
            assert installmentApproval != null && !installmentApproval.isEmpty() : "Installment approval should not be null or empty";
            assert enableRegistrationBonus != null && !enableRegistrationBonus.isEmpty() : "Enable registration bonus should not be null or empty";
            assert avgRegistrationBonus >= 0 : "Average registration bonus should be greater than or equal to 0";
            assert userCount > 0 : "User count should be greater than 0";
        }
    }

    /**
     * US26
     */
    @Given("a list of Group users according to their language and currency preferences prepared")
    public void a_list_of_group_users_according_to_their_language_and_currency_preferences_prepared() throws SQLException {
        query = getUs26();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify calculate and list of users in each language and currency combination and the total commission amount of each group")
    public void verify_calculate_and_list_of_users_in_each_language_and_currency_combination_and_the_total_commission_amount_of_each_group() throws SQLException {
        while (resultSet.next()) {
            String language = resultSet.getString("language");
            String currency = resultSet.getString("currency");
            int userCount = resultSet.getInt("user_count");
            double totalCommission = resultSet.getDouble("total_commission");

            // Print the results
            System.out.println("Language: " + language);
            System.out.println("Currency: " + currency);
            System.out.println("User Count: " + userCount);
            System.out.println("Total Commission: " + totalCommission);
            System.out.println("-------------------------------");
            assertTrue("Language should not be null or empty", language != null && !language.isEmpty());
            assertTrue("Currency should not be null or empty", currency != null && !currency.isEmpty());
            assertTrue("User count should be greater than 0", userCount > 0);
            assertTrue("Total commission should be greater than or equal to 0", totalCommission >= 0);
        }
        /**
         * US27
         ****/
    }

    @Given("execute query to fetch webinar statistics")
    public void execute_query_to_fetch_webinar_statistics() throws SQLException {
        query = getUs27();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify following statistics")
    public void verify_following_statistics() throws SQLException {
        while (resultSet.next()) {
            String fullName = resultSet.getString("full_name");
            String categorySlug = resultSet.getString("slug");
            double avgPrice = resultSet.getDouble("avg_price");
            int totalCapacity = resultSet.getInt("total_capacity");
            String lastUpdated = resultSet.getString("last_updated");

            // Example assertions
            assertNotNull(fullName, "Full name should not be null");
            assertNotNull(categorySlug, "Category slug should not be null");
            assertTrue("Average price should be greater than or equal to 0", avgPrice >= 0);
            assertTrue("Total capacity should be greater than 0 or not be null", totalCapacity > 0);
            assertNotNull(lastUpdated, "Last updated date should not be null");
        }
    }


    /**
     * US28
     ****/

    @Given("Query is prepared to analyze public and waitlisted webinars in the webinars table")
    public void query_is_prepared_to_analyze_public_and_waitlisted_webinars_in_the_webinars_table() throws SQLException {
        query = getUs28webinarsTeacher();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the webinar information Results are obtained that the teacher with id {int} had {int} awaiters.")
    public void verify_the_webinar_information_results_are_obtained(Integer id, Integer wListsCount) throws SQLException {
        teacher_ids = new HashMap<>();
        wLists = new HashMap<>();
        try {
            while (resultSet.next()) {
                teacher_ids.put(resultSet.getInt("id"), resultSet.getString("full_name"));
                wLists.put(resultSet.getString("full_name"), resultSet.getInt("waitlist_count"));
            }

            if (!teacher_ids.isEmpty()) {
                // Assert that the provided id exists in teacher_ids map
                assert teacher_ids.containsKey(id) : "ID not found in teacher_ids map";

                // Iterate through teacher_ids map and print entries
                for (Map.Entry<Integer, String> entry : teacher_ids.entrySet()) {
                    int teacherId = entry.getKey();
                    String fullName = entry.getValue();

                    // Print statements
                    System.out.println("Teacher ID: " + teacherId + ", Full Name: " + fullName);
                }

                // Iterate through wLists map and perform assertions and print statements
                for (Map.Entry<String, Integer> entry : wLists.entrySet()) {
                    String fullName = entry.getKey();
                    int waitlistCount = entry.getValue();

                    // Perform assertions
                    assert waitlistCount >= 0 : "Waitlist count should be non-negative";
                    assert waitlistCount == wListsCount : "Waitlist count does not match expected value";

                    // Print statements
                    System.out.println("Full Name: " + fullName + ", Waitlist Count: " + waitlistCount);
                }
            } else {
                assertFalse("ResultSet is empty", resultSet.next());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void assertFalse(String message, boolean condition) {
        if (condition) {
            throw new AssertionError(message);
        }

    }

    /**
     * US29
     */
    @Given("Query is prepared total number of webinars, average price and earliest start date for each group")
    public void query_is_prepared_total_number_of_webinars_average_price_and_earliest_start_date_for_each_group() throws SQLException {
        query = getUs29totalWebinars();
        resultSet = getStatement().executeQuery(query);

    }

    @Given("Verify the webinar information Results are obtained")
    public void verify_the_webinar_information_results_are_obtained() throws SQLException {
        while (resultSet.next()) {
            String certificate = resultSet.getString("certificate");
            boolean downloadable = resultSet.getBoolean("downloadable");
            int totalWebinars = resultSet.getInt("total_webinars");
            double avgPrice = resultSet.getDouble("avg_price");
            long earliestStartDate = resultSet.getLong("earliest_start_date");

            //sonucları listeleme
            System.out.printf("Certificate: %s, Downloadable: %b, Total Webinars: %d, Avg Price: %.2f, Earliest Start Date: %s%n",
                    certificate, downloadable, totalWebinars, avgPrice, earliestStartDate);
            // Basit assert kontrolleri
            assertNotNull(certificate, "Certificate should not be null");
            assertTrue("Total webinars should be greater than 0", totalWebinars > 0);
            assertTrue("Average price should be non-negative", avgPrice >= 0);
            assertNotNull("Earliest start date should not be null", earliestStartDate);
        }
    }

    /**
     * US30
     */
    @Given("Query is prepared best-selling products")
    public void query_is_prepared_best_selling_products() throws SQLException {
        query = getUs30best_seller_product();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the best-selling products")
    public void verify_the_best_selling_products() throws SQLException {
        salesRecords = new ArrayList<>();
        assertTrue("ResultSet should have at least one row", resultSet.next());
        do {
            int sellerId = resultSet.getInt("seller_id");
            int productId = resultSet.getInt("product_id");
            int totalSales = resultSet.getInt("total_sales");

            salesRecords.add(new SalesRecord(sellerId, productId, totalSales));
            // Assert that the list is not empty
            assertFalse("Sales records should not be empty", salesRecords.isEmpty());

            // Assert total sales for a specific seller-product pair
            SalesRecord specificRecord = findSalesRecord(salesRecords, 1016, 4); // Example IDs
            assertNotNull(specificRecord.toString(), "Specific seller-product record should exist");
            assertEquals(1, specificRecord.getTotalSales());


            // Assert that the records are sorted by total_sales in descending order
            for (int i = 0; i < salesRecords.size() - 1; i++) {
                assertTrue("Sales records should be sorted by total sales in descending order", salesRecords.get(i).getTotalSales() >= salesRecords.get(i + 1).getTotalSales()
                );
            }


            // Sonuçları yazdırma (isteğe bağlı)
            System.out.printf("Seller ID: %d, Product ID: %d, Total Sales: %d%n",
                    sellerId, productId, totalSales);

        } while (resultSet.next());
        // Assert the total number of sales records
        assertEquals(4, salesRecords.size());
    }

    /**
     * US31
     */
    @Given("Query is prepared max-total_selling products")
    public void query_is_prepared_max_total_selling_products() throws SQLException {
        query = getUs31totalsales();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the selling_id and total selling products")
    public void verify_the_selling_id_and_total_selling_products() throws SQLException {
        assertTrue("ResultSet should have at least one row", resultSet.next());

        int topSellerId = resultSet.getInt("seller_id");
        int topTotalSales = resultSet.getInt("total_sales");

        // Sonuçları yazdırma (isteğe bağlı)
        System.out.printf("Top Seller ID: %d, Total Sales: %d%n",
                topSellerId, topTotalSales);

        // Beklenen değerlerle doğrulama
        int expectedTopSellerId = 1015; // Örneğin, beklenen satıcı kimliği
        int expectedTopTotalSales = 2; // Örneğin, beklenen toplam satış

        assertEquals(expectedTopSellerId, topSellerId);
        assertEquals(expectedTopTotalSales, topTotalSales);


    }

    /**
     * US32
     */
    @Given("Query is prepared list the number of times each order status")
    public void query_is_prepared_list_the_number_of_times_each_order_status() throws SQLException {
        query = getUs32statusProductOrders();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the number of times each order status")
    public void verify_the_number_of_times_each_order_status() throws SQLException {
        statusCounts = new HashMap<>();
        assertTrue("ResultSet should have at least one row", resultSet.next());

        do {
            String status = resultSet.getString("status");
            int statusCount = resultSet.getInt("status_count");

            statusCounts.put(status, statusCount);

            // Sonuçları yazdırma (isteğe bağlı)
            System.out.printf("Status: %s, Count: %d%n", status, statusCount);

        } while (resultSet.next());
        assertStatusCounts(statusCounts);
    }

    @Given("Prepare a query that retrieves the full name of the teacher who created the webinar from the users table")
    public void prepare_a_query_that_retrieves_the_full_name_of_the_teacher_who_created_the_webinar_from_the_users_table() throws SQLException {
        query = getUs33webinarsMaxCapacity();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the full_name users table")
    public void verify_the_full_name_users_table() throws SQLException {
        webinars = new ArrayList<>();
        assertTrue("ResultSet should have at least one row", resultSet.next());
        do {
            int id = resultSet.getInt("id");
            int teacherId = resultSet.getInt("teacher_id");
            int capacity = resultSet.getInt("capacity");
            String slug = resultSet.getString("slug");
            String teacherName = resultSet.getString("teacher_name");

            webinars.add(new Webinars(id, teacherId, capacity, slug, teacherName));

            // Sonuçları yazdırma (isteğe bağlı)
            System.out.printf("Webinar ID: %d, Teacher ID: %d, Capacity: %d, Slug: %s, Teacher Name: %s%n",
                    id, teacherId, capacity, slug, teacherName);

        } while (resultSet.next());

        // Perform additional assertions on the collected data
        assertMaxCapacityWebinars(webinars, "Asher Morgan");
    }

    /**
     * US34
     */
    @Given("Prepare a query to product with a minimum rates score")
    public void prepare_a_query_to_product_with_a_minimum_rates_score() throws SQLException {
        query = getUs34_minimum_rates_product();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the product with a minimum rates score lower than {int}")
    public void verify_the_product_with_a_minimum_rates_score_lower_than(Integer int1) throws SQLException {
        List<ProductReviewStats> productReviewStatsList = new ArrayList<>();
        assertTrue(resultSet.next());
        do {
            int productId = resultSet.getInt("product_id");
            int totalReviews = resultSet.getInt("total_reviews");
            double averageRating = resultSet.getDouble("average_rating");
            int maxRating = resultSet.getInt("max_rating");
            int minRating = resultSet.getInt("min_rating");

            productReviewStatsList.add(new ProductReviewStats(productId, totalReviews, averageRating, maxRating, minRating));

            // Sonuçları yazdırma (isteğe bağlı)
            System.out.printf("Product ID: %d, Total Reviews: %d, Average Rating: %.2f, Max Rating: %d, Min Rating: %d%n",
                    productId, totalReviews, averageRating, maxRating, minRating);

        } while (resultSet.next());
        // Perform additional assertions on the collected data
        assertProductReviewStats(productReviewStatsList);
    }

    /****    US35 **/

    @Given("Prepare a query to to support transactions status")
    public void prepare_a_query_to_to_support_transactions_status() throws SQLException {
        query = getUs35support_transactions_status();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verify the status support count {int} {int}")
    public void verify_the_status_support_count(int expectedSupportCountOpen, int expectedSupportCountClose) throws SQLException {
        while (resultSet.next()) {
            String status = resultSet.getString("status");
            int actualSupportCount = resultSet.getInt("support_count");

            // Print result (optional)
            System.out.printf("Status: %s, Support Count: %d%n", status, actualSupportCount);
            // Switch statement to handle different statuses
            switch (status) {
                case "open":
                    assertEquals(expectedSupportCountOpen, actualSupportCount);
                    break;
                case "close":
                    assertEquals(expectedSupportCountClose, actualSupportCount
                    );
                    break;
                default:
                    fail("Unexpected status: " + status);
            }

        }
    }

    /****    US36 **/

    @Given("Prepare a query to products table")
    public void prepare_a_query_to_products_table() throws SQLException {
        query = getUs36stokList();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("inventory list obtained")
    public void inventory_list_obtained() throws SQLException {
        assertFalse("Resultset is empty", resultSet.next());
    }

    /**
     * US37
     **/
    @Given("Prepare a query to open tickets in the Tickets table")
    public void prepare_a_query_to_open_tickets_in_the_tickets_table() throws SQLException {
        query = getUs37ticketOpen();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("If the list is empty, verify that it is empty")
    public void ıf_the_list_is_empty_verify_that_it_is_empty() throws SQLException {
        List<Ticket> fullCapacityTickets = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int capacity = resultSet.getInt("capacity");
            int participantCount = resultSet.getInt("participant_count");

            if (participantCount >= capacity) {
                fullCapacityTickets.add(new Ticket(id, capacity, participantCount));
            }

            // Print result (optional)
            System.out.printf("Ticket ID: %d, Capacity: %d, Participant Count: %d%n",
                    id, capacity, participantCount);
        }

        // Assert if the list is empty
        if (fullCapacityTickets.isEmpty()) {
            System.out.println("No tickets have reached full capacity.");
            assertTrue("List should be empty", fullCapacityTickets.isEmpty());
        } else {
            // If the list is not empty, assert the tickets with full capacity
            for (Ticket ticket : fullCapacityTickets) {
                assertEquals(ticket.getCapacity(), ticket.getParticipantCount()
                );
            }
        }
    }

    /**
     * US38
     **/

    @Given("Prepare a query belonging to a specific webinar_id by session order")
    public void prepare_a_query_belonging_to_a_specific_webinar_id_by_session_order() throws SQLException {
        query = getUs38webinarIdSessionOrder();
        preparedStatement = getPraperedStatement(query);
        preparedStatement.setInt(1, 1998);
    }

    @Given("List sessions belonging to a specific webinar_id")
    public void list_sessions_belonging_to_a_specific_webinar_id() throws SQLException {
       resultSet= preparedStatement.executeQuery();
        List<Session> sessions = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int fetchedWebinarId = resultSet.getInt("webinar_id");
            long dateLong = resultSet.getLong("date");
            Timestamp date = new Timestamp(dateLong * 1000); // Unix epoch time to Timestamp
            int duration = resultSet.getInt("duration");
            int order = resultSet.getInt("order");
            String status = resultSet.getString("status");
            long createdAtLong = resultSet.getLong("created_at");
            Timestamp createdAt = new Timestamp(createdAtLong * 1000);
            long updatedAtLong = resultSet.getLong("updated_at");
            Timestamp updatedAt = new Timestamp(updatedAtLong * 1000);

            sessions.add(new Session(id, fetchedWebinarId, date, duration, order, status, createdAt, updatedAt));

            // Print result (optional)
            System.out.printf("Session ID: %d, Order: %d, Status: %s%n", id, order, status);
        }


        // Assert if the list is not empty
        assertFalse("Sessions list should not be empty", sessions.isEmpty());

        // Assert the order of sessions
        for (int i = 1; i < sessions.size(); i++) {
            assertTrue("Sessions should be in ascending order", sessions.get(i - 1).getOrder() <= sessions.get(i).getOrder());
        }
    }
        /** US39   **/
        @Given("Prepares query that calculates Total Sales and Total Commission according to Product Orders")
        public void prepares_query_that_calculates_total_sales_and_total_commission_according_to_product_orders() throws SQLException {
           query=getUs39();
           resultSet=getStatement().executeQuery(query);
        }
    @Given("Total Sales and Total Commission values are listed according to Product Orders")
    public void total_sales_and_total_commission_values_are_listed_according_to_product_orders() throws SQLException {
        List<Sales> salesList = new ArrayList<>();
        while (resultSet.next()) {
            int productOrderId = resultSet.getInt("product_order_id");
            double totalSales = resultSet.getDouble("total_sales");
            double totalCommission = resultSet.getDouble("total_commission");

            salesList.add(new Sales(productOrderId, totalSales, totalCommission));

            // Print result (optional)
            System.out.printf("Product Order ID: %d, Total Sales: %.2f, Total Commission: %.2f%n",
                    productOrderId, totalSales, totalCommission);
        }


    // Assert if the list is not empty
    assertFalse( "Sales list should not be empty",salesList.isEmpty());

    // Example assertion for the first item (you can customize this as needed)
    Sales firstSale = salesList.get(0);
    assertTrue("Product Order ID should be greater than zero",firstSale.getProductOrderId() > 0 );
    assertTrue("Total sales should be greater than zero",firstSale.getTotalSales() > 0);
    assertTrue("Total commission should be zero or greater",firstSale.getTotalCommission() >= 0);
    }

    /** US 40  **/
    @Given("Prepare a query that lists the sales generated in the last {int} days in descending order")
    public void prepare_a_query_that_lists_the_sales_generated_in_the_last_days_in_descending_order(Integer int1) throws SQLException {
        query=getUs40salesInterval_15days();
        resultSet=getStatement().executeQuery(query);
    }
    @Given("Sales are listed and results are verified")
    public void sales_are_listed_and_results_are_verified() throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int productOrderId = resultSet.getInt("product_order_id");
            double totalAmount = resultSet.getDouble("total_amount");
            double commission = resultSet.getDouble("commission");
            Timestamp createdAt = resultSet.getTimestamp("created_at");
            Timestamp formattedCreatedAt = resultSet.getTimestamp("formatted_created_at");

            // Print result (optional)
            System.out.printf("ID: %d, Product Order ID: %d, Total Amount: %.2f, Commission: %.2f, Created At: %s%n",
                    id, productOrderId, totalAmount, commission, formattedCreatedAt.toString());
        }
    }
}


























