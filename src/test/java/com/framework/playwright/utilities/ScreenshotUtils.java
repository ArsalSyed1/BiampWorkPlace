package com.framework.playwright.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {
    private static final Path ARTIFACTS_DIR = Paths.get(System.getProperty("user.dir"))
            .resolve("playwright")
            .resolve("artifacts");
    private static final DateTimeFormatter TS_FMT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private static void ensureDir() {
        try {
            Files.createDirectories(ARTIFACTS_DIR);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create artifacts directory: " + ARTIFACTS_DIR, e);
        }
    }

    private static String sanitize(String name) {
        if (name == null || name.isBlank()) {
            return "screenshot";
        }
        return name.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
    }

    private static Path buildPath(String baseName) {
        String ts = LocalDateTime.now().format(TS_FMT);
        String fileName = sanitize(baseName) + "_" + ts + ".png";
        return ARTIFACTS_DIR.resolve(fileName);
    }

    // Capture only the currently visible viewport (not the full page).
    public static String takeVisibleScreenshot(Page page, String name) {
        ensureDir();
        Path out = buildPath(name);
        try {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(out)
                    .setFullPage(false));
            return out.toAbsolutePath().toString();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to take visible screenshot: " + out, e);
        }
    }

    // Capture the entire page (including offscreen content).
    public static String takeFullPageScreenshot(Page page, String name) {
        ensureDir();
        Path out = buildPath(name);
        try {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(out)
                    .setFullPage(true));
            return out.toAbsolutePath().toString();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to take full page screenshot: " + out, e);
        }
    }

    // Capture a single element specified by a selector.
    public static String takeElementScreenshot(Page page, String selector, String name) {
        ensureDir();
        Path out = buildPath(name == null || name.isBlank() ? "element" : name);
        try {
            Locator locator = page.locator(selector).first();
            locator.screenshot(new Locator.ScreenshotOptions().setPath(out));
            return out.toAbsolutePath().toString();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to take element screenshot for selector '" + selector + "': " + out, e);
        }
    }
}
