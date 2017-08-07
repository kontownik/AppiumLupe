package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Pawel on 2017-04-19.
 */
public class LoginPage extends AbstractPage {

    @AndroidFindBy(id = "pl.bitsa.lupe2:id/et_username")
    private WebElement usernameInput;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/et_password")
    private WebElement passwordInput;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/bt_go_google")
    private WebElement googleLoginButton;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/bt_go_facebook")
    private WebElement facebookLoginButton;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/bt_go")
    private WebElement accountLoginButton;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/bt_guest")
    private WebElement guestLoginButton;

    //social login
    @AndroidFindBy(id = "com.google.android.gms:id/title")
    private WebElement googleModalTitle;
    @AndroidFindBy(id = "com.google.android.gms:id/account_name")
    private List<WebElement> googleAccountsList;

    String googleModalTitleText = "Wybierz konto dla aplikacji Lupe2";


    /*
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"pl.bitsa.lupe2:id/et_username\")")
    //@AndroidFindBy(xpath = "//*[@resource-id='pl.bitsa.lupe2:id/et_username']")
    private AndroidElement usernameInput;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"pl.bitsa.lupe2:id/et_password\")")
    private AndroidElement passwordInput;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"pl.bitsa.lupe2:id/bt_go_facebook\")")
    private AndroidElement googleLoginButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"pl.bitsa.lupe2:id/bt_go_google\")")
    private AndroidElement facebookLoginButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"pl.bitsa.lupe2:id/bt_go\")")
    private AndroidElement accountLoginButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"pl.bitsa.lupe2:id/bt_guest\")")
    private AndroidElement guestLoginButton;
    */


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //przykladowa metoda
    public boolean isLoginPageLoaded() {
        //inputs
        textInputVerify(usernameInput);
        textInputVerify(passwordInput);

        //buttons
        //buttonVerify(googleLoginButton);
        buttonVerify(facebookLoginButton);
        buttonVerify(accountLoginButton);
        buttonVerify(guestLoginButton);

        //sprawdza czy znajduje sie w poprawnym Activity
        Assert.assertEquals(getCurrentActivity(),".LoginActivity");
        System.out.println(getCurrentActivity()); //debug
        return true;
    }

    public boolean signInAsGuest() {
        guestLoginButton.click();
        return true;
    }

    public boolean signInAsUser(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        accountLoginButton.click();
        //sprawdzanie czy nie wyswietlil sie jakis komunikat bledu w przypadku kontrolki natywnej Androida (textedit) nie jest mozliwe
        return true;
    }

    public boolean signInAsGoogleUser(String socialEmail) {
        googleLoginButton.click();
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOf(googleModalTitle));
        Assert.assertEquals(googleModalTitle.getText(),googleModalTitleText);
        //wyszukuje adresu email na liscie kont
        for (WebElement item:googleAccountsList) {
            if(item.getText().equals(socialEmail)){
                item.click();
                return true;
            }
        }
        return false;
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
