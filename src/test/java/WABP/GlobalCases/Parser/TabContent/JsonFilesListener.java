package WABP.GlobalCases.Parser.TabContent;



import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class JsonFilesListener {

    public List<String> listJsonFormFiles() {
        String directoryPath = "desktop-raw-files-master\\json\\inputs";
        List<String> jsonFiles = new ArrayList<>();
        File directory = new File(directoryPath);
        try {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".json")) {
                        jsonFiles.add(file.getName());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jsonFiles;
    }

    public List<String> listJsonDataFiles() {
        String directoryPath = "src/test/java/WABP/JsonData/Input";
        List<String> DataFormMenuNumber = new ArrayList<>();
        File directory = new File(directoryPath);
        try {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".json")) {
                        String fileName = file.getName();
                        int lastUnderscoreIndex = fileName.lastIndexOf("_");
                        int extensionIndex = fileName.lastIndexOf(".json");
                        String id = fileName.substring(lastUnderscoreIndex + 1, extensionIndex);
                        DataFormMenuNumber.add(id);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DataFormMenuNumber;
    }
    public HashMap<String, String> MapJsonDataFiles() {
        String directoryPath = "src/test\\java/WABP\\JsonData\\Input";
        HashMap<String, String> FileMap = new HashMap<>();

        File directory = new File(directoryPath);
        try {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".json")) {
                        String fileName = file.getName();
                        int lastUnderscoreIndex = fileName.lastIndexOf("_");
                        int extensionIndex = fileName.lastIndexOf(".json");
                        String id = fileName.substring(lastUnderscoreIndex + 1, extensionIndex);
                        String formName = fileName.substring(0, lastUnderscoreIndex).replace("_", " ");
                        FileMap.put(id, formName);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return FileMap;
    }
}
