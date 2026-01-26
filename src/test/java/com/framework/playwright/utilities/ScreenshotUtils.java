package com.framework.playwright.utilities;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class ScreenshotUtils {

    public static void takeScreenshot(Page page, String name) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/" + name + ".png"))
                .setFullPage(true));
    }

}
