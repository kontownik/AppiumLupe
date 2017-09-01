package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Pawel on 2017-04-27.
 */
public class CityChoosePage extends AbstractPage {

    private WebDriver driver;
    private static final String refreshButtonLocator = "pl.bitsa.lupe2:id/action_refresh";
    private static final String mainListViewLocator = "pl.bitsa.lupe2:id/mainListView";
    private static final String addNewReportButtonLocator = "pl.bitsa.lupe2:id/fab";

    @AndroidFindBy(id = refreshButtonLocator)
    private WebElement refreshButton;
    @AndroidFindBy(id = mainListViewLocator)
    private WebElement mainListView;
    @AndroidFindBy(id = addNewReportButtonLocator)
    private WebElement addNewReportButton;


    public CityChoosePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return true;
    }

    // --- przenisesc
    /*
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/ld_choices")
    private WebElement cityViewList;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/city_name")
    private List<WebElement> cityNamesList;


    String  modalTitleText = "Wybierz miasto";
    Integer expectedCityCount = 3;

    public boolean isCityChooseLoaded(){ //okno z wyborem miast
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOf(modalTitle));
        Assert.assertEquals(modalTitle.getText(),modalTitleText);
        Assert.assertTrue(cityViewList.isDisplayed(),"Lista wyboru miast jest niewidoczna");
        Assert.assertEquals(getcityCount(), expectedCityCount);
        //TUTAJ CHYBA TRZEBA DAC CATCH
        return true;
    }

    //podaje wielkosc listy z wyborem miast
    public Integer getcityCount(){
        return cityNamesList.size();
    }
    // ----
    */

}
