package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Pawel on 2017-04-27.
 */
public class MapPage extends AbstractPage {

    private static final String refreshButtonLocator = "pl.bitsa.lupe2:id/action_refresh";
    private static final String addButtonLocator = "pl.bitsa.lupe2:id/fab";
    private static final String topToolbarLocator = "pl.bitsa.lupe2:id/toolbar";
    private static final String mapLayerLocator = "private static final String ";
    private static final String markerContainerLocator = "//android.view.View[contains(@content-desc,'Mapa Google')]";
    private static final String singleMarkerLocator = "//android.view.View[contains(@content-desc,'Mapa Google')]/android.view.View";
    private static final String detailsWindowLocator = "pl.bitsa.lupe2:id/info_layout";
    private static final String detailsTitleLocator = "pl.bitsa.lupe2:id/text_bottom_sheet_title";
    private static final String detailsDescriptionLocator = "pl.bitsa.lupe2:id/text_bottom_sheet_description";
    private static final String detailsButtonLocator = "pl.bitsa.lupe2:id/button_sheet_details";

    private static final String singleAreaLocator = "";
    private static final String touchOutsideLocator = "pl.bitsa.lupe2:id/touch_outside";


    @AndroidFindBy(id = refreshButtonLocator)
    private WebElement refreshButton;
    @AndroidFindBy(id = addButtonLocator)
    private WebElement addButton;
    @AndroidFindBy(id = topToolbarLocator)
    private WebElement topToolbar;
    @AndroidFindBy(id = mapLayerLocator)
    private WebElement mapLayer;
    @AndroidFindBy(xpath = markerContainerLocator)
    private WebElement markerContainer;
    @AndroidFindBy(xpath = singleMarkerLocator)
    private List<WebElement> markersList;

    //szczegoly po kliknieciu na marker
    @AndroidFindBy(id = detailsWindowLocator)
    private WebElement detailsWindow;
    @AndroidFindBy(id = detailsTitleLocator)
    private WebElement detailsTitle;
    @AndroidFindBy(id = detailsDescriptionLocator)
    private WebElement detailsDescription;
    @AndroidFindBy(id = detailsButtonLocator)
    private WebElement detailsButton;

    //tlo
    @AndroidFindBy(id = touchOutsideLocator)
    private WebElement touchOutside;

    public MapPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {

        //sprawdza czy znajduje sie w poprawnym Activity
        waitForActivity(".MapActivity");
        System.out.println("INFO: Przejście do: "+getCurrentAndroidActivity());

        //buttons
        buttonVerify(refreshButton);
        buttonVerify(addButton);

        //elementy
        isVisible(topToolbar);
        isVisible(mapLayer);
        isVisible(mapLayer);
        isVisible(markerContainer);
        return true;
    }

    public boolean isDetailsLoaded(){
        try {
            isVisible(detailsWindow);
            isVisible(detailsTitle);
            isVisible(detailsDescription);
            buttonVerify(detailsButton);
            System.out.println("INFO: Udało się otworzyć okno Szczegółów");
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public void useAddButton(){
        addButton.click();
    }

    public void clickMarkerByIndex(Integer index){
        driver.findElement(By.xpath(singleMarkerLocator+"["+index+"]")).click();
    }

    public void printCurrentDetails(){
        try {
            String title = detailsTitle.getText();
            String description = detailsDescription.getText();
            System.out.println("Tytuł: "+title+"\nOpis: "+description);
        }
        catch (Exception ex){
            throw new Error("ERROR: Nie można wyświetlić szczegółów markera | "+ex);
        }
    }

    public void clickOutside(){
        try {
            touchOutside.click();
        }
        catch (Exception ex){
            throw new Error("ERROR: Nie można cofnąć za pomocą clickOutside");
        }
    }

    //TODO: przeniesc jako toolbarpage
    public void openMenu(){
        WebElement menuButton = topToolbar.findElement(By.className("android.widget.ImageButton"));
        System.out.println(menuButton.getText());
        menuButton.click();
    }

}
