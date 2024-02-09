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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static SeleniumDriver.Driver.driver;
import static SeleniumDriver.Driver.wait;

public class Государственные_программы_DBЮ3P extends Driver {
    public static WebDriver driver1;

    WABP.GlobalCases.Auth Auth = new Auth();
    NovigateToFrorm NovToForm = new NovigateToFrorm();
    ExpW_OpenComboBox ExpOpen = new ExpW_OpenComboBox();

    ClickRightButton clickRightButton = new ClickRightButton();
    TakesScreenShots TakeScreen = new TakesScreenShots();
    WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonSave ButtonSave = new ButtonSave();
    WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonDelete ButtonDelete = new ButtonDelete();

    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='ГП']/input")//Поле ГП
    private WebElement governmentProgram;
    @FindBy(xpath = "//vaadin-text-field[@jsonkey='Наименование ГП']/input")//Поле наименование ГП
    private WebElement nameGovermentProgram;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='ИНН ОИГП']/input") //Поле инн ОИГП
    private WebElement innOIGP;
    @FindBy(xpath = "//vaadin-button[contains(., 'Да')]")// Кнопка "Да" (Переход к документу)
    private WebElement buttonYes;
    @FindBy(xpath = "//*[@id='overlay']/div/div/vaadin-vertical-layout/vaadin-button")//Кнопка "Ок" (Данные формы успешно сохранены/удалены)
    private WebElement buttonOkSaveAndDelete;

    @Test
    @Description("Государственные программы")
    public void SaveDoc()  {
        Auth.AuthWABP();
        NovToForm.NovigateTo("DBЮ3P");
        initElement();

        //------------------
        governmentProgram.sendKeys("55");
        governmentProgram.sendKeys(Keys.ENTER);

        //------------------
        waitClicableElem();
        innOIGP.sendKeys("7808043833");
        ExpOpen.waitFocusedElem();
        innOIGP.sendKeys(Keys.ENTER);

        //------------------
        nameGovermentProgram.sendKeys("Тестовое ГП 123");
        nameGovermentProgram.sendKeys(Keys.ENTER);

        //------------------
        ButtonSave.ClickToSave();
        TakeScreen.TakesScreenshotsSuccess("Save", "Государственные_программы");

    }

    @Test(dependsOnMethods = "SaveDoc")
    public void DeleteDoc() {
        Auth.AuthWABP();
        NovToForm.NovigateTo("DBЮ3P");
        initElement();

        //------------------
        governmentProgram.sendKeys("55");
        governmentProgram.sendKeys(Keys.ENTER);

        //------------------
        waitClicableElem();
        ButtonDelete.ClickToDelete();
        TakeScreen.TakesScreenshotsSuccess("Delete", "Государственные_программы");
    }
    public void initElement()
    {
        driver1 = driver;
        PageFactory.initElements(driver1, this);
    }

    @Description("Ждем пока элемент станет кликабельным после загрузки документа (Поле Поручение, графа 1")
    public void waitClicableElem(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(innOIGP));

    }
}
