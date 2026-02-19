Feature: Get Logs Feature Functionality
  Background:
    Given I am already logged into Biamp Workplace Cloud
    And On the Device Main Page
  Scenario: Verify Get Logs Feature Functionality

    Then I click on the Get Logs button
    And I should see a confirmation message indicating that the logs are being retrieved
    And I should receive the logs successfully within a reasonable time frame