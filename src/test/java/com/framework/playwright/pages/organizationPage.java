package com.framework.playwright.pages;

import com.framework.playwright.base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

// page.getByText("Impera Corporation").click();

public class organizationPage extends BasePage {

    private Locator organization;

    public organizationPage(Page page) {
        super(page);
        this.organization = page.getByText("Impera Corporation");
    }

    public void selectOrganization() {
        organization.click();
    }


}
