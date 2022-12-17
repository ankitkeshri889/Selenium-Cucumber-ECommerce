Feature: Functionality Related to a Customer
  		Adding a new Customer
  		Into the Database

  Background: Below are the common steps for each Scenario
    Given User Launches Chrome Browser
    When Users open the URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And User clicks on Login button
    Then User can View Dashboard

  
  Scenario: Adding a new Customer
    When User clicks on Customer menu
    And Clicks on Customer menu item
    And Clicks on Add new Button
    Then User can view Add New customer page
    When User enters the Customer details
    And Clicks on Save button
    Then User should be able to get confirmation message "The new customer has been added successfully"
    And Close the browser

  @Regression
  Scenario: Searching the created Customer by emailID
    When User clicks on Customer menu
    And Clicks on Customer menu item
    And User enter the email of the Customer
    And Clicks on Search button
    Then User should be able to see the Email in the Table
    And Close the browser

  @Regression
  Scenario: Searching the created Customer by FirstName and LastName
    When User clicks on Customer menu
    And Clicks on Customer menu item
    And User enter the First Name of the Customer
    And User enter the Last Name of the Customer
    And Clicks on Search button
    Then User should be able to see the Name in the Table
    And Close the browser
