@DB
Feature: DB

  Background: Database connection
    * Database connection established

  @US1  # Calculate the total meeting time in minutes for the user email=oske.work@gmail.com in the users table.
  Scenario: Calculate the total meeting time in minutes for the user

    * Calculate the total meeting time in minutes for the user "email" in the  "users" table.
    * Verify the information Results are obtained.
    * Database connection is closed

  @US2  #List the total number and ratio of Active (Open),  pending and finished  meetings in the reserve_meetings table
  Scenario Outline: List the total number and rate of meetings in the reserve_meetings table by status
    Given Preparation of query grouping according to statuses in the reserved meeting table
    Then The status should be "<status>"
    And The total_meetings should be <total_meetings>
    And The percentage should be <percentage>

    Examples:
      | status   | total_meetings | percentage |
      | open     | 1              | 0.9091     |
      | pending  | 84             | 76.3636    |
      | finished | 25             | 22.7273    |


  @US3  # Combine Product Orders and Gifts tables to list gift information for product orders

  Scenario: list gift information for product orders
    * prepare query to  into the  table
    * Verify results are obtained in the table
    * Database connection is closed

  @US4 #In the quizzes table, write the query that shows the limited questions and the number of questions and verify its id.
  Scenario: In the Quizzes table, a query is prepared showing the limited questions and the number of questions.
    *  In the Quizzes table, a query is prepared showing the limited questions and the number of questions.
    *  Verifies id 101 the returned result
    *  Database connection is closed

  @US05  #merge the questions in the quizzes_questions table with the quizzes table to list the quizzes with a pass_mark of 100 in the quizzes table and validate the quiz_ids in the list
  Scenario Outline: In the quizzes_questions table, validate quiz_ids with a pass_mark value of 100
    * In the quizzes_questions table, prepare a query that returns quiz_ids with a pass_mark value
    *  Verifies <expectedQuizId> the returned result
    *  Database connection is closed
    Examples:
      | expectedQuizId |
      | 72             |
      | 72             |
      | 72             |

  @US06  #In the supports table, add 2 data containing (user_id, webinar_id, department_id, title, created_at, updated_at) values at the same time.
  Scenario: Insert 2 data entry into the supports table
    * prepare query to insert data entry into the supports table
    * Verify that 2 added to the table
    * Database connection is closed

  @US07 # Verify that the id of the data with start_date date 1728570600 in the webinars table is 1996
  Scenario:authenticate the data with start_date
    * prepare query  of the data with start_date in the webinars table
    * Verifies id 1996 the returned result
    * Database connection is closed


  @US08 # Write the query that freezes the users who have financial approval and installment approval in the users table
        # and verify the freezing list.
  Scenario: List users with financial approval and installment approval in the users table
    *  A query is prepared for the list of users with financial approval and installment approval in the Users table.
    * Verify list of users with financial and installment approvals
    * Database connection is closed

  @US09 # Write the query that freezes the meetings with a total payment of more than 5000
  # when grouped by meeting_ids in the reserve_meetings table.
  # And verify the number of meetings from the list.
  Scenario:
    * Prepares a query that returns meetings with total payments more than 5000
    * Verifies 1 row the returned result
    * Database connection is closed

  @US10  #insert a data to the users_zoom_api table and update the account_id value of the added data.
         # Verify that the update operation has occurred.
  Scenario: insert a data to the users_zoom_api table and updateof the added data test
    * prepare query to insert data entry into the users_zoom_api table
    * Verify the data information Result is obtained.
    * Updated account_id
    * Verify the data information Result is obtained.
    * Database connection is closed

  @US11 #Verify that the data can be deleted from the same table after entering data containing the values
# (user_id, mobile, email, code, verified_at, expired_at, created_at) in the verifications table and verifying that it has been added.
  Scenario: Test to verify that data can be deleted from the  verifications table
    * prepare query to insert data entry into the  verifications table
    * Verify the data information Result is obtained.
    * Deleted data
    * Verify the data information Result is obtained.
    * Database connection is closed

  @US12
  # List the number of people classified by roles in the become_instructors table.
  Scenario Outline:List the number of people classified by roles
    * prepare query List the number of people classified by roles
    * Verify the list results "<role>" <record_count> obtained
    * Database connection is closed
    Examples:
      | role         | record_count |
      | teacher      | 23           |
      | organization | 4            |

  @US13
  Scenario:


  @US14
  Scenario:


  @US15 #Write the query that lists the products that are out of stock in the products table.
  Scenario:List out-of-stock products in the products table
    * prepare query List the products in the products table
    * Verify the list results "slug" and 0 inventory obtained
    * Database connection is closed

  @US16 #During the processing of a specific order (product_orders),
    # verify that the inventory of the products of this order is updated from the products table.
  Scenario: verify that the inventory of the products of this order is updated
    * prepare updated query from the products table
    * Verify that 1 added to the table
    * Database connection is closed

  @US17 #In the orders table, calculate the amount of orders placed on a specific date,
    # with the status 'paid' and using the 'credit' method.
  Scenario: In the orders table, calculate the amount of orders placed on a specific date
    * prepare query with the status 'paid' and using the 'credit' method
    * Verify total_credit_card_sales 5056.45 obtained
    * Database connection is closed

  @US18
  Scenario: List High Priority Physical and Virtual Product Discounts.
    * prepare query Physical and Virtual Product Discounts
    * Verify the list results 2 code obtained
    * Database connection is closed

  @US19
  Scenario: Add 5 data to failed_jobs table at the same time.
    * Add 5 data to failed_jobs table at the same time
    * 5 Enter the data in bulk. Check that it is added to the table.
    * Database connection is closed

  @US20 #Delete 1 data by entering uuid information in failed_jobs table.
  Scenario: Deleted data from failed_jobs table
    * Add 1 data to failed_jobs table at the same time
    * 1 Enter the data in bulk. Check that it is added to the table.
    * Deleted query prepare
    * Verify the data information Result is obtained.
    * Database connection is closed

  @US21 #Verify the total number of users by calculating the ban status, ban start and end dates,
                # as well as the total number of these users and the percentage of users in active ban status.
  Scenario:ban testing of users in the users table
    * prepare query of users in active ban status
    * Verify the ban information Results are obtained.
    * Database connection is closed

  @US22 #List the average sign-up bonus amount of users in each group and the total number of users,
    # grouping users according to their financial approval, installment approval and sign-up bonus activity status
  Scenario:Test the average signup bonus amount of users in each group and the total number of users
    * a list of the average signup bonus amount and the total number of users is prepared
    * Verify list average sign-up bonus amount of users in each group
    * Database connection is closed

  @US23 #Group users according to their language and currency preferences,
    # calculate and list the total number of users in each language and currency combination and the total commission amount of each group.
  Scenario:Group users according to their language and currency preferences
    * a list of Group users according to their language and currency preferences prepared
    * Verify calculate and list of users in each language and currency combination and the total commission amount of each group
    * Database connection is closed

  @US24 #list the average price per webinar, total capacity and last updated date, grouped by teachers and categories of webinars
  Scenario:Check average price, total capacity, and last updated date for webinars grouped by teachers and categories
    * execute query to fetch webinar statistics
    * Verify following statistics
    * Database connection is closed

  @US25 #Analyze the public and waitlisted webinars in the webinars table
    # and list the teacher, start date, price and capacity of each webinar. Verify the number of users on the waiting list.
  Scenario:Analyze public and waitlisted webinars in the webinars table
    * Query is prepared to analyze public and waitlisted webinars in the webinars table
    * Verify the webinar information Results are obtained that the teacher with id 2009 had 7 awaiters.
    * Database connection is closed

  @US26 #list the total number of webinars,
        # average price and earliest start date for each group, grouped according to whether the webinars offer certificates and downloadable content
  Scenario:total number of webinars, average price and earliest start date for each group, grouping by whether the webinars offer certificates and downloadable content
    * Query is prepared total number of webinars, average price and earliest start date for each group
    * Verify the webinar information Results are obtained
    * Database connection is closed

  @US27 #list the best-selling products of each vendor and the total sales of these products.
  Scenario: best-selling products test
    * Query is prepared best-selling products
    * Verify the best-selling products
    * Database connection is closed

  @US28 # calculate the total number of sales for each seller, group them by seller ID (seller_id) and verify the
    # ID of the seller with the highest sales and the total number of sales.
  Scenario: max-total_selling sellingid test
    * Query is prepared max-total_selling products
    * Verify the selling_id and total selling products
    * Database connection is closed

  @US29 # list the number of times each order status occurred
  Scenario: list the number of times each order status occurred
    * Query is prepared list the number of times each order status
    * Verify the number of times each order status
    * Database connection is closed

  @US30 # Verify the information of the webinar with the highest capacity from the webinars table
        # and the full name of the teacher who created this webinar by retrieving it from the users table.
  Scenario: Test to validate webinar information with a table of users
    * Prepare a query that retrieves the full name of the teacher who created the webinar from the users table
    * Verify the full_name users table
    * Database connection is closed

  @US31 #Calculate the total number of evaluations, the average evaluation score
            # and the highest/lowest scores for a given product and validate the product with a minimum rates score lower than 3.
  Scenario: Test to product with a minimum rates score
    * Prepare a query to product with a minimum rates score
    * Verify the product with a minimum rates score lower than 3
    * Database connection is closed

  @US32 # list support transactions showing the number and status of tickets for each department, grouped by
    # department titles and department IDs
  Scenario Outline: Test to support transactions status support count
    * Prepare a query to to support transactions status
    * Verify the status support count <expectedSupportCountOpen> <expectedSupportCountClose>
    * Database connection is closed
    Examples:
      | expectedSupportCountOpen | expectedSupportCountClose |
      | 17                       | 4                         |

  @US33 # List the products that have more than zero stock, are active and have been created in the last 30 days.
    # (If there is no product in the table, confirm that there is no product)
  Scenario: Test to inventory list from latest 30 days
    * Prepare a query to products table
    * inventory list obtained
    * Database connection is closed

  @US34 #Check the number of attendees and quota for the tickets open in the tickets table and list the ones whose capacity is full.
        # If the list is empty, verify that it is empty. If there is a ticket with full capacity in the list, close the ticket.
  Scenario:Check the number of participants and the quota for open tickets in the Tickets table
    * Prepare a query to open tickets in the Tickets table
    * If the list is empty, verify that it is empty
    * Database connection is closed


  @US35 #List the sessions belonging to a specific webinar_id, sorted by session order (order field).
  Scenario:List sessions belonging to a specific webinar_id by session order (queue field)
    * Prepare a query belonging to a specific webinar_id by session order
    * List sessions belonging to a specific webinar_id
    * Database connection is closed

  @US36 # Calculate and list Total Sales by Product Orders and Total Commission for products whose product_order_id value is not null.
  Scenario: Calculate Total Sales and Total Commission by Product Orders for products with non-null product_order_id value
    * Prepares query that calculates Total Sales and Total Commission according to Product Orders
    * Total Sales and Total Commission values are listed according to Product Orders
    * Database connection is closed

  @US37 # 
  Scenario: List sales created in the last 15 days in descending order by creation date
    * Prepare a query that lists the sales generated in the last 15 days in descending order
    * Sales are listed and results are verified
    * Database connection is closed

