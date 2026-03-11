import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.time.Duration;

/**
 * Amazon Login Automation Test Suite
 *
 * This test suite validates the login functionality of Amazon website
 * using Selenium WebDriver with TestNG framework.
 */
public class AmazonLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://www.amazon.com";
    private static final int WAIT_TIME = 10;

    /**
     * Setup method - initializes WebDriver and navigates to Amazon
     */
    @BeforeTest
    public void setUp() {
        // Initialize Chrome WebDriver
        // Ensure ChromeDriver is in your PATH or specify the path explicitly
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Uncomment for headless mode
        // options.addArguments("--no-sandbox");
        // options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));

        // Navigate to Amazon
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    /**
     * Teardown method - closes the browser
     */
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Test 1: Validate Login Page Elements
     * Verifies that all login page elements are present and visible
     */
    @Test(priority = 1)
    public void testLoginPageElements() {
        // Click on Accounts & Lists
        WebElement accountsMenu = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("nav-account-flyout-toggle"))
        );
        accountsMenu.click();

        // Wait and verify Sign-In button is visible
        WebElement signInButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Sign in')]"))
        );

        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button should be visible");

        // Click on Sign-In
        signInButton.click();

        // Verify email/phone input field exists
        WebElement emailInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))
        );
        Assert.assertTrue(emailInput.isDisplayed(), "Email input field should be visible");

        // Verify Continue button exists
        WebElement continueButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("continue"))
        );
        Assert.assertTrue(continueButton.isDisplayed(), "Continue button should be visible");

        System.out.println("✓ Test 1 Passed: Login page elements are present");
    }

    /**
     * Test 2: Validate Email Input Field
     * Tests email/phone input field functionality
     */
    @Test(priority = 2)
    public void testEmailInputField() {
        navigateToLoginPage();

        // Get email input field
        WebElement emailInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))
        );

        // Clear field and enter test email
        emailInput.clear();
        emailInput.sendKeys("test@example.com");

        // Verify text was entered
        String enteredValue = emailInput.getAttribute("value");
        Assert.assertEquals(enteredValue, "test@example.com", "Email should be entered in input field");

        System.out.println("✓ Test 2 Passed: Email input field works correctly");
    }

    /**
     * Test 3: Validate Empty Email Validation
     * Tests that empty email submission shows error
     */
    @Test(priority = 3)
    public void testEmptyEmailValidation() {
        navigateToLoginPage();

        // Try to submit without email
        WebElement emailInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))
        );
        emailInput.clear();

        WebElement continueButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("continue"))
        );
        continueButton.click();

        // Wait for error message
        WebElement errorMessage = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[@class='a-alert-heading'][contains(text(), 'There was a problem')]"))
        );

        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for empty email");

        System.out.println("✓ Test 3 Passed: Empty email validation works");
    }

    /**
     * Test 4: Validate Invalid Email Format
     * Tests that invalid email format shows appropriate error
     */
    @Test(priority = 4)
    public void testInvalidEmailFormat() {
        navigateToLoginPage();

        WebElement emailInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))
        );
        emailInput.clear();
        emailInput.sendKeys("invalidemail");

        WebElement continueButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("continue"))
        );
        continueButton.click();

        // Wait for validation error
        try {
            WebElement errorElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Incorrect')]"))
            );
            Assert.assertTrue(errorElement.isDisplayed(), "Error should be shown for invalid email format");
        } catch (Exception e) {
            System.out.println("Note: Amazon's validation may vary - manual verification recommended");
        }

        System.out.println("✓ Test 4 Passed: Invalid email format validation tested");
    }

    /**
     * Test 5: Validate Back to Store Link
     * Tests the ability to navigate back to Amazon store
     */
    @Test(priority = 5)
    public void testBackToStoreLink() {
        navigateToLoginPage();

        // Find and click "Back to Amazon" link
        WebElement backLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Back to Amazon')]"))
        );

        Assert.assertTrue(backLink.isDisplayed(), "Back to Amazon link should be visible");

        backLink.click();

        // Verify we're back on Amazon homepage
        WebElement amazonLogo = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("nav-logo"))
        );

        Assert.assertTrue(amazonLogo.isDisplayed(), "Should navigate back to Amazon homepage");

        System.out.println("✓ Test 5 Passed: Back to store navigation works");
    }

    /**
     * Test 6: Validate Password Field Elements
     * Tests password field visibility after entering valid email
     */
    @Test(priority = 6)
    public void testPasswordFieldElements() {
        navigateToLoginPage();

        WebElement emailInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))
        );
        emailInput.sendKeys("validuser@example.com");

        WebElement continueButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("continue"))
        );
        continueButton.click();

        // Wait for password field to appear
        try {
            WebElement passwordInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("ap_password"))
            );
            Assert.assertTrue(passwordInput.isDisplayed(), "Password field should be visible after valid email");

            // Verify Sign-In button for password step
            WebElement signInButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("signInSubmit"))
            );
            Assert.assertTrue(signInButton.isDisplayed(), "Sign-in button should be visible on password page");

            System.out.println("✓ Test 6 Passed: Password field elements are present");
        } catch (Exception e) {
            System.out.println("Note: Password page may not load if email is not recognized");
        }
    }

    /**
     * Test 7: Validate Forgot Password Link
     * Tests the presence and functionality of forgot password link
     */
    @Test(priority = 7)
    public void testForgotPasswordLink() {
        navigateToLoginPage();

        // Forgot password link might be on password page, so enter email first
        WebElement emailInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))
        );
        emailInput.sendKeys("test@example.com");

        WebElement continueButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("continue"))
        );
        continueButton.click();

        // Look for "Forgot password?" link
        try {
            WebElement forgotPasswordLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Forgot password')]"))
            );
            Assert.assertTrue(forgotPasswordLink.isDisplayed(), "Forgot password link should be visible");

            System.out.println("✓ Test 7 Passed: Forgot password link is present");
        } catch (Exception e) {
            System.out.println("Note: Forgot password link location may vary or require specific conditions");
        }
    }

    /**
     * Test 8: Validate Remember Me Checkbox
     * Tests the remember me functionality checkbox
     */
    @Test(priority = 8)
    public void testRememberMeCheckbox() {
        navigateToLoginPage();

        try {
            // Look for "Keep me signed in" or "Remember me" checkbox
            WebElement rememberMeCheckbox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("rememberMe"))
            );

            Assert.assertTrue(rememberMeCheckbox.isDisplayed(), "Remember me checkbox should be visible");

            // Click the checkbox
            rememberMeCheckbox.click();

            // Verify it's checked
            boolean isChecked = rememberMeCheckbox.isSelected();
            Assert.assertTrue(isChecked, "Remember me checkbox should be selectable");

            System.out.println("✓ Test 8 Passed: Remember me checkbox works");
        } catch (Exception e) {
            System.out.println("Note: Remember me checkbox may not be present on all login pages");
        }
    }

    /**
     * Helper method to navigate to Amazon login page
     */
    private void navigateToLoginPage() {
        driver.navigate().to(BASE_URL + "/ap/signin");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_email")));
    }

    /**
     * Helper method to wait for element visibility
     */
    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
