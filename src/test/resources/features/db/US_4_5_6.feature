

Feature: US_004, US_005, US_006

  Background: Database connection
    * Database connection established..

  @US4 #In the quizzes table, write the query that shows the limited questions and the number of questions and verify its id.
  Scenario: In the quizzes table, a query is prepared showing the limited questions and the number of questions.
    *  In the Quizzes table, a query is prepared showing the limited questions and the number of questionss.
    *  Verifies id 35 the returned resultt
    *  Database connection is closed..

@US05  #merge the questions in the quizzes_questions table with the quizzes table to list the quizzes with a pass_mark of 100 in the quizzes table and validate the quiz_ids in the list
Scenario Outline: In the quizzes_questions table, validate quiz_ids with a pass_mark value of 100
* In the quizzes_questions table, prepare a query that returns quiz_ids with a pass_mark valuee.
*  Verifies <expectedQuizId> the returned resultt.
*  Database connection is closed..
Examples:
| expectedQuizId |
| 74            |
| 76             |
| 78             |

@US06  #In the supports table, add 2 data containing (user_id, webinar_id, department_id, title, created_at, updated_at) values at the same time.
Scenario: Insert 2 data entry into the supports table
* prepare query to insert data entry into the supports table.
* Verify that 2 added to the table.
* Database connection is closed..