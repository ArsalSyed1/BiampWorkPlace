package biamp.framework.biampworkplace.pages;

import biamp.framework.biampworkplace.base.basePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static biamp.framework.biampworkplace.utilities.actionWrapper.*;
import static biamp.framework.biampworkplace.utilities.waitUtilities.*;
import static com.microsoft.playwright.options.LoadState.*;

public class devicesPage extends basePage {

    Locator searchSvg = page.locator("button[aria-label='Search...']");
    Locator searchTextBox = page.getByRole(AriaRole.TEXTBOX);
    Locator deviceRow = page.locator("tr[role='button']");
    Locator unregisterButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Unregister device"));
    Locator unregisterConfirmationButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Unregister"));
    Locator rebootButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reboot"));
    Locator rebootConfirmationButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reboot"));
    Locator messageAlert = page.locator(".MuiAlert-message");
    Locator fetchLogsCTA = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Fetch logs"));
    Locator fetchLogsConfirmationCTA = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get logs"));
    Locator logLink = page.locator(".MuiLink-root").filter(new Locator.FilterOptions().setHasText("EngineeringLogs"));
    Locator updateFirmwareCTA = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Update firmware"));
    Locator inputVersionTextBox = page.getByRole(AriaRole.COMBOBOX);
    Locator ProceedCTA = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed"));
    public devicesPage(Page page) {
        super(page);
    }

    public boolean devicesListVisible() throws InterruptedException {
        waitForLoadState(page, NETWORKIDLE);
        if (isVisible(deviceRow.first())) {
            System.out.println("Devices list is visible");
            return true;
        } else {
            System.out.println("Devices list is not visible");
            return false;
        }
    }

    public int searchDevice(String deviceSr) throws InterruptedException {
        click(searchSvg);
        type(searchTextBox, deviceSr);
        waitForLoadState(page, NETWORKIDLE);
        Thread.sleep(2000);
        return deviceRow.filter(new Locator.FilterOptions().setHasText(deviceSr)).count();
    }

    public void openDevicePage(String deviceSr) throws InterruptedException {
            click(deviceRow.filter(new Locator.FilterOptions().setHasText(deviceSr)));
    }
    public void unregisterDevice(String deviceSr, int count) throws InterruptedException {
             if(count > 0) {
                System.out.println("Device with ID " + deviceSr + " is present. Unregistering the device...");
                click(deviceRow.first());
                // Add steps to unregister the device here
                unregisterButton.scrollIntoViewIfNeeded(); // Not necessary here but added for better visibility during test execution
                click(unregisterButton);
                click(unregisterConfirmationButton);

            } else {
                System.out.println("Device with ID " + deviceSr + " is not present. No action needed.");
            }
    }

    public void rebootDevice() {
        System.out.println("Rebooting the device...");
        click(rebootButton);
        click(rebootConfirmationButton);
    }

    public void fetchLogs() {
        System.out.println("Fetching logs for the device...");
        click(fetchLogsCTA);
        click(fetchLogsConfirmationCTA);
    }

    public void logLinkAvailability(){
        try {
            logLink.waitFor(new Locator.WaitForOptions().setTimeout(60000));
            if (isVisible(logLink)) {
                System.out.println("Log link is visible: " + logLink.innerText());
            } else {
                System.out.println("Log link is not visible.");
            }
        } catch (Exception e) {
            System.out.println("Log link did not appear within the expected time.");
        }
    }

    public void updateFirmware(String version) throws InterruptedException {
        System.out.println("Updating firmware to version: " + version);
        click(updateFirmwareCTA);
        type(inputVersionTextBox, version);
        Thread.sleep(2000); // Added sleep to ensure that the dropdown options are loaded before pressing keys
        page.keyboard().press("ArrowDown");
        page.keyboard().press("Enter");
        click(ProceedCTA);
    }


    public String alertMessage() {
        try {
           // rebootConfirmationButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
            messageAlert.waitFor(new Locator.WaitForOptions().setTimeout(5000));
            System.out.println("Alert message: " + messageAlert.innerText());
            return messageAlert.innerText();
        } catch (Exception e) {
            System.out.println("No alert message is visible.");
            return null;
        }
    }
}
