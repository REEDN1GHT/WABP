package Logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriterLogs {

    public void WriteErrorToTXT(String Err, Throwable throwable){
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
            String className = throwable.getStackTrace()[0].getClassName();
            String methodName = throwable.getStackTrace()[0].getMethodName();

            if (throwable instanceof CustomException) {
                className = CustomException.class.getName();
                methodName = "N/A";
            }
                BufferedWriter writer = new BufferedWriter(new FileWriter("logs\\Log.txt", true)); // "true" appends to the file
            writer.write(timestamp + ":: " + className + "." + methodName + "---" + Err.replaceAll(",", "\n") + "\n");
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void WriteSuccessToTXT(String ClassName){
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("logs\\Log.txt", true)); // "true" appends to the file
            writer.write(timestamp + ":: " + ClassName + " --- " + "Success" + "\n");
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
