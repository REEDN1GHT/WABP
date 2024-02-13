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

public class Поручения_бюджетным_автономных_учреждений_43Ю1 extends Driver {
    public static WebDriver driver1;

    WABP.GlobalCases.Auth Auth = new Auth();
    NovigateToFrorm NovToForm = new NovigateToFrorm();
    ExpW_OpenComboBox ExpOpen = new ExpW_OpenComboBox();

    ClickRightButton clickRightButton = new ClickRightButton();
    TakesScreenShots TakeScreen = new TakesScreenShots();
    WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonSave ButtonSave = new ButtonSave();
    WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonDelete ButtonDelete = new ButtonDelete();

    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Распорядитель']/input") //Поле Распорядитель
    private WebElement innGRBS;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Организация']/input") //Поле организация
    private WebElement innInstitution;
    @FindBy(xpath = "//vaadin-text-field[@jsonkey='Номер']/input") //Поле номер документа
    private WebElement numDoc;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Тип обеспечения']/input") //Поле тип обеспечения
    private WebElement fieldTO;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Номер бюджетного обязательства']/input") //Поле номер бюджетного обязательства
    private WebElement fieldNumBO;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Форма оплаты']/input") //Поле форма оплаты
    private WebElement fieldFormPayment;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Экономическая статья']/input") //Поле код осгу
    private WebElement fieldCodeOSGY;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Вид расходов']/input") //Поле вид расходов
    private WebElement fieldTypeExpenses;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Подраздел']/input") //Поле подраздел
    private WebElement fieldSubsection;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Получатель']/input") //Поле получатель
    private WebElement culRecipient;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Наименование получателя']/input") //Поле получатель
    private WebElement nameRecipient;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Текущий счет']/input") //Поле текущий счет
    private WebElement currentAccount;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Наименование отделения']/input") //Поле наименование отделения
    private WebElement nameBank;
    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Месяц платежа']/input") //Поле месяц платежа
    private WebElement fieldMonth;
    @FindBy(xpath = "//vaadin-big-decimal-field[@jsonkey='Сумма платежа']/input") //Поле сумма платежа
    private WebElement fieldSumDoc;
    @FindBy(xpath = "//vaadin-button[contains(., 'Да')]")// Кнопка "Да" (Переход к документу)
    private WebElement buttonYes;
    @FindBy(xpath = "//*[@id='overlay']/div/div/vaadin-vertical-layout/vaadin-button")//Кнопка "Ок" (Данные формы успешно сохранены)
    private WebElement saveButtonOk;

    @Test
    @Description("Сохранение: Ввод поручений бюджетным(автономным)учреждениям")
    public void SaveDoc() throws InterruptedException {
        Auth.AuthWABP();
        NovToForm.NovigateTo("43Ю1");
        initElement();

        //--------------------------
        innGRBS.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        innGRBS.sendKeys(Keys.ENTER);

        //--------------------------
        innInstitution.sendKeys("7801006783");
        ExpOpen.waitFocusedElem();
        innInstitution.sendKeys(Keys.ENTER);

        //--------------------------
        numDoc.sendKeys("saveDoc");
        numDoc.sendKeys(Keys.ENTER);

        //--------------------------
        fieldTO.sendKeys("СГЗ");
        ExpOpen.waitFocusedElem();
        fieldTO.sendKeys(Keys.ENTER);

        //---------------------------
        fieldNumBO.sendKeys("[Нет]");
        ExpOpen.waitFocusedElem();
        fieldNumBO.sendKeys(Keys.ENTER);

        //---------------------------
        fieldFormPayment.sendKeys("01");
        ExpOpen.waitFocusedElem();
        fieldFormPayment.sendKeys(Keys.ENTER);

        //---------------------------
        waitClicableElem();
        fieldCodeOSGY.sendKeys("211");
        ExpOpen.waitFocusedElem();
        fieldCodeOSGY.sendKeys(Keys.ENTER);

        //----------------------------
        fieldTypeExpenses.sendKeys("111");
        ExpOpen.waitFocusedElem();
        fieldTypeExpenses.sendKeys(Keys.ENTER);

        //----------------------------
        fieldSubsection.sendKeys("0100");
        ExpOpen.waitFocusedElem();
        fieldSubsection.sendKeys(Keys.ENTER);

        //----------------------------
        culRecipient.sendKeys("7830002430");
        culRecipient.sendKeys(Keys.ENTER);

        //-----------------------------

        nameRecipient.sendKeys("УФК по г.Санкт-Петербургу (Комитет финансов СПб)");
        nameRecipient.sendKeys(Keys.ENTER);

        //-----------------------------
        currentAccount.sendKeys("02722001250");
        currentAccount.sendKeys(Keys.ENTER);

        //-----------------------------
        nameBank.click();
        ExpOpen.waitList();
        nameBank.sendKeys("СЕВЕРО-ЗАПАДНОЕ ГУ БАНКА РОССИИ//УФК по г.Санкт-Петербургу");
        ExpOpen.waitFocusedElem();
        nameBank.sendKeys(Keys.ENTER);

        //-----------------------------
        fieldMonth.sendKeys("февраль");
        ExpOpen.waitFocusedElem();
        fieldMonth.sendKeys(Keys.ENTER);

        //-----------------------------
        fieldSumDoc.sendKeys("1");
        fieldSumDoc.sendKeys(Keys.ENTER);

        //-----------------------------
        ButtonSave.ClickToSave();
        TakeScreen.TakesScreenshotsSuccess("Save", "Ввод_поручений_бюджетным_автономным_учреждениям");
        saveButtonOk.click();

    }
    @Test(dependsOnMethods = "SaveDoc")
    public void DeleteDoc() throws InterruptedException {
        Auth.AuthWABP();
        NovToForm.NovigateTo("43Ю1");
        initElement();

        //--------------------------
        innGRBS.sendKeys("7801042446");
        ExpOpen.waitFocusedElem();
        innGRBS.sendKeys(Keys.ENTER);

        //--------------------------
        innInstitution.sendKeys("7801006783");
        ExpOpen.waitFocusedElem();
        innInstitution.sendKeys(Keys.ENTER);

        //--------------------------
        numDoc.sendKeys("saveDoc");
        numDoc.sendKeys(Keys.ENTER);

        //--------------------------
        fieldTO.sendKeys("СГЗ");
        ExpOpen.waitFocusedElem();
        fieldTO.sendKeys(Keys.ENTER);

        //---------------------------
        fieldNumBO.sendKeys("[Нет]");
        ExpOpen.waitFocusedElem();
        fieldNumBO.sendKeys(Keys.ENTER);

        //---------------------------
        fieldFormPayment.sendKeys("01");
        ExpOpen.waitFocusedElem();
        fieldFormPayment.sendKeys(Keys.ENTER);

        //---------------------------
        //buttonYes.click();
        waitClicableElem();
        ButtonDelete.ClickToDelete();
        TakeScreen.TakesScreenshotsSuccess("Delete", "Ввод_поручений_бюджетным_автономным_учреждениям");
    }
    public void initElement()
    {
        driver1 = driver;
        PageFactory.initElements(driver1, this);
    }

    @Description("Ждем пока элемент станет кликабельным после загрузки документа (Поле Поручение, графа 1")
    public void waitClicableElem(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(fieldCodeOSGY));

    }
}
