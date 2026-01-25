package com.framework.playwright.pages;

import com.framework.playwright.base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.options.WaitForSelectorState.HIDDEN;
import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

/*
    page.locator("tr[role='button']").nth(0).waitFor(new Locator.WaitForOptions().setState(VISIBLE));
    page.locator("button[aria-label='Search...']").click();
    page.getByRole(AriaRole.TEXTBOX).fill("237401742");
    Thread.sleep(2000);
    page.locator("tr[role='button']").nth(0).waitFor(new Locator.WaitForOptions().setState(VISIBLE));
    page.locator("tr[role='button']").nth(20).waitFor(new Locator.WaitForOptions().setState(HIDDEN));
    int count = page.locator("tr[role='button']").count();
    System.out.println(count);
*/

public class devicesPage extends BasePage {

    private Locator device_row;
    private Locator search_svg;
    private Locator searchTextBox;

    public devicesPage(Page page) {
        super(page);
        this.device_row = page.locator("tr[role='button']");
        this.search_svg = page.locator("button[aria-label='Search...']");
        this.searchTextBox = page.getByRole(AriaRole.TEXTBOX);
    }

    public int searchDevice(String deviceSr) throws InterruptedException {
        device_row.nth(0).waitFor(new Locator.WaitForOptions().setState(VISIBLE));
        search_svg.click();
        searchTextBox.fill(deviceSr);
        Thread.sleep(2000);
        device_row.nth(0).waitFor(new Locator.WaitForOptions().setState(VISIBLE));
        device_row.nth(20).waitFor(new Locator.WaitForOptions().setState(HIDDEN));
        int count = device_row.count();
        System.out.println(count);
        return count;
    }
}
