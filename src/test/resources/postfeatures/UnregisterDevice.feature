Feature: Device Visibility on BWP Web Dashboard after Registration

  Background:
    Given I am already logged into Biamp Workplace Cloud
    And On the Device Main Page

Scenario: Verify user is able to unregister the device from BWP Web Dashboard

And I click on the Unregister button
Then I should see a confirmation message indicating that the device has been unregistered successfully
And I should no longer see the device in the list of registered devices on the BWP Web Dashboard