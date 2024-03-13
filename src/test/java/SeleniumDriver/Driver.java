package SeleniumDriver;

import Logs.TakesScreenShots;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;


public class Driver {
    public static org.openqa.selenium.WebDriver driver;
    public static org.openqa.selenium.WebDriver  driverToInit;
    public static WebDriverWait wait;
    static TakesScreenShots takesScreenShots = new TakesScreenShots();

    @BeforeClass
    public static void setUp() {
        //System.setProperty("webdriver.chrome.driver", "src/test/java/SeleniumDriver/chromedriver");
        System.out.println("google chrome init");
        ChromeOptions ops = new ChromeOptions();

        ops.setHeadless(true);
        ops.addArguments("--disable-dev-shm-usage");
        ops.addArguments("--no-sandbox");
        ops.addArguments("start-maximized");

//        ops.addArguments("--remote-allow-origins=*");
//        ops.setCapability("unexpectedAlertBehaviour", "dismiss");
//        ops.addArguments("--disable-extensions");
//        ops.addArguments("--disable-dev-shm-usage");
//        ops.addArguments("--ignore-ssl-errors=yes");
//        ops.addArguments("--ignore-certificate-errors");
//        ops.setBrowserVersion("121.0.6167.85");
//        ops.setHeadless(true);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(ops);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public static void tearDown() {
        takesScreenShots.TakesScreenshotsSuccess("tearDown", "Результат");
        driver.quit();
    }

    @AfterMethod
    public static void DownMethod(){
        takesScreenShots.TakesScreenshotsSuccess("DownMethod", "Результат");
        driver.navigate().to("https://asbpek-test.aisa.ru/budget24/login");
        var newWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-app-layout//vaadin-horizontal-layout/span[1]")));
    }
}
