package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import SeleniumDriver.Driver;
import org.openqa.selenium.By;

public class TableContextMenu extends Driver {

    public void ClickSave(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Сохранить')]")).click();
    }
    public void ClickAddNewRow(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Добавить')]")).click();
    }
    public void ClickCancel(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Отменить')]")).click();
    }
    public void ClickEdit(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Изменить')]")).click();
    }
    public void ClickDelete(){
        driver.findElement(By.xpath("//vaadin-context-menu-item[contains(., 'Удалить')]")).click();
    }

}
