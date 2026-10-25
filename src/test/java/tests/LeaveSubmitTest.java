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

public class LeaveSubmitTest {

    @Test
    public void submitValidLeaveRequestTest() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(
            "https://orbit-hrms-frontend.onrender.com"
        );

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(20)
        );

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            "priya.sharma@hrms.com",
            "Welcome@123"
        );

        System.out.println(
            "Login completed!"
        );

        // Wait for dashboard
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath(
                    "//*[contains(text(),'Welcome back')]"
                )
            )
        );

        System.out.println(
            "Dashboard loaded successfully!"
        );

        // Open Time Off
        By timeOffMenu = By.xpath(
            "//a[normalize-space()='Time Off']"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(
                timeOffMenu
            )
        ).click();

        System.out.println(
            "Time Off page opened!"
        );

        LeavePage leavePage = new LeavePage(driver);

        Assert.assertTrue(
            leavePage.isTimeOffPageDisplayed(),
            "Time Off page was not displayed"
        );

        // Open New Request
        leavePage.clickNewRequest();

        System.out.println(
            "New Request form opened!"
        );

        // Select Paid Time Off
        leavePage.selectLeaveType(
            "Paid Time Off"
        );

        System.out.println(
            "Paid Time Off selected!"
        );

        // Enter dates
        leavePage.enterStartDate(
            "09/25/2026"
        );

        leavePage.enterEndDate(
            "09/26/2026"
        );

        System.out.println(
            "Start and End dates entered!"
        );

        // Enter remarks
        leavePage.enterRemarks(
            "Personal work"
        );

        System.out.println(
            "Remarks entered!"
        );

        // Submit
        leavePage.clickSubmit();

        System.out.println(
            "Submit button clicked!"
        );

        // Wait for page to update
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath(
                    "//h2[normalize-space()='Time Off']"
                )
            )
        );

        System.out.println(
            "Time Off page displayed after submission!"
        );

        System.out.println(
            "TC06 PASSED: Leave request submitted successfully!"
        );

        driver.quit();
    }
}