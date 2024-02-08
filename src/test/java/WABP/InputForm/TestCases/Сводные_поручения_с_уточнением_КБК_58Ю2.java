package WABP.InputForm.TestCases;

import Logs.TakesScreenShots;
import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_OpenComboBox;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonDelete;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonSave;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ClickRightButton;
import WABP.GlobalCases.Auth;
import WABP.GlobalCases.NovigateToFrorm;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Сводные_поручения_с_уточнением_КБК_58Ю2 extends Driver {
public static WebDriver driver1;

    WABP.GlobalCases.Auth Auth = new Auth();
    NovigateToFrorm NovToForm = new NovigateToFrorm();
    ExpW_OpenComboBox ExpOpen = new ExpW_OpenComboBox();

    ClickRightButton clickRightButton = new ClickRightButton();
    TakesScreenShots TakeScreen = new TakesScreenShots();
    WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonSave ButtonSave = new ButtonSave();
    WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonDelete ButtonDelete = new ButtonDelete();



     @FindBy(xpath ="//input[@id='input-vaadin-combo-box-60']")//Поле распорядитель
    private WebElement innGRBS;

    @FindBy(xpath = "//input[@id='input-vaadin-combo-box-62']")//Поле учреждение
    private WebElement culChild;

    @FindBy(xpath= "//input[@id='input-vaadin-text-field-63']") //Поле пользовательский номер документа
    private WebElement documentNumber;

    @FindBy(xpath = "//vaadin-button[contains(., 'Да')]")// Кнопка "Да" (Переход к документу)
    private WebElement buttonYes;

    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Документ переброски']/input") //Поле "Поручение". Строка 1, Графа 1 в таблице
    private WebElement assigment;

    @FindBy (xpath = "//vaadin-grid[@class='column-name-center']")//Табличная часть в форме ввода
    private WebElement table;

    @FindBy (xpath = "//vaadin-context-menu-item[contains(., 'Добавить')]")//Кнопка добавить строку
    private WebElement addTableString;

    @FindBy (xpath = "//vaadin-context-menu-item[contains(., 'Сохранить')]")//Кнопка сохранить строку
    private WebElement saveTableString;

    @FindBy(xpath = "//*[@id='overlay']/div/div/vaadin-vertical-layout/vaadin-button")//Кнопка "Ок" (Данные формы успешно сохранены)
    private WebElement saveButtonOk;
    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Номер документа']/input")// Номер поручения. 2 строка, 1 графа
    private WebElement numAssigment;


    @Test
    @Description("Сохранение: Ввод сводных поручений с уточнением КБК")
    public void SaveDoc() throws InterruptedException {
        Auth.AuthWABP();
        NovToForm.NovigateTo("58Ю2");
        initElement();

        //------------------------------
        innGRBS.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        innGRBS.sendKeys(Keys.ENTER);

        //-----------------------------
        culChild.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        culChild.sendKeys(Keys.ENTER);

        //-----------------------------
        documentNumber.sendKeys("14");
        documentNumber.sendKeys(Keys.ENTER);

        //-----------------------------
        buttonYes.click();
        waitClicableElem();

        //-----------------------------
        assigment.sendKeys("7801042446/7801042446/1254");
        ExpOpen.waitFocusedElem();
        assigment.sendKeys(Keys.ENTER);

        //-----------------------------
        clickRightButton.clickRightButton(table);
        addTableString.click();

        //-----------------------------
        numAssigment.sendKeys("7801042446/7801042446/11122");
        ExpOpen.waitFocusedElem();
        numAssigment.sendKeys(Keys.ENTER);
        clickRightButton.clickRightButton(table);

        saveTableString.click();

        //-----------------------------
         ButtonSave.ClickToSave();
         TakeScreen.TakesScreenshotsSuccess("Save", "Сводные_поручения_с_уточнением_КБК");
         saveButtonOk.click();

    }

    @Test(dependsOnMethods = "SaveDoc")
    public void DeleteDoc() {
        Auth.AuthWABP();
        NovToForm.NovigateTo("58Ю2");
        initElement();

        //------------------------------
        innGRBS.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        innGRBS.sendKeys(Keys.ENTER);

        //-----------------------------
        culChild.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        culChild.sendKeys(Keys.ENTER);

        //-----------------------------
        documentNumber.sendKeys("14");
        documentNumber.sendKeys(Keys.ENTER);

        //-----------------------------
        buttonYes.click();
        waitClicableElem();
        ButtonDelete.ClickToDelete();
        TakeScreen.TakesScreenshotsSuccess("Delete", "Сводные_поручения_с_уточнением_КБК");
    }


    public void initElement()
    {
        driver1 = driver;
        PageFactory.initElements(driver1, this);
    }

    @Description("Ждем пока элемент станет кликабельным после загрузки документа (Поле Поручение, графа 1")
    public void waitClicableElem(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(assigment));

    }

}
