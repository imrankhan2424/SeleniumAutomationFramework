package Tests;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    LandingPage landingPage;
    public void setUp() throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("src/test/Global.properties");
        prop.load(fis);

        switch (prop.getProperty("browser").toLowerCase()){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;

            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
                break;

            default:
                System.out.println("Invalid BrowserName set in Global.properites");
                break;
        }
        driver.manage().window().maximize();
    }

    @BeforeTest
    public void launchApplication() throws IOException {
        setUp();
        landingPage=new LandingPage(driver);
        landingPage.goTo("https://rahulshettyacademy.com/client/");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
