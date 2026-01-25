package com.framework.playwright.uiComponent;

import com.framework.playwright.pages.devicesPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class leftNavigation {

    private Page page;
    private Locator navigate;

    public leftNavigation(Page page) {
        this.page = page;
    }

    public devicesPage gotoDevices() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Devices")).click();
        return new devicesPage(page);
    }


}
