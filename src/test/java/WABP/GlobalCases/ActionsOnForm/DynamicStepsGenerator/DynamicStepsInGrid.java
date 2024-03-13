package WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator;

import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_OpenComboBox;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_WaitDocOpen;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ClickRightButton;
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
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.Map;

public class DynamicStepsInGrid extends Driver {
    JSON_ColumnParser jsonColumnParser = new JSON_ColumnParser();
    JSON_DataParser jsonDataParser = new JSON_DataParser();
    GlobalXpath globalXpath = new GlobalXpath();
    TagMap tagMap = new TagMap();
    ExpW_OpenComboBox ExpListOpen = new ExpW_OpenComboBox();
    ExpW_WaitDocOpen expDocOpen = new ExpW_WaitDocOpen();
    ExpW_OpenComboBox expWOpenComboBox = new ExpW_OpenComboBox();
    ClickRightButton clickRightButton = new ClickRightButton();
    TableContextMenu tableContextMenu = new TableContextMenu();
    private static final Logger logger = LogManager.getLogger(DynamicStepsInGrid.class);
    private JSONObject SubFieldWithProperties = new JSONObject();
    private JSONObject SubFieldData = new JSONObject();
    private int MaxIndex = 0;

    @Description("Определяем наименование JSON файла с параметрами полей")
    public String subFormName(String FieldSub){
        try {
            FieldSub = driver.findElement(By.xpath(globalXpath.gSubForm(FieldSub))).getAttribute("objectname");
        } catch (NullPointerException e) {
            FieldSub = null;
            logger.error("Cannot find SubForm on Tab", e);
        }
        assert FieldSub != null;
        return FieldSub.trim();
    }

    @Description("Парсим JSON десктоп АИС БП подформы")
    public JSONObject parseSubProperty(String FileSub) {
        return jsonColumnParser.FormFieldProperties(jsonColumnParser.GenerateJSON(FileSub +".json"));
    }

    @Description("Ищет ячейку строки в гриде по уникальному значению")
    /*
    Реворк в связи с изменением гридов по https://tc.aisa.ru/browse/WABP-2325
    */
    private WebElement searchFieldInRow(String uniqueDataInField, String jsonKeyTable){
//        String slot = driver.findElement(By.xpath("//vaadin-grid-cell-content//*[contains(text(), '"+uniqueDataInField+"')]/../..")).getAttribute("slot");
//        WebElement shadow = driver.findElement(By.xpath("//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKeyTable+"']/vaadin-grid"));
//        SearchContext shadowRoot = shadow.getShadowRoot();
//        return shadowRoot
//                .findElement(By.cssSelector("[name='"+slot+"']"))
//                .findElement(By.xpath("../.."));
        return driver.findElement(By.xpath("//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKeyTable+"']/vaadin-grid/vaadin-grid-cell-content//*[contains(text(), '"+uniqueDataInField+"')]/../.."));
    }
    @Description("Ищем последнюю строку в гриде которую надо кликнуть, для создания новой/сохранении предыдущей")
    private WebElement searchRowToCreate(String jsonKeyTable){
        WebElement shadow = driver.findElement(By.xpath("//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKeyTable+"']/vaadin-grid"));
        SearchContext shadowRoot = shadow.getShadowRoot();
        String slot = shadowRoot
                .findElement(By.cssSelector("tr[last]:not([hidden])"))
                .findElement(By.cssSelector("td[first-column] > slot"))
                .getAttribute("name");
        return driver.findElement(By.xpath("//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKeyTable+"']/vaadin-grid/vaadin-grid-cell-content[@slot='"+slot+"']"));
    }

    @Description("Валидация наличия заполненной строки в vaadin-grid")
    private Boolean validateCompletedRowExists(String uniqueDataInField, String jsonKeyTable){
        try {
            searchFieldInRow(uniqueDataInField, jsonKeyTable);
            return true;
        } catch (NoSuchElementException e) {
            logger.trace("the completed line is missing" + e);
            return false;
        }
    }
    @Description("Валидация наличия строк в vaadin-grid")
    private Boolean validateRowExists(String jsonKeyTable){
        try {
            searchRowToCreate( jsonKeyTable);
            return true;
        } catch (NoSuchElementException e) {
            logger.trace("the line is missing" + e);
            return false;
        }
    }

    //todo обработать условие когда есть строка в форме но нет в ней значений как в уточнении кбк
    @Description("Основной метод генерации строки, ее заполнения")
    public void setDataOnRow(String jsonKeyTable, String formName, JSONObject FieldData){
            SubFieldWithProperties = parseSubProperty(formName);
            SubFieldData = FieldData;
            JSONArray arrayRows = SubFieldData.getJSONObject("Tables").getJSONObject(jsonKeyTable).getJSONObject("Rows").names();
            for (int i = 1; i<=arrayRows.length(); i++){
                String uniqueData = null;
                if (SubFieldData.getJSONObject("Tables").getJSONObject(jsonKeyTable).getJSONObject("Rows").getJSONObject(String.valueOf(i)).has("Значение для поиска строки")){
                    uniqueData = SubFieldData.getJSONObject("Tables").getJSONObject(jsonKeyTable).getJSONObject("Rows").getJSONObject(String.valueOf(i)).getString("Значение для поиска строки");
                }
                if (uniqueData != null && validateCompletedRowExists(uniqueData, jsonKeyTable)) {
                    tableContextMenu.clickToEdit(searchFieldInRow(uniqueData, jsonKeyTable));
                    SetDataIntoLine("SubForm", formName, validateSubBodyField(formName), String.valueOf(i), jsonKeyTable);
                } else {
                    if (!validateRowExists(jsonKeyTable)){
                        tableContextMenu.clickToCreateRowInGrid(jsonKeyTable);
                    }
                    tableContextMenu.clickToEdit(searchRowToCreate(jsonKeyTable));
                    SetDataIntoLine("SubForm", formName, validateSubBodyField(formName), String.valueOf(i), jsonKeyTable);
                }
            }
    }
    @Description("Определение, параметра поля и валидация поля между Json'нами")
    private void SetDataIntoLine(String TypeData,
                                 String FormName,
                                 HashMap<String, Integer> LineMap,
                                 String numberRow,
                                 String jsonKeyTable){
        boolean LimitToList;
        for (int i=0; i<=MaxIndex; i++){
            String Field = getKeyByValue(LineMap, i);
            try {
                LimitToList = SubFieldWithProperties.getJSONObject(FormName).getJSONObject(Field).getBoolean("LimitToList");
            } catch (JSONException e) {
                LimitToList = true;
            }
            if (Field != null){
                if (SubFieldData.getJSONObject("Tables").getJSONObject(jsonKeyTable).getJSONObject("Rows").getJSONObject(numberRow).has(Field)){
                    setDataInFieldOnRow(SubFieldWithProperties.getJSONObject(FormName).getJSONObject(Field).getInt("ControlType"), LimitToList ,Field, numberRow, jsonKeyTable);
                }
            }
        }
    }
    @Description("Валидация поля на пред возможности его заполнения")
    private HashMap<String, Integer> validateSubBodyField(String FormName){
        HashMap<String, Integer> Line = new HashMap<>();
        JSONArray ListFields = SubFieldWithProperties.getJSONObject(FormName).names();
        for (Object Field : ListFields){
            if (SubFieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getBoolean("Visible") &&
                    !SubFieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getBoolean("Locked")) {
                int Index = SubFieldWithProperties.getJSONObject(FormName).getJSONObject(Field.toString()).getInt("TabIndex");
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
        return Line;
    }
    @Description("Заполнение полей данными в строке")
    private void setDataInFieldOnRow(int TypeForm,
                                     boolean LimitToList,
                                     String Field,
                                     String numberRow,
                                     String jsonKeyTable){
        //expDocOpen.waitClicableElem(globalXpath.gGridInput(jsonKeyTable, Field));
        expWOpenComboBox.Sleep(500);
        WebElement element = driver.findElement(By.xpath(globalXpath.gGridInput(jsonKeyTable, Field)));
        if (TypeForm == 106) {
            //todo Обработать клик в чекбокс с проверкой состояния true/false на морде и датасете
            element.click();
        } else {
            element.clear();
            //expWOpenComboBox.Sleep(200);
            element.click();
            //expWOpenComboBox.Sleep(200);
            if (TypeForm == 111){
                ExpListOpen.waitList();
            }
            element.sendKeys(SubFieldData.getJSONObject("Tables").getJSONObject(jsonKeyTable).getJSONObject("Rows").getJSONObject(numberRow).getString(Field));
            if (TypeForm == 111 && LimitToList){
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
}
