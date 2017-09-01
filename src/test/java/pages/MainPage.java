package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Pawel on 2017-04-27.
 */
public class MainPage extends AbstractPage {

    private WebDriver driver;
    private static final String refreshButtonLocator = "pl.bitsa.lupe2:id/action_refresh";
    private static final String mainListViewLocator = "pl.bitsa.lupe2:id/recyclerview";
    private static final String addNewReportButtonLocator = "pl.bitsa.lupe2:id/fab";
    private static final String topToolbarLocator = "pl.bitsa.lupe2:id/toolbar";

    //brak danych do wyswietlenia 9np. brak zgloszen na liscie)
    private static final String textNoDataLocator = "pl.bitsa.lupe2:id/text_no_data";

    @AndroidFindBy(id = refreshButtonLocator)
    private WebElement refreshButton;
    @AndroidFindBy(id = mainListViewLocator)
    private WebElement mainListView;
    @AndroidFindBy(id = addNewReportButtonLocator)
    private WebElement addNewReportButton;
    @AndroidFindBy(id = topToolbarLocator)
    private WebElement topToolbar;
    @AndroidFindBy(id = textNoDataLocator)
    private WebElement textNoData;


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

        //elementy
        isVisible(mainListView);
        isVisible(topToolbar);
        return true;
    }

    public boolean isListEmpty() {
        try
        {
            isVisible(textNoData);
            return true;
        }
        catch(Exception ex) {
            System.out.println("INFO: Lista główna nie jest pusta");
        }
        return false;
    }

}
