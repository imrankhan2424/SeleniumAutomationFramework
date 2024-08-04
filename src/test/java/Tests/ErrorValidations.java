package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest{

    @Test
    public void errorValidation(){
        landingPage.login("invalidUsername@yopmail.com","Test1234");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }
}