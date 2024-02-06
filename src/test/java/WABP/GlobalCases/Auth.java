package WABP.GlobalCases;

import SeleniumDriver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static SeleniumDriver.Driver.driver;

public class Auth extends Driver {
    private static final Logger logger = LogManager.getLogger(Auth.class);
    private By ByLogin = By.xpath("//input[@name='username']");
    private By ByPassword = By.xpath("//input[@name='password']");
    private By ByConfirmButton = By.xpath("//vaadin-button[@role='button']");

    String XpathWabpLogo = "//vaadin-app-layout//vaadin-horizontal-layout/span[1]";

    public void AuthWABP() {
        if (!SearchWabpLogo(XpathWabpLogo)) {
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
    }

    public void waitAuth() {
        try {
            var newWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-app-layout//vaadin-horizontal-layout/span[1]")));
        } catch (TimeoutException e) {
           logger.error("Wa not enough time for auth", e.getStackTrace());
        }
    }

    public boolean SearchWabpLogo(String XpathLogo){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
            driver.findElement(By.xpath(XpathLogo));
            return true;

        }catch (NoSuchElementException e)
        {
            return false;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
    }
}
