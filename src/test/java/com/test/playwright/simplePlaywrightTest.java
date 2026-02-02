package com.test.playwright;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

public class simplePlaywrightTest {

    Playwright playwright;
    Browser browser;
    BrowserContext context;

    @BeforeEach
    void setupTrace(){

        playwright= Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true) );
    }



    @Test
    void showPageTitle() throws InterruptedException {


        Page page = context.newPage();

        page.navigate("https://practicesoftwaretesting.com");
        String title = page.title();
        Assertions.assertTrue(title.contains("Practice Software Testing"));


    }

    @AfterEach
    void recordTraces(TestInfo testInfo){

        String traceName = testInfo.getDisplayName().replace(" ", "-") .replaceAll("[\\\\/:*?\"<>|()]", "") // remove illegal Windows chars
                .toLowerCase();

        context.tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get("target/playwright-traces/" + "trace-"+traceName+".zip")) // Will save with display name of test
        );

        context.close();
        browser.close();
        playwright.close();
    }

}
