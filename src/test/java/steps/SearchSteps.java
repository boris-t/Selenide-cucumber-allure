package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.SearchGooglePage;


public class SearchSteps {


        private SearchGooglePage searchGooglePage = new SearchGooglePage();

        @Given("^Navigate to Google page$")
        public void navigateToPage() throws Throwable {
            searchGooglePage.open();
        }

        @When("^Enter value \"([^\"]*)\"$")
        public void enterValue(String value) {
            searchGooglePage.enterSearchParam(value);
        }

        @And("^Click search button$")
        public void clickSearchButton() {
            searchGooglePage.clickSearch();

        }

        @Then("^First link have \"([^\"]*)\"$")
        public void checkFirstLink(String value) {
            searchGooglePage.checkFirstResult(value);
        }

}
