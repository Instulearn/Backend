@dbfurkan

Feature: US_13_14_15 Database Operations

  @US13
  Scenario: US13 - Son 15 gün içinde onaylanmış instructor'ların bilgileri
    Given Database connection established for furkan's scenario
    When I query accepted instructors from last 15 days
    Then I should see accepted instructors list
    And the count should be greater than zero
    And Database connection is closed for furkan's scenario

  @US13
  Scenario Outline: US13 - Farklı gün aralıklarında onaylanmış instructor'lar
    Given Database connection established for furkan's scenario
    When I query accepted instructors from last "<days>" days
    Then I should see accepted instructors for the specified period
    And Database connection is closed for furkan's scenario

    Examples:
      | days |
      | 7    |
      | 15   |
      | 30   |

  @US14
  Scenario: US14 - Become instructors table role validation
    Given Database connection established for furkan's scenario
    When I query become_instructors table for all roles
    Then I should see only teacher and organization roles
    And no invalid roles should exist
    And Database connection is closed for furkan's scenario

  @US14
  Scenario: US14 - Become instructors valid roles verification
    Given Database connection established for furkan's scenario
    When I query become_instructors table for valid roles
    Then I should see teacher and organization roles only
    And Database connection is closed for furkan's scenario

  @US15
  Scenario: US15 - Products out of stock validation
    Given Database connection established for furkan's scenario
    When I query products table for out of stock items
    Then I should see out of stock products list
    And the out of stock count should be greater than or equal to zero
    And Database connection is closed for furkan's scenario

  @US15
  Scenario: US15 - Products stock analysis
    Given Database connection established for furkan's scenario
    When I query products table for stock analysis
    Then I should see stock status breakdown
    And Database connection is closed for furkan's scenario
