package WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator;

import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_OpenComboBox;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_WaitDocOpen;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonAdd;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ClickRightButton;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.TableContextMenu;
import WABP.GlobalCases.Parser.JSON_DataParser;
import WABP.GlobalCases.Parser.TabContent.JSON_ColumnParser;
import WABP.GlobalCases.Parser.TabContent.TagMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;

public class DynamicStepsInCustomForm extends Driver {
    JSON_ColumnParser jsonColumnParser = new JSON_ColumnParser();
    JSON_DataParser jsonDataParser = new JSON_DataParser();
    GlobalXpath globalXpath = new GlobalXpath();
    TagMap tagMap = new TagMap();
    ExpW_OpenComboBox ExpListOpen = new ExpW_OpenComboBox();
    ExpW_WaitDocOpen expDocOpen = new ExpW_WaitDocOpen();
    ExpW_OpenComboBox expWOpenComboBox = new ExpW_OpenComboBox();
    ClickRightButton clickRightButton = new ClickRightButton();
    TableContextMenu tableContextMenu = new TableContextMenu();
    ButtonAdd buttonAdd = new ButtonAdd();
    private static final Logger logger = LogManager.getLogger(DynamicStepsInCustomForm.class);
    private JSONObject ModalFieldWithProperties = new JSONObject();
    private JSONObject ModalFieldData = new JSONObject();
    private int MaxIndex = 0;

    public boolean validateTypeForm(String menuNumber){
        if (driver.findElement(By.xpath("//input-form-component-div[@menunumber='"+menuNumber+"']"))
                .getAttribute("objectname")
                .equals("Поиск классификаторов")) {
            return true;
        } else {
            return false;
        }
    }

    public void setDataToModal(){

    }

}
