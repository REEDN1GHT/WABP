package WABP.GlobalCases.GeneratedTests;

import Logs.TakesScreenShots;
import SeleniumDriver.Driver;
import WABP.GlobalCases.MenuSearchForm;
import WABP.GlobalCases.NovigateToFrorm;
import WABP.GlobalCases.Parser.TXT_ParserConnectF;
import WABP.GlobalCases.Parser.ParserMenuObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class DynamicTestGenerator extends Driver {
    NovigateToFrorm NTF = new NovigateToFrorm();
    TakesScreenShots takesScreenShots = new TakesScreenShots();
    ParserMenuObject ParserMenuObject = new ParserMenuObject();
    TXT_ParserConnectF txtParserConnectF = new TXT_ParserConnectF();
    MenuSearchForm MSF = new MenuSearchForm();
    private JSONObject XlsxData = new JSONObject();

    @DataProvider(name = "menuData")
    public Object[][] getMenuData() {
        JSONObject ConData = ValidateShadowForm();
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
        //WebElement Field = driver.findElement(By.xpath("//*[text() = '" + NameForm + "']"));
        takesScreenShots.TakesScreenshotsSuccess("Наличие_в_меню", NameForm);
        Assert.assertNotNull(driver.findElement(By.xpath("//*[text() = '" + NameForm + "']")));
    }

    @Test(dataProvider = "menuData")
    public void DocMenuOpen(String Path, String NameForm){
        MenuSearchForm MSF = new MenuSearchForm();
        MSF.SearchFormContent(Path);
        driver.findElement(By.xpath("//*[text() = '" + NameForm + "']")).click();
        NTF.waitContentIntoInput();
        Assert.assertNotNull(driver.findElement(By
                .xpath("//vaadin-app-layout/vaadin-vertical-layout/div/vaadin-vertical-layout[2]")), "Форма не открылась");
        takesScreenShots.TakesScreenshotsSuccess("DocOpen", NameForm);
        Assert.assertNotEquals(0, driver.findElements(By
                        .xpath("//vaadin-scroller/vaadin-vertical-layout/*/*"))
                .size(), "Контент формы не найден");
    }


    public JSONObject ValidateShadowForm(){
        XlsxData = ParserMenuObject.MenuDataFromBudget24();
        JSONObject ConData = txtParserConnectF.ParserConnectF();
        JSONArray ConData1 = new JSONArray();
        JSONObject ConData2 = new JSONObject();
        JSONArray valueArray = ConData.getJSONArray("value");
        List<Object> List = new ArrayList<>();
        for (int i = 0; i < valueArray.length(); i++) {
            String valueElement = valueArray.getString(i);
            try {
                String pathValue = XlsxData.getJSONObject(valueElement).getString("Path");
                String nameFormValue = XlsxData.getJSONObject(valueElement).getString("NameForm");
                ConData1.put(valueElement);
            } catch (Exception e){
            List.add(valueElement);
            }
        }
        System.out.println(ConData1);
        ConData2.put("value", ConData1);
        return ConData2;
    }

}
