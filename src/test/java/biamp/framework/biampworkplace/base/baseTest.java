package biamp.framework.biampworkplace.base;
import biamp.framework.biampworkplace.pages.signInPage;
import biamp.framework.biampworkplace.utilities.configUtilities;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.SameSiteAttribute;
import org.junit.jupiter.api.TestInfo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static biamp.framework.biampworkplace.utilities.configUtilities.*;
import static biamp.framework.biampworkplace.utilities.sessionUtilities.reuseSession;
import static biamp.framework.biampworkplace.utilities.sessionUtilities.saveStorageState;

public class baseTest {

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

    //@BeforeAll
    public void setup() throws InterruptedException {
        System.out.println("I am printing here the value of Headless: " + HEADLESS);
        initializePlaywright();
        setupBrowserContext();

        signInPage signInObj = new signInPage(page);
        signInObj.navigateTo(BASE_URL);
        signInObj.signIn(USERNAME, PASSWORD);
        Thread.sleep(2000);

        saveStorageState(context);
        closeResources();
    }

    //@BeforeEach
    public void beforeEach() {
        initializePlaywright();
        // Check if auth.json exists before attempting to reuse the session
        if (Files.exists(Paths.get("src/test/java/biamp/framework/biampworkplace/artifacts/auth.json"))) {
            System.out.println("Auth file found. Attempting to reuse session.");
            context = reuseSession(browser);
            page = context.newPage();
        }

        // Fallback to creating a new context if reuseSession returns null
        if (context == null) {
            System.out.println("Auth file not found. Setting Up Browser Context.");
            context = setupBrowserContext();
        }
        startTracing();


    }

  //  @AfterEach
    public void afterEach(TestInfo testInfo) {
        stopTracing(testInfo);
        closeResources();
    }


    private void initializePlaywright(){
        playwright= Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(configUtilities.HEADLESS));
    }

    private BrowserContext setupBrowserContext(){
        context = browser.newContext();
        context.addCookies(List.of(vercelCookie));
        page = context.newPage();
        return context;
    }

    private void startTracing(){
        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true) );
    }

    private void stopTracing(TestInfo testInfo){
        String traceName = sanitizeTestName(testInfo.getDisplayName());
        context.tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get("src/test/java/biamp/framework/biampworkplace/artifacts" + "traces-"+traceName+".zip")) // Will save with display name of test
        );
    }

    private String sanitizeTestName(String testName){
        return testName.replace(" ", "-")
                .replaceAll("[\\\\/:*?\"<>|()]", "")
                .toLowerCase();
    }

    private void closeResources() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

}
