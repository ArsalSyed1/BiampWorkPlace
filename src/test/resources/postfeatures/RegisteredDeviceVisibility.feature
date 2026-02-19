Feature: Device Visibility on BWP Web Dashboard after Registration

  Background:
    Given I am already logged into Biamp Workplace Cloud
    When I navigate to the Devices tab

  Scenario Outline: Registered Device Visibility on BWP Web Dashboard
    Then A list of registered devices should be displayed
    And search the device with ID "<deviceSr>" among the registered devices list
    And if the device is present with ID "<deviceSr>", pass the test
    But if the device is not present with ID "<deviceSr>", fail the test
    And if the device is present with ID "<deviceSr>", but count is more than 1, then fail the test
    Examples:
      | deviceSr |
      | devicesr |