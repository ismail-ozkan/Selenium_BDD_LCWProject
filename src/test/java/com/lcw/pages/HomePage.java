package com.lcw.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "(//a[.='BEBEK'])[1]")
    public WebElement bebekModuleButton;

    @FindBy(xpath = "//button[.='Tüm Çerezlere İzin Ver']")
    public WebElement cookiesAcceptButton;

    @FindBy(xpath = "//*[@class='main-header-logo']")
    public WebElement brandLogoOnHeader;

}
