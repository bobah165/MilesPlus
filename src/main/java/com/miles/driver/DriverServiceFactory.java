package com.miles.driver;

import com.miles.config.Constant;
import com.miles.exceptions.DriverException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


@Component
public class DriverServiceFactory {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriver create() {
        WebDriver driver = createRemoteDriver();
        manageTimeouts(driver);
        return driver;
    }

    private WebDriver createRemoteDriver()  {
        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(Constant.SELENIUM_HUB), chromeOptions());
            return remoteWebDriver;
        } catch (MalformedURLException e) {
            throw new DriverException(Constant.DRIVER_CREATION_ERROR, e);
            }
    }

    private ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("version",Constant.BROWSER_VERSION);
        options.setCapability("platform", Platform.WIN10);
        options.setCapability("video", "True");

        return options;
    }

    private void manageTimeouts(WebDriver webDriver) {
        webDriver.manage().timeouts()
                 .implicitlyWait(Constant.WAIT_TIME_SECONDS, TimeUnit.SECONDS)
                 .setScriptTimeout(Constant.WAIT_TIME_SECONDS, TimeUnit.SECONDS)
                 .pageLoadTimeout(Constant.WAIT_TIME_SECONDS, TimeUnit.SECONDS);
    }
}
