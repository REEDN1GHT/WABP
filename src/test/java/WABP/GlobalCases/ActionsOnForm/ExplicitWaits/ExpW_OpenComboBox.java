package WABP.GlobalCases.ActionsOnForm.ExplicitWaits;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static SeleniumDriver.Driver.driver;

public class ExpW_OpenComboBox {


    @Description("Ждем когда отобразится введенное значение в списке (Появится атрибут focused), " +
            "Используется после senkeys в поле, чтобы корректно вставить значение")
    public void waitFocusedElem() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-combo-box-item[@focused]")));
    }

    @Description("Ждем когда отобразится список. Для списков с большим объемом" +
            "Указывается после клика на список")
    public void waitList() {
        WebDriverWait driverWait = new WebDriverWait(driver,Duration.ofSeconds(3));
        driverWait.until(driver1 -> driver.findElements(By.xpath("//vaadin-combo-box-item")).size()>1);

    }

    public void Sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
