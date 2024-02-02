package org.example;

import Logs.TakesScreenShots;
import Selenium.WebDriver;
import org.junit.Test;

public class Main extends WebDriver{
    public static void main(String[] args) {
    }
    @Test
    public void test() throws InterruptedException {
        TakesScreenShots web = new TakesScreenShots();
        driver.navigate().to("http://172.31.1.149/#/");
        Thread.sleep(200);
        web.TakesScreenshotsSuccess("Тест");
    }
}