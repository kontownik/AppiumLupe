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
public class MainPage extends LoadableComponent<MainPage> {

    private WebDriver driver;

    @AndroidFindBy(id = "pl.bitsa.lupe2:id/ld_title")
    private WebElement modalTitle;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/action_refresh")
    private WebElement refreshButton;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/mainListView")
    private WebElement mainListView;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/fab")
    private WebElement addNewReportButton;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/ld_choices")
    private WebElement cityViewList;
    @AndroidFindBy(id = "pl.bitsa.lupe2:id/city_name")
    private List<WebElement> cityNamesList;


    String  modalTitleText = "Wybierz miasto";
    Integer expectedCityCount = 3;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void isLoaded() throws Error {

        if(!this.isPageLoaded()) {
            throw new Error("ERROR: Obiekt 'MainPage' nie przeszedl testow dostepnosci i widocznosci elementow");
        }

        System.out.println("INFO: Udalo sie zaladowac obiekt 'MainPage'");
    }

    @Override
    protected void load() {
    }

    public boolean isPageLoaded() { //TODO: rework city picker do osobnego obiektu
        //isCityChooseLoaded();
        //sprawdza czy znajduje sie w poprawnym Activity
        Assert.assertEquals(ElementHelper.getCurrentAndroidActivity(driver),".MainActivity");
        System.out.println(ElementHelper.getCurrentAndroidActivity(driver)); //debug
        return true;
    }

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

}
