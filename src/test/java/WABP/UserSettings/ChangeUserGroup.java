package WABP.UserSettings;

import DataBase.UserSettingsDAO.ChangeUserGroupDAO;
import SeleniumDriver.Driver;
import WABP.GlobalCases.Parser.JSON_DataParser;
import org.json.JSONObject;
import org.openqa.selenium.By;

public class ChangeUserGroup extends Driver {

    JSON_DataParser jsonDataParser = new JSON_DataParser();
    ChangeUserGroupDAO changeUserGroupDAO = new ChangeUserGroupDAO();

    private String groupNameForDoc(String menuNumber){
        JSONObject jsonObject = jsonDataParser.ReadJSON(menuNumber);
        if (jsonObject.has("formArgs") && jsonObject.getJSONObject("formArgs").has("UserGroup")) {
            return jsonObject.getJSONObject("formArgs").getString("UserGroup");
        } else {
            return null;
        }
    }

    //TODO: Изменить .equals("8") на .equals(groupNameForDoc(menuNumber)) после решения https://tc.aisa.ru/browse/WABP-3484
    public void changeGroupForDoc(String menuNumber){
        if (groupNameForDoc(menuNumber) != null) {
            changeUserGroupDAO.updateUserGroup(groupNameForDoc(menuNumber), "UKT_Riabtsev");
            while (!driver.findElement(By.xpath("//span[@class='user-info__group']")).getText().equals("8")) {
                driver.navigate().to("http://172.31.1.149/aispbpek/budget24-newdb/login");
            }
        }
    }

    public void changeGroupDefault(){
        if (!changeUserGroupDAO.checkUserGroup("UKT_Riabtsev").equals("7830002430/1599")) {
            changeUserGroupDAO.updateUserGroup("7830002430/1599", "UKT_Riabtsev");

        }
    }
}
