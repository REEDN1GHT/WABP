package WABP.GlobalCases;

import Logs.TakesScreenShots;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static SeleniumDriver.Driver.driver;

public class NovigateToFrorm {
    private static final Logger logger = LogManager.getLogger(NovigateToFrorm.class);
    TakesScreenShots takesScreenShots = new TakesScreenShots();


    public void NovigateTo(String AppsID){
        driver.navigate().to("http://172.31.1.149/aispbpek/budget24-newdb/lk/tabs?selectedTab="+AppsID);
        waitContentIntoInput();
    }

    @Description("Ожидание Загрузки формы")
    public void waitContentIntoInput() {
        try {
            var newWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/vaadin-vertical-layout")));
        } catch (Exception e) {
            logger.error("Error while waiting opening forms", e.getStackTrace());
            throw e;
        }
    }
}
