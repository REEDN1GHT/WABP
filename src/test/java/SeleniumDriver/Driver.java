package SeleniumDriver;

import DataBase.CleanUserData;
import Logs.TakesScreenShots;
import WABP.GlobalCases.NovigateToFrorm;
import WABP.UserSettings.ChangeUserGroup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
    private static final Logger logger = LogManager.getLogger(Driver.class);
    static ChangeUserGroup changeUserGroup = new ChangeUserGroup();


    @BeforeClass
    public static void setUp() {
        //System.setProperty("webdriver.chrome.driver", "src/test/java/SeleniumDriver/chromedriver");
        System.out.println("google chrome init");
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        //ops.setHeadless(true);
//        ops.addArguments("--disable-dev-shm-usage");
        ops.addArguments("--remote-allow-origins=*");
        ops.setCapability("unexpectedAlertBehaviour", "dismiss");
        ops.addArguments("--disable-extensions");
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

        driver = new ChromeDriver(ops);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //driver.manage().window().setSize(new Dimension(3840, 2160)); //4k
        driver.manage().window().setSize(new Dimension(1920, 1080)); //fullhd
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

//    @BeforeClass
//    public static void setUp() {
//        //System.setProperty("webdriver.chrome.driver", "src/test/java/SeleniumDriver/chromedriver");
//        WebDriverManager.chromedriver().clearDriverCache().setup();
//        ChromeOptions ops = new ChromeOptions();
//        ops.addArguments("--remote-allow-origins=*");
//        ops.setCapability("unexpectedAlertBehaviour", "dismiss");
//        ops.addArguments("--disable-extensions");
//        driver = new ChromeDriver(ops);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//    }


    @AfterClass
    public static void tearDown() {
        takesScreenShots.TakesScreenshotsSuccess("tearDown", "Результат");
        driver.quit();
    }

    @AfterMethod
    public static void DownMethod(){
        takesScreenShots.TakesScreenshotsSuccess("DownMethod", "Результат");
        changeUserGroup.changeGroupDefault();
        driver.navigate().to("http://172.31.1.149/aispbpek/budget24-newdb/login");
        var newWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-app-layout//vaadin-horizontal-layout/span[1]")));
    }

    //@BeforeSuite
    public static void cleanButest(){
        CleanUserData cleanUserData = new CleanUserData();
        changeUserGroup.changeGroupDefault();
        logger.trace("Clean DataBase: butest24");
        cleanUserData.cleanData();
    }
}
