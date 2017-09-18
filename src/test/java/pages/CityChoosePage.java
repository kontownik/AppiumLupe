package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Pawel on 2017-04-27.
 */
public class CityChoosePage extends AbstractPage {

    private WebDriver driver;
    private static final String mainTitleLocator = "pl.bitsa.lupe2:id/ld_title";
    private static final String messageLocator = "pl.bitsa.lupe2:id/ld_message";
    private static final String closeButtonLocator = "pl.bitsa.lupe2:id/ld_btn_yes";
    private static final String cityListContainerLocator =  "pl.bitsa.lupe2:id/ld_choices";
    private static final String singleCityItemLocator = "pl.bitsa.lupe2:id/city_name";
    private static final String cityChooseExpectedTitle = "Wybierz miasto";
    private Integer expectedCityCount = 3; //TODO: pobierac z restAPI

    private static final String reportAddedExpectedTitle = "Lupe";
    private static final String reportAddedExpectedMessage = "Zgłoszenie dodane";

    @AndroidFindBy(id = mainTitleLocator)
    private WebElement mainTitle;
    @AndroidFindBy(id = messageLocator)
    private WebElement message;
    @AndroidFindBy(id = cityListContainerLocator)
    private WebElement cityListContainer;
    @AndroidFindBy(id = singleCityItemLocator)
    private List<WebElement> cityList;
    @AndroidFindBy(id = closeButtonLocator)
    private WebElement closeButton;


    public CityChoosePage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {

        //sprawdza czy znajduje sie w poprawnym Activity
        waitForActivity(".MainActivity");
        System.out.println("INFO: Przejście do: "+getCurrentAndroidActivity());

        isVisible(mainTitle);
        return true;
    }

    //podaje tekst modala z wyborem miast
    public String getModalTitle(){
        return mainTitle.getText();
    }

    //podaje wielkosc listy z wyborem miast
    public Integer getcityCount(){
        return cityList.size();
    }

    public boolean verifyCityList(){
        isVisible(cityListContainer);
        if(!mainTitle.getText().equals(cityChooseExpectedTitle)) {
            return false;
        }
        if(!getcityCount().equals(expectedCityCount)){
            return false;
        }
        return true;
    }

    public boolean verifyReportAdded(){
        if(!mainTitle.getText().equals(reportAddedExpectedTitle)) {
            return false;
        }
        if(!message.getText().equals(reportAddedExpectedMessage)) {
            return false;
        }
        closeButton.click();
        return true;
    }

}
