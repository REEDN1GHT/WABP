package WABP.InputForm.TestCases;

import Logs.TakesScreenShots;
import Logs.WriterLogs;
import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_OpenComboBox;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonDelete;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonSave;
import WABP.GlobalCases.Auth;
import WABP.GlobalCases.MenuSearchForm;
import WABP.GlobalCases.NovigateToFrorm;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.time.Duration;


public class Поручения_на_оплату_расходов_51Ю2 extends Driver {

    private String ClassName ="Поручение_на_оплату_расходов";

    Auth Auth = new Auth();
    NovigateToFrorm NovToForm = new NovigateToFrorm();
    ExpW_OpenComboBox ExpOpen = new ExpW_OpenComboBox();
    TakesScreenShots TakeScreen = new TakesScreenShots();
    ButtonSave ButtonSave = new ButtonSave();
    ButtonDelete ButtonDelete = new ButtonDelete();



    @Test
    @Description("Сохранение: Ввод поручения на оплату расходов")
    public void SaveDoc() throws InterruptedException {
        Auth.AuthWABP();
        NovToForm.NovigateTo("51Ю2");
        //-----------------------------------------
        WebElement Cul_parent = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[3]/input"));
        Cul_parent.click();
        Cul_parent.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        Cul_parent.sendKeys(Keys.ENTER);
        //------------------------------------------
        WebElement Cul = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[4]/input"));
        Cul.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        Cul.sendKeys(Keys.ENTER);
        //------------------------------------------
        WebElement CDOC = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-text-field[11]/input"));
        CDOC.sendKeys("SaveDoc");
        //------------------------------------------
        WebElement BO_Number = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[8]/input"));
        BO_Number.sendKeys("[Нет]");
        ExpOpen.waitFocusedElem();
        BO_Number.sendKeys(Keys.ENTER);
        //------------------------------------------
        WebElement FO_Number = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[5]/input"));
        FO_Number.sendKeys("01");
        ExpOpen.waitFocusedElem();
        FO_Number.sendKeys(Keys.ENTER);
        //-----------------------------------------
        WebElement ButtonOverlayYes = driver.findElement(By
                .xpath("//vaadin-button[contains(., 'Да')]"));
        ButtonOverlayYes.click();
        waitClicableElem();
        //-----------------------------------------
        WebElement K_LSR = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[20]/input"));
        K_LSR.sendKeys("531202");
        ExpOpen.waitFocusedElem();
        K_LSR.sendKeys(Keys.ENTER);
        //----------------------------------------
        WebElement CDS = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[33]/input"));
        CDS.sendKeys("1");
        ExpOpen.waitFocusedElem();
        CDS.sendKeys(Keys.ENTER);
        //----------------------------------------
        WebElement Cul_recipient = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[16]/input"));
        Cul_recipient.sendKeys("7830002430");
       // Thread.sleep(1000);
        //ExpOpen.waitFocusedElem();
        Cul_recipient.sendKeys(Keys.ENTER);
        //----------------------------------------
       WebElement Name_recipient = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[17]/input"));
        Name_recipient.sendKeys("УФК по г.Санкт-Петербургу (Комитет финансов Санкт-Петербурга)");
        Name_recipient.sendKeys(Keys.ENTER);
        //---------------------------------------
        WebElement CurrentAccount = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[18]/input"));
        CurrentAccount.sendKeys("02722001250");
        CurrentAccount.sendKeys(Keys.ENTER);
        //----------------------------------------
        WebElement Month = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[29]/input"));
        Month.sendKeys("январь");
        ExpOpen.waitFocusedElem();
        Month.sendKeys(Keys.ENTER);
        //----------------------------------------
        WebElement Summ = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-big-decimal-field[2]/input"));
        Summ.sendKeys("1");
        //----------------------------------------
        WebElement PurpOfPayment = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[1]/input"));
        PurpOfPayment.sendKeys("основание");
        PurpOfPayment.sendKeys(Keys.ENTER);
        //---------------------------------------
        WebElement BankName = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[19]/input"));
        BankName.click();
        ExpOpen.waitList();
        BankName.sendKeys("СЕВЕРО-ЗАПАДНОЕ ГУ БАНКА РОССИИ//УФК по г.Санкт-Петербургу");
        ExpOpen.waitFocusedElem();
        BankName.sendKeys(Keys.ENTER);
        ButtonSave.ClickToSave();
        TakeScreen.TakesScreenshotsSuccess("Save", "Поручение_на_оплату_расходов");
        driver.findElement(By.xpath("//*[@id='overlay']/div/div/vaadin-vertical-layout/vaadin-button")).click();
        //ButtonDelete.ClickToDelete();

    }

    @Description("Ждем пока элемент станет кликабельным после загрузки документа")
    public void waitClicableElem(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[20]/input")));

    }
    @Test
    @Description("Открытие: Поручение на оплату расходов из меню")
    public void OpenDoc(){
        MenuSearchForm MSF = new MenuSearchForm();
        MSF.SearchAndOpenForm("Финансирование" +
                "/Поручения на оплату расходов" +
                "/Ввод" +
                "/Ввод поручений на оплату расходов");
        WebElement Field = driver.findElement(By.xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[3]/input"));
        Assert.assertNotNull(Field);
    }

    @Test(dependsOnMethods = "SaveDoc")
    public void DeleteDoc(){
        Auth.AuthWABP();
        NovToForm.NovigateTo("51Ю2");
        //-----------------------------------------
        WebElement Cul_parent = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[3]/input"));
        Cul_parent.click();
        Cul_parent.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        Cul_parent.sendKeys(Keys.ENTER);
        //------------------------------------------
        WebElement Cul = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[4]/input"));
        Cul.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        Cul.sendKeys(Keys.ENTER);
        //------------------------------------------
        WebElement CDOC = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-text-field[11]/input"));
        CDOC.sendKeys("SaveDoc");
        //------------------------------------------
        WebElement BO_Number = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[8]/input"));
        BO_Number.sendKeys("[Нет]");
        ExpOpen.waitFocusedElem();
        BO_Number.sendKeys(Keys.ENTER);
        //------------------------------------------
        WebElement FO_Number = driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]/vaadin-vertical-layout/vaadin-scroller/vaadin-vertical-layout/input-form-component-div/vaadin-combo-box[5]/input"));
        FO_Number.sendKeys("01");
        ExpOpen.waitFocusedElem();
        FO_Number.sendKeys(Keys.ENTER);
        //-----------------------------------------
        WebElement ButtonOverlayYes = driver.findElement(By
                .xpath("//vaadin-button[contains(., 'Да')]"));
        ButtonOverlayYes.click();
        waitClicableElem();
        ButtonDelete.ClickToDelete();
    }
}
