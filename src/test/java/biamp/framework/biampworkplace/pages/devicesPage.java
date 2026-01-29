package biamp.framework.biampworkplace.pages;

import biamp.framework.biampworkplace.base.basePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static biamp.framework.biampworkplace.utilities.waitUtilities.*;
import static com.microsoft.playwright.options.LoadState.*;

public class devicesPage extends basePage {

    Locator searchSvg = page.locator("button[aria-label='Search...']");
    Locator searchTextBox = page.getByRole(AriaRole.TEXTBOX);
    Locator deviceRow = page.locator("tr[role='button']");

    public devicesPage(Page page) {
        super(page);
    }

    public int searchDevice(String deviceSr) {
        click(searchSvg);
        type(searchTextBox, deviceSr);
        waitForLoadState(page, NETWORKIDLE);
        return deviceRow.count();
    }
}
