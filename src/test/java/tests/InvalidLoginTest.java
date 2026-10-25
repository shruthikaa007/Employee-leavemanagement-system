package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;

public class InvalidLoginTest {

    @Test
    public void invalidPasswordTest() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Orbit HRMS
        driver.get("https://orbit-hrms-frontend.onrender.com");

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        // Login page
        LoginPage loginPage = new LoginPage(driver);

        // Enter invalid password
        loginPage.login(
                "priya.sharma@hrms.com",
                "wrong123"
        );

        System.out.println("Invalid credentials entered!");

        // Wait for login processing
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Check whether Dashboard link is displayed
        boolean dashboardDisplayed =
                driver.findElements(
                        By.xpath("//a[normalize-space()='Dashboard']")
                ).size() > 0;

        // Check whether login form is still displayed
        boolean passwordFieldDisplayed =
                driver.findElements(
                        By.cssSelector("input[type='password']")
                ).size() > 0;

        System.out.println(
                "Dashboard displayed: " + dashboardDisplayed
        );

        System.out.println(
                "Login password field displayed: "
                + passwordFieldDisplayed
        );

        // Invalid login should NOT open the authenticated dashboard
        Assert.assertFalse(
                dashboardDisplayed,
                "Invalid credentials opened the authenticated dashboard"
        );

        System.out.println(
                "TC02 PASSED: Invalid credentials did not open the dashboard!"
        );

        driver.quit();
    }
}