package book_a_flight_tests;

import base_test_logic.BaseTest;
import org.testng.annotations.Test;
import pages.FlightsPage;
import pages.GeneratedFlightPage;

public class CheapeastFlightTest extends BaseTest {

    @Test
    public void bookTheCheapestFlight() {
        FlightsPage flightsPage = welcomePage.navigateToFlightsPage();
        flightsPage.navigateToSearch();
        flightsPage.chooseRandomOriginAndDestination(FlightsPage.origin);
        flightsPage.chooseRandomOriginAndDestination(FlightsPage.destination);
        flightsPage.chooseFutureDate();
        flightsPage.chooseAdultAndEconomy();
        GeneratedFlightPage currentFlight = flightsPage.searchForFlights();
        currentFlight.checkForCorrectDates();
        currentFlight.sortByCheapest();
        currentFlight.checkThatLowestPriceIsCorrect();
    }

}
