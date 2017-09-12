package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pawel on 2017-04-27.
 */
public class MenuPage extends AbstractPage {

    private static final String navContainerLocator = "pl.bitsa.lupe2:id/nav_view";
    private static final String personAvatarLocator = "pl.bitsa.lupe2:id/image_view_avatar";
    private static final String personDisplayNameLocator = "pl.bitsa.lupe2:id/text_view_app_bar_display_name";
    private static final String personEmailLocator = "pl.bitsa.lupe2:id/text_view_app_bar_mail";
    private static final String singleMenuItemLocator = "pl.bitsa.lupe2:id/design_menu_item_text";
    private static final String menuContainerLocator = "pl.bitsa.lupe2:id/design_navigation_view";

    private static final String reportsCountLocator =  "pl.bitsa.lupe2:id/nav_list_events";
    private static final String messagesCountLocator = "pl.bitsa.lupe2:id/nav_messages";


    @AndroidFindBy(id = navContainerLocator)
    private WebElement navContainer;
    @AndroidFindBy(id = personAvatarLocator)
    private WebElement personAvatar;
    @AndroidFindBy(id = personDisplayNameLocator)
    private WebElement personDisplayName;
    @AndroidFindBy(id = personEmailLocator)
    private WebElement personEmail;
    @AndroidFindBy(id = singleMenuItemLocator)
    private List<WebElement> itemsList;


    public MenuPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        isVisible(navContainer);
        isVisible(personAvatar);
        isVisible(personDisplayName);
        isVisible(personEmail);
        return true;
    }

    public String getPersonName() {
        return personDisplayName.getText();
    }

    public String getPersonEmail() {
        return personEmail.getText();
    }

    public int getMenuSize(){
        return itemsList.size();
    }

    public void scroll() {
        ((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+ "new UiSelector().text(\"Komunikaty\"));").click();
    }

    public void clickMenuItemByText(String menuItemText){

        for (WebElement item:itemsList){
            if(item.getAttribute("text").equals(menuItemText)){
                item.click();
                return;
            }
        }
        //jezeli po wykonaniu petli nie widac menuItema, scrolluj do ostatniego elementu i sprawdz liste petla
        //TODO: scroll to??
        WebElement lastItem = itemsList.get(itemsList.size()-1);
        new TouchAction(((AndroidDriver) driver)).press(lastItem).moveTo(100,100).release(); //waitAction((int)2000)
        this.clickMenuItemByText(menuItemText);

    }

    public void printMenuItems(){
        System.out.println("INFO: Menu widoczne na ekranie ("+getMenuSize()+"): ");
        for (WebElement item:itemsList){
            System.out.println("-> "+item.getText());
        }
    }
}
