package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeavePage {

    WebDriver driver;
    WebDriverWait wait;

    // =====================================================
    // TC03 - Leave Balance
    // =====================================================

    By timeOffHeading = By.xpath(
        "//h2[normalize-space()='Time Off']"
    );

    By paidTimeOff = By.xpath(
        "//div[contains(@class,'stat-card')]" +
        "[.//span[normalize-space()='Paid Time Off']]" +
        "//span[contains(@class,'stat-value')]"
    );

    By sickLeave = By.xpath(
        "//div[contains(@class,'stat-card')]" +
        "[.//span[normalize-space()='Sick Leave']]" +
        "//span[contains(@class,'stat-value')]"
    );

    By pendingRequests = By.xpath(
        "//div[contains(@class,'stat-card')]" +
        "[.//span[normalize-space()='Pending Requests']]" +
        "//span[contains(@class,'stat-value')]"
    );


    // =====================================================
    // TC04 - New Leave Request
    // =====================================================

    By newRequestButton = By.xpath(
        "//button[contains(normalize-space(),'+ New Request')]"
    );


    // =====================================================
    // TC05 - Leave Type Selection
    // =====================================================

    By leaveTypeDropdown = By.xpath(
        "//select[1]"
    );


    // =====================================================
    // TC06 - Submit Leave Request
    // =====================================================

    By startDate = By.xpath(
        "(//input[@type='date'])[1]"
    );

    By endDate = By.xpath(
        "(//input[@type='date'])[2]"
    );

    By remarks = By.xpath(
        "//textarea"
    );

    By submitButton = By.xpath(
        "//button[normalize-space()='Submit']"
    );


    // =====================================================
    // TC08 - Leave Status
    // =====================================================

    By statusColumn = By.xpath(
        "//th[normalize-space()='Status']"
    );

    By leaveStatus = By.xpath(
        "//tbody//td[last()]"
    );


    // =====================================================
    // Constructor
    // =====================================================

    public LeavePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(15)
        );
    }


    // =====================================================
    // TC03 - Check Time Off Page
    // =====================================================

    public boolean isTimeOffPageDisplayed() {

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                timeOffHeading
            )
        ).isDisplayed();
    }


    // =====================================================
    // TC03 - Get Paid Time Off
    // =====================================================

    public String getPaidTimeOff() {

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                paidTimeOff
            )
        ).getText();
    }


    // =====================================================
    // TC03 - Get Sick Leave
    // =====================================================

    public String getSickLeave() {

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                sickLeave
            )
        ).getText();
    }


    // =====================================================
    // TC03 - Get Pending Requests
    // =====================================================

    public String getPendingRequests() {

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                pendingRequests
            )
        ).getText();
    }


    // =====================================================
    // TC04 - Click New Request
    // =====================================================

    public void clickNewRequest() {

        wait.until(
            ExpectedConditions.elementToBeClickable(
                newRequestButton
            )
        ).click();
    }


    // =====================================================
    // TC05 - Select Leave Type
    // =====================================================

    public void selectLeaveType(String leaveType) {

        Select select = new Select(
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    leaveTypeDropdown
                )
            )
        );

        select.selectByVisibleText(leaveType);
    }


    // =====================================================
    // TC05 - Get Selected Leave Type
    // =====================================================

    public String getSelectedLeaveType() {

        Select select = new Select(
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    leaveTypeDropdown
                )
            )
        );

        return select
            .getFirstSelectedOption()
            .getText();
    }


    // =====================================================
    // TC06 - Enter Start Date
    // =====================================================

    public void enterStartDate(String date) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                startDate
            )
        ).sendKeys(date);
    }


    // =====================================================
    // TC06 - Enter End Date
    // =====================================================

    public void enterEndDate(String date) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                endDate
            )
        ).sendKeys(date);
    }


    // =====================================================
    // TC06 - Enter Remarks
    // =====================================================

    public void enterRemarks(String text) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                remarks
            )
        ).sendKeys(text);
    }


    // =====================================================
    // TC06 - Click Submit
    // =====================================================

    public void clickSubmit() {

        wait.until(
            ExpectedConditions.elementToBeClickable(
                submitButton
            )
        ).click();
    }


    // =====================================================
    // TC08 - Check Status Column
    // =====================================================

    public boolean isStatusColumnDisplayed() {

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                statusColumn
            )
        ).isDisplayed();
    }


    // =====================================================
    // TC08 - Get Leave Status
    // =====================================================

    public String getLeaveStatus() {

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                leaveStatus
            )
        ).getText();
    }
}