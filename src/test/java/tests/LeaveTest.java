package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.LeavePage;

public class LeaveTest {

    @Test
    public void leaveBalanceTest() {

        // Open browser
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Open Orbit HRMS
        driver.get(
            "https://orbit-hrms-frontend.onrender.com"
        );

        // Wait
        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(20)
        );

        // Wait for page loading
        wait.until(
            driver1 -> ((org.openqa.selenium.JavascriptExecutor) driver1)
                .executeScript("return document.readyState")
                .equals("complete")
        );

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            "priya.sharma@hrms.com",
            "Welcome@123"
        );

        System.out.println("Login completed!");

        // Wait for dashboard
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Welcome back')]")
            )
        );

        System.out.println(
            "Dashboard loaded successfully!"
        );

        // Time Off menu
        By timeOffMenu = By.xpath(
            "//a[normalize-space()='Time Off']"
        );

        // Click Time Off
        wait.until(
            ExpectedConditions.elementToBeClickable(
                timeOffMenu
            )
        ).click();

        System.out.println(
            "Time Off page opened!"
        );

        // Create LeavePage object
        LeavePage leavePage = new LeavePage(driver);

        // Verify Time Off page
        Assert.assertTrue(
            leavePage.isTimeOffPageDisplayed(),
            "Time Off page was not displayed"
        );

        // Get leave balances
        String paidLeave =
            leavePage.getPaidTimeOff();

        String sickLeave =
            leavePage.getSickLeave();

        String pendingRequests =
            leavePage.getPendingRequests();

        // Display balances
        System.out.println(
            "Paid Time Off: " + paidLeave
        );

        System.out.println(
            "Sick Leave: " + sickLeave
        );

        System.out.println(
            "Pending Requests: " + pendingRequests
        );

        // Verify balances
        Assert.assertFalse(
    paidLeave.isEmpty(),
    "Paid Time Off balance is not displayed"
);

Assert.assertFalse(
    sickLeave.isEmpty(),
    "Sick Leave balance is not displayed"
);

Assert.assertFalse(
    pendingRequests.isEmpty(),
    "Pending Requests count is not displayed"
);

System.out.println(
    "TC03 PASSED: Leave balance information displayed successfully!"
);

        // Close browser
        driver.quit();
    }
}