import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class InitialTests {
    @Test
    public void Initial() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver=new FirefoxDriver();
        driver.manage().window().maximize();

        String username="yobeje7646@eixdeal.com";
        String password="Test@1234";
        String productName="ZARA COAT 3";

        driver.get("https://rahulshettyacademy.com/client/");

        driver.findElement(By.id("userEmail")).sendKeys(username);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> productNames=wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-body")));

        WebElement product=productNames.stream().filter(s-> s.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);

        product.findElement(By.cssSelector("button:last-of-type")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink*='cart']")));
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();

        List<WebElement> k=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartSection h3")));
        Assert.assertTrue(k.stream().anyMatch(s->s.getText().equals(productName)));

        WebElement checkout=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".subtotal button")));
//        wait.until(ExpectedConditions.elementToBeClickable(checkout)).click();
        Actions mouse=new Actions(driver);
        mouse.moveToElement(checkout).click().build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder='Select Country']"))).sendKeys("India");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item:nth-child(3)"))).click();

//        WebElement submit= driver.findElement(By.cssSelector(".action__submit"));submit.click();
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", submit);

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))).getText().trim().equalsIgnoreCase("Thankyou for the order."));

        driver.quit();
    }
}
