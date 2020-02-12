package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends BasePage{
    @FindBy(id = "com.tajawal:id/englishBorderLinear")
    private WebElement englishLanguageButton;

    @FindBy(id = "com.tajawal:id/arabicView")
    private WebElement arabicLanguageButton;

    @FindBy(id = "com.tajawal:id/action_sticky_textview")
    private WebElement continueButton;

    @FindBy(id = "com.tajawal:id/flightButton")
    private WebElement flightsButton;

    public WelcomePage(AndroidDriver driver){
        super(driver);
    };

    public void setEnglishLanguage(){
        if (checkIfElementIsChoosen(englishLanguageButton))
            clickOnElement(englishLanguageButton);
    }

    public FlightsPage navigateToFlightsPage(){
        clickOnElement(continueButton);
        waitForElementToBeClickable(flightsButton);
        clickOnElement(flightsButton);
        return PageFactory.initElements(driver, FlightsPage.class);
    }

}
