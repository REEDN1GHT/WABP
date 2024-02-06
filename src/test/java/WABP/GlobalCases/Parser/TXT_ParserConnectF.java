package WABP.GlobalCases.Parser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TXT_ParserConnectF {

    public JSONObject ParserConnectF(){
        String filePath = "src/test/resources/connectF.txt";
        JSONObject ConnectF = new JSONObject();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            JSONArray jsonArray = new JSONArray();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if (key.equals("value")) {
                        String[] elements = value.split(",");
                        for (String element : elements) {
                            jsonArray.put(element.trim());
                        }
                    }
                }
            }
            br.close();

            ConnectF.put("value", jsonArray);

            // Используйте toString() для вывода без кавычек
            //System.out.println(ConnectF.getJSONArray("value").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ConnectF;
    }
}
