import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class InitialTests {
    @Test
    public void Initial(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();

        String username="yobeje7646@eixdeal.com";
        String password="Test@1234";

        driver.get("https://rahulshettyacademy.com/client/");

        driver.findElement(By.id("userEmail")).sendKeys(username);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> productNames=wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-body")));

        WebElement product=productNames.stream().filter(s-> s.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
                .findFirst().orElse(null);

        product.findElement(By.cssSelector("button:last-of-type")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        driver.findElement(By.cssSelector("button[routerLink*='cart']")).click();
    }
}
