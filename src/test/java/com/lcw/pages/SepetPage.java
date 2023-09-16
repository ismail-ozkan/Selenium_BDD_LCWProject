package com.lcw.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SepetPage extends BasePage {

    @FindBy(xpath = "//*[@class='rd-cart-item-code']")
    public WebElement productIdOnTheCart;

}
