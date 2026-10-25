package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LogoutInspectTest {

    @Test
    public void logoutInspectTest() throws InterruptedException {

        // Open Chrome
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Orbit HRMS
        driver.get("https://orbit-hrms-frontend.onrender.com");

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );

        // ==============================
        // LOGIN
        // ==============================

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "priya.sharma@hrms.com",
                "Welcome@123"
        );

        System.out.println("Login completed!");

        // Wait for Dashboard
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[normalize-space()='Dashboard']")
                )
        );

        System.out.println("Dashboard loaded successfully!");

        // ==============================
        // OPEN PROFILE MENU
        // ==============================

        By profileButton = By.xpath(
                "//button[contains(normalize-space(.),'Priya Sharma')]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(profileButton)
        ).click();

        System.out.println("Profile menu opened!");

        // ==============================
        // FIND LOGOUT OPTION
        // ==============================

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

        wait.until(
                ExpectedConditions.elementToBeClickable(logoutOption)
        ).click();

        System.out.println("Logout option clicked!");

        // Wait for logout action to complete
        Thread.sleep(2000);

        // ==============================
        // INSPECT PAGE AFTER LOGOUT
        // ==============================

        System.out.println();
        System.out.println("========== AFTER LOGOUT ==========");

        System.out.println(
                "Current URL: " + driver.getCurrentUrl()
        );

        System.out.println(
                "Page Title: " + driver.getTitle()
        );

        // ==============================
        // BUTTONS AFTER LOGOUT
        // ==============================

        System.out.println();
        System.out.println(
                "========== BUTTONS AFTER LOGOUT =========="
        );

        for (var button : driver.findElements(By.tagName("button"))) {

            System.out.println(
                    "BUTTON: [" + button.getText() + "]"
            );
        }

        // ==============================
        // LINKS AFTER LOGOUT
        // ==============================

        System.out.println();
        System.out.println(
                "========== LINKS AFTER LOGOUT =========="
        );

        for (var link : driver.findElements(By.tagName("a"))) {

            System.out.println(
                    "LINK: [" + link.getText() + "]"
            );
        }

        // ==============================
        // PAGE TEXT AFTER LOGOUT
        // ==============================

        System.out.println();
        System.out.println(
                "========== PAGE TEXT AFTER LOGOUT =========="
        );

        System.out.println(
                driver.findElement(By.tagName("body")).getText()
        );

        System.out.println();
        System.out.println("========== END ==========");

        // Close browser
        driver.quit();
    }
}