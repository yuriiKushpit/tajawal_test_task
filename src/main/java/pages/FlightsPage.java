package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Randomizer;

import java.util.Calendar;
import java.util.List;

import static utils.Dates.getMonthName;
import static utils.Randomizer.getRandomNumber;

public class FlightsPage extends BasePage {
    private final static String oneAdult = "1";

    private final static int firstDay = 1;
    private final static int firstTenDays = 10;
    private final static int nextDaysOfMonth = 28;

    public final static String origin = "ORIGIN";
    public final static String destination = "DESTINATION";

    private final static String[] orgins = {"DXB", "AUH", "SHJ", "JED", "RUH"};
    private final static String[] destanations = {"AMM", "CAI", "DEL", "KHI", "PAR"};

    private final static int twoMonthAfterCurrent = 3;

    private final static String adultAndEconomy = "1 Adult - Economy";


    @FindBy(id = "com.tajawal:id/origin_container")
    private WebElement originLabel;

    @FindBy(id = "com.tajawal:id/destination_container")
    private WebElement destinationLabel;

    @FindBy(id = "com.tajawal:id/departure_date_container")
    private WebElement departureDateLabel;

    @FindBy(id = "com.tajawal:id/edAirportSearch")
    private WebElement searchField;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"month_name_view\"]")
    private WebElement monthLabel;

    @FindBy(id = "com.tajawal:id/confirmBtn")
    private WebElement confirmButton;

    @FindBy(id = "com.tajawal:id/class_and_passenger_value")
    private WebElement passangerAndCabinClassLabel;

    @FindBy(id = "com.tajawal:id/searchFlights")
    private WebElement searchFlightsButton;

    @FindBy(id = "com.tajawal:id/tvAirportLocationName")
    private WebElement airoportLabel;

    @FindBy(xpath = "(//android.widget.TextView[@content-desc=\"month_name_view\"])[1]/" +
            "following-sibling::com.prolificinteractive.materialcalendarview.MaterialCalendarView" +
            "//android.widget.CheckedTextView")
    private List<WebElement> days;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.tajawal:id/rows_container\"]/android.widget.FrameLayout")
    private WebElement economyCabinClass;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.tajawal:id/add_remove_value\"]")
    private WebElement numberOfAdults;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.tajawal:id/remove_icon\"]")
    private WebElement decreaseNumberOfAdults;


    FlightsPage(AndroidDriver<MobileElement> driver) {
        super(driver);
    }


    public void navigateToSearch() {
        clickOnElement(originLabel);
    }

    public void chooseRandomOriginAndDestination(String value) {
        if (value.equals(origin))
            sendKeysToElement(searchField, Randomizer.getRandomValueFromArray(orgins));
        if (value.equals(destination))
            sendKeysToElement(searchField, Randomizer.getRandomValueFromArray(destanations));
        waitForElementToBeClickable(airoportLabel);
        clickOnElement(airoportLabel);
    }

    public void chooseFutureDate() {
        clickOnElement(departureDateLabel);
        while (getTextOfElement(monthLabel).toLowerCase().contains(getMonthName(Calendar.getInstance().get(Calendar.MONTH) + twoMonthAfterCurrent))) {
            scroll();
        }
        clickOnElement(days.get(getRandomNumber(firstDay, firstTenDays)));
        clickOnElement(days.get(getRandomNumber(firstTenDays, nextDaysOfMonth)));
        clickOnElement(confirmButton);
    }

    public void chooseAdultAndEconomy(){
        if (!getTextOfElement(passangerAndCabinClassLabel).equals(adultAndEconomy)){
            clickOnElement(passangerAndCabinClassLabel);
            waitForElementToBeClickable(economyCabinClass);
            clickOnElement(economyCabinClass);
            while (getTextOfElement(numberOfAdults).equals(oneAdult)){
                clickOnElement(decreaseNumberOfAdults);
            }
        }
    }

    public GeneratedFlightPage searchForFlights(){
        clickOnElement(searchFlightsButton);
        return PageFactory.initElements(driver, GeneratedFlightPage.class);
    }
}
