package com.framework.playwright.steps;

import com.framework.playwright.base.BaseTest;
import com.framework.playwright.pages.loginPage;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.options.WaitForSelectorState.HIDDEN;
import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;
public class BWPLoginTest extends BaseTest {

    @Test
    void login() throws InterruptedException {
        loginPage loginobj = new loginPage(page);
        loginobj.navigateTo("https://stage.workplace.biamp.app");
        loginobj.login("arsal.syed@biamp.com", "nustseecs@2K13");

        page.getByText("Impera Corporation").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Devices")).click();
        page.locator("tr[role='button']").nth(0).waitFor(new Locator.WaitForOptions().setState(VISIBLE));
        page.locator("button[aria-label='Search...']").click();
        page.getByRole(AriaRole.TEXTBOX).fill("237401742");
        Thread.sleep(2000);
        page.locator("tr[role='button']").nth(0).waitFor(new Locator.WaitForOptions().setState(VISIBLE));
        page.locator("tr[role='button']").nth(20).waitFor(new Locator.WaitForOptions().setState(HIDDEN));
        int count = page.locator("tr[role='button']").count();
        System.out.println(count);
        Assertions.assertEquals(1, count);
    }

}
