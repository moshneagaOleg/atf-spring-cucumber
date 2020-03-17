@Login
Feature: Login & LogOut
Business Need: Verify that user can sign-in and sign-out to his account.

  @Negative
  Scenario: Login negative
    Given user opens page 'Home'
    And user logs in with invalid credentials
    And user is on the 'Sing In' page
    Then user verifies error message

  @Positive
  Scenario: Login positive
    Given user opens page 'Home'
    And user logs in with valid credentials
    And user finds random books
    And user is on the 'Search' page
    And user marks 3 first books
    And user logs out
    Then user is on the 'Sign Out' page
