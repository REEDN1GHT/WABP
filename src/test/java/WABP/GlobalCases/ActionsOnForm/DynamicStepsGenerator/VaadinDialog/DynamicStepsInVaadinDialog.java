package WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator.VaadinDialog;


import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator.DynamicStepsInGrid;
import WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator.GlobalXpath;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_OpenComboBox;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_WaitDocOpen;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonYesWhenOpen;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.TableContextMenu;
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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicStepsInVaadinDialog extends Driver {
    JSON_ColumnParser jsonColumnParser = new JSON_ColumnParser();
    JSON_DataParser jsonDataParser = new JSON_DataParser();
    GlobalXpath globalXpath = new GlobalXpath();
    TagMap tagMap = new TagMap();
    ExpW_OpenComboBox ExpListOpen = new ExpW_OpenComboBox();
    ExpW_WaitDocOpen ExpDocOpen = new ExpW_WaitDocOpen();
    ButtonYesWhenOpen buttonYesWhenOpen = new ButtonYesWhenOpen();
    DynamicStepsinVaadinDialogInGrid dynamicStepsinVaadinDialogInGrid = new DynamicStepsinVaadinDialogInGrid();
    TableContextMenu tableContextMenu = new TableContextMenu();

    private static final Logger logger = LogManager.getLogger(DynamicStepsInVaadinDialog.class);
    private JSONObject dialogFieldWithProperties = new JSONObject();
    public JSONObject dialogFieldData = new JSONObject();
    private int MaxIndex = 0;

    private String objectNameDialog(){
        return driver.findElement(By.xpath("//vaadin-dialog-overlay//input-form-component-div[@jsontype='acRectangle']"))
                .getAttribute("jsonKey");
    }

    public void StepsToSave(JSONObject FieldData){
        String formName = objectNameDialog();
        String FileName = formName+".json";
        dialogFieldWithProperties = jsonColumnParser.FormFieldProperties(jsonColumnParser.GenerateJSON(FileName));
        dialogFieldData = FieldData;
        SetDataIntoLine("Body", formName, ValidateBodyField(formName));
    }


    private void SetDataIntoLine(String TypeData, String FormName,  HashMap<String, Integer> LineMap){
        boolean LimitToList;
        for (int i=0; i<=MaxIndex; i++){
            String Field = getKeyByValue(LineMap, i);
            try {
                LimitToList = dialogFieldWithProperties.getJSONObject(FormName).getJSONObject(Field).getBoolean("LimitToList");
            } catch (JSONException e) {
                LimitToList = true;
            }
            if (Field != null){
                if (dialogFieldData.getJSONObject("vaadinDialogOverlay").getJSONObject(FormName).has(Field) && !Field.matches("Документ.*")){
                    SetField(dialogFieldWithProperties.getJSONObject(FormName).getJSONObject(Field).getInt("ControlType"), LimitToList ,Field, FormName);
                }
                if (Field.matches("Документ.*") && ContainsSub(Field)){
                    if (dialogFieldData.getJSONObject("vaadinDialogOverlay").getJSONObject(FormName).has(Field)){
                        dynamicStepsinVaadinDialogInGrid.setDataOnRow(FormName, Field, dynamicStepsinVaadinDialogInGrid.subFormName(Field), dialogFieldData);
                    }
                }
            }
        }
    }
    //todo: может потребоваться обернуть в try catch, если табы будут залочены
    private void createTabsOnDOM(){
        List<WebElement> tabs = driver.findElements(By.xpath("//vaadin-tabs[@jsontype='acTabCtl']/vaadin-tab[@class='tab']"));
        if (tabs.size()>0){
            for (WebElement element : tabs){
                element.click();
            }
        }
    }

    private String validateTabs(String field, String type){
        if (type.equals("subForm")){
            try{
                if (dialogFieldData.getJSONObject("Tables").has(field)){
                    return driver.findElement(By.xpath("//div[input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+ field +"']][@jsontype='acPage']")).getAttribute("jsonkey");
                }
            } catch (NoSuchElementException e) {
                logger.trace("cannot find tab" + e.getStackTrace());
                return null;
            }
        }
        return null;
    }

    private HashMap<String, Integer> ValidateHeaderField(String FormName){
        HashMap<String, Integer> Line = new HashMap<>();
        JSONArray ListFields = dialogFieldWithProperties.getJSONObject(FormName).names();
        for (Object Field : ListFields){
            if(HeaderField(dialogFieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getString("Tag"))) {
                int Index = dialogFieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getInt("TabIndex");
                if (Index > MaxIndex){
                    MaxIndex=Index;
                }
                Line.put(Field.toString(), dialogFieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getInt("TabIndex"));
            }
        }
        return Line;
    }

    private HashMap<String, Integer> ValidateBodyField(String FormName){
        HashMap<String, Integer> Line = new HashMap<>();
        JSONArray ListFields = dialogFieldWithProperties.getJSONObject(FormName).names();
        for (Object Field : ListFields){
            if (!HeaderField(dialogFieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getString("Tag")))
            {
                if (dialogFieldWithProperties.getJSONObject(FormName)
                        .getJSONObject(Field.toString())
                        .getBoolean("Visible") &&
                        (!dialogFieldWithProperties.getJSONObject(FormName)
                                .getJSONObject(Field.toString())
                                .has("Locked") ||
                                !dialogFieldWithProperties.getJSONObject(FormName)
                                        .getJSONObject(Field.toString())
                                        .getBoolean("Locked"))) {
                    int Index = dialogFieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getInt("TabIndex");
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
    private void SetField(int TypeForm,
                          boolean LimitToList,
                          String Field,
                          String dialogFormName){
        WebElement element;
        if (TypeForm != 104) {
            element = driver.findElement(By.xpath(globalXpath.gDialogInput(Field)));
        } else {
            element = driver.findElement(By.xpath(globalXpath.gDialogButton(Field)));
        }
        tableContextMenu.scrollIntoView(element);
        if (TypeForm == 106 || TypeForm == 104) {
            //todo Обработать клик в чекбокс с проверкой состояния true/false на морде и датасете
            element.click();
        } else {
            element.clear();
            element.click();
            if (TypeForm == 111) {
                ExpListOpen.waitList();
            }
            element.sendKeys(dialogFieldData.getJSONObject("vaadinDialogOverlay").getJSONObject(dialogFormName).getString(Field));
            if (TypeForm == 111 && LimitToList) {
                ExpListOpen.waitFocusedElem();
            }
            element.sendKeys(Keys.ENTER);
        }
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
            driver.findElement(By.xpath(globalXpath.gDialogSubForm(jsonKeyTable)));
            return true;
        } catch (NoSuchElementException e){
            logger.trace("cannot find SubForm");
            return false;
        }
    }
}
