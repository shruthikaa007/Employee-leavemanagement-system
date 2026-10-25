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

public class LeaveHistoryTest {

    @Test
    public void leaveHistoryTest() {

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
        // 8. Verify Leave History Table
        // ==========================================

        By leaveHistoryTable = By.xpath(
            "//table"
        );

        boolean tableDisplayed = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                leaveHistoryTable
            )
        ).isDisplayed();

        Assert.assertTrue(
            tableDisplayed,
            "Leave history table was not displayed"
        );

        System.out.println(
            "Leave history table displayed!"
        );

        // ==========================================
        // 9. Verify Table Headers
        // ==========================================

        Assert.assertTrue(
            driver.findElements(
                By.xpath(
                    "//th[normalize-space()='Name']"
                )
            ).size() > 0,
            "Name column was not displayed"
        );

        Assert.assertTrue(
            driver.findElements(
                By.xpath(
                    "//th[normalize-space()='Type']"
                )
            ).size() > 0,
            "Type column was not displayed"
        );

        Assert.assertTrue(
            driver.findElements(
                By.xpath(
                    "//th[normalize-space()='Start Date']"
                )
            ).size() > 0,
            "Start Date column was not displayed"
        );

        Assert.assertTrue(
            driver.findElements(
                By.xpath(
                    "//th[normalize-space()='End Date']"
                )
            ).size() > 0,
            "End Date column was not displayed"
        );

        Assert.assertTrue(
            driver.findElements(
                By.xpath(
                    "//th[normalize-space()='Status']"
                )
            ).size() > 0,
            "Status column was not displayed"
        );

        System.out.println(
            "Leave history table headers verified!"
        );

        // ==========================================
        // 10. Test Result
        // ==========================================

        System.out.println(
            "TC09 PASSED: Leave history displayed successfully!"
        );

        // ==========================================
        // 11. Close Browser
        // ==========================================

        driver.quit();
    }
}