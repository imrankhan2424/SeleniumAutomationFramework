package Tests;

import PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EcommerceTest extends BaseTest {
    @Test
    public void EndtoEndFlow() {
        String productName="ZARA COAT 3";

        ProductCatalogue productCatalogue=landingPage.login("yobeje7646@eixdeal.com","Test@1234");
        productCatalogue.addProduct(productName);

        CartPage cartPage=productCatalogue.goToCart();
        Assert.assertTrue(cartPage.verifyProductinCart(productName));

        CheckoutPage checkoutPage=cartPage.proceedToCheckout();
        checkoutPage.searchforCountry("India");

        ConfirmationPage confirmationPage=checkoutPage.placeOrder();
        Assert.assertTrue(confirmationPage.getMessage().equalsIgnoreCase("Thankyou for the order."));
    }
}