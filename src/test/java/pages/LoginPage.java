package pages;

import helpers.ElementHelper;
import helpers.PageLoad;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pawel on 2017-04-19.
 */
public class LoginPage extends AbstractPage {

    private WebDriver driver;
    private static final String usernameInputLocator = "pl.bitsa.lupe2:id/et_username";
    private static final String passwordInputLocator = "pl.bitsa.lupe2:id/et_password";
    private static final String googleLoginButtonLocator = "pl.bitsa.lupe2:id/bt_go_google";
    private static final String facebookLoginButtonLocator = "pl.bitsa.lupe2:id/bt_go_facebook";
    private static final String accountLoginButtonLocator = "pl.bitsa.lupe2:id/bt_go";
    private static final String guestLoginButtonLocator = "pl.bitsa.lupe2:id/bt_guest";
    private static final String googleModalTitleLocator = "com.google.android.gms:id/title";
    private static final String googleAccountsListLocator = "com.google.android.gms:id/account_name";

    @AndroidFindBy(id = usernameInputLocator)
    private WebElement usernameInput;
    @AndroidFindBy(id = passwordInputLocator)
    private WebElement passwordInput;
    @AndroidFindBy(id = googleLoginButtonLocator)
    private WebElement googleLoginButton;
    @AndroidFindBy(id = facebookLoginButtonLocator)
    private WebElement facebookLoginButton;
    @AndroidFindBy(id = accountLoginButtonLocator)
    private WebElement accountLoginButton;
    @AndroidFindBy(id = guestLoginButtonLocator)
    private WebElement guestLoginButton;

    //google social login
    @AndroidFindBy(id = googleModalTitleLocator)
    private WebElement googleModalTitle;
    @AndroidFindBy(id = googleAccountsListLocator)
    private List<WebElement> googleAccountsList;
    private static final String googleModalTitleText = "Wybierz konto dla aplikacji Lupe2";


    /*
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /*
    @Override
    protected void isLoaded() throws Error {

        if(!this.isPageLoaded()) {
            throw new Error("ERROR: Obiekt 'LoginPage' nie przeszedl testow dostepnosci i widocznosci elementow");
        }

        System.out.println("INFO: Udalo sie zaladowac obiektu 'LoginPage'");
    }

    @Override
    protected void load() {
    }
*/

    /*
    public boolean isPageLoaded() {
        //inputs
        ElementHelper.textInputVerify(driver, By.id(usernameInputLocator), 5);
        ElementHelper.textInputVerify(driver, By.id(passwordInputLocator), 5);

        //buttons
        ElementHelper.buttonVerify(driver, By.id(googleLoginButtonLocator),5);
        ElementHelper.buttonVerify(driver, By.id(facebookLoginButtonLocator),5);
        ElementHelper.buttonVerify(driver, By.id(accountLoginButtonLocator),5);
        ElementHelper.buttonVerify(driver, By.id(guestLoginButtonLocator),5);

        //sprawdza czy znajduje sie w poprawnym Activity
        Assert.assertEquals(ElementHelper.getCurrentAndroidActivity(driver),".LoginActivity");
        System.out.println(ElementHelper.getCurrentAndroidActivity(driver)); //debug
        return true;
    }
    */
    public boolean isPageLoaded() {

        //sprawdza czy znajduje sie w poprawnym Activity
        waitForActivity(".LoginActivity");
        System.out.println("INFO: Przejście do: "+getCurrentAndroidActivity());

        //inputs
        textInputVerify(usernameInput);
        textInputVerify(passwordInput);

        //buttons
        //buttonVerify(googleLoginButton);
        //buttonVerify(facebookLoginButton);
        buttonVerify(accountLoginButton);
        buttonVerify(guestLoginButton);

        return true;
    }

    public void signInAsGuest() {
        guestLoginButton.click();
    }

    /*
    public MainPage signInAsGuest() {
        try {
            guestLoginButton.click();
        }
        catch (Exception ex) {
            throw new Error("ERROR: Nie udalo sie przejsc logowania jako gosc | "+ex);
        }
        return new MainPage(driver);
    }
    */

    public MainPage signInAsUser(String username, String password) {
        try {
            usernameInput.sendKeys(username);
            passwordInput.sendKeys(password);
            accountLoginButton.click();
        }
        catch (Exception ex) {
            throw new Error("ERROR: Nie udalo sie przejsc logowania jako uzytkownik | "+ex);
        }
        return new MainPage(driver);
    }

    public GoogleLoginPage signInAsGoogleUser() {
        googleLoginButton.click();
        return new GoogleLoginPage(driver);
    }

    //android.webkit.WebView
    // http://toolsqa.com/selenium-webdriver/switch-commands/
    /*
    public boolean signInAsFacebookUser(String socialEmail) {
        facebookLoginButton.click();
        System.out.println("Obecne okno: "+driver.getWindowHandle()); //debug
        Set<String> handles = driver.getWindowHandles();
        System.out.println("handles: " + handles);
        //czekam na zaladowanie sie okien (2)
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver webDriver) {
                return driver.getWindowHandles().size() == 2;
            }
        });

        System.out.println("handles: " + driver.getWindowHandles());

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }


        return false;
    }
    */
}
