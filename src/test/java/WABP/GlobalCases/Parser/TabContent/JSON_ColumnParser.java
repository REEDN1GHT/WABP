package WABP.GlobalCases.Parser.TabContent;

import org.checkerframework.checker.units.qual.A;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class JSON_ColumnParser {
    JsonFilesListener JFL = new JsonFilesListener();

    public JSONObject ColumnParser(List<String> FilesList){
        JSONObject properties = new JSONObject();
        return properties;
    }

    public JSONObject GenerateJSON(String FileName){
        String filePath = "desktop-raw-files-master/json/inputs/"+FileName;
        JSONTokener tokener = null;
        try {
            tokener = new JSONTokener(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonFile = new JSONObject(tokener);
        return  jsonFile;
    }

    public JSONObject FormFieldProperties(JSONObject json) {
        JSONObject FormFields = new JSONObject();
        JSONObject FieldProperties = new JSONObject();
        int AddIndex = 90;
        String FormName = "";
        JSONArray keys = json.names();
        for (int i=0; i< keys.length(); i++){
            String key = keys.getString(i);
            if (!key.equals("Menu_number")){
                FormName = key;
                break;
            }
        }
        // Получение значений для переменной fieldNames
        String[] fieldNames = {};
        if (json.has(FormName)) {
            JSONObject fileNameObject = json.getJSONObject(FormName);
            if (fileNameObject.has("Controls")) {
                JSONObject controlsObject = fileNameObject.getJSONObject("Controls");
                fieldNames = controlsObject.keySet().toArray(new String[0]);
            }
        }
        // Обход объекта и извлечение нужных данных
        for (String fieldName : fieldNames) {
            if (json.has(FormName)) {
                JSONObject fileNameObject = json.getJSONObject(FormName);
                if (fileNameObject.has("Controls")) {
                    JSONObject controlsObject = fileNameObject.getJSONObject("Controls");
                    if (controlsObject.has(fieldName)) {
                        JSONObject fieldObject = controlsObject.getJSONObject(fieldName);
                        if (fieldObject.has("Properties")) {
                            JSONObject propertiesObject = fieldObject.getJSONObject("Properties");
                            if (propertiesObject.getBoolean("Visible")) {
                                if (propertiesObject.has("ControlType")) {
                                    int controlType = propertiesObject.getInt("ControlType");
                                    // Проверка на нужные типы контроллеров
                                    if (controlType == 111 || controlType == 109 || controlType == 112 || controlType == 106 || controlType == 104) {
                                        // Получение нужных данных
                                        HashMap properties = new HashMap();
                                        properties.put("Name", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getString("Name"));
                                        properties.put("ControlType", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getInt("ControlType"));
                                        properties.put("TabIndex", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getInt("TabIndex"));
                                        properties.put("TabStop", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getString("TabStop"));
                                        properties.put("Tag", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getString("Tag"));
                                        properties.put("Enabled", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getString("Enabled"));
                                        if (propertiesObject.has("Locked")){
                                            properties.put("Locked", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getString("Locked"));
                                        }
                                        properties.put("Visible", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getString("Visible"));
                                        if (propertiesObject.has("LimitToList")){
                                            properties.put("LimitToList", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getString("LimitToList"));
                                        }
                                        FieldProperties.put(fieldName, properties);
                                        FormFields.put(FormName, FieldProperties);
                                    } else if (controlType == 123){
                                        JSONObject AddControls = fieldObject.getJSONObject("Controls");
                                        JSONArray ArrayAddTab = AddControls.names();
                                        for (Object AddTab : ArrayAddTab) {
                                            JSONObject AddTabFieldObject = AddControls.getJSONObject(AddTab.toString());
                                            JSONArray ArrayAddFields = AddTabFieldObject.getJSONObject("Controls").names();
                                            for (Object AddField : ArrayAddFields){
                                                if (AddTabFieldObject.has("Controls")) {
                                                    JSONObject AddFieldsObject = AddTabFieldObject.getJSONObject("Controls").getJSONObject(AddField.toString());
                                                    if (AddFieldsObject.has("Properties")) {
                                                        JSONObject addPropertiesObject = AddFieldsObject.getJSONObject("Properties");
                                                        if (addPropertiesObject.getBoolean("Visible")) {
                                                            int AddcontrolType = addPropertiesObject.getInt("ControlType");
                                                            if (AddcontrolType == 111 || AddcontrolType == 109 || AddcontrolType == 112 || AddcontrolType == 106 || AddcontrolType == 104) {
                                                                HashMap addproperties = new HashMap();
                                                                addproperties.put("Name", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Controls").getJSONObject(AddTab.toString()).getJSONObject("Controls").getJSONObject(AddField.toString()).getJSONObject("Properties").getString("Name"));
                                                                addproperties.put("ControlType", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Controls").getJSONObject(AddTab.toString()).getJSONObject("Controls").getJSONObject(AddField.toString()).getJSONObject("Properties").getInt("ControlType"));
                                                                addproperties.put("TabIndex", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Controls").getJSONObject(AddTab.toString()).getJSONObject("Controls").getJSONObject(AddField.toString()).getJSONObject("Properties").getInt("TabIndex"));
                                                                addproperties.put("TabStop", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Controls").getJSONObject(AddTab.toString()).getJSONObject("Controls").getJSONObject(AddField.toString()).getJSONObject("Properties").getString("TabStop"));
                                                                addproperties.put("Tag", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Controls").getJSONObject(AddTab.toString()).getJSONObject("Controls").getJSONObject(AddField.toString()).getJSONObject("Properties").getString("Tag"));
                                                                addproperties.put("Enabled", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Controls").getJSONObject(AddTab.toString()).getJSONObject("Controls").getJSONObject(AddField.toString()).getJSONObject("Properties").getString("Enabled"));
                                                                if (propertiesObject.has("Locked")){
                                                                    addproperties.put("Locked", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Properties").getString("Locked"));

                                                                }
                                                                addproperties.put("Visible", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Controls").getJSONObject(AddTab.toString()).getJSONObject("Controls").getJSONObject(AddField.toString()).getJSONObject("Properties").getString("Visible"));
                                                                if (addPropertiesObject.has("LimitToList")){
                                                                    addproperties.put("LimitToList", json.getJSONObject(FormName).getJSONObject("Controls").getJSONObject(fieldName).getJSONObject("Controls").getJSONObject(AddTab.toString()).getJSONObject("Controls").getJSONObject(AddField.toString()).getJSONObject("Properties").getString("LimitToList"));
                                                                }
                                                                FieldProperties.put(AddField.toString(), addproperties);
                                                                FormFields.put(FormName, FieldProperties);
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return FormFields;
    }

    @Test
    public void test(){
        List<String> ListForms = JFL.listJsonFormFiles();
        //for (String listForm : ListForms) {
        System.out.println(FormFieldProperties(GenerateJSON("Ввод поручений.json")));
        //}

    }


}
