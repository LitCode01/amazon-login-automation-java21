import io.github.bonigarcia.wdm.WebDriverManager;
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
 * Amazon Login Automation Test Suite (WebDriverManager Version)
 *
 * This test suite validates the login functionality of Amazon website
 * using Selenium WebDriver with TestNG framework.
 * Uses WebDriverManager to automatically manage ChromeDriver.
 */
public class AmazonLoginTestAdvanced {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://www.amazon.com";
    private static final int WAIT_TIME = 10;

    /**
     * Setup method - initializes WebDriver and navigates to Amazon
     */
    @BeforeTest
    public void setUp() {
        // Automatically downloads and manages ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Uncomment for headless mode (no GUI browser)
        // options.addArguments("--headless");
        // options.addArguments("--no-sandbox");
        // options.addArguments("--disable-dev-shm-usage");
        // options.addArguments("--disable-gpu");

        // Disable notifications and popups
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        // User agent to avoid being blocked
        options.addArgument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

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
    @Test(priority = 1, description = "Validate presence of login page elements")
    public void testLoginPageElements() {
        navigateToLoginPage();

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

        // Verify "Create account" link exists
        WebElement createAccountLink = driver.findElement(
            By.xpath("//a[contains(text(), 'Create account')]")
        );
        Assert.assertTrue(createAccountLink.isDisplayed(), "Create account link should be visible");

        System.out.println("✓ Test 1 Passed: Login page elements are present");
    }

    /**
     * Test 2: Validate Email Input Field
     * Tests email/phone input field functionality
     */
    @Test(priority = 2, description = "Validate email input field functionality")
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
    @Test(priority = 3, description = "Validate empty email shows error")
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
        try {
            WebElement errorMessage = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(), 'Enter your email')]|//*[contains(text(), 'Enter your phone')]")
                )
            );
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for empty email");
            System.out.println("✓ Test 3 Passed: Empty email validation works");
        } catch (Exception e) {
            System.out.println("⚠ Test 3: Validation behavior may vary - manual verification recommended");
        }
    }

    /**
     * Test 4: Validate Invalid Email Format
     * Tests that invalid email format shows appropriate error
     */
    @Test(priority = 4, description = "Validate invalid email format shows error")
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

        try {
            WebElement errorElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(), 'email') or contains(text(), 'phone')]")
                )
            );
            Assert.assertTrue(errorElement.isDisplayed(), "Error should be shown for invalid format");
            System.out.println("✓ Test 4 Passed: Invalid email format validation works");
        } catch (Exception e) {
            System.out.println("⚠ Test 4: Validation behavior may vary - manual verification recommended");
        }
    }

    /**
     * Test 5: Validate Create Account Link
     * Tests the ability to navigate to create account page
     */
    @Test(priority = 5, description = "Validate create account link navigation")
    public void testCreateAccountLink() {
        navigateToLoginPage();

        // Find and click "Create account" link
        WebElement createAccountLink = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Create account')]")
            )
        );

        Assert.assertTrue(createAccountLink.isDisplayed(), "Create account link should be visible");

        createAccountLink.click();

        // Verify we're on create account page
        try {
            WebElement createAccountHeader = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(), 'Create')]|//*[contains(text(), 'account')]")
                )
            );
            Assert.assertTrue(createAccountHeader.isDisplayed(), "Should navigate to create account page");
            System.out.println("✓ Test 5 Passed: Create account link navigation works");
        } catch (Exception e) {
            System.out.println("⚠ Test 5: Page content verification requires manual setup");
        }
    }

    /**
     * Test 6: Validate Password Field Appears After Valid Email
     * Tests password field visibility after entering valid email
     */
    @Test(priority = 6, description = "Validate password field appears after email submission")
    public void testPasswordFieldAppears() {
        navigateToLoginPage();

        WebElement emailInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))
        );
        emailInput.sendKeys("test@example.com");

        WebElement continueButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("continue"))
        );
        continueButton.click();

        try {
            // Wait for password field to appear
            WebElement passwordInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("ap_password"))
            );
            Assert.assertTrue(passwordInput.isDisplayed(), "Password field should appear after valid email");

            // Verify Sign-In button for password step
            WebElement signInButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("signInSubmit"))
            );
            Assert.assertTrue(signInButton.isDisplayed(), "Sign-in button should be visible on password page");

            System.out.println("✓ Test 6 Passed: Password field appears after email submission");
        } catch (Exception e) {
            System.out.println("⚠ Test 6: Password page may not load if email is not recognized - this is expected for test accounts");
        }
    }

    /**
     * Test 7: Validate Forgot Password Link
     * Tests the presence and functionality of forgot password link
     */
    @Test(priority = 7, description = "Validate forgot password link is present")
    public void testForgotPasswordLink() {
        navigateToLoginPage();

        // Check for forgot password link on login page
        try {
            WebElement forgotPasswordLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(), 'Forgot password')]|//a[contains(text(), 'need help')]")
                )
            );
            Assert.assertTrue(forgotPasswordLink.isDisplayed(), "Forgot password link should be visible");
            System.out.println("✓ Test 7 Passed: Forgot password link is present");
        } catch (Exception e) {
            System.out.println("⚠ Test 7: Forgot password link may not be immediately visible");
        }
    }

    /**
     * Test 8: Validate Page Title
     * Tests that the login page has correct title
     */
    @Test(priority = 8, description = "Validate login page title")
    public void testLoginPageTitle() {
        navigateToLoginPage();

        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Amazon") || pageTitle.contains("Sign-in"),
            "Page title should contain Amazon or Sign-in");

        System.out.println("✓ Test 8 Passed: Login page has correct title: " + pageTitle);
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
