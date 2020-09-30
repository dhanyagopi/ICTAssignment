package PageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class SearchPage {
    private final WebDriver driver;

    @FindBy(name = "searchVal")
    public WebElement searchId;

    @FindBy(xpath = "//div/img")
    public WebElement prodName;

    @FindBy(className = "prod-container")
    public WebElement prodContainer;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void SearchItem(String SearchData) throws IOException {
        searchId.sendKeys(SearchData);
        searchId.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(prodName));
    }

    public void SelectItem() throws IOException {
        prodName.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(prodContainer));
    }
}



