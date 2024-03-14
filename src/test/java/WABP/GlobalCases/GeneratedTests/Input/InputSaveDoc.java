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
import org.testng.Assert;
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



    /*TODO: в строке buttonSave.ClickToSave();
        Обрарботать исключения, когда форма не сохраняется с модалкой
       "При сохранении документа произошла неизвестная ошибка. Код: 187b5e12-5e17-4b82-ab83-5b89852e7f1f"
       ожидаемый результат теста: Fail*/
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

//    @Test
//    public void test(){
//        System.out.println("start test google");
//        driver.navigate().to("https://www.google.com");
//        System.out.println(driver.findElement(By.xpath("//img[@alt='Google']")).getAttribute("alt"));
//    }


}
