Feature: BWP Device Unregister

  Background:
    Given I am logged into Biamp Workplace Cloud

  Scenario Outline: Verify Device Presence on Biamp Workplace Cloud
    When I navigate to the Devices section
    Then I should see a list of registered devices
    And check if the device with ID "<deviceSr>" is present in the list or not
    And if the device is present with ID "<deviceSr>", then unregister it else do nothing
    And Trigger PAD Script
    Examples:
      | deviceSr |
      | devicesr|



  #Scenario: Verify signout functionality
   # When I click on the sign out CTA
    #Then I should be signed out successfully



