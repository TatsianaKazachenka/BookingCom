package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;

public class BookingSteps {
    String city;
    public static final String RATING_HOTEL = "//*[contains(text(), '%s')]/ancestor::*[contains(@class ,'sr_property_block_main_row')]//*[@class='bui-review-score__badge']";
    public static final String URL = "https://www.booking.com/";

    @Given("User is looking for {string} city")
    public void userIsLookingForCity(String city) {
        this.city = city;
    }

    @When("User does search")
    public void userDoesSearch() {
        open(URL);
        $(By.id("ss")).sendKeys(city);
        $(By.className("sb-searchbox__button")).click();
    }

    @Then("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String hotel) {
        assertThat($$(".sr-hotel__name").texts(), hasItem(hotel));
    }

    @Then("Rating of the hotel {string} is {string}")
    public void ratingHotel(String hotel, String rating) {
        String getRating = $(By.xpath(String.format(RATING_HOTEL, hotel))).getText().trim();
        assertEquals(rating, getRating);
    }
}
