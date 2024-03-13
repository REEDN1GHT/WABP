package WABP.GlobalCases.GeneratedTests.Input;

import Logs.TakesScreenShots;
import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonDelete;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonSave;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.TypeAction;
import WABP.GlobalCases.Auth;
import WABP.GlobalCases.GeneratedTests.Input.DataProvider.DataProviderForInput;
import WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator.DynamicSteps;
import WABP.GlobalCases.NovigateToFrorm;
import WABP.GlobalCases.Parser.TabContent.JSON_ColumnParser;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class InputSaveDoc extends Driver {
    JSON_ColumnParser jsonColumnParser = new JSON_ColumnParser();
    Auth auth = new Auth();
    NovigateToFrorm novigateToFrorm = new NovigateToFrorm();
    DynamicSteps steps = new DynamicSteps();
    ButtonSave buttonSave = new ButtonSave();
    ButtonDelete buttonDelete = new ButtonDelete();
    TakesScreenShots takesScreenShots = new TakesScreenShots();
    TypeAction typeAction = new TypeAction();




    @Test(dataProviderClass = DataProviderForInput.class, dataProvider = "FormDataExists")
    public void inputDoc(String MenuNumber, String FormName, String FileName){
        System.out.println(MenuNumber+ " xxxxxxx " + FileName);
        //FileName = "Ввод лицевых счетов.json";
        Allure.description("Сохранение формы " + FormName);
        auth.AuthWABP();
        novigateToFrorm.NovigateTo(MenuNumber);
        steps.StepsToSave(MenuNumber, FileName);
        if (typeAction.actionSave(MenuNumber)){
            buttonSave.ClickToSave();
            takesScreenShots.TakesScreenshotsSuccess("Сохранение", FormName);
            driver.findElement(By.xpath("//vaadin-button[text()='ОК']")).click();
        }
        if (typeAction.actionDelete(MenuNumber)) {
            buttonDelete.ClickToDelete();
            takesScreenShots.TakesScreenshotsSuccess("Удаление", FormName);
        }

    }

    @Test
    public void test(){
        auth.AuthWABP();
        driver.navigate().to("http://10.13.2.112:8080/lk/tabs?selectedTab=DBЮ3Y");
        System.out.println(("eadadae"));
        System.out.println(("eadadae"));
        WebElement shadow = driver.findElement(By.xpath("//vaadin-menu-bar[@class='tabs-page__buttons-bar']"));
        SearchContext shadowRoot = shadow.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("vaadin-menu-bar-button[part='menu-bar-button'] span[menutext='Сохранить']")).click();
        System.out.println(("eadadae"));
        System.out.println(("eadadae"));
        System.out.println(("eadadae"));
        System.out.println(("eadadae"));
    }

}
