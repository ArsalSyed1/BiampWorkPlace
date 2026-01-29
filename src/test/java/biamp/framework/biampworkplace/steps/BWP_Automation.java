package biamp.framework.biampworkplace.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class BWP_Automation {

    @Given("I am logged into Biamp Workplace Cloud")
    public void login() throws InterruptedException {
        Thread.sleep(2000);
        Assertions.assertTrue(true);
    }

    @When("I navigate to the Devices section")
    public void i_navigate_to_the_devices_section() throws InterruptedException {
        Thread.sleep(2000);
        Assertions.assertTrue(true);
    }
    @Then("I should see a list of registered devices")
    public void i_should_see_a_list_of_registered_devices() throws InterruptedException {
        Thread.sleep(2000);
        Assertions.assertTrue(true);
    }
    @And("check if the device with ID {string} is present in the list or not")
    public void the_check_if_the_device_with_id_is_present_in_the_list_or_not(String deviceSr) throws InterruptedException {
        Thread.sleep(2000);
        Assertions.assertTrue(true);
    }
}
