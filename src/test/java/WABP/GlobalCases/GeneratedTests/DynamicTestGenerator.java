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
        MSF.SearchForm(Path, NameForm);
        //WebElement Field = driver.findElement(By.xpath("//*[text() = '" + NameForm + "']"));
        try {
            Assert.assertNotNull(driver.findElement(By.xpath("//span[text() = '" + NameForm + "']")));
            takesScreenShots.TakesScreenshotsSuccess("Наличие_в_меню", NameForm);
        } catch (Exception e){
            takesScreenShots.TakesScreenshotsErrors(e, NameForm);
        }
    }

    @Test(dataProvider = "menuData")
    public void DocMenuOpen(String Path, String NameForm){
        MenuSearchForm MSF = new MenuSearchForm();
        MSF.SearchForm(Path, NameForm);
        driver.findElement(By.xpath("//span[text() = '" + NameForm + "']")).click();

        try {
            NTF.waitContentIntoInput();
            Assert.assertNotNull(driver.findElement(By
                    .xpath("//div[@class='tabs-page__content']")), "Форма не открылась");
            takesScreenShots.TakesScreenshotsSuccess("DocOpen", NameForm);
        } catch (Exception e){
            takesScreenShots.TakesScreenshotsErrors(e, NameForm);
            throw e;
        }
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
