package com.framework.playwright.base;

import com.framework.playwright.pages.devicesPage;
import com.framework.playwright.pages.loginPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }
}
