package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.DashboardPage;

public class LoginTest {

    @Test
    public void validLoginTest() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Open Orbit HRMS
        driver.get("https://orbit-hrms-frontend.onrender.com");

        // Login page
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            "priya.sharma@hrms.com",
            "Welcome@123"
        );

        // Dashboard page
        DashboardPage dashboardPage = new DashboardPage(driver);

        // Verify dashboard
        Assert.assertTrue(
            dashboardPage.isDashboardDisplayed(),
            "Dashboard was not displayed after login"
        );

        System.out.println("Login successful!");
        System.out.println("Dashboard opened successfully!");

        driver.quit();
    }
}