package WABP.GlobalCases;

import SeleniumDriver.Driver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static SeleniumDriver.Driver.driver;

public class MenuSearchForm extends Driver {
    Auth Auth = new Auth();
    NovigateToFrorm NTF = new NovigateToFrorm();

    public void SearchAndOpenForm(String FullFormPath) {
        Auth.AuthWABP();
        String[] parts = FullFormPath.split("/");
        for (int i = 0; i < parts.length - 1; i++) {
            driver.findElement(By.xpath("//*[text() = '" + parts[i] + "']/parent::*")).click();
        }
        driver.findElement(By.xpath("//*[text() = '" + parts[parts.length-1] + "']")).click();
        NTF.waitContentIntoInput();
    }

    public void SearchForm(String FormPath) {
        Auth.AuthWABP();
        String[] parts = FormPath.split("/");
        for (int i = 0; i < parts.length; i++) {
            driver.findElement(By.xpath("//*[text() = '" + parts[i] + "']/parent::*")).click();
        }
    }
}
