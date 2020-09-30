package PageFactory;

import freemarker.template.utility.NumberUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public class CartPage {
    private final WebDriver driver;

    @FindAll({@FindBy(css = ".cartqty div")})
    public List<WebElement> quantity;

    @FindBy(css = ".cartqty div")
    public WebElement qtyDropDown;

    @FindBy(className = "increment")
    public WebElement incrementQty;

    @FindBy(id = "updateQuantity")
    public WebElement updateQuantity;

    @FindBy(className = "product-card-wrapper")
    public List<WebElement> productList;

    @FindBy(css = ".err-msg-blk > div > span.msg-content")
    public WebElement successMsg;

    @FindBy(css = ".mybag-section:nth-child(1)")
    public WebElement totalItems;

    @FindBy(css = "#orderTotal > span.price-value")
    public WebElement orderTotal;

    @FindBy(css = "#bagTotal > span.price-value")
    public WebElement bagTotal;

    @FindBy(css = "#bagDiscount > span.price-value")
    public WebElement bagDiscount;

    @FindBy(css = "#estimatedGst > span.price-value")
    public WebElement estimatedGst;

    @FindBy(css = "#delivery > span.price-value")
    public WebElement delivery;

    @FindBy(css = ".input-area .input-box-div input#couponCodeInput")
    public WebElement couponInput;

    @FindBy(className = ".apply-button")
    public WebElement couponApply;

    @FindBy(css = ".voucher-list-item > div")
    public WebElement couponRadio;

    @FindBy(css = "#couponCodeInput")
    public WebElement couponInputs;
    @FindBy(css = ".slick-slide:nth-child(3) > .circle")
    public WebElement sizeSelect;

    @FindBy(xpath = "//div[@class='btn-gold']")
    public WebElement btnGold;

    @FindBy(css = ".mini-cart-btn")
    public WebElement goTocartBtn;

    @FindBy(css = "p.you-save-text")
    public WebElement appliedCoupon;

    @FindBy(css = "#couponDiscount > span.price-value")
    public WebElement couponSaving;


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public Integer getBagCount() throws IOException {
        return Integer.parseInt(totalItems.getText().replaceAll("[^0-9]", ""));
    }

    public void updateQuantity() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(qtyDropDown));
        qtyDropDown.click();
        incrementQty.click();
        updateQuantity.click();
        wait.until(ExpectedConditions.visibilityOf(successMsg));
    }

    public Integer getCartCount() throws IOException {
        int cartQty = 0;
        for (WebElement element : quantity) {
            cartQty = cartQty + Integer.parseInt(element.getText());
        }
        return cartQty;
    }

    public Double getOrderTotal() throws IOException {
        return this.getPrice(orderTotal.getText());
    }

    public Double getBagTotal() throws IOException {
        return this.getPrice(bagTotal.getText());
    }

    public Double getBagDiscount() throws IOException {
        return this.getPrice(bagDiscount.getText());
    }

    public Double getEstimatedGst() throws IOException {
        return this.getPrice(estimatedGst.getText());
    }

    public Double getDelivery() throws IOException {
        String deliveryRate = delivery.getText();
        if (deliveryRate.toLowerCase().equals("free")) {
            return 0.0;
        } else {
            return this.getPrice(deliveryRate);
        }
    }

    public void applyCoupon() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(couponInput));
        couponRadio.click();
        couponApply.click();
        wait.until(ExpectedConditions.visibilityOf(successMsg));
    }

    private Double getPrice(String price) throws IOException {
        String priceCheck = price.split(" ")[1].replace(",", "");
        return Double.parseDouble(priceCheck);
    }
}



