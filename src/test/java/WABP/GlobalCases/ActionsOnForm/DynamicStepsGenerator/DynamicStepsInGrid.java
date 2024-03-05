package WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator;

import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_OpenComboBox;
import WABP.GlobalCases.ActionsOnForm.ExplicitWaits.ExpW_WaitDocOpen;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ClickRightButton;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.TableContextMenu;
import WABP.GlobalCases.Auth;
import WABP.GlobalCases.NovigateToFrorm;
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
import org.testng.annotations.Test;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class DynamicStepsInGrid extends Driver {
    JSON_ColumnParser jsonColumnParser = new JSON_ColumnParser();
    JSON_DataParser jsonDataParser = new JSON_DataParser();
    GlobalXpath globalXpath = new GlobalXpath();
    TagMap tagMap = new TagMap();
    ExpW_OpenComboBox ExpListOpen = new ExpW_OpenComboBox();
    ExpW_WaitDocOpen ExpDocOpen = new ExpW_WaitDocOpen();
    ClickRightButton clickRightButton = new ClickRightButton();
    TableContextMenu tableContextMenu = new TableContextMenu();
    private static final Logger logger = LogManager.getLogger(DynamicStepsInGrid.class);
    private JSONObject SubFieldWithProperties = new JSONObject();
    private JSONObject SubFieldData = new JSONObject();
    private int MaxIndex = 0;

    public String subFormName(String FieldSub){
        try {
            FieldSub = driver.findElement(By.xpath(globalXpath.gSubForm(FieldSub))).getAttribute("objectname");
        } catch (NullPointerException e) {
            FieldSub = null;
            logger.error("Cannot find SubForm on Tab", e);
        }
        return FieldSub.trim();
    }

    public JSONObject parseSubProperty(String FileSub) {
        return jsonColumnParser.FormFieldProperties(jsonColumnParser.GenerateJSON(FileSub +".json"));
    }

    private WebElement searchFieldInRow(String uniqueDataInField, String jsonKeyTable){
        String slot = driver.findElement(By.xpath("//vaadin-grid-cell-content//*[contains(text(), '"+uniqueDataInField+"')]/../..")).getAttribute("slot");
        WebElement shadow = driver.findElement(By.xpath("//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKeyTable+"']/vaadin-grid"));
        SearchContext shadowRoot = shadow.getShadowRoot();
        return shadowRoot
                .findElement(By.cssSelector("[name='"+slot+"']"))
                .findElement(By.xpath("../.."));
    }

    public Boolean validateCompletedRowExists(String uniqueDataInField, String jsonKeyTable){
        try {
            searchFieldInRow(uniqueDataInField, jsonKeyTable);
            return true;
        } catch (NullPointerException e) {
            logger.trace("the completed line is missing" + e);
            return false;
        }
    }

    //todo обработать условие когда есть строка в форме но нет в ней значений как в уточнении кбк
    @Description("Определение строки в которую будут вставляться данные")
    public void setDataOnRow(String jsonKeyTable, String formName, JSONObject FieldData){
            SubFieldWithProperties = parseSubProperty(formName);
            SubFieldData = FieldData;
            JSONArray arrayRows = SubFieldData.getJSONObject("Tables").getJSONObject(jsonKeyTable).getJSONObject("Rows").names();
            for (int i = 1; i<=arrayRows.length(); i++){
                String uniqueData = SubFieldData.getJSONObject("Tables").getJSONObject(jsonKeyTable).getJSONObject("Rows").getJSONObject(String.valueOf(i)).getString("Значение для поиска строки");
                if (validateCompletedRowExists(uniqueData, jsonKeyTable)) {
                    tableContextMenu.clickToEdit(searchFieldInRow(uniqueData, jsonKeyTable));
//                    clickRightButton.clickRightButton(searchFieldInRow(uniqueData, jsonKeyTable));
//                    tableContextMenu.clickEdit();
                    SetDataIntoLine("SubForm", formName, validateSubBodyField(formName), String.valueOf(i), jsonKeyTable);
//                    clickRightButton.clickRightButton(searchFieldInRow(uniqueData, jsonKeyTable));
                } else {
                    clickRightButton.clickRightButton(driver.findElement(By.xpath(globalXpath.gSubFormGrid(jsonKeyTable))));
                    tableContextMenu.clickAddNewRow();
                    SetDataIntoLine("SubForm", formName, validateSubBodyField(formName), String.valueOf(i), jsonKeyTable);
                    clickRightButton.clickRightButton(driver.findElement(By.xpath(globalXpath.gSubFormGrid(jsonKeyTable))));
                }
                //todo 1. После раскрытия строки мы не можем повторно искать по тексту так как span пропадает
                //todo 2. Мы не можем кликнуть в строку или в ячейку, так как элементы друг друга перекрывают
                //todo 3. Рассмотреть вариант установки значений не используя scroller в shadowroot
                tableContextMenu.clickToSaveRow(searchFieldInRow(uniqueData, jsonKeyTable));
            }
    }

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
                                     String NumberRow,
                                     String jsonKeyTable){
        WebElement element = driver.findElement(By.xpath(globalXpath.gInput(Field)));
        if (TypeForm == 106) {
            //todo Обработать клик в чекбокс с проверкой состояния true/false на морде и датасете
            element.click();
        } else {
            element.clear();
            element.click();
            if (TypeForm == 111){
                ExpListOpen.waitList();
            }
            element.sendKeys(SubFieldData.getJSONObject("Tables").getString(Field));
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









    Auth auth = new Auth();
    NovigateToFrorm novigateToFrorm = new NovigateToFrorm();

    @Test
    public void test(){
        String menuNumber = "DKЮН";
        SubFieldData = jsonDataParser.ReadJSON(menuNumber);
        auth.AuthWABP();
        novigateToFrorm.NovigateTo(menuNumber);

        System.out.println("12311");
        System.out.println("12311");
        System.out.println("12311");
        System.out.println("12311");
    }
}
