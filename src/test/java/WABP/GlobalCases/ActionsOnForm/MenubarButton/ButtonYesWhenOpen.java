package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import SeleniumDriver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ButtonYesWhenOpen extends Driver {
    private static final Logger logger = LogManager.getLogger(ButtonYesWhenOpen.class);

    public WebElement modalWhenOpenDoc(){
        WebElement ButtonYes;
        try {
            ButtonYes = driver.findElement(By.xpath("//vaadin-button[contains(., 'Да')]"));
            return ButtonYes;
        } catch (NoSuchElementException e ){
            logger.trace("Cannot find element", e);
            return null;
        }
    }
}
