package WABP.GlobalCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Selenium.WebDriver.driver;

public class Auth {

    private By ByLogin = By.xpath("//input[@name='username']");
    private By ByPassword = By.xpath("//input[@name='password']");
    private By ByConfirmButton = By.xpath("//vaadin-button[@role='button']");

    public void AuthWABP(){
        driver.navigate().to("http://172.31.1.149/aispbpek/budget24/login");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        driver.findElement(ByLogin).sendKeys("UKT_Riabtsev");
        driver.findElement(ByPassword).sendKeys("UKT_Riabtsev1");
        driver.findElement(ByConfirmButton).click();
        waitAuth();
    }

    public void waitAuth() {
        var newWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-app-layout//vaadin-horizontal-layout/span[1]")));
    }
}
