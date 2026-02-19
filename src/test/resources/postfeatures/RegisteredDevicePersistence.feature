Feature: Persistence of Registered Device on BWP Web Dashboard after Device Reboot
  Background:
    Given I am already logged into Biamp Workplace Cloud
    And On the Device Main Page

  Scenario: Persistence of Registered Device on BWP Web Dashboard after Device Reboot
    Then Reboot the device from the device main page successfully
    And Message should appear indicating that the device reboot command is sent successfully


