package WABP.GlobalCases.Parser;

import DataBase.ConnectBD;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ParserMenuObject extends ConnectBD {

    public JSONObject GenerateJson(){
        String filePath = "src/test/resources/меню_автоматизация — копия.xlsx";
        JSONObject json = new JSONObject();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            Map<String, Map<String, String>> dataMap = new HashMap<>();
            for (Row row : sheet) {

                String key = row.getCell(1).getStringCellValue();

                Map<String, String> valueMap = new HashMap<>();
                valueMap.put("Number", row.getCell(0).getStringCellValue());
                valueMap.put("AppsID", row.getCell(1).getStringCellValue());
                String str = row.getCell(2).getStringCellValue().replace("...", "/");
                valueMap.put("Path", str);
                valueMap.put("NameForm", row.getCell(3).getStringCellValue());
                valueMap.put("Type", row.getCell(4).getStringCellValue());

                dataMap.put(key, valueMap);
            }

            JSONObject jsonDATA = new JSONObject(dataMap);
            json=jsonDATA;
            //System.out.println(json.toString(4));
            //System.out.println(json.getJSONObject("53Я705").getString("Path"));

        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static final String MenuFormData =
                    "Create table #MENU (Apps_MenuID Int,MenuNumber VarChar(255),MenuText VarChar(2555),ObjectType int,ObjectName VarChar(255),OpenMode int,\n" +
                    "                    OpenArgs VarChar(2255),WindowCaption VarChar(2255),[label] VarChar(255),imageMso VarChar(255),\n" +
                    "                    ButtonSize VarChar(255), keytip VarChar(255),screentip VarChar(255),\n" +
                    "                    supertip VarChar(255),onAction VarChar(255), TypeOfObject VarChar(255),\n" +
                    "                    Apps_Objects VarChar(255), Apps_ObjectsID_PARENT VarChar(255), MenuNumberUnique VarChar(255), CountInt VarChar(10))\n" +
                    "INsert Into #MENU (Apps_MenuID,MenuNumber,MenuText,ObjectType,ObjectName,OpenMode,OpenArgs,WindowCaption,[label],imageMso,ButtonSize, keytip,screentip,supertip,onAction, TypeOfObject, Apps_Objects, Apps_ObjectsID_PARENT, MenuNumberUnique)\n" +
                    "    Execute dbo.Apps_Menu_Load @ApplicationType='budget24', @Application=Null, @VersionNumber=33.9, @Mode='Data'\n" +
                    "Select R.MenuNumber,\n" +
                    "       R.MenuNumberUnique,\n" +
                    "       Case When P_1.MenuText Is Not Null Then P_1.MenuText Else '' End +\n" +
                    "       Case When P_2.MenuText Is Not Null Then '/' + P_2.MenuText Else '' End +\n" +
                    "       Case When P_3.MenuText Is Not Null Then '/' + P_3.MenuText Else '' End +\n" +
                    "       Case When P_4.MenuText Is Not Null Then '/' + P_4.MenuText Else '' End +\n" +
                    "       Case When P_5.MenuText Is Not Null Then '/' + P_5.MenuText Else '' End As Path,\n" +
                    "       R.ObjectName as FileName,\n" +
                    "       R.MenuText As NameForm,\n" +
                    "       R.TypeOfObject As Type\n" +
                    "from #MENU As R Left join #MENU As P_1 on (Left(R.MenuNumber,1)=P_1.MenuNumber And P_1.TypeOfObject='Folder')\n" +
                    "                Left join #MENU As P_2 on (Left(R.MenuNumber,2)=P_2.MenuNumber And P_2.TypeOfObject='Folder')\n" +
                    "                Left join #MENU As P_3 on (Left(R.MenuNumber,3)=P_3.MenuNumber And P_3.TypeOfObject='Folder')\n" +
                    "                Left join #MENU As P_4 on (Left(R.MenuNumber,4)=P_4.MenuNumber And P_4.TypeOfObject='Folder')\n" +
                    "                Left join #MENU As P_5 on (Left(R.MenuNumber,5)=P_5.MenuNumber And P_5.TypeOfObject='Folder')\n" +
                    "Where R.TypeOfObject IN ('Form')\n" +
                    "drop table #MENU\n";

    public JSONObject MenuDataFromBudget24() {
        Map<String, Map<String, String>> dataMap = new HashMap<>();
        try {
            Statement statement = getConnectionBudget23UKT().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(MenuFormData);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int indexColumn = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                String key = resultSet.getString("MenuNumberUnique");
                Map<String, String> valueMap = new HashMap<>();
                valueMap.put("Number", resultSet.getString("MenuNumber"));
                valueMap.put("AppsID", resultSet.getString("MenuNumberUnique"));
                valueMap.put("Path", resultSet.getString("Path"));
                valueMap.put("FileName", resultSet.getString("FileName"));
                valueMap.put("NameForm", resultSet.getString("NameForm"));
                valueMap.put("Type", resultSet.getString("Type"));

                dataMap.put(key, valueMap);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonDATA = new JSONObject(dataMap);
        return jsonDATA;
    }
}
