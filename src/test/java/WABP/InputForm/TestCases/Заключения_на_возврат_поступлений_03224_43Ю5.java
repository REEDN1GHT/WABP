package WABP.InputForm.TestCases;

import Logs.TakesScreenShots;
import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_OpenComboBox;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonDelete;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonSave;
import WABP.GlobalCases.Auth;
import WABP.GlobalCases.NovigateToFrorm;
import io.qameta.allure.Description;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Заключения_на_возврат_поступлений_03224_43Ю5 extends Driver {
    private WebDriver driver1;

    WABP.GlobalCases.Auth Auth = new Auth();
    NovigateToFrorm NovToForm = new NovigateToFrorm();
    ExpW_OpenComboBox ExpOpen = new ExpW_OpenComboBox();
    TakesScreenShots TakeScreen = new TakesScreenShots();
    WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonSave ButtonSave = new ButtonSave();
    WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonDelete ButtonDelete = new ButtonDelete();



    @FindBy(xpath ="//vaadin-combo-box[@jsonkey='Район']/input")//Поле лицевой счет Номер
    private WebElement inputLS;

    @FindBy(xpath = "//vaadin-combo-box[@jsonkey='Номер']/input")//Поле номер документа
    private WebElement numDocument;

    @FindBy(xpath= "//input[@id='input-vaadin-text-field-63']") //Поле пользовательский номер документа
    private WebElement documentNumber;

    @FindBy(xpath = "//vaadin-button[contains(., 'Да')]")// Кнопка "Да" (Переход к документу)
    private WebElement buttonYes;

    @FindBy (xpath = "//vaadin-date-picker[@jsonkey='Дата']/input")//Поле дата документа
    private WebElement dateDoc;

    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Заявитель']/input") //Поле инн заявителя
    private WebElement innCulZ;

    @FindBy (xpath = "//vaadin-date-picker[@jsonkey='Дата заявления']/input")//Поле дата заявления
    private WebElement datepAplication;

    @FindBy (xpath = "//vaadin-text-field[@jsonkey='Входящий номер заявления']/input")// Поле Входящий номер заявления
    private WebElement applicationNumber;

    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Получатель']/input")//Поле инн получателя
    private WebElement innCul;

    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Наименование получателя']/input") //Поле наименование получателя
    private WebElement nameRecipient;

    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Текущий счет']/input")// Поле текущий счет
    private WebElement currentAccount;

    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Наименование отделения']/input")// Поле наименование отделения банка
    private WebElement bankName;

    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Администратор']/input")//Поле администратор
    private WebElement admin;

    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='КОСГУ']/input") //Поле КОСГУ
    private WebElement kosgy;
    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Лицевой счет']/input") // Поле лицевой счет
    private WebElement personalAccount;
    @FindBy (xpath = "//vaadin-big-decimal-field[@jsonkey='Сумма']/input")// Поле сумма
    private WebElement sumDoc;
    @FindBy (xpath = "//vaadin-text-field[@jsonkey='Номер налогового документа']/input") //Поле Номер налогового документа
    private WebElement numberTaxDoc;
    @FindBy (xpath = "//vaadin-date-picker[@jsonkey='Дата налогового документа']/input")// Дата налогового документа
    private WebElement dateTaxDoc;
    @FindBy (xpath = "//vaadin-combo-box[@jsonkey='Тип обеспечения']/input")// Дата тип обеспечения
    private WebElement fieldTO;

    @FindBy(xpath = "//*[@id='overlay']/div/div/vaadin-vertical-layout/vaadin-button")//Кнопка "Ок" (Данные формы успешно сохранены/удалены)
    private WebElement buttonOkSaveAndDelete;



    @Test
    @Description("Сохранение: Ввод заключений на возврат поступлений 03224")
    public void SaveDoc() throws InterruptedException {
        Auth.AuthWABP();
        NovToForm.NovigateTo("43Ю5");
        initElement();

        //------------------------------
        inputLS.click();
        ExpOpen.waitList();
        inputLS.sendKeys("0151121");
        ExpOpen.waitFocusedElem();
        inputLS.sendKeys(Keys.ENTER);


        //-----------------------------
        numDocument.sendKeys("1423");
        numDocument.sendKeys(Keys.ENTER);

        //-----------------------------
        buttonYes.click();
        waitClicableElem();

        //-----------------------------
        dateDoc.sendKeys("07.02.2024");
        dateDoc.sendKeys(Keys.ENTER);

        //-----------------------------
        innCulZ.sendKeys("7807021756");
        ExpOpen.waitFocusedElem();
        innCulZ.sendKeys(Keys.ENTER);

        //-----------------------------
        datepAplication.sendKeys("07.02.2024");
        datepAplication.sendKeys(Keys.ENTER);

        //-----------------------------
        applicationNumber.sendKeys("1");
        applicationNumber.sendKeys(Keys.ENTER);

        //-----------------------------
        innCul.sendKeys("7830002430");
        innCul.sendKeys(Keys.ENTER);

        //-----------------------------

        nameRecipient.sendKeys("УФК по г.Санкт-Петербургу (Комитет финансов СПб)");
        nameRecipient.sendKeys(Keys.ENTER);

        //-----------------------------
        currentAccount.sendKeys("03226643400000007200");
        currentAccount.sendKeys(Keys.ENTER);

        //-----------------------------
        bankName.click();
        ExpOpen.waitList();
        bankName.sendKeys("СЕВЕРО-ЗАПАДНОЕ ГУ БАНКА РОССИИ//УФК по г.Санкт-Петербургу");
        ExpOpen.waitFocusedElem();
        bankName.sendKeys(Keys.ENTER);

        //-----------------------------
        admin.sendKeys("816");
        ExpOpen.waitFocusedElem();
        admin.sendKeys(Keys.ENTER);

        //-----------------------------
        kosgy.sendKeys("120");
        ExpOpen.waitFocusedElem();
        kosgy.sendKeys(Keys.ENTER);

        //-----------------------------
        fieldTO.sendKeys("ПД");
        ExpOpen.waitFocusedElem();
        fieldTO.sendKeys(Keys.ENTER);

        //-----------------------------
        personalAccount.sendKeys("0151121");
        ExpOpen.waitFocusedElem();
        personalAccount.sendKeys(Keys.ENTER);

        //-----------------------------
        sumDoc.sendKeys("1");
        sumDoc.sendKeys(Keys.ENTER);

        //-----------------------------
        numberTaxDoc.sendKeys("1");
        numberTaxDoc.sendKeys(Keys.ENTER);

        //-----------------------------
        dateTaxDoc.sendKeys("07.02.2024");
        dateTaxDoc.sendKeys(Keys.ENTER);

        //-----------------------------
        ButtonSave.ClickToSave();
        TakeScreen.TakesScreenshotsSuccess("Save", "Заключения_на_возврат_поступлений_03224");
        buttonOkSaveAndDelete.click();



    }

    @Test(dependsOnMethods = "SaveDoc")
    public void DeleteDoc() {
        Auth.AuthWABP();
        NovToForm.NovigateTo("43Ю5");
        initElement();

        //-----------------------------
        inputLS.click();
        ExpOpen.waitList();
        inputLS.sendKeys("0151121");
        ExpOpen.waitFocusedElem();
        inputLS.sendKeys(Keys.ENTER);


        //-----------------------------
        numDocument.sendKeys("1423");
        numDocument.sendKeys(Keys.ENTER);

        //-----------------------------
        buttonYes.click();
        waitClicableElem();
        ButtonDelete.ClickToDelete();

        TakeScreen.TakesScreenshotsSuccess("Delete", "Заключения_на_возврат_поступлений_03224");

    }


    public void initElement()
    {
        this.driver1 = driver;
        PageFactory.initElements(driver1, this);
    }

    @Description("Ждем пока элемент станет кликабельным после загрузки документа (Поле Поручение, графа 1")
    public void waitClicableElem(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(innCulZ));

    }
}

