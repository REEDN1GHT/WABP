package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import SeleniumDriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        element.click();
    }

    public void clickToSaveRow(WebElement element){
        element.findElement(By.xpath("../tr[@last]")).click();
    }

}
