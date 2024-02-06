package WABP.GlobalCases.GeneratedTests;

import SeleniumDriver.Driver;
import WABP.GlobalCases.MenuSearchForm;
import WABP.GlobalCases.Parser.TXT_ParserConnectF;
import WABP.GlobalCases.Parser.ParserMenuObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DynamicTestGenerator extends Driver {

    ParserMenuObject XLSXParserMenuObject = new ParserMenuObject();
    TXT_ParserConnectF txtParserConnectF = new TXT_ParserConnectF();
    MenuSearchForm MSF = new MenuSearchForm();

    @DataProvider(name = "menuData")
    public Object[][] getMenuData() {
        JSONObject XlsxData = XLSXParserMenuObject.GenerateJson();
        JSONObject ConData = txtParserConnectF.ParserConnectF();
        JSONArray valueArray = ConData.getJSONArray("value");

        Object[][] data = new Object[valueArray.length()][2];

        for (int i = 0; i < valueArray.length(); i++) {
            String valueElement = valueArray.getString(i);
            String pathValue = XlsxData.getJSONObject(valueElement).getString("Path");
            String nameFormValue = XlsxData.getJSONObject(valueElement).getString("NameForm");
            data[i][0] = pathValue;
            data[i][1] = nameFormValue;
        }

        return data;
    }


    @Test(dataProvider = "menuData")
    public void DocMenuExists(String Path, String NameForm){
        MenuSearchForm MSF = new MenuSearchForm();
        MSF.SearchForm(Path);
        WebElement Field = driver.findElement(By.xpath("//*[text() = '" + NameForm + "']"));
        Assert.assertNotNull(Field);
    }

}
