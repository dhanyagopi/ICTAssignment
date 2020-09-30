package ExtentReport;

import CommonDriver.Driver;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetAddress;

public class ListenerTest extends Driver implements ITestListener {
    /*@Override
    public void onTestSuccess(ITestResult Result) throws NullPointerException {
        try {
            String screens = Driver.takeScreenshot(driver, Result.getName());
            test.info("Screenshot Captured", MediaEntityBuilder.createScreenCaptureFromPath(screens).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void onTestFailure(ITestResult arg0) {
        // TODO Auto-generated method stub
        extentTest.log(Status.FAIL,arg0.getInstanceName());
        try {
            extentTest.addScreenCaptureFromPath(takeScreenshot());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onTestStart(ITestResult arg0) {
        // TODO Auto-generated method stub
        String path=System.getProperty("user.dir");

        // TODO Auto-generated method stub
        extentTest=reports.createTest(arg0.getInstanceName());
        extentTest.log(Status.INFO,arg0.getInstanceName());
        try {
            extentTest.addScreenCaptureFromPath(takeScreenshot());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
