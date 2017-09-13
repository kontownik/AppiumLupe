package tests;

import org.testng.annotations.Test;

/**
 * Created by Pawel on 2017-04-19.
 */
public class APP12MessageListTest extends MainListTest {

    //POSITIVE
    @Test
    public void reportListTest() throws Exception {
        mainListTest("Lista zgłoszeń");
    }

}
