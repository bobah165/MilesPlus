package com.miles.model;

import com.miles.config.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class WorkRecordingPage {

    @FindBy(css = Constant.START_HOUR_FIELD_NAME)
    private List<WebElement> startHoursList;

    @FindBy(css = Constant.END_HOUR_FIELD_NAME)
    private List<WebElement> endHoursList;

    @FindBy(css = Constant.TOTAL_HOUR_FIELD_NAME)
    private List<WebElement> totalHoursList;

    @FindBy(css = Constant.CSS_FOR_SAVE_BUTTON)
    private WebElement saveButton;

    @FindBy(css = Constant.CSS_FOR_SETTING_WEEK)
    private List<WebElement> weekList;


    public WorkRecordingPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void fillAllHours(String startHours, String endHours, String totalHours) {
        for(int i=0; i<weekList.size();i++) {
            List<WebElement> weeks = new ArrayList<>(weekList);
            weeks.get(i).click();
            fillHoursInWeek(startHours, endHours, totalHours);
            saveButton.click();
        }
    }

    private void fillHoursInWeek(String startHours, String endHours, String totalHours) {
        setHours(startHoursList,startHours);
        setHours(endHoursList,endHours);
        setHours(totalHoursList,totalHours);
    }


    private void setHours(List<WebElement> elements, String setHours) {
        elements.stream()
              .filter(inputTypeField->!(inputTypeField.getAttribute("class").equals("weekend")||
                                        inputTypeField.getAttribute("class").equals("holyday")))
              .forEach(hour->{
                  hour.clear();
                  hour.sendKeys(setHours);
              });
    }
}
