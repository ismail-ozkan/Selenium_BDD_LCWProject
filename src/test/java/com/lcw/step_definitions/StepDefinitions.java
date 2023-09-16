package com.lcw.step_definitions;

import com.lcw.pages.BebekPage;
import com.lcw.pages.HomePage;
import com.lcw.pages.SepetPage;
import com.lcw.utilities.BrowserUtils;
import com.lcw.utilities.ConfigurationReader;
import com.lcw.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

public class StepDefinitions {

    HomePage homePage = new HomePage();
    BebekPage bebekPage = new BebekPage();
    SepetPage sepetPage = new SepetPage();
    static String productId;// create class level variable to use more than one steps for verification


    @Given("User goes to LCW homepage")
    public void userGoesToLCWHomepage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("homepageUrl"));
        String actualTitle = Driver.getDriver().getTitle();
        String expectedPartialTitle = "LC Waikiki";
        assertTrue(actualTitle.contains(expectedPartialTitle));
    }

    @When("User clicks on Bebek module button")
    public void user_clicks_on_bebek_module_button() {
        homePage.bebekModuleButton.click();
        String actualURL = Driver.getDriver().getCurrentUrl();
        String expectedPartialURL = "bebek";
        assertTrue(actualURL.contains(expectedPartialURL));
    }

    @When("User clicks on one of the category on the Bebek page")
    public void user_clicks_on_one_of_the_category_on_the_bebek_page() {
        bebekPage.listOfCategories.get(0).click();
        String actualTitle = Driver.getDriver().getTitle();
        String expectedPartialTitle = "En Favori Desenler";
        assertTrue(actualTitle.contains(expectedPartialTitle));
    }

    @When("User clicks on first product on the list of products")
    public void user_clicks_on_first_product_on_the_list_of_products() {
        homePage.cookiesAcceptButton.click();
        bebekPage.firstProductOnTheList.click();
    }

    @When("User chooses one of the available sizes")
    public void user_chooses_one_of_the_available_sizes() {
        bebekPage.bedenButton.click();
        for (WebElement eachProductSize : bebekPage.listOfProductSizes) {
            if (!eachProductSize.getAttribute("class").contains("disabled")) {
                eachProductSize.click();
                break;
            }
        }
        // store the product id in class level variable to use following step for verification
        productId = bebekPage.selectedProductId.getText();
    }

    @When("User clicks on Sepete Ekle Button")
    public void user_clicks_on_sepete_ekle_button() {
        BrowserUtils.waitFor(2);
        bebekPage.sepeteEkleButton.click();
    }

    @Then("User should be able to see {string} message")
    public void user_should_be_able_to_see_message(String sepeteEklendiMessage) {
        // I created a new method with FluentWait to catch the text of the element which appears in a short time on page
      String actualMessage = BrowserUtils.waitWithFluentWaitForGetTextOfElement(bebekPage.sepeteEklendiMessage, 6,100);
        System.out.println(bebekPage.sepeteEklendiMessage.getText());
        assertEquals(sepeteEklendiMessage,actualMessage);
    }

    @Then("User clicks Sepetim Button")
    public void user_clicks_sepetim_button() {
        bebekPage.sepetimButton.click();
        String actualURL = Driver.getDriver().getCurrentUrl();
        assertTrue(actualURL.endsWith("sepetim"));
    }

    @Then("User should see the same product in Sepet page")
    public void user_should_see_the_same_product_in_sepet_page() {
        assertEquals(productId, sepetPage.productIdOnTheCart.getText());
    }

    @Then("User clicks LC Waikiki logo to go to homepage")
    public void user_clicks_lc_waikiki_logo_to_go_to_homepage() {
        homePage.brandLogoOnHeader.click();
    }

    @Then("User should be able to see homepage")
    public void user_should_be_able_to_see_homepage() {
        String actualURL = Driver.getDriver().getCurrentUrl();
        assertEquals(ConfigurationReader.getProperty("homepageUrl"), actualURL);
    }


}
