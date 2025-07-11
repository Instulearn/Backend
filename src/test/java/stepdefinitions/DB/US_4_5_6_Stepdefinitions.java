package stepdefinitions.DB;

import helperDB.CommonData;
import io.cucumber.java.en.Given;
import manage.US_4_5_6_Manage;

import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertFalse;

import static helperDB.CommonData.*;
import static helperDB.CommonData.quizId;
import static helperDB.JDBC_Structure_Methods.*;
import static helperDB.JDBC_Structure_Methods.resultSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class US_4_5_6_Stepdefinitions extends US_4_5_6_Manage {
    CommonData data = new CommonData();

    public US_4_5_6_Stepdefinitions() throws SQLException {
    }

    @Given("Database connection established..")
    public void database_connection_established() {
        createConnection();
    }

    @Given("Database connection is closed..")
    public void database_connection_is_closed() {
        closeConnection();
    }


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
    @Given("In the Quizzes table, a query is prepared showing the limited questions and the number of questionss.")
    public void ın_the_quizzes_table_a_query_is_prepared_showing_the_limited_questions_and_the_number_of_questions() throws SQLException {
        query = getUs05_quizzes_limited_questions();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verifies id {int} the returned resultt")
    public void verifies_id_the_returned_result(int expectedId) throws SQLException {
        resultSet.next();
        limitedQuestionsId = resultSet.getInt("id");
        assertEquals(expectedId, limitedQuestionsId);

    }

    /**
     * US06
     */
    @Given("In the quizzes_questions table, prepare a query that returns quiz_ids with a pass_mark valuee.")
    public void in_the_quizzes_questions_table_prepare_a_query_that_returns_quiz_ids_with_a_pass_mark_value() throws SQLException {
        query = getUs06_quizzess_questions();
        resultSet = getStatement().executeQuery(query);
    }

    @Given("Verifies {int} the returned resultt.")
    public void verifies_the_returned_result(int expectedQuizId) throws SQLException {
        while (resultSet.next()) {
            quizId = resultSet.getInt("quiz_id");
            assertEquals(expectedQuizId, quizId);
        }
    }

    @Given("prepare query to insert data entry into the supports table.")
    public void prepare_query_to_insert_data_entry_into_the_supports_table() throws SQLException {
        query = "INSERT INTO supports (user_id, webinar_id, department_id, title, created_at, updated_at) VALUES " +
                "(1, 101, 201, 'Title A', NOW(), NOW()), " +
                "(2, 102, 202, 'Title B', NOW(), NOW())";
        getStatement().executeUpdate(query);
    }

    @Given("Verify that {int} added to the table.")
    public void verify_that_added_to_the_table(int expectedCount) throws SQLException {
        query = "SELECT COUNT(*) AS total FROM supports WHERE title IN ('Title A', 'Title B')";
        resultSet = getStatement().executeQuery(query);
        resultSet.next();
        int actualCount = resultSet.getInt("total");
        assertEquals(expectedCount, actualCount);

    }
}