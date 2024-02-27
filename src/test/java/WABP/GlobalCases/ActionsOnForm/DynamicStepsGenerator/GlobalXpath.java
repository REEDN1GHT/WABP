package WABP.GlobalCases.ActionsOnForm.DynamicStepsGenerator;

public class GlobalXpath {

    public String gInput(String jsonKey){
        jsonKey = "//*[@jsonkey='"+jsonKey+"']/input";
        return jsonKey;
    }

    public String gSubForm(String jsonKey){
        return "//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKey+"']";
    }

    public String gSubFormGrid(String jsonKey) {
        return  "//input-form-component-div[@jsontype='acSubForm' and @jsonkey='"+jsonKey+"']/vaadin-grid";
    }
}
