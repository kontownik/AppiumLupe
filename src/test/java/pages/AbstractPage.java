package pages;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
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
        PageFactory.initElements(new AppiumFieldDecorator(driver, 3, TimeUnit.SECONDS), this);
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
                //System.out.println("DEBUG: "+value);
                return value.equals(activityText);
            }
        });
    }

    public void waitForVisibility(WebElement element) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForText(String activityName) {

    }

    public void clickOnListPosition(WebElement element, Integer position) {

    }

    //przycisk wstecz
    public void nativeGoBack() {
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }

    public void nativeHideKeyboard() {
        try{
            driver.hideKeyboard();
        }
        catch (Exception ex){
            System.out.println("WARNING: Nie udało się ukryć klawiatury (nie jest wyświetlona)");
        }
    }

    public void checkIfTyped(final WebElement element, final String expectedValue){
        new WebDriverWait(driver,10).until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                String value = element.getText();
                return value.equals(expectedValue);
            }
        });
    }

    public void scrollToTextAndClick(String value) {
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + value + "\"));").click();
        }
        catch (Exception e){
            throw new Error(e);
        }
    }

    public void scrollIntoView(String value){
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + value + "\"));");
        }
        catch (Exception e){
            throw new Error(e);
        }
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

    public void isErrorVisible() { //snackbar
        try {
            driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Błąd\");");
            new Error("ERROR: Znaleziono błąd! (snackbar)");
        }
        catch (Exception ex) {
            return;
        }
    }

    public void switchContext (){

    }

}
