@HRZLogin
Feature: HRZ Login feature

  Scenario: HRZ Login/LogOut
    Given user open page 'Login'
    When user login on the page
    And user is on the 'Dashboard' page
    And user logOut
    And user verify 'You have successfully logged out.' message
    Then user is on the 'Main' page
