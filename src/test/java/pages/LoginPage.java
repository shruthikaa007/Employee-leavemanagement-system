package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    By emailField = By.cssSelector(
        "input[type='email'], input:not([type='password'])"
    );

    By passwordField = By.cssSelector(
        "input[type='password']"
    );

    By signInButton = By.xpath(
        "//button[contains(normalize-space(),'Sign In')]"
    );

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(30)
        );
    }

    public void enterEmail(String email) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                emailField
            )
        );

        WebDriverWait fieldWait =
            new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
            );

        fieldWait.until(
            ExpectedConditions.elementToBeClickable(
                emailField
            )
        ).clear();

        driver.findElement(emailField)
            .sendKeys(email);
    }

    public void enterPassword(String password) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                passwordField
            )
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(
                passwordField
            )
        ).clear();

        driver.findElement(passwordField)
            .sendKeys(password);
    }

    public void clickSignIn() {

        wait.until(
            ExpectedConditions.elementToBeClickable(
                signInButton
            )
        ).click();
    }

    public void login(
        String email,
        String password
    ) {

        wait.until(
            driver1 ->
                ((org.openqa.selenium.JavascriptExecutor) driver1)
                    .executeScript(
                        "return document.readyState"
                    )
                    .equals("complete")
        );

        enterEmail(email);

        enterPassword(password);

        clickSignIn();

        System.out.println(
            "Login button clicked!"
        );
    }
}