package com.framework.playwright.steps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.SameSiteAttribute;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

public class BiampWorkplace {


    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    BrowserContext context = browser.newContext();
    @Test
    void loginFlow() {
        // 1️⃣ Initialize Playwright and browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        BrowserContext context = browser.newContext();

        // 2️⃣ Inject Vercel cookie
        Cookie vercelCookie = new Cookie("_vercel_jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwYXNzd29yZC1wcm90ZWN0aW9uIiwiaGFzaCI6IjI3Zjc4ODM4MmNhZTAyNjk0YzU5YzRjNjFmYjNjYzU2YmRlZjQxMzBkZTZhYjk5ODFmYjIzYmFlYjM5YzcyOWEiLCJhdWQiOiJzdGFnZS53b3JrcGxhY2UuYmlhbXAuYXBwIiwiaWF0IjoxNzY5MDc4Mjg3fQ.LbiyOhjRsQZOtHWzrTHIMQ1WT92ZRFew0R-yQju4Dqw")
                .setDomain("stage.workplace.biamp.app")
                .setPath("/")
                .setHttpOnly(true)
                .setSecure(true)
                .setSameSite(SameSiteAttribute.LAX);

        context.addCookies(java.util.List.of(vercelCookie));

        // 3️⃣ Create APIRequestContext
        APIRequestContext apiRequestContext = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://stage.iam.workplace.biamp.app/")
                        .setExtraHTTPHeaders(java.util.Map.of(
                                "Accept", "application/json",
                                "Content-Type", "application/x-www-form-urlencoded"
                        ))
        );

        // 4️⃣ Prepare login FormData
        FormData loginForm = FormData.create()
                .set("grant_type", "authorization_code")
                .set("redirect_uri", "https://stage.workplace.biamp.app/auth/signin")
                .set("code", "CfplAghPgDn6uNBem6Yb6TW_ijDicJ7edDfxkUsOBaL_5g")       // replace with actual code
                .set("code_verifier", "0be74d0373c448b9ae36c08001891eb45c4f6ad893124eeebb6b46a2af1005fb8d1742f782aa49788b0b2b5e3508c3ea")   // replace with actual verifier
                .set("client_id", "269392376458461213@platform");

        // 5️⃣ Call login API
        APIResponse loginResponse = apiRequestContext.post(
                "/oauth/v2/token",
                RequestOptions.create().setForm(loginForm)
        );

        System.out.println("Login Status: " + loginResponse.status());
        String loginBody = loginResponse.text();
        System.out.println("Login Response: " + loginBody);

        // 6️⃣ Parse JSON to extract access_token
        JsonObject json = JsonParser.parseString(loginBody).getAsJsonObject();
        String accessToken = json.get("access_token").getAsString();
        System.out.println("Access Token: " + accessToken);

        // 7️⃣ Call userinfo API
        APIResponse userInfoResponse = apiRequestContext.get(
                "/oidc/v1/userinfo",
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer " + accessToken)
                        .setHeader("Accept", "application/json, application/jwt")
        );

        System.out.println("UserInfo Status: " + userInfoResponse.status());
        System.out.println("UserInfo Response: " + userInfoResponse.text());

        // 8️⃣ Cleanup
        apiRequestContext.dispose();
        context.close();
        browser.close();
        playwright.close();
    }


    @Test
    void code(){
        Page page = context.newPage();
        page.navigate("https://stage.workplace.biamp.app");

        // wait until redirected to URL containing "code="
        page.waitForURL(url -> url.contains("code="));
        String redirectedUrl = page.url();

        // extract code from query params
        String code = redirectedUrl.split("code=")[1].split("&")[0];

        System.out.println("The Code is: " + code);

    }
}
