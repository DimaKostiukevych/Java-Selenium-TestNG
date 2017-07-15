package tests;


import org.testng.annotations.Test;

/**
 * Created by maciej on 15.07.2017.
 */
public class PlaygroundTest  extends GUITest{


    @Test
    public void testLol(){
        driver.get("http://google.com");
    }

}
