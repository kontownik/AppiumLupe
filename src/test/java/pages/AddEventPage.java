package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

/**
 * Created by Pawel on 2017-04-27.
 */
public class AddEventPage extends AbstractPage {

    private static final String refreshButtonLocator = "pl.bitsa.lupe2:id/action_refresh";
    private static final String actionBarLocator = "pl.bitsa.lupe2:id/action_bar";
    private static final String toolbarGoBackLocator = "//android.widget.ImageButton[contains(@content-desc,'Przejdź wyżej')]";

    private static final String categoryLocator = "pl.bitsa.lupe2:id/text_event_details_category";
    private static final String descriptionLocator = "pl.bitsa.lupe2:id/event_card_title_description";
    private static final String addressLocator = "pl.bitsa.lupe2:id/text_event_details_address";
    private static final String dateLocator = "pl.bitsa.lupe2:id/text_event_details_date";
    private static final String notesLocator = "pl.bitsa.lupe2:id/text_event_details_notes";
    private static final String thumbnailContainerLocator = "pl.bitsa.lupe2:id/horizontal_scroll_thumbnails";
    private static final String firstThumbnailLocator = "pl.bitsa.lupe2:id/image_event_thumbnail";

    private static final String touchOutsideLocator = "pl.bitsa.lupe2:id/touch_outside";

    @AndroidFindBy(id = refreshButtonLocator)
    private WebElement refreshButton;
    @AndroidFindBy(id = actionBarLocator)
    private WebElement actionBar;
    @AndroidFindBy(xpath = toolbarGoBackLocator)
    private WebElement toolbarGoBack;

    @AndroidFindBy(id = categoryLocator)
    private WebElement category;
    @AndroidFindBy(id = descriptionLocator)
    private WebElement description;
    @AndroidFindBy(id = addressLocator)
    private WebElement address;
    @AndroidFindBy(id = dateLocator)
    private WebElement date;
    @AndroidFindBy(id = notesLocator)
    private WebElement notes;
    @AndroidFindBy(id = thumbnailContainerLocator)
    private WebElement thumbnailContainer;
    @AndroidFindBy(id = firstThumbnailLocator)
    private WebElement firstThumbnail;

    //tlo
    @AndroidFindBy(id = touchOutsideLocator)
    private WebElement touchOutside;

    public AddEventPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {

        //sprawdza czy znajduje sie w poprawnym Activity
        waitForActivity(".AddEventActivity");
        System.out.println("INFO: Przejście do: "+getCurrentAndroidActivity());

        //elementy
        isVisible(actionBar);
        isVisible(toolbarGoBack);
        isVisible(category);
        isVisible(address);
        isVisible(date);
        isVisible(notes);
        return true;
    }

    public void printCurrentDetails(){
        try {
            String categoryText = category.getText();
            String descriptionText = description.getText();
            String addressText = address.getText();
            String dateText = date.getText();
            String notesText = notes.getText();
            System.out.println("Kategoria: "+categoryText+"\nOpis: "+descriptionText+"\nAdres: "+addressText+"\nData: "+dateText+"\nUwagi: "+notesText);
        }
        catch (Exception ex){
            throw new Error("ERROR: Nie można wyświetlić szczegółów | "+ex);
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

}
