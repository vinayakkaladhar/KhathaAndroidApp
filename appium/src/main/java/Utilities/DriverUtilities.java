package Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverUtilities {
    public static AppiumDriver driver;

    public  AppiumDriver getDriver() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.ANDROID);
        cap.setCapability("enablePerformanceLogging", true);
        cap.setCapability("platformVersion","8.0");
        cap.setCapability("deviceName","emulator-5554");
        //cap.setCapability(MobileCapabilityType.APP, "/Users/cb-vinayak/Downloads/khata-book-udhar-bahi-khata-credit-ledger-account.apk");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),cap);
        return driver;


    }
}
