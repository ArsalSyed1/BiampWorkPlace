package com.framework.playwright.steps;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.SameSiteAttribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class BWPLoginTest {

    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    BrowserContext context = browser.newContext();
    Page page = context.newPage();

    Cookie vercelCookie = new Cookie("_vercel_jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwYXNzd29yZC1wcm90ZWN0aW9uIiwiaGFzaCI6IjI3Zjc4ODM4MmNhZTAyNjk0YzU5YzRjNjFmYjNjYzU2YmRlZjQxMzBkZTZhYjk5ODFmYjIzYmFlYjM5YzcyOWEiLCJhdWQiOiJzdGFnZS53b3JrcGxhY2UuYmlhbXAuYXBwIiwiaWF0IjoxNzY5MDc4Mjg3fQ.LbiyOhjRsQZOtHWzrTHIMQ1WT92ZRFew0R-yQju4Dqw")
            .setDomain("stage.workplace.biamp.app")
            .setPath("/")
            .setHttpOnly(true)
            .setSecure(true)
            .setSameSite(SameSiteAttribute.LAX);

    @Test
    void login() throws InterruptedException {
        context.addCookies(List.of(vercelCookie));
        page.navigate("https://stage.workplace.biamp.app");
        page.getByText("Sign in or Register").click();
        page.locator("#loginName").fill("arsal.syed@biamp.com");
        page.locator("#submit-button").click();
        page.getByPlaceholder("Password").fill("nustseecs@2K13");
        page.locator("#idSIButton9").click();
        page.getByText("Impera Corporation").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Devices")).click();
        page.locator("input[type='search']").fill("237401742");
        page.locator("tr[role='button']").nth(0).waitFor(new Locator.WaitForOptions().setState(VISIBLE));
        int count = page.locator("tr[role='button']").count();
        System.out.println(count);
        Assertions.assertEquals(1, count);
    }
}
