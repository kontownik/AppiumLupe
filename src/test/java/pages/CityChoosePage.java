package pages;

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
    private static final String cityListContainerLocator =  "pl.bitsa.lupe2:id/ld_choices";
    private static final String singleCityItemLocator = "pl.bitsa.lupe2:id/city_name";
    private static final String modalExpectedTitle = "Wybierz miasto";
    private Integer expectedCityCount = 3; //TODO: pobierac z restAPI

    @AndroidFindBy(id = mainTitleLocator)
    private WebElement mainTitle;
    @AndroidFindBy(id = cityListContainerLocator)
    private WebElement cityListContainer;
    @AndroidFindBy(id = singleCityItemLocator)
    private List<WebElement> cityList;


    public CityChoosePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        isVisible(cityListContainer);
        isVisible(mainTitle);
        verifyCityModal();
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

    public boolean verifyCityModal(){
        if(!mainTitle.getText().equals(modalExpectedTitle)) {
            return false;
        }
        if(!getcityCount().equals(expectedCityCount)){
            return false;
        }
        return true;
    }

}
