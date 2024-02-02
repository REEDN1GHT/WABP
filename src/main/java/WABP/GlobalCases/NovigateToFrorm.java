package WABP.GlobalCases;

import jdk.jfr.Name;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Selenium.WebDriver.driver;

public class NovigateToFrorm {


    public void NovigateTo(String AppsID){
        driver.navigate().to("http://172.31.1.149/aispbpek/budget24/lk/tabs?selectedTab="+AppsID);
        waitContentIntoInput();
    }

    @Name("Ожидание Загрузки формы")
    public void waitContentIntoInput() {
        var newWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]")));
    }
}
