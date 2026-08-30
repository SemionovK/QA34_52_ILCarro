package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxTriesCount = 3;


    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount< maxTriesCount){
            retryCount++;
            return true;
        }
        return false;
    }
}
