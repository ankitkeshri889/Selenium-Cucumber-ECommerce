Feature: Login

  @Sanity
  Scenario: Successful Login with valid Credentials
    Given User Launches Chrome Browser
    When Users open the URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And User clicks on Login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User Clicks on Log out Link
    Then Page Title should be "Your store. Login"
    And Close the Browser

  # Data-Driven Test for multiple login credentials
  
  @Regression
  Scenario Outline: Login Data Driven
    Given User Launches Chrome Browser
    When Users open the URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enters email as "<email>" and password as "<password>"
    And User clicks on Login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User Clicks on Log out Link
    Then Page Title should be "Your store. Login"
    And Close the Browser

    Examples: 
      | email                  | password  |
      | admin@yourstore.com    | admin     |
      | admin1@yourstore.com   | admin123  |
      | admin123@yourstore.com | random123 |
