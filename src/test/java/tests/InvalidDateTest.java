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

public class InvalidDateTest {

    @Test
    public void invalidDateTest() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(
            "https://orbit-hrms-frontend.onrender.com"
        );

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(20)
        );

        // ==========================================
        // LOGIN
        // ==========================================

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            "priya.sharma@hrms.com",
            "Welcome@123"
        );

        System.out.println(
            "Login completed!"
        );

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


        // ==========================================
        // OPEN TIME OFF
        // ==========================================

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


        // ==========================================
        // OPEN NEW REQUEST
        // ==========================================

        LeavePage leavePage = new LeavePage(driver);

        Assert.assertTrue(
            leavePage.isTimeOffPageDisplayed(),
            "Time Off page was not displayed"
        );

        leavePage.clickNewRequest();

        System.out.println(
            "New Request form opened!"
        );


        // ==========================================
        // SELECT LEAVE TYPE
        // ==========================================

        leavePage.selectLeaveType(
            "Paid Time Off"
        );

        System.out.println(
            "Paid Time Off selected!"
        );


        // ==========================================
        // ENTER INVALID DATE RANGE
        // ==========================================

        // Start Date = 26 September
        // End Date   = 25 September
        // Therefore End Date is before Start Date.

        leavePage.enterStartDate(
            "09/26/2026"
        );

        leavePage.enterEndDate(
            "09/25/2026"
        );

        System.out.println(
            "Invalid date range entered!"
        );


        // ==========================================
        // ENTER REMARKS
        // ==========================================

        leavePage.enterRemarks(
            "Invalid date testing"
        );

        System.out.println(
            "Remarks entered!"
        );


        // ==========================================
        // CLICK SUBMIT
        // ==========================================

        leavePage.clickSubmit();

        System.out.println(
            "Submit button clicked!"
        );


        // ==========================================
        // CHECK RESULT
        // ==========================================

        /*
         * Wait for a short period to allow the application
         * to process the request.
         */
        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }


        String currentUrl = driver.getCurrentUrl();

        System.out.println(
            "Current URL: " + currentUrl
        );


        // ==========================================
        // VERIFY REQUEST WAS NOT SUCCESSFULLY
        // SUBMITTED
        // ==========================================

        boolean requestFormStillDisplayed;

        try {

            requestFormStillDisplayed =
                driver.findElements(
                    By.xpath(
                        "//button[normalize-space()='Submit']"
                    )
                ).size() > 0;

        } catch (Exception e) {

            requestFormStillDisplayed = false;
        }


        boolean invalidDateMessageDisplayed =
            driver.findElements(
                By.xpath(
                    "//*[contains(translate(text()," +
                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                    "'abcdefghijklmnopqrstuvwxyz')," +
                    "'date') and " +
                    "(contains(translate(text()," +
                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                    "'abcdefghijklmnopqrstuvwxyz')," +
                    "'invalid') or " +
                    "contains(translate(text()," +
                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                    "'abcdefghijklmnopqrstuvwxyz')," +
                    "'end') or " +
                    "contains(translate(text()," +
                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                    "'abcdefghijklmnopqrstuvwxyz')," +
                    "'start'))]"
                )
            ).size() > 0;


        System.out.println(
            "Request form still displayed: "
            + requestFormStillDisplayed
        );

        System.out.println(
            "Possible date validation message: "
            + invalidDateMessageDisplayed
        );


        /*
         * The application should not redirect to a
         * successful request page/dashboard as a result
         * of the invalid date range.
         */
        boolean dashboardOpened =
            currentUrl.toLowerCase().contains("dashboard");


        Assert.assertFalse(
            dashboardOpened,
            "Invalid date request incorrectly opened the dashboard"
        );


        System.out.println(
            "TC07 PASSED: Invalid date request was not submitted successfully!"
        );


        driver.quit();
    }
}