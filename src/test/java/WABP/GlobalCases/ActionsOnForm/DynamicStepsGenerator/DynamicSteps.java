package WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator;

import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_OpenComboBox;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_WaitDocOpen;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonYesWhenOpen;
import WABP.GlobalCases.Parser.JSON_DataParser;
import WABP.GlobalCases.Parser.TabContent.JSON_ColumnParser;
import WABP.GlobalCases.Parser.TabContent.TagMap;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicSteps extends Driver {

    JSON_ColumnParser jsonColumnParser = new JSON_ColumnParser();
    JSON_DataParser jsonDataParser = new JSON_DataParser();
    GlobalXpath globalXpath = new GlobalXpath();
    TagMap tagMap = new TagMap();
    ExpW_OpenComboBox ExpListOpen = new ExpW_OpenComboBox();
    ExpW_WaitDocOpen ExpDocOpen = new ExpW_WaitDocOpen();
    ButtonYesWhenOpen buttonYesWhenOpen = new ButtonYesWhenOpen();
    DynamicStepsInGrid dynamicStepsInGrid = new DynamicStepsInGrid();

    private static final Logger logger = LogManager.getLogger(DynamicSteps.class);
    private JSONObject FieldWithProperties = new JSONObject();
    public JSONObject FieldData = new JSONObject();
    private int MaxIndex = 0;

    public void StepsToSave(String MenuNumber, String FileName){
        String FormName = FileName.replaceAll(".json", "");
        FieldWithProperties = jsonColumnParser.FormFieldProperties(jsonColumnParser.GenerateJSON(FileName));
        FieldData = jsonDataParser.ReadJSON(MenuNumber);

        SetDataIntoLine("Header", FormName, ValidateHeaderField(FormName));
        WebElement Modal = buttonYesWhenOpen.modalWhenOpenDoc();
        if (Modal != null) {
            Modal.click();
        }
        ExpDocOpen.waitForModalLoadingDataToDisappear();
        SetDataIntoLine("Body", FormName, ValidateBodyField(FormName));
    }

//    public void StepsToDelete(String MenuNumber, String FileName){
//        String FormName = FileName.replaceAll(".json", "");
//        FieldWithProperties = jsonColumnParser.FormFieldProperties(jsonColumnParser.GenerateJSON(FileName));
//        FieldData = jsonDataParser.ReadJSON(MenuNumber);
//
//        SetDataIntoLine("Header", FormName, ValidateHeaderField(FormName));
//        driver.findElement(By.xpath("//vaadin-button[contains(., 'Да')]")).click();
//
//    }

    private void SetDataIntoLine(String TypeData, String FormName,  HashMap<String, Integer> LineMap){
        boolean LimitToList;
        for (int i=0; i<=MaxIndex; i++){
            String Field = getKeyByValue(LineMap, i);
            try {
                LimitToList = FieldWithProperties.getJSONObject(FormName).getJSONObject(Field).getBoolean("LimitToList");
            } catch (JSONException e) {
                LimitToList = true;
            }
            if (Field != null){
                if (FieldData.getJSONObject("FormField").has(Field)){
                    SetField(FieldWithProperties.getJSONObject(FormName).getJSONObject(Field).getInt("ControlType"), LimitToList ,Field);
                }
                if (Field.matches("Документ.*") && ContainsSub(Field)){
                    if (FieldData.getJSONObject("Tables").has(Field)){
                        dynamicStepsInGrid.setDataOnRow(Field, dynamicStepsInGrid.subFormName(Field), FieldData);
                    }
                }
            }
        }
    }

    private HashMap<String, Integer> ValidateHeaderField(String FormName){
        HashMap<String, Integer> Line = new HashMap<>();
        JSONArray ListFields = FieldWithProperties.getJSONObject(FormName).names();
        for (Object Field : ListFields){
            if(HeaderField(FieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getString("Tag"))) {
                int Index = FieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getInt("TabIndex");
                if (Index > MaxIndex){
                    MaxIndex=Index;
                }
                Line.put(Field.toString(), FieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getInt("TabIndex"));
            }
        }
        return Line;
    }

    private HashMap<String, Integer> ValidateBodyField(String FormName){
        HashMap<String, Integer> Line = new HashMap<>();
        JSONArray ListFields = FieldWithProperties.getJSONObject(FormName).names();
        for (Object Field : ListFields){
            if (!HeaderField(FieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getString("Tag")))
            {
                if (FieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getBoolean("Visible") &&
                        !FieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getBoolean("Locked")) {
                    int Index = FieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getInt("TabIndex");
                    if (Index > MaxIndex) {
                        MaxIndex = Index;
                    }
                    if (getKeyByValue(Line, Index) != null) {
                        Index = MaxIndex + 1;
                        MaxIndex = Index;
                    }
                    Line.put(Field.toString(), Index);
                }
            }
        }
        return Line;
    }

    @Description("Определение ключевых полей в шапке формы")
    private Boolean HeaderField(String Tag){
        boolean HeaderField = false;
        HashMap map = tagMap.CsvParser(Tag);
        boolean containsFieldKey1 = map.containsKey("FieldKey") && map.get("FieldKey").equals("1");
        boolean enableDocPresent = map.containsKey("EnableDoc") && map.get("EnableDoc").equals("1");
        if (containsFieldKey1 && !enableDocPresent) {
            HeaderField = true;
        }
        return HeaderField;
    }
    @Description("Определение полей в теле формы")
    private Boolean BodyField(String Tag){
        boolean BodyField = false;
        HashMap map = tagMap.CsvParser(Tag);
        boolean containsFieldKey1 = map.containsKey("FieldKey") && map.get("FieldKey").equals("1");
        boolean enableDocPresent = map.containsKey("EnableDoc") && map.get("EnableDoc").equals("1");
        if (!containsFieldKey1 && enableDocPresent) {
            BodyField = true;
        }
        return BodyField;
    }


    @Description("Заполнение полей данными в форме")
    private void SetField(int TypeForm, boolean LimitToList, String Field){
        WebElement element = driver.findElement(By.xpath(globalXpath.gInput(Field)));
        element.clear();
        element.click();
        if (TypeForm == 111){
            ExpListOpen.waitList();
        }
        element.sendKeys(FieldData.getJSONObject("FormField").getString(Field));
        if (TypeForm == 111 && LimitToList){
            ExpListOpen.waitFocusedElem();
        }
        element.sendKeys(Keys.ENTER);
    }

    @Description("Поиск ключа в мапе по TabIndex")
    private String getKeyByValue(Map<String, Integer> map, int value) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }

    private boolean ContainsSub(String jsonKeyTable){
        try {
            driver.findElement(By.xpath(globalXpath.gSubForm(jsonKeyTable)));
            return true;
        } catch (JSONException e){
            logger.trace("cannot find SubForm");
            return false;
        }
    }
}
