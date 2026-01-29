package biamp.framework.biampworkplace.pages;

import biamp.framework.biampworkplace.base.basePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class signInPage extends basePage {

    Locator signInButton = page.getByText("Sign in or Register");
    Locator emailField = page.locator("#loginName");
    Locator proceedButton = page.locator("#submit-button");
    Locator passwordField = page.getByPlaceholder("Password");
    Locator loginButton = page.locator("#idSIButton9");

    public signInPage(Page page) {
        super(page);
    }


    public void signIn(String email, String password) {
        click(signInButton);
        type(emailField, email);
        click(proceedButton);
        type(passwordField, password);
        click(loginButton);
    }
}
