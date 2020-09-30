package TestCases;

import CommonDriver.Driver;
import DataProviders.SearchItems;
import ExtentReport.ListenerTest;
import PageFactory.SearchPage;
import PageFactory.CartPage;
import PageFactory.ProductPage;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

@Listeners(ListenerTest.class)
public class QuantityUpdate extends Driver {

    SearchPage search;
    CartPage cart;
    ProductPage product;

    @Test(dataProvider = "Search", dataProviderClass = SearchItems.class, priority = 1)
    public void Search(ArrayList<String> item) throws IOException {
        search = PageFactory.initElements(driver, SearchPage.class);
        cart = PageFactory.initElements(driver, CartPage.class);
        product = PageFactory.initElements(driver, ProductPage.class);

        for (String value : item) {
            search.SearchItem(value);
            search.SelectItem();
            product.add();
            // TC_AJ_003
            Assert.assertTrue(product.checkItem(product.getProductDetail()));
        }

        product.goToCart();
        // TC_AJ_004
        Assert.assertTrue(cart.getBagCount() > 0);

        Double prevOrderTotal = cart.getOrderTotal();
        Double prevBagTotal = cart.getBagTotal();
        Double prevBagDiscount = cart.getBagDiscount();
        Double prevEstimatedGst = cart.getEstimatedGst();
        Double prevDelivery = cart.getDelivery();

        cart.updateQuantity();

        Double newOrderTotal = cart.getOrderTotal();
        Double newBagTotal = cart.getBagTotal();
        Double newBagDiscount = cart.getBagDiscount();
        Double newEstimatedGst = cart.getEstimatedGst();
        Double newDelivery = cart.getDelivery();

        // TC_AJ_005
        Assert.assertEquals(cart.getCartCount(), cart.getBagCount());

        // TC_AJ_006
        Assert.assertNotEquals(prevOrderTotal, newOrderTotal);
        Assert.assertNotEquals(prevBagTotal, newBagTotal);
        Assert.assertNotEquals(prevBagDiscount, newBagDiscount);
        Assert.assertNotEquals(prevEstimatedGst, newEstimatedGst);
        Assert.assertTrue(prevDelivery >= newDelivery);


    }

}
