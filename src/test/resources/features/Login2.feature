Feature: Login functionality

  Scenario: Datatable Demo Scenarios
    #Given User navigates to login page
    #When User enters invalid email address into email field
    #And User enters invalid password "1234567890" into password field
    When User enters data from datatable
      #| Name    | age | emailid             |
      #| John    |  38 | john38@gmail.com    |
      #| William |  39 | William39@gmail.com |
      #| Cathy   |  40 | Cathy40@gmail.com   |
      | Name    | age |
      | John    |  38 |
      | William |  39 |
      | Cathy   |  40 |
