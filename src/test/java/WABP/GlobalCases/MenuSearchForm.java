package WABP.GlobalCases;

import Logs.TakesScreenShots;
import SeleniumDriver.Driver;
import WABP.GlobalCases.ActionsOnForm.MenubarButton.ButtonDelete;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static SeleniumDriver.Driver.driver;

public class MenuSearchForm extends Driver {
    Auth Auth = new Auth();
    NovigateToFrorm NTF = new NovigateToFrorm();
    TakesScreenShots takesScreenShots = new TakesScreenShots();
    private static final Logger logger = LogManager.getLogger(MenuSearchForm.class);


    public void SearchAndOpenForm(String FullFormPath) {
        Auth.AuthWABP();
        String[] parts = FullFormPath.split("/");
        for (int i = 0; i < parts.length - 1; i++) {
            driver.findElement(By.xpath("//*[text() = '" + parts[i] + "']/parent::*")).click();
        }
        driver.findElement(By.xpath("//*[text() = '" + parts[parts.length-1] + "']")).click();
        NTF.waitContentIntoInput();
    }

    public void SearchForm(String FormPath, String Form) {
        Auth.AuthWABP();
        String[] parts = FormPath.split("/");
        try {
            for (int i = 0; i < parts.length; i++) {
                driver.findElement(By.xpath("//vaadin-grid-tree-toggle[@style = '---level: "+i+";' and text() = '"+parts[i]+"']/parent::*")).click();
                var newWait = new WebDriverWait(driver, Duration.ofMillis(800));
                if (i < parts.length-1) {
                    newWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//vaadin-grid-tree-toggle[@style = '---level: " + (i + 1) + ";']/parent::*")));
                }else {
                    newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = '" + Form + "']")));

                }
            }
        } catch (Exception e){
            logger.error("Error while Search/opening form", e.getStackTrace());
            takesScreenShots.TakesScreenshotsErrors(e, "Error while Search/opening form");
            throw e;
        }
    }
}
