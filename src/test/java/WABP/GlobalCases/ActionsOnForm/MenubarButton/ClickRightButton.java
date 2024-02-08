package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static WABP.InputForm.TestCases.Сводные_поручения_с_уточнением_КБК_58Ю2.driver1;

public class ClickRightButton {
    public void clickRightButton(WebElement element)
    {
        Actions action = new Actions(driver1);
        action.contextClick(element).perform();
    }
}
