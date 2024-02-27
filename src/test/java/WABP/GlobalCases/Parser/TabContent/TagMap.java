package WABP.GlobalCases.Parser.TabContent;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class TagMap {

    public HashMap<String, String> CsvParser(String Tag) {
        String line = "";
        String cvsSplitBy = ";";

        HashMap<String, String> map = new HashMap<>();

        try (StringReader reader = new StringReader(Tag);
             BufferedReader br = new BufferedReader(reader)) {
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(cvsSplitBy);
                for (String field : fields) {
                    String[] keyValue = field.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();
                        map.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
