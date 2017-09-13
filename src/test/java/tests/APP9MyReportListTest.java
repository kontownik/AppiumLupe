package tests;

import org.testng.annotations.Test;

/**
 * Created by Pawel on 2017-04-19.
 */
public class APP9MyReportListTest extends MainListTest {

    //POSITIVE
    @Test
    public void myReportListTest() throws Exception {
        mainListTest("Moje zgłoszenia");
    }

}
