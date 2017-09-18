package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Pawel on 2017-04-27.
 */
public class AddEventPage extends AbstractPage {

    private static final String actionBarLocator = "pl.bitsa.lupe2:id/action_bar";
    private static final String toolbarGoBackLocator = "//android.widget.ImageButton[contains(@content-desc,'Przejdź wyżej')]";
    private static final String addPictureButtonLocator = "pl.bitsa.lupe2:id/action_add_pictures";

    private static final String categorySelectLocator = "pl.bitsa.lupe2:id/add_event_category_spinner";
    private static final String singleCategoryItemLocator = "android.widget.CheckedTextView"; //classname
    private static final String addressTextLocator = "pl.bitsa.lupe2:id/add_event_address_text";
    private static final String descriptionAddLocator = "pl.bitsa.lupe2:id/add_event_edit_description";
    private static final String confirmButtonLocator = "pl.bitsa.lupe2:id/add_event_confirm_button";
    private static final String cancelButtonLocator = "pl.bitsa.lupe2:id/add_event_cancel_button";

    private static final String touchOutsideLocator = "pl.bitsa.lupe2:id/touch_outside";

    @AndroidFindBy(id = actionBarLocator)
    private WebElement actionBar;
    @AndroidFindBy(xpath = toolbarGoBackLocator)
    private WebElement toolbarGoBack;
    @AndroidFindBy(id = addPictureButtonLocator)
    private WebElement addPictureButton;

    @AndroidFindBy(id = categorySelectLocator)
    private WebElement categorySelect;
    @AndroidFindBy(className = singleCategoryItemLocator)
    private List<WebElement> categoryList;
    @AndroidFindBy(id = addressTextLocator)
    private WebElement addressText;
    @AndroidFindBy(id = descriptionAddLocator)
    private WebElement descriptionAdd;
    @AndroidFindBy(id = confirmButtonLocator)
    private WebElement confirmButton;
    @AndroidFindBy(id = cancelButtonLocator)
    private WebElement cancelButton;

    public AddEventPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {

        //sprawdza czy znajduje sie w poprawnym Activity
        waitForActivity(".AddEventActivity");
        System.out.println("INFO: Przejście do: "+getCurrentAndroidActivity());

        //klawiatura
        nativeHideKeyboard();

        //elementy
        isVisible(actionBar);
        isVisible(toolbarGoBack);
        isVisible(addPictureButton);
        isVisible(addressText);
        isVisible(categorySelect);
        isVisible(descriptionAdd);
        isVisible(confirmButton);
        isVisible(cancelButton);
        return true;
    }

    public void setCategory(String value){
        //scrollIntoView("Kategoria");
        //isVisible(categorySelect);
        categorySelect.click();
        for(WebElement item:categoryList){
            if(item.getText().equals(value)){
                item.click();
                return;
            }
        }
    }

    public String getAddress(){
        //scrollIntoView("Adres");
        //isVisible(addressText);
        return addressText.getText();
    }

    public void setDescription(String value){
        descriptionAdd.click();
        descriptionAdd.sendKeys(value);
        checkIfTyped(descriptionAdd, value);
        nativeGoBack();
    }

    public void addPicture(String picFilename){
        addPictureButton.click();
    }

    public void addReport(String newCategory, String newDescription, String picFilename){
        setCategory(newCategory);
        setDescription(newDescription);
        if(!picFilename.equals("")){
            addPicture(picFilename);
        }
        confirmButton.click();
    }

}
