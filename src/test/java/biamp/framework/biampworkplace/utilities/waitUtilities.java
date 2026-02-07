package biamp.framework.biampworkplace.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;


public class waitUtilities {


    // Make sure to define all methods as static to use them without creating an object in other classes

    public static final int DEFAULT_TIMEOUT_MS = 5000;
    private static final int POLL_INTERVAL_MS = 100;

    public static void waitForLoadState(Page page, LoadState state) {
        page.waitForLoadState(state,
                new Page.WaitForLoadStateOptions().setTimeout(DEFAULT_TIMEOUT_MS));
    }

    public static void waitForLocatorState(Page page, String selector, String state) {
        WaitForSelectorState waitState = WaitForSelectorState.valueOf(state.toUpperCase());
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(waitState).setTimeout((double) DEFAULT_TIMEOUT_MS));
    }

    public static void waitForCondition(Page page, java.util.function.Supplier<Boolean> condition) {
        page.waitForCondition(condition::get,
                new Page.WaitForConditionOptions().setTimeout((double) DEFAULT_TIMEOUT_MS));
    }

    public static void waitForResponse(Page page, java.util.function.Supplier<Boolean> condition){
        //page.waitForResponse("**products?page=0&sort**" , () → { page.getByTestId("sort").selectOption("Price (High - Low)"); });
        page.waitForResponse("**products?page=0&sort**" , condition::get);
    }

    public static void waitForResponse(Page page, String url, double statusCode){
        // page.waitForResponse(response -> response.url().contains("login") && response.status() == 200 );
        // page.waitForResponse(urlPattern , () -> { } );
        page.waitForResponse(
                response -> response.url().contains(url) && response.status() == statusCode,
                () -> { } );

    }

    public static void waitForRequest(Page page, String url, String httpMethod, String selector){
        page.waitForRequest(
                req -> req.url().contains(url) && req.method().equals(httpMethod),
                () -> { page.click(selector); } );

    }

}
