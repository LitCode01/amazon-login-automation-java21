# Amazon Login Automation Test Suite

## Overview

This repository contains comprehensive Java Selenium automation tests for validating the login functionality of the Amazon website. The test suite uses TestNG framework and follows industry best practices for automated testing.

## Files Included

- **AmazonLoginTest.java** - Basic implementation with manual ChromeDriver management
- **AmazonLoginTestAdvanced.java** - Recommended version using WebDriverManager for automatic driver management
- **pom.xml** - Maven configuration with all dependencies
- **testng.xml** - TestNG configuration for test execution
- **README.md** - This file

## Prerequisites

Before running the tests, ensure you have:

1. **Java JDK 21 LTS** installed
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Set JAVA_HOME environment variable
   - Java 21 is a Long-Term Support (LTS) version

2. **Maven 3.6 or higher** installed
   - Download from: https://maven.apache.org/download.cgi
   - Add Maven to PATH

3. **Git** (optional, for cloning the repository)

## Installation & Setup

### Step 1: Clone or Download the Repository

```bash
git clone <repository-url>
cd amazon-login-automation
```

### Step 2: Install Dependencies

Run Maven to download all required dependencies:

```bash
mvn clean install
```

This will download:
- Selenium WebDriver 4.15.0
- TestNG 7.8.1
- WebDriverManager 5.6.2
- SLF4J for logging

### Step 3: Verify Installation

```bash
mvn -v
java -version
```

## Test Suite Overview

### Test Cases Included

| Test # | Test Name | Description |
|--------|-----------|-------------|
| 1 | testLoginPageElements | Validates presence of all login page UI elements |
| 2 | testEmailInputField | Tests email input field data entry functionality |
| 3 | testEmptyEmailValidation | Validates error handling for empty email submission |
| 4 | testInvalidEmailFormat | Tests validation for invalid email formats |
| 5 | testCreateAccountLink | Verifies create account navigation link |
| 6 | testPasswordFieldAppears | Tests password field appearance after email submission |
| 7 | testForgotPasswordLink | Validates forgot password link presence |
| 8 | testLoginPageTitle | Checks page title correctness |

## Running the Tests

### Run All Tests

```bash
mvn test
```

### Run Tests with TestNG Configuration

```bash
mvn test -Dtest=AmazonLoginTest
```

### Run Specific Test Class (Advanced Version)

```bash
mvn test -Dtest=AmazonLoginTestAdvanced
```

### Run in Headless Mode

To run in headless mode (no browser window), uncomment the headless option in the setUp() method:

```java
options.addArguments("--headless");
```

## Test Execution Details

### WebDriver Configuration

**Basic Version (AmazonLoginTest.java):**
- Requires manual ChromeDriver setup
- Set chromedriver path in setUp() method

**Advanced Version (AmazonLoginTestAdvanced.java - RECOMMENDED):**
- Uses WebDriverManager to automatically manage ChromeDriver
- No manual driver configuration needed
- Automatically downloads the correct ChromeDriver version

### Wait Strategies

- **Explicit Waits**: 10-second default wait time for elements
- **ExpectedConditions**: Used for reliable element detection
- Configured in `WAIT_TIME = 10` constant

### Browser Configuration

- Chrome browser is used
- Window is maximized for consistent element visibility
- User-agent header is set to avoid being blocked
- Notifications and popups are disabled

## Test Reports

After test execution, reports are generated at:

```
target/surefire-reports/
```

View HTML report:
```
target/surefire-reports/index.html
```

## Important Notes

### Legal & Ethical Considerations

1. **Respect Amazon's Terms of Service**: Ensure your usage complies with Amazon's ToS
2. **Rate Limiting**: Amazon may detect and block automated requests
3. **CAPTCHA & Security**: Amazon may show CAPTCHA or require additional verification
4. **Test Accounts**: Use dedicated test accounts rather than real accounts

### Potential Issues & Solutions

| Issue | Solution |
|-------|----------|
| ChromeDriver version mismatch | Use WebDriverManager version (AmazonLoginTestAdvanced.java) |
| CAPTCHA blocking tests | Add delays, use headless mode less frequently, or rotate IPs |
| Element not found | Wait times may be too short, increase WAIT_TIME constant |
| Connection timeout | Check internet connection or try with proxy settings |
| Tests failing intermittently | Add explicit waits, verify element locators match current page structure |

## Customization

### Modify Wait Time

Edit `WAIT_TIME` constant in the test class:

```java
private static final int WAIT_TIME = 15; // Increase to 15 seconds
```

### Change Target URL

Modify `BASE_URL` constant:

```java
private static final String BASE_URL = "https://www.amazon.co.uk"; // For UK Amazon
```

### Disable GUI (Headless Mode)

In setUp() method, uncomment:

```java
options.addArguments("--headless");
options.addArguments("--no-sandbox");
options.addArguments("--disable-gpu");
```

### Add New Test Methods

Follow the pattern:

```java
@Test(priority = 9, description = "Your test description")
public void testNewFeature() {
    // Your test code
    System.out.println("✓ Test passed");
}
```

## Element Locators Used

| Element | Locator Type | Value |
|---------|-------------|-------|
| Email Input | ID | ap_email |
| Continue Button | ID | continue |
| Password Input | ID | ap_password |
| Sign-In Button | ID | signInSubmit |
| Create Account Link | XPath | //a[contains(text(), 'Create account')] |

**Note**: Amazon updates their website frequently. If tests fail due to element not found, verify and update locators using browser developer tools (F12).

## Debugging

### Enable Logging

Tests use SLF4J for logging. Output is shown in console during execution.

### Take Screenshots

Add this helper method to capture screenshots on failure:

```java
private void takeScreenshot(String testName) {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
        FileUtils.copyFile(screenshot, new File("screenshots/" + testName + ".png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

### HTML Report Analysis

Check generated HTML reports in `target/surefire-reports/` for detailed test execution logs.

## Best Practices Applied

✓ Explicit waits instead of thread.sleep()
✓ Page Object Model ready architecture
✓ Descriptive test method names
✓ Proper setup and teardown methods
✓ Error handling with try-catch blocks
✓ Helper methods for code reusability
✓ TestNG annotations for test management
✓ Comprehensive documentation
✓ Priority-based test execution
✓ Meaningful assertions with messages

## Dependency Versions

```
Selenium WebDriver: 4.15.0
TestNG: 7.8.1
WebDriverManager: 5.6.2
SLF4J: 2.0.9
Java: 21 LTS
Maven: 3.6+
```

## Troubleshooting

### Maven Build Fails

```bash
mvn clean
mvn install -U  # Update all dependencies
```

### Tests Timeout

Increase WAIT_TIME in test class or in testng.xml:

```xml
<parameter name="timeout" value="30000"/>
```

### Chrome Not Found

Ensure Chrome browser is installed. WebDriverManager will automatically download the correct ChromeDriver version.

## Contributing & Maintenance

- Update element locators when Amazon website changes
- Add new test cases for additional login scenarios
- Keep dependencies updated via Maven
- Test new features before merging to main branch

## Disclaimer

This automation script is provided for educational and testing purposes only. Usage must comply with:
- Amazon's Terms of Service
- Local laws and regulations
- Ethical guidelines for web automation
- Rate limiting and responsible usage practices

## Support & Resources

- **Selenium Documentation**: https://www.selenium.dev/documentation/
- **TestNG Documentation**: https://testng.org/doc/
- **WebDriverManager**: https://bonigarcia.dev/webdrivermanager/

## License

This project is provided as-is for educational purposes.

---

**Last Updated**: March 2026 (Upgraded to Java 21 LTS)
**Version**: 1.0.0
