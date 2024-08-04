package PageObjects;

import UtilComponent.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".hero-primary")
    WebElement confirmationTitle;

    @FindBy(css=".toast-container")
    WebElement ToastMessage;

    public String getMessage(){
        waitTillInvisibilityofElement(ToastMessage,10);
        waitTillElementisDisplayed(confirmationTitle,5);
        return confirmationTitle.getText().trim();
    }
}
