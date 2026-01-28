package biamp.framework.biampworkplace.base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.SameSiteAttribute;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.nio.file.Paths;

import static com.framework.playwright.utilities.auth_sessionUtils.reuseSession;

public class baseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    protected Page page;

    static Cookie vercelCookie = new Cookie("_vercel_jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwYXNzd29yZC1wcm90ZWN0aW9uIiwiaGFzaCI6IjI3Zjc4ODM4MmNhZTAyNjk0YzU5YzRjNjFmYjNjYzU2YmRlZjQxMzBkZTZhYjk5ODFmYjIzYmFlYjM5YzcyOWEiLCJhdWQiOiJzdGFnZS53b3JrcGxhY2UuYmlhbXAuYXBwIiwiaWF0IjoxNzY5MDc4Mjg3fQ.LbiyOhjRsQZOtHWzrTHIMQ1WT92ZRFew0R-yQju4Dqw")
            .setDomain("stage.workplace.biamp.app")
            .setPath("/")
            .setHttpOnly(true)
            .setSecure(true)
            .setSameSite(SameSiteAttribute.LAX);

    public baseTest(Page page) {
        this.page = page;
    }

    @BeforeAll
    public static void setup() {


    }

    @BeforeEach
    public void beforeEach() {
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
    public void afterEach(TestInfo testInfo) {
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
