package pages;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pawel on 2017-04-19.
 */
public abstract class AbstractPage {

    protected AndroidDriver driver;
    private static String activityText = "";

    public AbstractPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
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

    public void waitForText(String activityName) {

    }

    public void clickOnListPosition(WebElement element, Integer position) {

    }

    //przycisk wstecz
    public void nativeGoBack() {

    }

    public void scrollTo(WebElement element){
/*
        WebElement abc = driver.findElement(By.name8(elementName1));
        WebElement abc2 = driver.findElement(By.name8(elementName2));
        int x = abc.getLocation().getX();
        int y = abc.getLocation().getY();
        int x1 = abc2.getLocation().getX();
        int y1 = abc2.getLocation().getY();
        (AndroidDriver)driver.sw
*/
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

    //czy checkbos jest zaznaczony
    public void checkboxCheckedVerify(){

    }

    public void isVisible(WebElement element){
        Assert.assertTrue(element.isDisplayed(),"Element '"+element.getText()+"' jest niewidoczny");
    }

    public void switchContext (){

    }



}
