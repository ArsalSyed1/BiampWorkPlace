Feature: Firmware Upgrade Functionality
  Background:
    Given I am already logged into Biamp Workplace Cloud
    And On the Device Main Page

Scenario: Verify Firmware Update Functionality
And I click on the Firmware Update button
Then I should see a confirmation message indicating that the firmware update process has started
And I should receive a notification upon successful completion of the firmware update process

Scenario: Device Info shows Updated Firmware Version and Online Status after Firmware Update
Then I should see the updated firmware version displayed in the device information section
And I should see the device status as Online in the device information section