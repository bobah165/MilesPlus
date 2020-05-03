package com.miles.model;

import com.miles.config.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimeManagementPage {

    @FindBy(css = Constant.CSS_OF_TIME_MANAGEMENT_LINK)
    private WebElement timeManagement;

    public TimeManagementPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public TimeManagementPage clickButton(){
        timeManagement.click();
        return this;
    }
}
