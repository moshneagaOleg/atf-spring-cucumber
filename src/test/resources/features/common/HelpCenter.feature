@HelpCenter @Smoke
Feature: Help Center links

  Scenario Outline: Help Center links
    Given user open page 'Help Center Welcome'
    When user clicks on the '<Page>' from 'Help Center Welcome' page
    And user is on the '<Page>' page
    Then user verify page title from '<Page>' page

    Examples:
      | Page                       |
#      | Support Requests           |
      | Academics                  |
      | Help Center Proctoring     |
      | eTextbooks                 |
      | Help Center Tutoring       |
      | Billing Account Management |
      | Credit Transfer            |
      | System Check               |