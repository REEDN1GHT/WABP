package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import WABP.GlobalCases.NovigateToFrorm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static SeleniumDriver.Driver.driver;

public class ButtonDelete {
    private static final Logger logger = LogManager.getLogger(ButtonDelete.class);
    public void ClickToDelete(){
        WebElement shadow = driver.findElement(By.xpath("//vaadin-vertical-layout[@class='TagPage tabsPage']/vaadin-horizontal-layout/vaadin-menu-bar"));
        SearchContext shadowRoot = shadow.getShadowRoot();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(shadowRoot.findElement(By.cssSelector("div > vaadin-menu-bar-button:nth-child(2)")))).click();

        WaitModalDelete();
        driver.findElement(By.xpath("//vaadin-button[@buttonyes]")).click();
        WaitModalDeleteSuccessful();

    }

    public void WaitModalDelete(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"overlay\"]/flow-component-renderer/div/vaadin-vertical-layout/span")));
        } catch (Exception e) {
            logger.error("Error while waiting opening confirmation window", e.getStackTrace());
        }
    }
    public void WaitModalDeleteSuccessful(){
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Данные удалены']")));
        } catch (Exception e) {
            logger.error("Error while waiting opening window success", e.getStackTrace());
        }
    }


}
