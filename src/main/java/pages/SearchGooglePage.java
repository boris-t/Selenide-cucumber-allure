package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchGooglePage {

    /*Opening url method*/

    public SearchGooglePage open() {

        Selenide.open("https://www.google.com/");
        return this;
    }

    /*Methods working with elements*/

    public void enterSearchParam(String value) {
        inputSearch.val(value);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void checkFirstResult(String value) {
        searchResults.get(0).shouldHave(text(value));
    }

    /*Locators*/

    private SelenideElement inputSearch = $("input[name='q']");
    private SelenideElement searchButton = $("input[name='btnK']");
    private ElementsCollection searchResults = $$("div[class='r'] h3");

}
