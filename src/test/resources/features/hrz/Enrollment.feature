@HRZEnrollment @Smoke
Feature: HRZ Enrollment feature

  Scenario Outline: HRZ Enrollment
    Given user open page 'Home'
    And user is on the 'Home' page
    And user clicks on the 'Enroll Now' from 'Home' page
    And user is on the 'Program' page
    And user select '<school>' and '<degree>'

    Examples:
      | school | degree |