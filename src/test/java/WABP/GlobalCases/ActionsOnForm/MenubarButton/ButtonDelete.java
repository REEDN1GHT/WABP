package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import Logs.TakesScreenShots;
import io.qameta.allure.Description;
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
    TakesScreenShots takesScreenShots = new TakesScreenShots();

    public void ClickToDelete(){
        WebElement shadow = driver.findElement(By.xpath("//vaadin-menu-bar[@class='tabs-page__buttons-bar']"));
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
            throw e;
        }
    }
    @Description("Ожидаем пока окно с сообщением 'Загрузка данных' исчезнет и появится окно с сообщением 'Данные удалены'")
    public void WaitModalDeleteSuccessful(){
        try {

            WebElement element = driver.findElement(By.xpath("//*[@id='overlay']/flow-component-renderer/div/vaadin-vertical-layout/div"));
            WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(50));
            wait1.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            logger.error("Error while waitingafter click", e.getStackTrace());
            takesScreenShots.TakesScreenshotsErrors(e, "Delete:Error while waiting after click");
            throw e;
        }
    }


}
