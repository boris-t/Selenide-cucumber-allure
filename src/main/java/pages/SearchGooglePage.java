package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.ChromeDriverManager;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchGooglePage {

    /*Opening url method*/

    public SearchGooglePage open() {
        ChromeDriverManager.getInstance().setup();

        /*Run browser in headless mode*/
//        Configuration.headless = true;


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

    public void checkFirstResult(){
        searchResults.get(0).shouldHave(text("Selenide: concise UI tests in Java"));
    }


    /*Locators*/
    private SelenideElement inputSearch = $("input[name='q']");
    private SelenideElement searchButton = $("input[name='btnK']");
    private ElementsCollection searchResults = $$("div[class='r'] h3");



    public void clickOnElement(String element){
        $(element).click();
    }


}
