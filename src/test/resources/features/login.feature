Feature: Biamp Workplace Integration with Project Designer

  Background:
    Given I am logged into Biamp Workplace Cloud

  Scenario Outline: Verify Device Presence on Biamp Workplace Cloud
    When I navigate to the Devices section
    Then I should see a list of registered devices
    And check if the device with ID "<deviceSr>" is present in the list or not
    Examples:
      | deviceSr |
      |237401742 |


  Scenario: Verify signout functionality
    When I click on the sign out CTA
    Then I should be signed out successfully



