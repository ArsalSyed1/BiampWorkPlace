package biamp.framework.biampworkplace.uicomponent;

import biamp.framework.biampworkplace.base.basePage;
import biamp.framework.biampworkplace.pages.devicesPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static biamp.framework.biampworkplace.utilities.pageUtilities.*;


public class leftNavBar extends basePage {

    Locator devicesNavLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Devices")).first();

    public leftNavBar(Page page) {
        super(page);
    }

    public devicesPage navigateToDevices() {
        click(devicesNavLink);
        return new devicesPage(page);
    }
}
