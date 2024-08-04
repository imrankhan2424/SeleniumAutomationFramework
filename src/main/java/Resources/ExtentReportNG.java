package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
    public static ExtentReports getExtentReport() {
        String path="./reports/index.html";
        ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("E2E ECommerce Test Report");
        extentSparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports=new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester","Imran Khan");
        return extentReports;
    }
}
