package Tests;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    LandingPage landingPage;
    public void setUp() throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("src/main/java/Resources/Global.properties");
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
                System.out.println("Invalid BrowserName set in Global.properties");
                break;
        }
        driver.manage().window().maximize();
    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        String path="./reports/"+testCaseName+".png";
        TakesScreenshot ts=(TakesScreenshot) driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        File dest=new File(path);
        FileUtils.copyFile(src,dest);
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException {
        setUp();
        landingPage=new LandingPage(driver);
        landingPage.goTo("https://rahulshettyacademy.com/client/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
