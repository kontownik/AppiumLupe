package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

import utility.PropertyValues;

/**
 * Created by Pawel on 2017-04-19.
 */
public class BaseTest {
    protected static AppiumDriver driver = null;
    private static DesiredCapabilities capabilities = null;
    PropertyValues properties = new PropertyValues();

    @BeforeMethod(alwaysRun=true)
    public void beforeMethod(ITestContext iTestContext) throws MalformedURLException {
        capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1"); // adb -s " + deviceID + " shell getprop ro.build.version.release
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android"); //wartosc ponoc nie uzywana ale wymagana
        capabilities.setCapability(MobileCapabilityType.UDID, "YT910NB72F"); //ID zalezne od urzadzenia, pobiera sie z "adb devices"
        //File appDir = new File(userDir, fileName);
        //capabilities.setCapability(MobileCapabilityType.APP, appDir.getAbsolutePath());
        //Java package of the Android app you want to run.
        //ANDROID_HOME\sdk\build-tools\android-4.4.2>aapt dump badging path_to_apk_file
        capabilities.setCapability("appPackage", "pl.bitsa.lupe2");
        /*
         * Activity name for the Android activity you want to launch from your package.
         * This often needs to be preceded by a . (e.g., .MainActivity instead of MainActivity).
         */
        capabilities.setCapability("appActivity", "pl.bitsa.lupe2.SplashActivity");
        //Activity name for the Android activity you want to wait for.
        capabilities.setCapability("appWaitActivity", ".LoginActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @AfterMethod(alwaysRun=true)
    public void afterMethod(ITestResult iTestResult, ITestContext iTestContext) throws Exception {
        try {
            driver.quit();
        } catch (Exception e) {}
    }

}
