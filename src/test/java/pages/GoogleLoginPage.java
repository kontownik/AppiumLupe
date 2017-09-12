package pages;

import helpers.ElementHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
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
public class GoogleLoginPage extends LoadableComponent<GoogleLoginPage> {

    private WebDriver driver;

    //social login
    @AndroidFindBy(id = "com.google.android.gms:id/title")
    private MobileElement googleModalTitle;
    @AndroidFindBy(id = "com.google.android.gms:id/account_name")
    private List<MobileElement> googleAccountsList;

    String googleModalTitleText = "Wybierz konto dla aplikacji Lupe2";


    public GoogleLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void isLoaded() throws Error {

        if(!this.isPageLoaded()) {
            throw new Error("ERROR: Obiekt 'GoogleLoginPage' nie przeszedl testow dostepnosci i widocznosci elementow");
        }

        System.out.println("INFO: Udalo sie zaladowac obiekt 'GoogleLoginPagee'");
    }

    @Override
    protected void load() {
    }

    //przykladowa metoda
    public boolean isPageLoaded() {

        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(googleModalTitle));
            Assert.assertEquals(googleModalTitle.getText(), googleModalTitleText);

            //sprawdza czy znajduje sie w poprawnym Activity
            //Assert.assertEquals(ElementHelper.getCurrentAndroidActivity(driver),".LoginActivity");
            //System.out.println(ElementHelper.getCurrentAndroidActivity(driver)); //debug
            return true;
        }
        catch (Exception ex) {
            throw new Error("ERROR: Blad ladowania obiektow na GoogleLoginPage|"+ex);
        }
    }

    public void loginAction(String socialEmail) {

        //wyszukuje adresu email na liscie kont (zdefiniowanych wczesniej na telefonie)
        for (WebElement item:googleAccountsList) {
            if(item.getText().equals(socialEmail)){
                item.click();
            }
        }
    }
}
