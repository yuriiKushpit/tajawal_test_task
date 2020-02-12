package base_test_logic;

import android_driver.AndroidDriverHandler;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.WelcomePage;

public class BaseTest {
    private AndroidDriver<MobileElement> driver;

    protected WelcomePage welcomePage;

    @BeforeTest
    public void setup() throws Exception {
        driver = AndroidDriverHandler.getDriverInstance();
        welcomePage = PageFactory.initElements(driver, WelcomePage.class);
        welcomePage.setEnglishLanguage();
    }

    @AfterTest
    public void tearDown() throws Exception {
            driver.quit();
    }
}
