package WABP.GlobalCases.ActionsOnForm.MenubarButton;

import WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator.DynamicSteps;

public class TypeAction {
    DynamicSteps dynamicSteps = new DynamicSteps();

    public boolean actionSave(){
        if (dynamicSteps.FieldData.getJSONObject("Actions").has("Save")){
            return dynamicSteps.FieldData.getJSONObject("Actions").getBoolean("Save");
        } else {
            return false;
        }
    }
    public boolean actionDelete(){
        if (dynamicSteps.FieldData.getJSONObject("Actions").has("Delete")){
            return dynamicSteps.FieldData.getJSONObject("Actions").getBoolean("Delete");
        } else {
            return false;
        }
    }
}
