package WABP.GlobalCases.Parser;

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
import java.util.HashMap;
import java.util.Map;

public class XLSX_ParserMenuObject {

    public JSONObject GenerateJson(){
        String filePath = "src/test/resources/меню_автоматизация.xlsx";
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
    @Test
    public void test(){
        GenerateJson();
    }
}
