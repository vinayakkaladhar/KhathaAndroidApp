package POM;

import Utilities.DriverUtilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class LoginPage extends DriverUtilities {

    AppiumDriver driver;

    public LoginPage() throws MalformedURLException {
        driver = getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.vaibhavkalpe.android.khatabook:id/efb_add_customer")
    private AndroidElement addCustomer;

    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_deny_button")
    private AndroidElement denyOption;

    @AndroidFindBy(id = "com.vaibhavkalpe.android.khatabook:id/nameInput")
    private AndroidElement customerInput;

    @AndroidFindBy(className = "android.widget.Button")
    private AndroidElement saveButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.Toast")
    private AndroidElement toastContent;

    @AndroidFindBy(id = "com.vaibhavkalpe.android.khatabook:id/youGave")
    private AndroidElement givenButton;

    @AndroidFindBy(id = "com.vaibhavkalpe.android.khatabook:id/youGot")
    private AndroidElement gotButton;

    @AndroidFindBy(id = "com.vaibhavkalpe.android.khatabook:id/amount_et")
    private AndroidElement amountInput;

    @AndroidFindBy(id = "com.vaibhavkalpe.android.khatabook:id/dataLayout")
    private AndroidElement balanceData;

    @AndroidFindBy(id = "com.vaibhavkalpe.android.khatabook:id/reportTextView")
    private AndroidElement reportOption;

    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.TextView[2]")
    private AndroidElement balance;

    @AndroidFindBy(id = "com.vaibhavkalpe.android.khatabook:id/mobileInput")
    private AndroidElement mobileNumber;

    @AndroidFindBy(id = "com.vaibhavkalpe.android.khatabook:id/paymentTextView")
    private AndroidElement paymentButton;


    public void enterCustomerDetails(String customerName,String phoneNumber) throws InterruptedException {
        waitForElement(addCustomer);
        addCustomer.click();
        waitForElement(denyOption);
        denyOption.click();
        customerInput.click();
        customerInput.clear();
        customerInput.sendKeys(customerName);
        mobileNumber.click();
        mobileNumber.sendKeys(phoneNumber);
        saveButton.click();
        Thread.sleep(2000);
    }

    public void enterAmountGiven(String amount) throws InterruptedException {
        givenButton.click();
        waitForElement(amountInput);
        amountInput.click();
        char a[] = amount.toCharArray();
        for(int i=0;i<a.length;i++){
            driver.findElementByXPath("//android.widget.TextView[@text='"+a[i]+"']").click();
        }
        waitForElement(saveButton);
        saveButton.click();
        Thread.sleep(4000);
    }

    public void enterAmountReceived(String amount) throws InterruptedException {
        gotButton.click();
        waitForElement(amountInput);
        amountInput.click();
        char a[] = amount.toCharArray();
        for(int i=0;i<a.length;i++){
            driver.findElementByXPath("//android.widget.TextView[@text='"+a[i]+"']").click();
        }
        waitForElement(saveButton);
        saveButton.click();
        Thread.sleep(4000);
    }

    public void clickNotNowOption() throws InterruptedException {
        driver.findElementByXPath("//android.widget.Button[@text='NOT NOW']").click();
    }

    public boolean getData(int row,String givenAmount, String gotAmount) throws InterruptedException {

        return driver.findElementByXPath("//androidx.cardview.widget.CardView[" + row + "]/android.widget.LinearLayout[@resource-id='com.vaibhavkalpe.android.khatabook:id/dataLayout']/android.widget.TextView[1]").getText().equals(givenAmount)
                && driver.findElementByXPath("//androidx.cardview.widget.CardView[" + row + "]/android.widget.LinearLayout[@resource-id='com.vaibhavkalpe.android.khatabook:id/dataLayout']/android.widget.TextView[2]").getText().equals(gotAmount);

    }

    public boolean verifyBalance(String balanceAmount) throws InterruptedException {
        reportOption.click();
        return balance.getText().equals(balanceAmount);

    }

    public boolean verifyToastContent(String message) throws InterruptedException {
        try{
            paymentButton.click();
            return toastContent.getText().equals(message);
        }catch (Exception e){

        }
return false;

    }
}
