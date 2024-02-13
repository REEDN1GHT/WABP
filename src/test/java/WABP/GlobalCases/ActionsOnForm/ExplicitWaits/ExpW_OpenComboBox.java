package WABP.GlobalCases.ActionsOnForm.ExplicitWaits;

import Logs.TakesScreenShots;
import WABP.GlobalCases.Auth;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static SeleniumDriver.Driver.driver;

public class ExpW_OpenComboBox {
    private static final Logger logger = LogManager.getLogger(ExpW_OpenComboBox.class);
    TakesScreenShots takesScreenShots = new TakesScreenShots();

    @Description("Ждем когда отобразится введенное значение в списке (Появится атрибут focused), " +
            "Используется после senkeys в поле, чтобы корректно вставить значение")
    public void waitFocusedElem() {
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-combo-box-item[@focused]")));
        } catch (Exception e){
            logger.error("Error while waiting list", e.getStackTrace());
            takesScreenShots.TakesScreenshotsErrors(e, "Error while waiting list");
            throw e;
        }
        }

    @Description("Ждем когда отобразится список. Для списков с большим объемом" +
            "Указывается после клика на список")
    public void waitList() {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver,Duration.ofSeconds(3));
            driverWait.until(driver1 -> driver.findElements(By.xpath("//vaadin-combo-box-item")).size()>1);

        } catch (Exception e) {
            logger.error("Error while waiting list after click", e.getStackTrace());
            takesScreenShots.TakesScreenshotsErrors(e, "Error while waiting list after click");
            throw e;
        }
    }

    public void Sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
