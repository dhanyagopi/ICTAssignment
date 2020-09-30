package CommonDriver;

import Config.config;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Driver {
    public static ExtentReports reports;
    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentTest extentTest;

    public static WebDriver driver = null;
    public static config config;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test = null;

    static {
        try {
            config = config.configReturn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  /*  @BeforeSuite
    public void beginReport() throws UnknownHostException {
        htmlReporter = new ExtentHtmlReporter("report//report.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional Report");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Hostname", InetAddress.getLocalHost().getHostName());
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Browser", "chrome");
    }*/
  @BeforeTest
  public void init()
  {
      String path=System.getProperty("user.dir");
      reports=new ExtentReports();
      extentSparkReporter=new ExtentSparkReporter(path+"\\report\\report.html");
      reports.setSystemInfo("Machine Name","");
      reports.attachReporter(extentSparkReporter);
  }


    @BeforeMethod
    public static WebDriver openBrowser() {
        String browser = config.get("BROWSER");
        String url = config.get("URL");
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver.exe");
                //disabled popup
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("useAutomationExtension", false);
                driver = new ChromeDriver(options);//launch browser
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", "src//main//resources//IEDriverServer.exe");
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                InternetExplorerOptions ieoptions = new InternetExplorerOptions(capabilities);
                driver = new InternetExplorerDriver(ieoptions);
                break;
        }
        driver.manage().window().maximize();//maximize window
        driver.navigate().to(url);//navigate to url
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        return driver;
    }

   /* public static String takeScreenshot(WebDriver driver, String name) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File image = screenshot.getScreenshotAs(OutputType.FILE);
        Random random = new Random();
        String file = "image" + random.nextInt(1000) + ".png";
        String fileName = System.getProperty("user.dir") + "\\report\\" + file;
        File destinationFile = new File(fileName);
        FileUtils.copyFile(image, new File(fileName));
        return file;

    }*/

    public static String takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Random random = new Random();
        String file = "ss" + random.nextInt(1000) + ".png";
        String fileName = System.getProperty("user.dir") + "\\report\\" + file;
        File destinationFile = new File(fileName);
        FileUtils.copyFile(screenshot, destinationFile);
        return file;
    }

    @AfterMethod
    public static void closeBrowser() {
        reports.flush();
        driver.close();
    }

}
