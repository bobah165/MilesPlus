package com.miles.model;

import com.miles.config.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    @FindBy(name = "username")
    private List<WebElement> usernameList;

    @FindBy(name = "password")
    private List<WebElement> passwordList;

    @FindBy(className = Constant.LOGIN_BUTTON)
    private List<WebElement> loginButton;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public LoginPage setCredentials(String username, String password) {
      usernameList.get(1).sendKeys(username);
      passwordList.get(1).sendKeys(password);
      return this;
    }

    public void clickLoginButton() {loginButton.get(2).click(); }

}
