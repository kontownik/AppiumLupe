package tests;

import org.testng.annotations.Test;
import pages.MapPage;
import pages.MenuPage;

/**
 * Created by Pawel on 2017-04-19.
 */
public class APP11ReportDetailsByMapTest extends BaseTest {

    //POSITIVE
    @Test
    public void reportMapTest() throws Exception {

        //Test sprawdzajacy mape, markery, popup szczegolow
        MapTest mTest = new MapTest();
        mTest.mapTest("Mapa zgłoszeń");

        // Test sprawdzajacy widok szczegolowy pojedynczego zgloszenia
        SingleEventTest seTest = new SingleEventTest();
        seTest.singleEventTest();

        //Wracam na mapę zgłoszeń
        MapPage mapPage = new MapPage(driver);
        mapPage.clickOutside();

        //Otwieram menu
        mapPage.openMenu();
        MenuPage menuPage = new MenuPage(driver);
        if(menuPage.isPageLoaded()){
            System.out.println("INFO: Udało się otworzyć menu boczne");
        }

        //Wylogowuje się przez menu
        menuPage.scrollToTextAndClick("Wyloguj");
        System.out.println("INFO: Wylogowano przez boczne menu");
    }

}
