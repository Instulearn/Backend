@DB
Feature: US_001, US_002, US_003

  Background: Database connection
    * Database connection established.

  @US1  # Calculate the total meeting time in minutes for the user email=oske.work@gmail.com in the users table.
  Scenario: Calculate the total meeting time in minutes for the user

    * Calculate the total meeting time in minutes for the user "email" in the  "users" table
    * Verify the information Results are obtained
    * Database connection is closed.

  @US2  #List the total number and ratio of Active (Open),  pending and finished  meetings in the reserve_meetings table
  Scenario Outline: List the total number and rate of meetings in the reserve_meetings table by status
    * Preparation of query grouping according to statuses in the reserved meeting table.
    * The status should be "<status>".
    * The total_meetings should be <total_meetings>.
    * The percentage should be <percentage>.
    * Database connection is closed.

    Examples:
      | status   | total_meetings | percentage |
      | open     | 3              | 2.2727     |
      | pending  | 97             | 73.4848    |
      | finished | 32             | 24.2424    |


  @US3  # Combine Product Orders and Gifts tables to list gift information for product orders

  Scenario: list gift information for product orders
    * prepare query into the table.
    * Verify results are obtained in the table.
    * Database connection is closed.