package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Pawel on 2017-04-27.
 */
public class MenuPage extends AbstractPage {

    private WebDriver driver;
    private static final String navContainerLocator = "pl.bitsa.lupe2:id/nav_view";
    private static final String personAvatarLocator = "pl.bitsa.lupe2:id/image_view_avatar";
    private static final String personDisplayNameLocator = "pl.bitsa.lupe2:id/text_view_app_bar_display_name";
    private static final String personEmailLocator = "pl.bitsa.lupe2:id/text_view_app_bar_mail";
    private static final String singleMenuItemLocator = "pl.bitsa.lupe2:id/design_menu_item_text";
    private static Integer expectedMenuItems = 10;


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


    public MenuPage(WebDriver driver) {
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

    public void logOut(){
        //TODO: scroll to
        //WebElement element = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='"+value+"']")));
    }

    public void printMenuItems(){
        System.out.println("INFO: Menu ("+getMenuSize()+"): ");
        for (WebElement item:itemsList){
            System.out.println(item.getText());
        }
    }
}
