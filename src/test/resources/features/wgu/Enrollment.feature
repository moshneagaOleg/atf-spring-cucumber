@WGUEnrollment @Smoke
Feature: WGU Enrollment feature

  Scenario Outline: WGU Enrollment
    Given user open page 'Home'
    And user is on the 'Home' page
    And user clicks on the 'Enroll Now' from 'Home' page
    And user is on the 'Start' page
    And user clicks on the 'Continue' from 'Start' page
    And user is on the 'Account' page
    And user select '<PROGRAM_AREA>'
    And user clicks on the 'Continue' from 'Account' page
    And user is on the 'Payment' page
    And user select '<PAYMENT_TERM>' and '<PAYMENT_METHOD>'
    And user clicks on the 'Continue' from 'Payment' page
    And user is on the 'Review' page
    And user clicks on the 'Continue' from 'Review' page
    And user is on the 'Profile' page
    And user clicks on the 'Continue' from 'Profile' page
    And user is on the 'Courses' page
    And user clicks on the 'Continue' from 'Courses' page
    And user is on the 'Success' page
    And user clicks on the 'Continue' from 'Success' page
    And user is on the 'Dashboard' page
    Then user is verify required courses from dashboard

    Examples:
      | PROGRAM_AREA | PAYMENT_TERM | PAYMENT_METHOD |
      | blaBla       | blaBla       | blaBla         |