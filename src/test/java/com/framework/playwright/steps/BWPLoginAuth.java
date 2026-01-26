/*package com.framework.playwright.steps;

import com.framework.playwright.pages.loginPage;
import com.framework.playwright.pages.organizationPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class BWPLoginAuth {

    Playwright playwright;
    Browser browser, browser1;
    BrowserContext browserContext;
    Page page;

    @Test
    void login() throws InterruptedException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();

        page.navigate("https://stage.workplace.biamp.app");

        loginPage loginObj = new loginPage(page);
        loginObj.login("arsal.syed@biamp.com", "nustseecs@2K13");

        Thread.sleep(2000);

        browserContext.storageState(
                new BrowserContext.StorageStateOptions()
                        .setPath(Paths.get("com/framework/playwright/artifacts/auth.json")));

        Thread.sleep(2000);

        page.close();
        browserContext.close();
        browser.close();

        Thread.sleep(2000);

        browser1 = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

       BrowserContext context =  browser1.newContext(
                new Browser.NewContextOptions()
                        .setStorageStatePath(Paths.get("com/framework/playwright/artifacts/auth.json")));
        Page page1 = context.newPage();
        page1.navigate("https://stage.workplace.biamp.app");
        Thread.sleep(5000);

    }

}*/
