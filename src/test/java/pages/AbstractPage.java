package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pawel on 2017-04-19.
 */
public abstract class AbstractPage {

    protected WebDriver driver;
    private static String activityText = "";

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), this);
    }

    // pobiera aktualne Androidowe Activity
    public String getCurrentAndroidActivity()
    {
        return ((AndroidDriver<MobileElement>)driver).currentActivity();
    }

    public void waitForActivity(String activityName)
    {
        this.activityText = activityName;
        new WebDriverWait(driver,10).until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                String value = getCurrentAndroidActivity();
                return value.equals(activityText);
            }
        });
    }

    // sprawdza czy input jest widoczny i dostepny
    public void textInputVerify(WebElement textInput){
        Assert.assertTrue(textInput.isDisplayed(),"Input '"+textInput.getText()+"' jest niewidoczny");
        Assert.assertTrue(textInput.isEnabled(),"Input '"+textInput.getText()+"' jest niedostepny");
    }

    // sprawdza czy przycisk jest widoczny, dostepny i klikalny
    public void buttonVerify(WebElement button){
        Assert.assertTrue(button.isDisplayed(),"Przycisk '"+button.getAttribute("text")+"' jest niewidoczny");
        Assert.assertTrue(button.isEnabled(), "Przycisk '"+button.getAttribute("text")+"' jest niedostepny");
        Assert.assertTrue(Boolean.valueOf(button.getAttribute("clickable")),"Przycisk '"+button.getText()+"' jest nieklikalny");
    }

    public void switchContext (){

    }



}
