package PageObjects;

import UtilComponent.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userEmail")
    WebElement email;

    @FindBy(id="userPassword")
    WebElement password;

    @FindBy(id="login")
    WebElement submit;

    public void goTo(String url){
        driver.get(url);
    }

    public void login(String username,String pass){
        email.sendKeys(username);
        password.sendKeys(pass);
        submit.click();
    }

}
