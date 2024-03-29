package WABP.GlobalCases.GeneratedTests.Input.DataProvider;

import WABP.GlobalCases.Parser.JSON_DataParser;
import WABP.GlobalCases.Parser.ParserMenuObject;
import WABP.GlobalCases.Parser.TabContent.JSON_ColumnParser;
import WABP.GlobalCases.Parser.TabContent.JsonFilesListener;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataProviderForInput {
    JsonFilesListener JFL = new JsonFilesListener();
    JSON_DataParser jsonParser = new JSON_DataParser();
    JSON_ColumnParser jsonColumnParser = new JSON_ColumnParser();
    ParserMenuObject PMO = new ParserMenuObject();

    private JSONObject FormProperties = new JSONObject();
    private JSONObject MenuData = new JSONObject();


    @DataProvider(name = "FormDataExists")
    public Object[][] ValidatorDataForm(){
        List<String> listForms = JFL.listJsonFormFiles();
        List<String> listDataForForms = JFL.listJsonDataFiles();
        HashMap<String, String> fileMap = JFL.MapJsonDataFiles();
        JSONObject menuData = PMO.MenuDataFromBudget24();
        Object[][] data = new Object[listDataForForms.size()][3];
        for (int i = 0; i < listDataForForms.size(); i++) {
            String fileName = menuData.getJSONObject(listDataForForms.get(i)).getString("FileName") + ".json";
            String formName = fileMap.get(listDataForForms.get(i));
            data[i][0] = listDataForForms.get(i);
            data[i][1] = formName;
            data[i][2] = fileName;
            System.out.println("DataForDataDriver" + listDataForForms.get(i) + " " + formName + " " + fileName);
        }
        System.out.println("DataForDataDriver: listDataForForms " + listDataForForms.size() + " menuData " + menuData.length());

        return data;
    }


}
