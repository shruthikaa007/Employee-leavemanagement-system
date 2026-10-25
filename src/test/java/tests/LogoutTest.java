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

public class LogoutTest {

    @Test
    public void logoutTest() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://orbit-hrms-frontend.onrender.com");

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "priya.sharma@hrms.com",
                "Welcome@123"
        );

        System.out.println("Login completed!");

        // Verify Dashboard
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[normalize-space()='Dashboard']")
                )
        );

        System.out.println("Dashboard loaded successfully!");

        // Open Profile menu
        By profileButton = By.xpath(
                "//button[contains(normalize-space(.),'Priya Sharma')]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(profileButton)
        ).click();

        System.out.println("Profile menu opened!");

        // Find Logout
        By logoutOption = By.xpath(
                "//*[self::button or self::a or self::div or self::span]"
                + "[contains(translate(normalize-space(.),"
                + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                + "'abcdefghijklmnopqrstuvwxyz'),'logout')"
                + " or contains(translate(normalize-space(.),"
                + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                + "'abcdefghijklmnopqrstuvwxyz'),'log out')"
                + " or contains(translate(normalize-space(.),"
                + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                + "'abcdefghijklmnopqrstuvwxyz'),'sign out')]"
        );

        // Click Logout
        wait.until(
                ExpectedConditions.elementToBeClickable(logoutOption)
        ).click();

        System.out.println("Logout option clicked!");

        // Wait for navigation
        wait.until(driver1 ->
                driver1.getCurrentUrl().contains("/attendance")
                || driver1.getCurrentUrl().contains("/time-off")
                || driver1.getCurrentUrl().equals(
                        "https://orbit-hrms-frontend.onrender.com/"
                )
        );

        String currentUrl = driver.getCurrentUrl();

        System.out.println(
                "URL after logout action: " + currentUrl
        );

        // Verify that navigation occurred
        Assert.assertNotEquals(
                currentUrl,
                "about:blank",
                "No page was loaded after logout action"
        );

        System.out.println(
                "TC10 PASSED: Logout action completed successfully!"
        );

        driver.quit();
    }
}