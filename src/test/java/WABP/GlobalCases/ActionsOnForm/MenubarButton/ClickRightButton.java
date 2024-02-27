package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import SeleniumDriver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import static WABP.InputForm.TestCases.Сводные_поручения_с_уточнением_КБК_58Ю2.driverToInit;

public class ClickRightButton extends Driver{
    public void clickRightButton(WebElement element)
    {
        Actions action = new Actions(driverToInit);
        action.contextClick(element).perform();
    }
    public void initElement()
    {
        driverToInit = driver;
        PageFactory.initElements(driverToInit, this);
    }
}
