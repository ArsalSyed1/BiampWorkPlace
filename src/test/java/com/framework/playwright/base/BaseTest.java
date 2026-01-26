package com.framework.playwright.base;

import com.framework.playwright.pages.loginPage;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.SameSiteAttribute;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.BeforeParameterizedClassInvocation;

import java.nio.file.Paths;
import java.util.List;

import static com.framework.playwright.utilities.auth_sessionUtils.*;

public class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    protected static Page page;

    static Cookie vercelCookie = new Cookie("_vercel_jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwYXNzd29yZC1wcm90ZWN0aW9uIiwiaGFzaCI6IjI3Zjc4ODM4MmNhZTAyNjk0YzU5YzRjNjFmYjNjYzU2YmRlZjQxMzBkZTZhYjk5ODFmYjIzYmFlYjM5YzcyOWEiLCJhdWQiOiJzdGFnZS53b3JrcGxhY2UuYmlhbXAuYXBwIiwiaWF0IjoxNzY5MDc4Mjg3fQ.LbiyOhjRsQZOtHWzrTHIMQ1WT92ZRFew0R-yQju4Dqw")
            .setDomain("stage.workplace.biamp.app")
            .setPath("/")
            .setHttpOnly(true)
            .setSecure(true)
            .setSameSite(SameSiteAttribute.LAX);


    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        playwright= Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        context = browser.newContext();
        context.addCookies(List.of(vercelCookie));
        page = context.newPage();

        loginPage loginObj = new loginPage(page);
        loginObj.navigateTo("https://stage.workplace.biamp.app");
        loginObj.login("arsal.syed@biamp.com", "nustseecs@2K13");
        Thread.sleep(2000);

        saveStorageState(context);
        page.close();
        context.close();
        browser.close();
        playwright.close();

    }

    @BeforeEach
    void setupTrace(){
        playwright= Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context =reuseSession(browser);
        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true) );

        page = context.newPage();

    }

    @AfterEach
    void recordTraces(TestInfo testInfo){

        String traceName = testInfo.getDisplayName().replace(" ", "-") .replaceAll("[\\\\/:*?\"<>|()]", "") // remove illegal Windows chars
                .toLowerCase();

        context.tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get("src/test/java/com/framework/playwright/artifacts/" + "traces-"+traceName+".zip")) // Will save with display name of test
        );

        context.close();
        browser.close();
        playwright.close();
    }

}
