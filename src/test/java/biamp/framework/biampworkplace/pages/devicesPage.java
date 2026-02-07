package biamp.framework.biampworkplace.pages;

import biamp.framework.biampworkplace.base.basePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static biamp.framework.biampworkplace.utilities.pageUtilities.*;
import static biamp.framework.biampworkplace.utilities.waitUtilities.*;
import static com.microsoft.playwright.options.LoadState.*;

public class devicesPage extends basePage {

    Locator searchSvg = page.locator("button[aria-label='Search...']");
    Locator searchTextBox = page.getByRole(AriaRole.TEXTBOX);
    Locator deviceRow = page.locator("tr[role='button']");
    Locator unregisterButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Unregister device"));

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

    public int searchDevice(String deviceSr) {
        click(searchSvg);
        type(searchTextBox, deviceSr);
        waitForLoadState(page, NETWORKIDLE);
        return deviceRow.count();
    }
        public void unregisterDevice(String deviceSr, int count) throws InterruptedException {
             if(count > 0) {
                System.out.println("Device with ID " + deviceSr + " is present. Unregistering the device...");
                click(deviceRow.first());
                // Add steps to unregister the device here
                unregisterButton.scrollIntoViewIfNeeded(); // Not necessary here but added for better visibility during test execution
                click(unregisterButton);
            } else {
                System.out.println("Device with ID " + deviceSr + " is not present. No action needed.");
            }
        }
}
