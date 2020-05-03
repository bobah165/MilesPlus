package com.miles.driver;

import com.miles.config.Constant;
import com.miles.model.LoginPage;
import com.miles.model.TimeManagementPage;
import com.miles.model.WorkRecordingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Driver {

    private WebDriver driver;

    private void setUp()  {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constant.WEBSITE_ADDRESS);
        driver.manage().window().maximize();
    }

    public void fillMilesPlusHour() {
        setUp();

        fillCredentials();
        clickTimeManagement();
        fillAllHours();
        quit();
    }

    private void fillAllHours() {
        WorkRecordingPage workRecording = new WorkRecordingPage(driver);
        workRecording.fillAllHours(Constant.SET_START_HOUR,Constant.SET_END_HOUR,Constant.SET_TOTAL_HOUR);
    }


    private void clickTimeManagement() {
        TimeManagementPage timeManagement = new TimeManagementPage(driver);
        timeManagement.clickButton();
    }

    private void fillCredentials() {
        LoginPage login = new LoginPage(driver);
        login.setCredentials(Constant.USERNAME,Constant.PASSWORD)
             .clickLoginButton();
    }

    private void quit() {driver.quit();}
}
