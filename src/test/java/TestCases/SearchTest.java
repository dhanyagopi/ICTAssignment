package TestCases;

import CommonDriver.Driver;
import DataProviders.SearchItems;
import ExtentReport.ListenerTest;
import PageFactory.SearchPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

@Listeners(ListenerTest.class)
public class SearchTest extends Driver {

    SearchPage search;

    @Test(dataProvider = "Search", dataProviderClass = SearchItems.class, priority = 1)
    public void Execute(ArrayList<String> item) throws IOException {
        search = PageFactory.initElements(driver, SearchPage.class);
        for (String value : item) {
            search.SearchItem(value);
        }
    }
}
