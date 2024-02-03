package org.example;

import Logs.WriterLogs;
import SeleniumDriver.Driver;
import WABP.InputForm.TestCases.Поручения_на_оплату_расходов_51Ю2;
import org.testng.annotations.*;



public class Main {

    Поручения_на_оплату_расходов_51Ю2 Test = new Поручения_на_оплату_расходов_51Ю2();


    @Test
    public void test() throws InterruptedException {
        boolean WasExecption = false;
        WriterLogs WL = new WriterLogs();
        try {
            Test.SaveDoc();
        } catch (Exception e) {
            WasExecption=true;
            WL.WriteErrorToTXT(e.toString(), e);
        } finally {
            if (!WasExecption) {
                WL.WriteSuccessToTXT("Поручение_на_оплату_расходов");
            }
        }
        }
}