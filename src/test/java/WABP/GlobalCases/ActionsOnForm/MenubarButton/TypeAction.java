package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator.DynamicSteps;
import WABP.GlobalCases.Parser.JSON_DataParser;
import org.json.JSONObject;

public class TypeAction {
    JSON_DataParser jsonDataParser = new JSON_DataParser();


    public boolean actionSave(String MenuNumber){
        JSONObject FieldData = jsonDataParser.ReadJSON(MenuNumber);
        if (FieldData.getJSONObject("Actions").has("Save")){
            return FieldData.getJSONObject("Actions").getBoolean("Save");
        } else {
            return false;
        }
    }
    public boolean actionDelete(String MenuNumber){
        JSONObject FieldData = jsonDataParser.ReadJSON(MenuNumber);
        if (FieldData.getJSONObject("Actions").has("Delete")){
            return FieldData.getJSONObject("Actions").getBoolean("Delete");
        } else {
            return false;
        }
    }
}
