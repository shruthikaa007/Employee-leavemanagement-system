package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmployeeLeaveManagementTest {

    public static void main(String[] args) throws InterruptedException {

        // Open Chrome
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Orbit HRMS
        driver.get("https://orbit-hrms-frontend.onrender.com");

        Thread.sleep(2000);

        // Login
        driver.findElement(By.cssSelector("input")).sendKeys(
                "priya.sharma@hrms.com");

        driver.findElement(By.cssSelector(
                "input[type='password']")).sendKeys(
                "Welcome@123");

        driver.findElement(By.xpath(
                "//button[contains(text(),'Sign In')]")).click();

        Thread.sleep(3000);

        System.out.println("LOGIN TEST PASSED");

        // Open Time Off
        driver.findElement(By.xpath(
                "//a[normalize-space()='Time Off']")).click();

        Thread.sleep(2000);

        System.out.println("TIME OFF PAGE OPENED");

        // Click New Request
        driver.findElement(By.xpath(
                "//button[contains(text(),'+ New Request')]")).click();

        Thread.sleep(1000);

        System.out.println("LEAVE REQUEST FORM OPENED");

        // Select Leave Type
        driver.findElement(By.xpath(
                "//select[1]")).click();

        driver.findElement(By.xpath(
                "//select[1]/option[contains(text(),'Sick Leave')]")).click();

        System.out.println("SICK LEAVE SELECTED");

        // Enter Start Date
        driver.findElement(By.xpath(
                "(//input[@type='date'])[1]")).sendKeys("09/25/2026");

        // Enter End Date
        driver.findElement(By.xpath(
                "(//input[@type='date'])[2]")).sendKeys("09/26/2026");

        // Enter Remarks
        driver.findElement(By.xpath(
                "//textarea")).sendKeys("Personal work");

        System.out.println("LEAVE DETAILS ENTERED");

        // Submit Leave Request
        driver.findElement(By.xpath(
                "//button[normalize-space()='Submit']")).click();

        Thread.sleep(3000);

        System.out.println("LEAVE REQUEST SUBMITTED");

        // Close browser
        driver.quit();

        System.out.println("TEST COMPLETED SUCCESSFULLY");
    }
}