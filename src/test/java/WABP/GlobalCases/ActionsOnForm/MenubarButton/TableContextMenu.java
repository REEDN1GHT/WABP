package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import SeleniumDriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TableContextMenu extends Driver {

    public void clickSave(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Сохранить')]")).click();
    }
    public void clickAddNewRow(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Добавить')]")).click();
    }
    public void clickCancel(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Отменить')]")).click();
    }
    public void clickEdit(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Изменить')]")).click();
    }
    public void clickDelete(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Удалить')]")).click();
    }

    public void clickToEdit(WebElement element){
        try {
            element.click();
        } catch (Exception e) {
            scrollIntoView(element);
            element.click();
        }
    }
        public void scrollIntoView(WebElement element) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }

    public void clickToCreateRowInGrid(String jsonKeyTable){
        driver.findElement(By.xpath("//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKeyTable+"']")).click();
    }
    public void dialogClickToCreateRowInGrid(String jsonKeyTable){
        driver.findElement(By.xpath("//vaadin-dialog-overlay//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKeyTable+"']")).click();
    }

    public void clickToSaveRow(WebElement element){
        element.findElement(By.xpath("../tr[@last]")).click();
    }

    public void clickToOpenTab(String jsonKeyTab){
        if (jsonKeyTab != null){
            driver.findElement(By.xpath("//vaadin-tabs/vaadin-tab[@jsonkey='tab "+ jsonKeyTab +"']")).click();
        }
    }



}
