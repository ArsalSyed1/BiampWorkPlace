package biamp.framework.biampworkplace.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static biamp.framework.biampworkplace.utilities.configUtilities.SCREENSHOT_PATH;

public class screenshotUtilities {

    // Make sure to define all methods as static to use them without creating an object in other classes

    // Let's define the file paths directly for simplicity
    //private static final String Screenshot_PATH = "biamp/framework/biampworkplace/artifacts/screenshot_" ;

    // Capture only the currently visible viewport (not the full page).
    public static void takeVisibleScreenshot(Page page, String name) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(SCREENSHOT_PATH+ name + "_" + System.currentTimeMillis()+".png")));
    }

    // Capture the entire page (including offscreen content).
    public static void takeFullPageScreenshot(Page page, String name) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(SCREENSHOT_PATH+ name + "_" + System.currentTimeMillis()+".png"))
                .setFullPage(true));
    }

    // Capture a single element specified by a selector.
    public static void takeElementScreenshot(Page page, String selector, String name) {
        page.locator(selector)
                .screenshot(new Locator.ScreenshotOptions()
                        .setPath(Paths.get(SCREENSHOT_PATH+ name + "_" + System.currentTimeMillis()+".png")));
    }


}
