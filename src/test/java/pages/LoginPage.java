package pages;

import helpers.ElementHelper;
import helpers.PageLoad;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 2017-04-19.
 */
public class LoginPage extends LoadableComponent<LoginPage> {

    private WebDriver driver;

    @AndroidFindBy(id = "pl.bitsa.lupe2:id/et_username")
    private MobileElement usernameInput;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/et_password")
    private MobileElement passwordInput;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/bt_go_google")
    private MobileElement googleLoginButton;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/bt_go_facebook")
    private MobileElement facebookLoginButton;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/bt_go")
    private MobileElement accountLoginButton;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/bt_guest")
    private MobileElement guestLoginButton;

    //social login
    @AndroidFindBy(id = "com.google.android.gms:id/title")
    private MobileElement googleModalTitle;
    @AndroidFindBy(id = "com.google.android.gms:id/account_name")
    private List<MobileElement> googleAccountsList;

    String googleModalTitleText = "Wybierz konto dla aplikacji Lupe2";


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void isLoaded() throws Error {

        if(!this.isPageLoaded()) {
            throw new Error("ERROR: Obiekt 'LoginPage' nie przeszedl testow dostepnosci i widocznosci elementow");
        }

        System.out.println("INFO: Udalo sie zaladowac obiekt 'LoginPage'");
    }

    @Override
    protected void load() {
    }

    //przykladowa metoda
    public boolean isPageLoaded() {
        //inputs
        ElementHelper.textInputVerify(usernameInput);
        ElementHelper.textInputVerify(passwordInput);

        //buttons
        //buttonVerify(googleLoginButton);
        ElementHelper.buttonVerify(facebookLoginButton);
        ElementHelper.buttonVerify(accountLoginButton);
        ElementHelper.buttonVerify(guestLoginButton);

        //sprawdza czy znajduje sie w poprawnym Activity
        Assert.assertEquals(ElementHelper.getCurrentAndroidActivity(driver),".LoginActivity");
        System.out.println(ElementHelper.getCurrentAndroidActivity(driver)); //debug
        return true;
    }

    public MainPage signInAsGuest() {
        try {
            guestLoginButton.click();
        }
        catch (Exception ex) {
            throw new Error("ERROR: Nie udalo sie przejsc logowania jako gosc | "+ex);
        }
        return new MainPage(driver);
    }

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
