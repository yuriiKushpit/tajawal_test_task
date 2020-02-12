package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.util.Assert;

public class GeneratedFlightPage extends BasePage {

    private static String lowestPrice;

    @FindBy(id = "com.tajawal:id/flight_list_subtitle")
    private WebElement flightDatesTitle;

    @FindBy(id = "com.tajawal:id/flights_result_sort_text")
    private WebElement sortLabel;

    @FindBy(id = "com.tajawal:id/flights_result_filter_text")
    private WebElement filterLabel;

    @FindBy(id = "com.tajawal:id/item_name")
    private WebElement lowestPriceLabel;

    @FindBy(id = "com.tajawal:id/finalPrice")
    private WebElement finalPriceLabel;

    @FindBy(id = "com.tajawal:id/tvPriceRangeBarFrom")
    private WebElement lowestRange;

    public GeneratedFlightPage(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    public void sortByCheapest(){
        clickOnElement(sortLabel);
        clickOnElement(lowestPriceLabel);
    }

    public void checkThatLowestPriceIsCorrect(){
        lowestPrice = getTextOfElement(finalPriceLabel);
        clickOnElement(filterLabel);
        Assert.isTrue(lowestPriceLabel.equals(getTextOfElement(lowestRange)));
    }
}
