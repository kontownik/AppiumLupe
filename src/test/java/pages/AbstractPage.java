package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pawel on 2017-04-19.
 */
public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), this);
    }

    // pobiera aktualne Androidowe Activity
    public String getCurrentActivity()
    {
        return ((AndroidDriver<MobileElement>)driver).currentActivity();
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
