package com.framework.playwright.steps;

import com.framework.playwright.base.BaseTest;
import com.framework.playwright.pages.devicesPage;
import com.framework.playwright.pages.loginPage;
import com.framework.playwright.pages.organizationPage;
import com.framework.playwright.uiComponent.leftNavigation;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static com.framework.playwright.utilities.assertionUtils.*;
import static com.framework.playwright.utilities.auth_sessionUtils.*;
public class BWPLoginTest extends BaseTest {

    @Test
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
    }

}
