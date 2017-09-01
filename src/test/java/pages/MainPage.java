package pages;

import helpers.ElementHelper;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Pawel on 2017-04-27.
 */
public class MainPage extends AbstractPage {

    private WebDriver driver;
    private static final String refreshButtonLocator = "pl.bitsa.lupe2:id/action_refresh";
    private static final String mainListViewLocator = "pl.bitsa.lupe2:id/mainListView";
    private static final String addNewReportButtonLocator = "pl.bitsa.lupe2:id/fab";
    private static final String topToolbarLocator = "pl.bitsa.lupe2:id/toolbar";

    @AndroidFindBy(id = refreshButtonLocator)
    private WebElement refreshButton;
    @AndroidFindBy(id = mainListViewLocator)
    private WebElement mainListView;
    @AndroidFindBy(id = addNewReportButtonLocator)
    private WebElement addNewReportButton;
    @AndroidFindBy(id = topToolbarLocator)
    private WebElement topToolbar;


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {

        //sprawdza czy znajduje sie w poprawnym Activity
        waitForActivity(".MainActivity");
        System.out.println("INFO: Przejście do: "+getCurrentAndroidActivity());

        //buttons
        buttonVerify(refreshButton);
        buttonVerify(addNewReportButton);

        return true;
    }

}
