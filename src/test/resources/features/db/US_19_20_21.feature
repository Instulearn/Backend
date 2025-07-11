Feature: US_019, US_020, US_021

  @US_019_LE
  Scenario Outline: Add 5 data to failed_jobs table at the same time.

    * Database connection established for LE's Scenario.
    * Add 5 data to failed_jobs table's "<uuid>","<connection>","<queue>","<payload>","<exception>" columns at the same time.
    * Check that the values are added into the table.
    * Database connection is closed for LE's Scenario.

    Examples:
      | uuid                | connection         | queue        | payload        | exception        |
      | deneme_mySQL_uuid_1 | connection_deneme1 | queue_mySQL1 | payload_mySQL1 | exception_mySQL1 |
      | deneme_mySQL_uuid_2 | connection_deneme2 | queue_mySQL2 | payload_mySQL2 | exception_mySQL2 |
      | deneme_mySQL_uuid_3 | connection_deneme3 | queue_mySQL3 | payload_mySQL3 | exception_mySQL3 |
      | deneme_mySQL_uuid_4 | connection_deneme4 | queue_mySQL4 | payload_mySQL4 | exception_mySQL4 |
      | deneme_mySQL_uuid_5 | connection_deneme5 | queue_mySQL5 | payload_mySQL5 | exception_mySQL5 |


  @US_020_LE
  Scenario Outline: Delete 1 data by entering uuid information in failed_jobs table.

    * Database connection established for LE's Scenario.
    * Add 1 data to failed_jobs table's "<uuid>","<connection>","<queue>","<payload>","<exception>" columns at the same time for US.
    * Check that the values are added into the table.
    * Deleted query prepared for LE's Scenario with "<uuid>".
    * Verify the data information Result is obtained for LE's scenario with "<uuid>".
    * Database connection is closed for LE's Scenario.

      Examples:
        | uuid                      | connection                | queue               | payload               | exception               |
        | deneme1_mySQL_uuid_US20_1 | connection_deneme1_US20_1 | queue_mySQL1_US20_1 | payload_mySQL1_US20_1 | exception_mySQL1_US20_1 |


  @US_021_LE
  Scenario: Verify the total number of users by calculating the ban status, ban start and end dates, as well as
  the total number of these users and the percentage of users in active ban status.
    * Database connection established for LE's Scenario.
    * Prepare query of users in active ban status for LE's Scenario.
    * Verify the ban information Results are obtained for LE's Scenario.
    * Database connection is closed for LE's Scenario.
