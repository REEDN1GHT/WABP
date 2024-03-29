package WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator;

public class GlobalXpath {

    public String gInput(String jsonKey){
        jsonKey = "//*[@jsonkey='"+jsonKey+"']/input";
        return jsonKey;
    }
    public String gDialogInput(String jsonKey){
        jsonKey = "//vaadin-dialog-overlay//*[@jsonkey='"+jsonKey+"']/input";
        return jsonKey;
    }
    public String gDialogButton(String jsonKey){
        jsonKey = "//vaadin-dialog-overlay//*[@jsonkey='"+jsonKey+"']";
        return jsonKey;
    }
    public String gGridInput(String jsonKeyTable, String fieldkey){
        fieldkey = "//input-form-component-div[@jsonkey='"+ jsonKeyTable +"']//*[@fieldkey='"+ fieldkey +"']/input";
        return fieldkey;
    }
    public String gDialogGridInput(String jsonKeyTable, String fieldkey){
        fieldkey = "//vaadin-dialog-overlay//input-form-component-div[@jsonkey='"+ jsonKeyTable +"']//*[@fieldkey='"+ fieldkey +"']/input";
        return fieldkey;
    }
    public String gGridButton(String jsonKeyTable, String fieldkey){
        fieldkey = "//input-form-component-div[@jsonkey='"+ jsonKeyTable +"']//vaadin-horizontal-layout/*[@fieldkey='"+ fieldkey +"']";
        return fieldkey;
    }
    public String gDialogGridButton(String jsonKeyTable, String fieldkey){
        fieldkey = "//vaadin-dialog-overlay//input-form-component-div[@jsonkey='"+ jsonKeyTable +"']//vaadin-horizontal-layout/*[@fieldkey='"+ fieldkey +"']";
        return fieldkey;
    }

    public String gSubForm(String jsonKey){
        return "//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKey+"']";
    }
    public String gDialogSubForm(String jsonKey){
        return "//vaadin-dialog-overlay//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKey+"']";
    }

    public String gSubFormGrid(String jsonKey) {
        return  "//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKey+"']/vaadin-grid";
    }
}
