package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public abstract class BasePage {

    private static final int defaultTimeWait = 60;

    protected AndroidDriver<MobileElement> driver;

    public BasePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    protected boolean checkIfElementIsChoosen(WebElement element){
        return element.getAttribute("clickable").equals("false");
    }

    protected void clickOnElement(WebElement element){
        element.click();
    }

    protected void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeWait);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void sendKeysToElement(WebElement element, String text){
        element.sendKeys(text);
    }

    protected String getTextOfElement(WebElement element){
        return element.getText();
    }

    protected void scroll(){
        Dimension size = driver.manage().window().getSize();
        int starty = (int) (size.height * 0.80);
        int endy = (int) (size.height * 0.20);
        int startx = size.width / 2;
        System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

        new TouchAction(driver).press(point(startx, starty)).waitAction(waitOptions(Duration.ofMillis(2000)))
                .moveTo(point(startx, endy)).release().perform();
    }
}
