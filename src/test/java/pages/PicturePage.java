package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

/**
 * Created by Pawel on 2017-04-27.
 */
public class PicturePage extends AbstractPage {

    public PicturePage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {

        //sprawdza czy znajduje sie w poprawnym Activity
        //waitForActivity(".DetailsActivity");
        //System.out.println("INFO: Przejście do: "+getCurrentAndroidActivity());

        //elementy
        //isVisible(actionBar);
        return true;
    }

}
