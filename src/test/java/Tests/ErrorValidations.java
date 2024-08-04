package Tests;

import PageObjects.CartPage;
import PageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest{

    @Test
    public void loginErrorValidation(){
        landingPage.login("invalidUsername@yopmail.com","Test1234");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

    @Test
    public void productErrorValidation(){
        String productName="ZARA COAT 3";

        ProductCatalogue productCatalogue=landingPage.login("yobeje7646@eixdeal.com","Test@1234");
        productCatalogue.addProduct(productName);

        CartPage cartPage=productCatalogue.goToCart();
        Assert.assertFalse(cartPage.verifyProductinCart("iPhone"));
    }
}