package WABP.GlobalCases.ActionsOnForm.ExplicitWaits;

import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator.GlobalXpath;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExpW_WaitDocOpen extends Driver {

    GlobalXpath globalXpath = new GlobalXpath();

    @Description("Ждем пока элемент станет кликабельным после загрузки документа")
    public void waitClicableElem(String xpath){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void waitForModalLoadingDataToDisappear(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Загрузка данных']")));
    }


    public void waitForAttributeToDisappear(WebElement element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(driver -> {
            String attributeValue = element.getAttribute(attribute);
            return attributeValue == null || !attributeValue.equals(value);
        });
    }

    public void waitTabVisabilityOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
