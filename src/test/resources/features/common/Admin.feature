@Admin @Smoke @Sprint31
Feature: Admin feature

  Scenario: Admin
    Given user open page 'Admin'
    And user is on the 'Admin Login' page
    Then user clicks on the 'Sing In' from 'Admin Login' page
#    And user verify 'You have successfully logged out.' message


  Scenario: Admin component
    Given user open page 'Admin'
    And user is on the 'Admin Login' page
    And user clicks submit
#    Then user clicks on the 'Sing In' from 'Admin Login' page
