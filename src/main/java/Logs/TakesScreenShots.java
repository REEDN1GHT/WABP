package Logs;

import SeleniumDriver.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakesScreenShots extends Driver {

    public void TakesScreenshotsSuccess(String TestName, String Form) {
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String projectPath = System.getProperty("user.dir");
        SimpleDateFormat dateFormatFolder = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String timestampFolder = dateFormatFolder.format(new Date());
        String timestamp = dateFormat.format(new Date());
        String Path = "\\Screenshots\\"+timestampFolder+"\\"+Form;
        new File(projectPath + Path).mkdirs();
        String fileName = projectPath + Path + "\\" + timestamp + " " + TestName +".png";
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
