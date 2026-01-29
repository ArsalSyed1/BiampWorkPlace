package biamp.framework.biampworkplace.uicomponent;

import biamp.framework.biampworkplace.base.basePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class leftNavBar extends basePage {

    Locator devicesNavLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Devices"));

    public leftNavBar(Page page) {
        super(page);
    }

    public void navigateToDevices() {
        click(devicesNavLink);
    }
}
