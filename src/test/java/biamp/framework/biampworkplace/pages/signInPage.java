package biamp.framework.biampworkplace.pages;

import biamp.framework.biampworkplace.base.basePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static biamp.framework.biampworkplace.utilities.pageUtilities.*;

public class signInPage extends basePage {

    Locator signInButton = page.getByText("Sign in or Register");
    Locator emailField = page.locator("#loginName");
    Locator proceedButton = page.locator("#submit-button");
    Locator passwordField = page.getByPlaceholder("Password");
    Locator loginButton = page.locator("#idSIButton9");
    Locator organization = page.getByText("Go to your organizations");
    public signInPage(Page page) {
        super(page);
    }


    public organizationPage signIn(String email, String password) {
       if(isVisible(signInButton)) {
           click(signInButton);
           type(emailField, email);
           click(proceedButton);
           type(passwordField, password);
           click(loginButton);

       } else if (isVisible(organization)) {
           organization.click();
       }
        return new organizationPage(page);
    }
}
