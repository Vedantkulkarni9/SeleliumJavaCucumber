package extracodes;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {}

    public static synchronized ExtentReports getExtent() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter reporter =
                new ExtentSparkReporter("reports/AutomationReport.html");
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
