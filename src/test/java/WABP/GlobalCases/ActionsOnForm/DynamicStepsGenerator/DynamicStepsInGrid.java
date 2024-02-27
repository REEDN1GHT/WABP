package WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator;

import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_OpenComboBox;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_WaitDocOpen;
import WABP.GlobalCases.Auth;
import WABP.GlobalCases.NovigateToFrorm;
import WABP.GlobalCases.Parser.JSON_DataParser;
import WABP.GlobalCases.Parser.TabContent.JSON_ColumnParser;
import WABP.GlobalCases.Parser.TabContent.TagMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DynamicStepsInGrid extends Driver {
    JSON_ColumnParser jsonColumnParser = new JSON_ColumnParser();
    JSON_DataParser jsonDataParser = new JSON_DataParser();
    GlobalXpath globalXpath = new GlobalXpath();
    TagMap tagMap = new TagMap();
    ExpW_OpenComboBox ExpListOpen = new ExpW_OpenComboBox();
    ExpW_WaitDocOpen ExpDocOpen = new ExpW_WaitDocOpen();

    private static final Logger logger = LogManager.getLogger(DynamicStepsInGrid.class);
    private JSONObject FieldWithProperties = new JSONObject();
    private JSONObject FieldData = new JSONObject();

    public String SubFormName(String FieldSub){
        try {
            FieldSub = driver.findElement(By.xpath(globalXpath.gSubForm(FieldSub))).getAttribute("objectname");
        } catch (NullPointerException e) {
            FieldSub = null;
            logger.error("Cannot find SubForm on Tab", e);
        }
        return FieldSub;
    }

    public void StepsInGrid(String MenuNumber, String FileSub) {
        String FormName = FileSub.replaceAll(".json", "");
        FieldWithProperties = jsonColumnParser.FormFieldProperties(jsonColumnParser.GenerateJSON(FileSub.trim()+".json"));
        FieldData = jsonDataParser.ReadJSON(MenuNumber);
    }

    Auth auth = new Auth();
    NovigateToFrorm novigateToFrorm = new NovigateToFrorm();
    @Test
    public void test(){
//        auth.AuthWABP();
//        novigateToFrorm.NovigateTo("DKЮН");
        String MenuNumber = "DKЮН";
//        String FileSub = SubFormName("Документ");
//        FieldWithProperties = jsonColumnParser.FormFieldProperties(jsonColumnParser.GenerateJSON(FileSub+".json"));
        FieldData = jsonDataParser.ReadJSON(MenuNumber);
        //TODO продолжить ковырять ShaaowRoot

        JSONArray arrayTables = FieldData.getJSONObject("Tables").names();
        //JSONArray arrayRows = FieldData.getJSONObject("Tables").getJSONArray("Rows");
        for (Object Table : arrayTables){
            JSONArray arrayRows = FieldData.getJSONObject("Tables").getJSONObject(Table.toString()).getJSONObject("Rows").names();
            for (Object Row : arrayRows){
                FieldData.getJSONObject("Tables").getJSONObject(Table.toString()).getJSONObject("Rows").getJSONObject(Row.toString());
            }
        }
        System.out.println("sfsfsf");
        System.out.println("sfsfsf");
        System.out.println("sfsfsf");
        System.out.println("sfsfsf");
        String slot = driver.findElement(By.xpath("//vaadin-grid-cell-content//*[contains(text(), '9999999990')]/../..")).getAttribute("slot");

        WebElement shadow = driver.findElement(By.xpath("//input-form-component-div[@jsontype='acSubForm' and @jsonkey='Документ']/vaadin-grid"));
        SearchContext shadowRoot = shadow.getShadowRoot();
        WebElement result = shadowRoot.findElement(By.cssSelector("[name='"+slot+"']"));
        List<WebElement> list = result.findElements(By.xpath("../../td"));
        for (WebElement elem:list){
            System.out.println(elem.getAttribute("id"));
        }

    }
}
