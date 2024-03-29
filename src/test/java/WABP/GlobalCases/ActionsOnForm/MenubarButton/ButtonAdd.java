package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import Logs.TakesScreenShots;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static SeleniumDriver.Driver.driver;

public class ButtonAdd {
    private static final Logger logger = LogManager.getLogger(ButtonAdd.class);
    TakesScreenShots takesScreenShots = new TakesScreenShots();


    public void ClickToAdd(){
        WebElement shadow = driver.findElement(By.xpath("//vaadin-menu-bar[@class='tabs-page__buttons-bar']"));
        SearchContext shadowRoot = shadow.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("[button_name='Добавить']")).click();
        WaitModalAdd();
    }

    public void WaitModalAdd(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='overlay']")));
        } catch (Exception e) {
            logger.error("Error while waiting after click", e.getStackTrace());
            takesScreenShots.TakesScreenshotsErrors(e, "ButtonAdd_Error while waiting after click");
            throw e;
        }
    }
}
