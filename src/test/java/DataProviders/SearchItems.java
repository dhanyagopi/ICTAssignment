package DataProviders;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class SearchItems {
    @DataProvider(name = "Search")
    public Object[][] dataProviderSearch() {
        List<String> ls = new ArrayList<>();
        //ls.add("sarees");
        ls.add("jewellery");
        ls.add("bags");
        return new Object[][]{{ls}};

    }
}
