package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Pawel on 2017-04-27.
 */
public class MapPage extends AbstractPage {

    private static final String refreshButtonLocator = "pl.bitsa.lupe2:id/action_refresh";
    private static final String addNewReportButtonLocator = "pl.bitsa.lupe2:id/fab";
    private static final String topToolbarLocator = "pl.bitsa.lupe2:id/toolbar";


    @AndroidFindBy(id = refreshButtonLocator)
    private WebElement refreshButton;
    @AndroidFindBy(id = addNewReportButtonLocator)
    private WebElement addNewReportButton;
    @AndroidFindBy(id = topToolbarLocator)
    private WebElement topToolbar;


    public MapPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {

        //sprawdza czy znajduje sie w poprawnym Activity
        waitForActivity(".MapActivity");
        System.out.println("INFO: Przejście do: "+getCurrentAndroidActivity());

        //buttons
        buttonVerify(refreshButton);
        buttonVerify(addNewReportButton);

        //elementy
        isVisible(topToolbar);
        return true;
    }

    public void useAddButton(){
        addNewReportButton.click();
    }

    //TODO: przeniesc jako toolbarpage
    public void openMenu(){
        WebElement menuButton = topToolbar.findElement(By.className("android.widget.ImageButton"));
        System.out.println(menuButton.getText());
        menuButton.click();
    }

}
