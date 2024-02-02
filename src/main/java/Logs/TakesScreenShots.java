package Logs;

import Selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakesScreenShots extends WebDriver {

    public void TakesScreenshotsSuccess(String TestName) {
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String projectPath = System.getProperty("user.dir");
        String Path = "\\Screenshots\\Success";
        new File(projectPath + Path).mkdirs();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        String fileName = projectPath + Path + "\\" + TestName + " " + timestamp + ".png";
        File File = new File(fileName);
        try {
            FileUtils.copyFile(sourceFile, File);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void TakesScreenshotsErrors(Throwable throwable){
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String projectPath = System.getProperty("user.dir");
        String Path = "\\Screenshots\\Errors";
        new File(projectPath + Path).mkdirs();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        String fileName = projectPath + Path + "\\" + throwable.getStackTrace()[0].getMethodName() + " " + timestamp + ".png";
        File File = new File(fileName);
        try {
            FileUtils.copyFile(sourceFile, File);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
