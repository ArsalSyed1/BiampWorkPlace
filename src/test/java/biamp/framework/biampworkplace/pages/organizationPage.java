package biamp.framework.biampworkplace.pages;

import biamp.framework.biampworkplace.base.basePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class organizationPage extends basePage {

    Locator selectOrganization = page.getByText("Impera Corporation");

    public organizationPage(Page page) {
        super(page);
    }

    public void selectOrganization() {
        click(selectOrganization);
    }
}
