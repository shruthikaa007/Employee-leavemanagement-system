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

public class LeaveStatusTest {

    @Test
    public void leaveStatusTest() {

        // ==========================================
        // 1. Open Browser
        // ==========================================

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(
            "https://orbit-hrms-frontend.onrender.com"
        );

        // ==========================================
        // 2. Create Explicit Wait
        // ==========================================

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(20)
        );

        // ==========================================
        // 3. Login
        // ==========================================

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            "priya.sharma@hrms.com",
            "Welcome@123"
        );

        System.out.println(
            "Login completed!"
        );

        // ==========================================
        // 4. Verify Dashboard
        // ==========================================

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
        // 5. Open Time Off Page
        // ==========================================

        By timeOffMenu = By.xpath(
            "//a[contains(@href,'/time-off')]"
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
        // 6. Create Leave Page Object
        // ==========================================

        LeavePage leavePage = new LeavePage(driver);

        // ==========================================
        // 7. Verify Time Off Page
        // ==========================================

        Assert.assertTrue(
            leavePage.isTimeOffPageDisplayed(),
            "Time Off page was not displayed"
        );

        System.out.println(
            "Time Off page verified!"
        );

        // ==========================================
        // 8. Verify Status Column
        // ==========================================

        Assert.assertTrue(
            leavePage.isStatusColumnDisplayed(),
            "Status column was not displayed"
        );

        System.out.println(
            "Status column displayed!"
        );

        // ==========================================
        // 9. Get Leave Status
        // ==========================================

        String status = leavePage.getLeaveStatus();

        System.out.println(
            "Leave Status: " + status
        );

        // ==========================================
        // 10. Verify Status is Not Empty
        // ==========================================

        Assert.assertFalse(
            status.isEmpty(),
            "Leave status is empty"
        );

        System.out.println(
            "Leave status verified successfully!"
        );

        // ==========================================
        // 11. Test Result
        // ==========================================

        System.out.println(
            "TC08 PASSED: Leave status displayed successfully!"
        );

        // ==========================================
        // 12. Close Browser
        // ==========================================

        driver.quit();
    }
}