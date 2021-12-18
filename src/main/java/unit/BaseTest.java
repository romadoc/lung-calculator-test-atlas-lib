package unit;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import io.qameta.atlas.webdriver.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pages.CalculatorTomographyOff;
import pages.CalculatorTomographyOn;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class})
public class BaseTest {
    protected static final String BASE_URL = "https://gsmu.by/upload/calcForGSMU/calcGSMU.html";
    private Atlas atlas;
    public static WebDriver driver;
    @BeforeSuite
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        atlas = new Atlas(new WebDriverConfiguration(driver));
    }
    @BeforeMethod
    public void startDriver() {
        driver.get(BASE_URL);
    }

    @AfterSuite
    public void closeDriver() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    protected <T extends WebPage> T onPage(Class<T> page) {
        return atlas.create(driver, page);
    }

    protected CalculatorTomographyOff onCalculatorTomographyOff() {
        return onPage(CalculatorTomographyOff.class);
    }

    protected CalculatorTomographyOn onCalculatorTomographyOn() {
        return onPage(CalculatorTomographyOn.class);
    }

}
