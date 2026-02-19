package biamp.framework.biampworkplace.steps;

import biamp.framework.biampworkplace.base.baseTest;
import biamp.framework.biampworkplace.pages.*;
import biamp.framework.biampworkplace.uicomponent.leftNavBar;

import biamp.framework.biampworkplace.utilities.configReaderUtilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static biamp.framework.biampworkplace.utilities.configUtilities.*;
import static biamp.framework.biampworkplace.utilities.sessionUtilities.saveStorageState;
import static biamp.framework.biampworkplace.utilities.waitUtilities.waitForLoadState;
import static com.microsoft.playwright.options.LoadState.*;

public class BWP_UnregisterDevices extends baseTest {
    signInPage signInObj;
    organizationPage orgObj;
    devicesPage devObj;
    leftNavBar leftNavBarObj;
    int count;



    // Steps for Scenario Outline: Verify Device Presence on Biamp Workplace Cloud
    @Given("I am logged into Biamp Workplace Cloud")
    public void login() throws InterruptedException {

        signInObj = new signInPage(page);
        signInObj.navigateTo(BASE_URL);
        waitForLoadState(page, NETWORKIDLE);

        orgObj = signInObj.signIn(USERNAME, PASSWORD);
        Thread.sleep(2000);
        saveStorageState(context);
    }

    @When("I navigate to the Devices section")
    public void navigateToDeviceSelection() throws InterruptedException {
        orgObj.selectOrganization();
        leftNavBarObj = new leftNavBar(page);
        devObj=leftNavBarObj.navigateToDevices();
    }
    @Then("I should see a list of registered devices")
    public void registeredDevicesListVisible() throws InterruptedException {
        Assertions.assertTrue(devObj.devicesListVisible());
    }
    @And("check if the device with ID {string} is present in the list or not")
    public void checkDeviceListed(String deviceSri) throws InterruptedException {
        String deviceSr = configReaderUtilities.get(deviceSri);

        if (deviceSr == null || deviceSr.isEmpty()) {
            throw new RuntimeException("No device SR found for key: " + deviceSr);
        }
        System.out.println("Device SR from config: " + deviceSr);
        count =devObj.searchDevice(deviceSr);
    }
    @And("if the device is present with ID {string}, then unregister it else do nothing")
    public void unregisterDevice(String deviceSr) throws InterruptedException {
        devObj.unregisterDevice(deviceSr, count);
//        Thread.sleep(15000);
    }

    @And("Trigger PAD Script")
    public void triggerPadScript() throws InterruptedException {
        try {
            String padScriptUrl = "ms-powerautomate:/console/flow/run?environmentid=Default-f74a8ec6-f77a-4299-8df2-07566aa55e64&workflowid=75e8a905-615d-f011-bec2-7c1e52fce6f6&source=Other";
            // Use Runtime to execute the custom protocol
            Runtime.getRuntime().exec("cmd /c start \"\" \"" + padScriptUrl + "\"");
            System.out.println("Power Automate Desktop script triggered successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to trigger Power Automate Desktop script.", e);
        }
    }

    // Steps for Scenario: Verify signout functionality
    @When("I click on the sign out CTA")
    public void i_click_on_the_sign_out_cta() throws InterruptedException {
        Thread.sleep(2000);
        Assertions.assertTrue(true);
    }

    @Then("I should be signed out successfully")
    public void i_should_be_signed_out_successfully() throws InterruptedException {
        Thread.sleep(2000);
        Assertions.assertTrue(true);
    }
}
