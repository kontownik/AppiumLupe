package tests;

import pages.*;

/**
 * Created by Pawel on 2017-04-19.
 */
public class SingleEventTest extends BaseTest {

    public void singleEventTest() throws Exception {

        //* Zmienne wejsciowe

        //Klikam przycisk (przechodzę do widoku wybranego elementu)
        SingleEventPage singleEventPage = new SingleEventPage(driver);
        if(singleEventPage.isPageLoaded()){
            System.out.println("INFO: Udało się przejść na stronę szczegółów pojedynczego wpisu");
        }

        //Wyswietlam szczegóły
        singleEventPage.printCurrentDetails();

        //cofa sie natywnym przyciskiem, klika w czarne tlo (zeby dostac sie do menu)
        singleEventPage.nativeGoBack();
    }

}
