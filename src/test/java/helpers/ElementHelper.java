package helpers;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
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
    public static void textInputVerify(MobileElement textInput){
        Assert.assertTrue(textInput.isDisplayed(),"Input '"+textInput.getText()+"' jest niewidoczny");
        Assert.assertTrue(textInput.isEnabled(),"Input '"+textInput.getText()+"' jest niedostepny");
    }

    // sprawdza czy przycisk jest widoczny, dostepny i klikalny
    public static void buttonVerify(MobileElement button){
        Assert.assertTrue(button.isDisplayed(),"Przycisk '"+button.getAttribute("text")+"' jest niewidoczny");
        Assert.assertTrue(button.isEnabled(), "Przycisk '"+button.getAttribute("text")+"' jest niedostepny");
        Assert.assertTrue(Boolean.valueOf(button.getAttribute("clickable")),"Przycisk '"+button.getText()+"' jest nieklikalny");
    }

}
