@HelpCenter @Smoke
Feature: Help Center links

  Scenario Outline: Help Center links
    Given user open page 'Help Center Welcome'
    When user clicks on the '<PageCard>' from 'Help Center Welcome' page
    And user is on the '<PageCard>' page
    Then user verify '<PageTitle>'

    Examples:
      | PageCard                   | PageTitle           |
      | Support Requests           | Registered Students |
      | Academics                  | Academics: Course   |
      | Help Center Proctoring     | Proctoring          |
      | eTextbooks                 | eTextbooks          |
      | Help Center Tutoring       | Tutoring            |
      | Billing Account Management | Billing & Account   |
      | Credit Transfer            | Credit Transfer     |
      | System Check               | System Check        |