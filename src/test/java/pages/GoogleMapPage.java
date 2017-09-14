package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Pawel on 2017-04-27.
 */
public class GoogleMapPage extends AbstractPage {

    private WebDriver driver;
    private static final String locationMarkerLocator = "com.google.android.gms:id/selected_location_marker";
    private static final String mapContainerLocator = "com.google.android.gms:id/expanding_scroll_view";
    private static final String nearPlaceIconLocator = "com.google.android.gms:id/place_icon";
    private static final String selectMarkerLocator = "com.google.android.gms:id/select_marker_location";

    //card container
    private static final String cardContainerLocator = "com.google.android.gms:id/card_container";
    private static final String cardTitleLocator = "com.google.android.gms:id/card_title";
    private static final String placeNameLocator = "com.google.android.gms:id/place_name";
    private static final String placeAddressLocator = "com.google.android.gms:id/place_address";
    private static final String cancelButtonLocator = "com.google.android.gms:id/cancel_button";
    private static final String confirmButtonLocator = "com.google.android.gms:id/confirm_button";


    @AndroidFindBy(id = locationMarkerLocator)
    private WebElement locationMarker;
    @AndroidFindBy(id = mapContainerLocator)
    private WebElement mapContainer;
    @AndroidFindBy(id = nearPlaceIconLocator)
    private WebElement nearPlaceIcon;
    @AndroidFindBy(id = selectMarkerLocator)
    private WebElement selectMarker;

    //card container
    @AndroidFindBy(id = cardContainerLocator)
    private WebElement cardContainer;
    @AndroidFindBy(id = cardTitleLocator)
    private WebElement cardTitle;
    @AndroidFindBy(id = placeNameLocator)
    private WebElement placeName;
    @AndroidFindBy(id = placeAddressLocator)
    private WebElement placeAddress;
    @AndroidFindBy(id = cancelButtonLocator)
    private WebElement cancelButton;
    @AndroidFindBy(id = confirmButtonLocator)
    private WebElement confirmButton;

    public GoogleMapPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        isVisible(mapContainer);
        isVisible(locationMarker);

        //conajmniej jeden wpis (miejsce w pobliżu)
        isVisible(nearPlaceIcon);
        return true;
    }

    public boolean isCardContainerLoaded(){
        isVisible(cardContainer);
        return true;
    }

    public void pickPlace(){
        selectMarker.click();
    }

    //card container
    public void confirmCard() {
        confirmButton.click();
    }
}
