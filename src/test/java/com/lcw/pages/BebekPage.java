package com.lcw.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BebekPage extends BasePage {

    @FindBy(xpath = "//*[@class='section flexcontainer']/div")
    public List<WebElement> listOfCategories;

    @FindBy(xpath = "(//*[@alt=\"kombiniAl\"]/../..)[1]")
    public WebElement firstProductOnTheList;

    @FindBy(xpath = "//div[@id='option-size']/a")
    public List<WebElement> listOfProductSizes;

    @FindBy(xpath = "(//span[.='Beden'])[1]")
    public WebElement bedenButton;

    @FindBy(xpath = "(//div[@class='look-product-detail-col look-product-code hidden-xs hidden-sm']//p)[2]")
    public WebElement selectedProductId;

    @FindBy(xpath = "(//a[@data-tracking-label='SepeteEkle'])[2]")
    public WebElement sepeteEkleButton;

    @FindBy(xpath = "(//a[contains(text(),'SEPETE EKLEN')])[2]")
    public WebElement sepeteEklendiMessage;

    @FindBy(xpath = "//a[.='Sepetim']")
    public WebElement sepetimButton;

}
