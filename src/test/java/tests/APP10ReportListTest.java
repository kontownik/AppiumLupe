package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CityChoosePage;
import pages.LoginPage;
import pages.MainPage;
import pages.MenuPage;

/**
 * Created by Pawel on 2017-04-19.
 */
public class APP10ReportListTest extends MainListTest {

    //POSITIVE
    @Test
    public void reportListTest() throws Exception {
        mainListTest("Lista zgłoszeń");
    }

}
