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

public class LeaveRequestTest {

    @Test
    public void openLeaveRequestTest() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(
            "https://orbit-hrms-frontend.onrender.com"
        );

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(20)
        );

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            "priya.sharma@hrms.com",
            "Welcome@123"
        );

        System.out.println("Login completed!");

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Welcome back')]")
            )
        );

        System.out.println(
            "Dashboard loaded successfully!"
        );

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

        leavePage.clickNewRequest();

        System.out.println(
            "New Request button clicked!"
        );

        // Verify that the leave request form/modal is displayed
        boolean formDisplayed = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath(
                    "//*[contains(text(),'Leave') or " +
                    "contains(text(),'Request')]"
                )
            )
        ).isDisplayed();

        Assert.assertTrue(
            formDisplayed,
            "Leave request form was not displayed"
        );

        System.out.println(
            "TC04 PASSED: Leave request form opened successfully!"
        );

        driver.quit();
    }
}
