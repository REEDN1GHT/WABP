package WABP.GlobalCases.Parser;

import Logs.TakesScreenShots;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSON_DataParser {
    private static final Logger logger = LogManager.getLogger(JSON_DataParser.class);
    static TakesScreenShots takesScreenShots = new TakesScreenShots();

    public JSONObject ReadJSON(String MenuNumber) {
        JSONObject jsonObject = null;
        String directoryPath = "src/test/java/WABP/JsonData/Input/";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(MenuNumber + ".json"));
        if (files != null && files.length > 0) {
            File jsonFile = files[0];
            JSONTokener tokener = null;
            try {
                tokener = new JSONTokener(new FileReader(jsonFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            jsonObject = new JSONObject(tokener);
        } else {
            throw new RuntimeException("Файл не найден: " + MenuNumber);
        }
        return jsonObject;
    }

    @Test
    public void test(){
        System.out.println(ReadJSON("51Ю2").toString(4));
    }
}
