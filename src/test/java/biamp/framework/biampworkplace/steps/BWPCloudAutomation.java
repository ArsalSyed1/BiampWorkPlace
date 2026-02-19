package biamp.framework.biampworkplace.steps;

import biamp.framework.biampworkplace.base.baseTest;
import biamp.framework.biampworkplace.pages.devicesPage;
import biamp.framework.biampworkplace.pages.organizationPage;
import biamp.framework.biampworkplace.pages.signInPage;
import biamp.framework.biampworkplace.uicomponent.leftNavBar;
import biamp.framework.biampworkplace.utilities.assertionUtilities;
import biamp.framework.biampworkplace.utilities.configReaderUtilities;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import static biamp.framework.biampworkplace.utilities.configUtilities.*;
import static biamp.framework.biampworkplace.utilities.sessionUtilities.saveStorageState;
import static biamp.framework.biampworkplace.utilities.waitUtilities.waitForLoadState;
import static com.microsoft.playwright.options.LoadState.NETWORKIDLE;

public class BWPCloudAutomation extends baseTest {

    signInPage signInObj;
    organizationPage orgObj;
    devicesPage devObj;
    leftNavBar leftNavBarObj;
    int count;

    // Steps for Background:
    @Given("I am already logged into Biamp Workplace Cloud")
    public void log_into_bwp_cloud() throws InterruptedException {
        // Implement the step to log into Biamp Workplace Cloud
        signInObj = new signInPage(page);
        signInObj.navigateTo(BASE_URL);
        waitForLoadState(page, NETWORKIDLE);

        orgObj = signInObj.signIn(USERNAME, PASSWORD);
        Thread.sleep(2000);
        saveStorageState(context);
    }
    @When("I navigate to the Devices tab")
    public void navigate_to_devices_tab() {
        // Implement the step to navigate to the Devices tab
        orgObj.selectOrganization();
        leftNavBarObj = new leftNavBar(page);
        devObj=leftNavBarObj.navigateToDevices();
    }

    // Steps for Scenario Outline: Device Visibility on BWP Wen Dashboard after Registration
    @Then("A list of registered devices should be displayed")
    public void registered_devices_displayed() throws InterruptedException {
        // Implement the step to verify that a list of registered devices is displayed
        Assertions.assertTrue(devObj.devicesListVisible());
    }
    @And("search the device with ID {string} among the registered devices list")
    public void registeredDeviceSearch(String deviceSri) throws InterruptedException {
        // Implement the step to check if the device with the specified ID is present in the registered devices list
        String deviceSr = configReaderUtilities.get(deviceSri);

        if (deviceSr == null || deviceSr.isEmpty()) {
            throw new RuntimeException("No device SR found for key: " + deviceSr);
        }
        System.out.println("Device SR from config: " + deviceSr);
        count =devObj.searchDevice(deviceSr);
        //assertionUtilities.assertEquals(count, 1);
    }
    @And("if the device is present with ID {string}, pass the test")
    public void registeredDeviceDisplayed(String deviceSri) throws InterruptedException {
        // Implement the step to open the device main page if the device is present, otherwise fail the test
        String deviceSr = configReaderUtilities.get(deviceSri);
        if(count == 1) {
                System.out.println("Device with ID " + deviceSr + " is present.");
                Assertions.assertTrue(true);
            }
    }
    @But("if the device is not present with ID {string}, fail the test")
    public void registeredDeviceNotDisplayed(String deviceSri){
        String deviceSr = configReaderUtilities.get(deviceSri);
        if(count==0) {
            {
                System.out.println("Device with ID " + deviceSr + " is not present. Failing the test...");
                Assertions.fail("Device with ID " + deviceSr + " is not present in the registered devices list.");
            }
        }

    }
    @And("if the device is present with ID {string}, but count is more than 1, then fail the test")
    public void registeredDeviceDuplicate(String deviceSri){
        String deviceSr = configReaderUtilities.get(deviceSri);
        if(count > 1) {
            {
                System.out.println("Device with ID " + deviceSr + " is present more than once. Failing the test...");
                Assertions.fail("Device with ID " + deviceSr + " is present more than once in the registered devices list.");
            }
        }

    }

    // Steps for Scenario: Persistence of Registered Device on BWP Web Dashboard after Device Reboot
    String deviceSr = configReaderUtilities.get("devicesr");
    @Given("On the Device Main Page")
    public void navigateToDeviceMainPage() throws InterruptedException {
        // Implement the step to navigate to the device main page
        orgObj.selectOrganization();
        leftNavBarObj = new leftNavBar(page);
        devObj=leftNavBarObj.navigateToDevices();

        if (deviceSr == null || deviceSr.isEmpty()) {
            throw new RuntimeException("No device SR found for key: " + deviceSr);
        }
        System.out.println("Device SR from config: " + deviceSr);
        count =devObj.searchDevice(deviceSr);
        devObj.openDevicePage(deviceSr);

    }
    @Then("Reboot the device from the device main page successfully")
    public void RebootDevice() {
        // Implement the step to reboot the device from the device main page
        devObj.rebootDevice();
    }
    @And("Message should appear indicating that the device reboot command is sent successfully")
    public void rebootStatus() {
        // Implement the step to verify that a success message is displayed indicating that the device has been rebooted
        String alertMessage = devObj.alertMessage();
        if (alertMessage != null && alertMessage.contains("sent") && !alertMessage.contains("failed")) {
            System.out.println("Success message is displayed: " + alertMessage);
            Assertions.assertTrue(true);
        }
        else if(alertMessage.contains("failed")) {
            System.out.println("Failure message is displayed: " + alertMessage);
            Assertions.fail("Reboot command failed with message: " + alertMessage);
        }
    }

    // Steps for Scenario: Verify Get Logs Feature Functionality
    @Then("I click on the Get Logs button")
    public void GetLogs() {
        // Implement the step to click on the Get Logs button
        devObj.fetchLogs();

    }
    @And("I should see a confirmation message indicating that the logs are being retrieved")
    public void getLogsConfirmation() {
        // Implement the step to verify that a confirmation message is displayed indicating that the logs are being retrieved
        String alertMessage = devObj.alertMessage();
        if (alertMessage != null && alertMessage.contains("sent") && !alertMessage.contains("failed")) {
            System.out.println("Success message is displayed: " + alertMessage);
            Assertions.assertTrue(true);
        }else if(alertMessage.contains("failed")) {
            System.out.println("Failure message is displayed: " + alertMessage);
            Assertions.fail("Fetch Logs command failed with message: " + alertMessage);
        }
    }
    @And("I should receive the logs successfully within a reasonable time frame")
    public void getLogsSuccess() {
        // Implement the step to verify that the logs are received successfully within a reasonable time frame
    devObj.logLinkAvailability();
    }

    // Steps for Scenario: Verify Firmware Update Functionality
    @And("I click on the Firmware Update button")
    public void firmwareUpdate() throws InterruptedException {
        // Implement the step to click on the Firmware Update button
        devObj.updateFirmware("3.40.0");
    }
    @Then("I should see a confirmation message indicating that the firmware update process has started")
    public void firmwareUpdateConfirmation() {
        // Implement the step to verify that a confirmation message is displayed indicating that the firmware update process has started
        String alertMessage = devObj.alertMessage();
        if (alertMessage != null && alertMessage.contains("initiated") && !alertMessage.contains("failed")) {
            System.out.println("Success message is displayed: " + alertMessage);
            Assertions.assertTrue(true);
        }else if(alertMessage.contains("failed")) {
            System.out.println("Failure message is displayed: " + alertMessage);
            Assertions.fail("Firmware Update command failed with message: " + alertMessage);
        }
    }
    @And("I should receive a notification upon successful completion of the firmware update process")
    public void firmwareUpdateSuccess() {
        // Implement the step to verify that a notification is received upon successful completion of the firmware update process
    }

    // Steps for Scenario: Device Info shows Updated Firmware Version and Online Status after Firmware Update
    @Then("I should see the updated firmware version displayed in the device information section")
    public void updatedFirmwareVersion() {
        // Implement the step to verify that the updated firmware version is displayed in the device information section
    }
    @And("I should see the device status as Online in the device information section")
    public void deviceStatusOnline() {
        // Implement the step to verify that the device status is displayed as Online in the device information section
    }

   // Steps for Scenario: Verify user is able to unregister the device from BWP Web Dashboard
    @And("I click on the Unregister button")
    public void unregisterDevice() {}
    @Then("I should see a confirmation message indicating that the device has been unregistered successfully")
    public void unregisterDeviceSuccess() {}
    @And("I should no longer see the device in the list of registered devices on the BWP Web Dashboard")
    public void deviceUnregisterVerification() {}



}
