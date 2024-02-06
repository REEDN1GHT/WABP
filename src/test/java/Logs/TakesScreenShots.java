package Logs;

import SeleniumDriver.Driver;
import WABP.GlobalCases.Auth;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakesScreenShots extends Driver {
    @Attachment(value = "Page screenshot", type = "image/png")
    public void TakesScreenshotsSuccess(String TestName, String Form) {
        var sourceFile = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
        byte[] AllureScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        String projectPath = System.getProperty("user.dir");
        SimpleDateFormat dateFormatFolder = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String timestampFolder = dateFormatFolder.format(new Date());
        String timestamp = dateFormat.format(new Date());
        String Path = "\\Screenshots\\"+timestampFolder+"\\"+Form;
        new File(projectPath + Path).mkdirs();
        String fileName = projectPath + Path + "\\" + timestamp + " " + TestName +".png";
        File File1 = new File(fileName);
        try {
            FileUtils.copyFile(sourceFile, File1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Allure.addAttachment(TestName + " " + Form + " " + timestamp, new ByteArrayInputStream(AllureScreenshot));
    }

    public void TakesScreenshotsErrors(Throwable throwable){
        var sourceFile = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
        byte[] AllureScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        String projectPath = System.getProperty("user.dir");
        String Path = "\\Screenshots\\Errors";
        new File(projectPath + Path).mkdirs();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        String fileName = projectPath + Path + "\\" + ClassNameError(throwable) + " " + timestamp + ".png";
        File File = new File(fileName);
        try {
            FileUtils.copyFile(sourceFile, File);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Allure.addAttachment("Error" + ClassNameError(throwable) + " " + timestamp, new ByteArrayInputStream(AllureScreenshot));
    }

    public String ClassNameError(Throwable throwable) {
        StackTraceElement[] stack = throwable.getStackTrace();
        String className = "";
        for (StackTraceElement element : stack) {
            if (element.getClassName().startsWith("WABP")) {
                className = element.getClassName();
                break;
            }
        }
        return className;
    }
    @Test
    public void test(){
        Auth auth = new Auth();
        auth.AuthWABP();
        TakesScreenshotsSuccess("TESTTTT", "KERTTT");
    }
}


