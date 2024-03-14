package WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator;

public class GlobalXpath {

    public String gInput(String jsonKey){
        jsonKey = "//*[@jsonkey='"+jsonKey+"']/input";
        return jsonKey;
    }
    public String gGridInput(String jsonKeyTable, String fieldkey){
        fieldkey = "//input-form-component-div[@jsonkey='"+ jsonKeyTable +"']//*[@fieldkey='"+ fieldkey +"']/input";
        return fieldkey;
    }

    public String gSubForm(String jsonKey){
        return "//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKey+"']";
    }

    public String gSubFormGrid(String jsonKey) {
        return  "//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKey+"']/vaadin-grid";
    }
}
