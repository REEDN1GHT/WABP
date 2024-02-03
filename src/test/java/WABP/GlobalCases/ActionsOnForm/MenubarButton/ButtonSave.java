package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static SeleniumDriver.Driver.driver;

public class ButtonSave {

    public void ClickToSave(){
        WebElement shadow = driver.findElement(By.xpath("//vaadin-vertical-layout[@class='TagPage tabsPage']/vaadin-horizontal-layout/vaadin-menu-bar"));
        SearchContext shadowRoot = shadow.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("div > vaadin-menu-bar-button:nth-child(3)")).click();
        WaitModalSave();
    }

    public void WaitModalSave(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-dialog-overlay[@id='overlay']/flow-component-renderer/div/vaadin-vertical-layout/span")));
    }
  /*  @Name("Ждем появления окна с сохранением")
    public void waitSaveDialog()
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='overlay']")));

    }*/
}
