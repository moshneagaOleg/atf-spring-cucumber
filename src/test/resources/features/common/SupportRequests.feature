@Enroll @Smoke
Feature: Support Request

  Scenario Outline: Support Request
    Given user open page 'Login'
    When user login on the page
    And user is on the 'Dashboard' page
    And user open page 'Support Requests'
    And user is on the 'Support Requests' page
    Then user complete '<positive>' request

    Examples:
      | positive |
      | true     |
      | false    |