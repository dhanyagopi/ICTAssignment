package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage {
    private final WebDriver driver;

    @FindBy(css = ".prod-container .prod-name")
    public WebElement prodContainerName;

    @FindBy(css = ".size-variant-block:nth-child(0) > .size-variant-item")
    public WebElement sizeSelect;

    @FindBy(className = "small-popup")
    public WebElement popup;

    @FindBy(className = "btn-gold")
    public WebElement submitBtn;

    @FindBy(css = ".small-popup .mini-cart-btn")
    public WebElement goToCart;

    @FindAll({@FindBy(css = ".minicart-prod-name")})
    public List<WebElement> miniCartProd;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductDetail() {
        return prodContainerName.getText();
    }

    public void add() {
        submitBtn.click();
        WebDriverWait wait = new WebDriverWait(this.driver, 100);
        wait.until(ExpectedConditions.visibilityOf(popup));
    }

    public void goToCart() {
        goToCart.click();
    }

    public boolean checkItem(String item) {
        boolean itemAdded = false;
        for (WebElement element : miniCartProd) {
            if (element.getText().toLowerCase().contains(item.toLowerCase())) {
                itemAdded = true;
                break;
            }
        }
        return itemAdded;
    }
}



