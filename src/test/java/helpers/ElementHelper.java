package helpers;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Pawel on 2017-08-24.
 */
public class ElementHelper {

    // pobiera aktualne Androidowe Activity
    public static String getCurrentAndroidActivity(WebDriver driver)
    {
        return ((AndroidDriver<MobileElement>)driver).currentActivity();
    }

    // sprawdza czy input jest widoczny i dostepny
    public static void textInputVerify(WebDriver driver, By locator, Integer timeoutInSeconds){
        /*
        Assert.assertTrue(textInput.isDisplayed(),"Input '"+textInput.getText()+"' jest niewidoczny");
        Assert.assertTrue(textInput.isEnabled(),"Input '"+textInput.getText()+"' jest niedostepny");
        */
        Assert.assertTrue(isVisible(driver,locator,timeoutInSeconds),"Input oznaczony '"+locator+"' jest niewidoczny");
        //TODO: isEnabled
    }

    // sprawdza czy przycisk jest widoczny, dostepny i klikalny
    public static void buttonVerify(WebDriver driver, By locator, Integer timeoutInSeconds){
        /*
        Assert.assertTrue(button.isDisplayed(),"Przycisk '"+button.getAttribute("text")+"' jest niewidoczny");
        Assert.assertTrue(button.isEnabled(), "Przycisk '"+button.getAttribute("text")+"' jest niedostepny");
        Assert.assertTrue(Boolean.valueOf(button.getAttribute("clickable")),"Przycisk '"+button.getText()+"' jest nieklikalny");
        */
        Assert.assertTrue(isVisible(driver,locator,timeoutInSeconds),"Przycisk oznaczony '"+locator+"' jest niewidoczny");
        //TODO: isEnabled
        Assert.assertTrue(isClickable(driver,locator,timeoutInSeconds),"Przycisk oznaczony '"+locator+"' jest nieklikalny");
    }

    public static boolean isClickable (WebDriver driver, By locator, Integer timeoutInSeconds) {

        try
        {
            new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
        }
        catch (WebDriverException ex)
        {
            return false;
        }
        return true;
    }

    public static boolean isVisible(WebDriver driver, By locator, Integer timeoutInSeconds) {

        try
        {
            new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch (WebDriverException ex)
        {
            return false;
        }
        return true;
    }

}
