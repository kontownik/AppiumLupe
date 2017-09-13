package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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
public class MainPage extends AbstractPage {

    private static final String mainTitleLocator = "pl.bitsa.lupe2:id/ld_title"; //city choose
    private static final String refreshButtonLocator = "pl.bitsa.lupe2:id/action_refresh";
    private static final String mainListViewLocator = "pl.bitsa.lupe2:id/recyclerview";
    private static final String addNewReportButtonLocator = "pl.bitsa.lupe2:id/fab";
    private static final String topToolbarLocator = "pl.bitsa.lupe2:id/toolbar";

    //brak danych do wyswietlenia 9np. brak zgloszen na liscie)
    private static final String textNoDataLocator = "pl.bitsa.lupe2:id/text_no_data";

    //bloczek ze zgloszeniem na liscie
    private static final String singleCardViewLocator = "pl.bitsa.lupe2:id/card_view";
    private static final String itemIconLocator = "pl.bitsa.lupe2:id/event_category_icon";
    private static final String itemCategoryLocator = "pl.bitsa.lupe2:id/event_category_text";
    private static final String itemDescriptionLocator = "pl.bitsa.lupe2:id/event_description_text";
    private static final String itemAddressLocator = "pl.bitsa.lupe2:id/event_address_text";
    // tylko w widoku "Moje zgłoszenia"
    private static final String itemStatusLocator = "pl.bitsa.lupe2:id/event_status_text";

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
    @AndroidFindBy(id = singleCardViewLocator)
    private List<WebElement> itemsList;


    public MainPage(AndroidDriver driver) {
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
            System.out.println("INFO: Lista główna zgłoszeń ma wpisy");
        }
        return false;
    }

    public CityChoosePage getCityChooseIfVisible() {
        try {
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(mainTitleLocator)));
            CityChoosePage cityChoosePage = new CityChoosePage(driver);
            return cityChoosePage;
        }
        catch (Exception e) {
            System.out.println("INFO: Nie widać okna wyboru miasta (mozliwe ze miasto jest juz wybrane)");
        }
        return null;
    }

    public void useAddButton(){
        addNewReportButton.click();
    }

    public void printText(){

    }

    public void verifyAndPrintVisibleItems(String view){
        Integer counter = 1;
        try {
            System.out.println("INFO: Widocznych wpisów: "+itemsList.size());
            for (WebElement item : itemsList) {
                isVisible(item.findElement(By.id(itemIconLocator)));
                WebElement category = item.findElement(By.id(itemCategoryLocator));
                WebElement description = item.findElement(By.id(itemDescriptionLocator));
                WebElement address = item.findElement(By.id(itemAddressLocator));
                isVisible(category);
                isVisible(description);
                isVisible(address);
                String singleItemText = "\nINFO: Wyświetlił się wpis(bloczek)["+counter+"]:\n->Kategoria: "+category.getText()+"\n->Opis: "+description.getText()+"\n->Address: "+address.getText();
                //status jest tylko w widoku moje zgloszenia
                if(view.equals("Moje zgłoszenia")){
                    WebElement status = item.findElement(By.id(itemStatusLocator));
                    singleItemText = singleItemText+"\n->Status: "+status.getText();
                }
                System.out.println(singleItemText);
                counter++;
            }
        }
        catch (Exception NoSuchElementException) {
            System.out.println("WARNING: W bloczku NR "+counter+" nie znaleziono wymaganych elementów");
        }
    }

    //TODO: przeniesc jako toolbarpage
    public void openMenu(){
        WebElement menuButton = topToolbar.findElement(By.className("android.widget.ImageButton"));
        System.out.println(menuButton.getText());
        menuButton.click();
    }

}
