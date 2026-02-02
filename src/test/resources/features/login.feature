Feature: Biamp Workplace Integration with Project Designer
  Scenario Outline: Verify Device Presence on Biamp Workplace Cloud
    Given I am logged into Biamp Workplace Cloud
    When I navigate to the Devices section
    Then I should see a list of registered devices
    And check if the device with ID "<deviceSr>" is present in the list or not
    Examples:
      | deviceSr |
      |237401742 |