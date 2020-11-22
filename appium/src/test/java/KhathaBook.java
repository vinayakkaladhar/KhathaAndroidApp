import POM.LoginPage;
import Utilities.DriverUtilities;
import io.appium.java_client.*;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidBatteryInfo;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.NetworkSpeed;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.openqa.selenium.interactions.Actions;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;
import static org.testng.Assert.assertEquals;

@Listeners(Utilities.ListenerTest.class)
public class KhathaBook extends LoginPage {

    public AppiumDriver driver;
    public LoginPage loginPage;

    public KhathaBook() throws MalformedURLException {
        driver = DriverUtilities.driver;
    }

    @BeforeClass
    public void setUp() throws MalformedURLException {
        loginPage = new LoginPage();
    }

    @BeforeMethod
    public void startApp(){
        Activity activity = new Activity("com.vaibhavkalpe.android.khatabook","in.khatabook.android.app.base.presentation.ui.view.MainActivity");
        ((AndroidDriver) driver).startActivity(activity);
}

    @Test(priority = 0)
    public void verifyDebitCreditDetails() throws InterruptedException {
        loginPage.enterCustomerDetails("vin","");
        Assert.assertEquals(driver.findElementById("com.vaibhavkalpe.android.khatabook:id/newEntryHint").getText(),"Add first transaction of vin");
        Reporter.log("Customer: vin added");
        loginPage.enterAmountGiven("100");
        Reporter.log("First transaction - amount lent: ₹ 100");
        Assert.assertTrue(driver.findElementByXPath("//android.widget.TextView[@text='Send Free SMS to vin']").isDisplayed(),"Send Message option is displayed!");
        loginPage.clickNotNowOption();
        Assert.assertTrue(loginPage.getData(1,"₹ 100",""));
        Reporter.log("Record Shows ₹ 100 lent");
        loginPage.enterAmountReceived("70");
        Reporter.log("Second transaction - amount received: ₹ 70");
        Assert.assertTrue(loginPage.getData(1,"","₹ 70"));
        Reporter.log("Table record 1 Shows ₹ 70 received");
        Assert.assertTrue(loginPage.getData(2,"₹ 100",""));
        Reporter.log("Table record 2 Shows ₹ 100 lent");
        Assert.assertTrue(loginPage.verifyToastContent("You haven't added mobile number for this customer please add mobile number and try again"));
        Reporter.log("SMS/Whatsapp cannot be done since mobile number was not entered during customer creation");
        Assert.assertTrue(loginPage.verifyBalance("₹ 30"));
        Reporter.log("Report and PDF displays ₹ 30 yet to be collected from customer");
    }

    //@Test(priority = 0)
    public void verifyCustomerCreatedWithPhoneNumber() throws InterruptedException {
        loginPage.enterCustomerDetails("vin","9600199816");
        Reporter.log("customer vin has been created successfully with mobile number");
        Assert.assertEquals(driver.findElementById("com.vaibhavkalpe.android.khatabook:id/newEntryHint").getText(),"Add first transaction of vin");
        loginPage.enterAmountGiven("100");
        loginPage.clickNotNowOption();
        Assert.assertTrue(!loginPage.verifyToastContent("You haven't added mobile number for this customer please add mobile number and try again"));
        Reporter.log("SMS/Whatsapp can be done since mobile number was entered during customer creation");
    }

    //@Test(priority = 1)
    public void verifyCreationOfExistingCustomer() throws InterruptedException {
        loginPage.enterCustomerDetails("vin","9600199816");
        Reporter.log("Successfully routed to existing customer account");
        Assert.assertTrue(loginPage.getData(1,"₹ 100",""));

    }

    @AfterMethod
    public void stopApp(){
        ((AndroidDriver) driver).pressKey(new KeyEvent().withKey(AndroidKey.HOME));
    }
}
