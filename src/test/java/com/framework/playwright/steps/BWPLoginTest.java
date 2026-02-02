package com.framework.playwright.steps;

import com.framework.playwright.base.BaseTest;
import com.framework.playwright.pages.devicesPage;
import com.framework.playwright.pages.organizationPage;
import com.framework.playwright.uiComponent.leftNavigation;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.*;
import static com.framework.playwright.utilities.assertionUtils.*;
import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BWPLoginTest extends BaseTest {

    /*@Test
    void login() throws InterruptedException {
        loginPage loginObj = new loginPage(page);
        loginObj.navigateTo("https://stage.workplace.biamp.app");
        organizationPage org =loginObj.login("arsal.syed@biamp.com", "nustseecs@2K13");

        org.selectOrganization();

        leftNavigation leftNav =  new leftNavigation(page);
        devicesPage device = leftNav.gotoDevices();

        int devCount = device.searchDevice("237401742");

        // Calling the Assertion Utils methods
        assertEquals(1, devCount);
        Thread.sleep(1000);
    }*/


    @Order(99)
    @Test
    void verifySignOutFunctionality() throws InterruptedException {
        page.navigate("https://stage.workplace.biamp.app");
        page.locator(".MuiButton-root").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign out")).waitFor(new Locator.WaitForOptions().setState(VISIBLE));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign out")).click();
        page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(5000));
        Assertions.assertTrue(page.getByText("Sign in or Register").isVisible(), "Sign out was not successful");
    }
    @Order(1)
    @Test
    void searchDevice() throws InterruptedException {
        page.navigate("https://stage.workplace.biamp.app");
        page.locator(".MuiButton-root").click();
        organizationPage org = new organizationPage(page);
        org.selectOrganization();

        leftNavigation leftNav =  new leftNavigation(page);
        devicesPage device = leftNav.gotoDevices();

        int devCount = device.searchDevice("237401742");

        // Calling the Assertion Utils methods
        assertEquals(1, devCount);
    }

    @Order(2)
    @Test
    void openDeviceDetails(){
        page.navigate("https://stage.workplace.biamp.app/admin/imperacorporation/devices?page=0&limit=25&search=237401742");
        page.locator("tr[role='button']").filter(new Locator.FilterOptions().setHasText("237401742")).click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertVisible(page.locator("div[role='dialog']").filter(new Locator.FilterOptions().setHasText("Device Impera Tango")));
    }

}
