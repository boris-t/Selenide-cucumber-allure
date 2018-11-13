Feature: Google test

  Scenario: Google search page
    Given Navigate to Google page
    When Enter value "Selenide"
    And Click search button
    Then First link have "Selenium"