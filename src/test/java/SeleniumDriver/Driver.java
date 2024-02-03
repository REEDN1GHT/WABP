package SeleniumDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;


public class Driver {
    public static org.openqa.selenium.WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.setCapability("unexpectedAlertBehaviour", "dismiss");
        ops.addArguments("--disable-extensions");
        ops.setBrowserVersion("116.0.5845.111");
        //ops.addArguments("headless");
        driver = new ChromeDriver(ops);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
