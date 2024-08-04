package Tests;

import PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class EcommerceTest extends BaseTest {
    String productName="ZARA COAT 3";

    @Test(dataProvider = "getData",groups = {"Purchase"})
    public void submitVerification(HashMap<String,String> input) {
        //ProductCatalogue productCatalogue=landingPage.login("yobeje7646@eixdeal.com","Test@1234");
        ProductCatalogue productCatalogue=landingPage.login(input.get("username"),input.get("password"));
        productCatalogue.addProduct(input.get("product"));

        CartPage cartPage=productCatalogue.goToCart();
        Assert.assertTrue(cartPage.verifyProductinCart(input.get("product")));

        CheckoutPage checkoutPage=cartPage.proceedToCheckout();
        checkoutPage.searchforCountry("India");

        ConfirmationPage confirmationPage=checkoutPage.placeOrder();
        Assert.assertTrue(confirmationPage.getMessage().equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"submitVerification"})
    public void ordersVerification(){
        ProductCatalogue productCatalogue=landingPage.login("yobeje7646@eixdeal.com","Test@1234");
        OrdersPage ordersPage=productCatalogue.goToOrdersPage();
        ordersPage.verifyProductinOrders(productName);
    }

    @DataProvider
    public Object[][] getData(){
        HashMap<String,String> map1=new HashMap<String,String>();
        map1.put("username","anshika@gmail.com");
        map1.put("password","Iamking@000");
        map1.put("product","ZARA COAT 3");

        HashMap<String,String> map2=new HashMap<String,String>();
        map2.put("username","yobeje7646@eixdeal.com");
        map2.put("password","Test@1234");
        map2.put("product","ADIDAS ORIGINAL");
        return new Object[][] {{map1},{map2}};

//        return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" }};
    }

}