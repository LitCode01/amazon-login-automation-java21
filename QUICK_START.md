# Quick Start Guide - Amazon Login Automation

## 5-Minute Setup

### 1. Prerequisites Check
```bash
java -version          # Should be Java 11+
mvn -v                 # Should be Maven 3.6+
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run Tests
```bash
# Run all tests
mvn test

# Run specific test class (Recommended - uses WebDriverManager)
mvn test -Dtest=AmazonLoginTestAdvanced

# Run in headless mode
mvn test -Dheadless=true
```

## Test Results Location
```
target/surefire-reports/index.html
```

## Recommended Test Class
**Use: AmazonLoginTestAdvanced.java**

Why?
- Automatically manages ChromeDriver (no manual setup)
- Uses WebDriverManager for version compatibility
- Better error handling
- Includes advanced browser options

## Key Test Cases

| # | Test | Expected Result |
|---|------|-----------------|
| 1 | Login Page Elements | All UI elements present ✓ |
| 2 | Email Input | Can enter email successfully ✓ |
| 3 | Empty Email | Shows validation error ✓ |
| 4 | Invalid Format | Shows format error ✓ |
| 5 | Create Account | Navigates to signup ✓ |
| 6 | Password Field | Appears after valid email ✓ |
| 7 | Forgot Password | Link is present ✓ |
| 8 | Page Title | Contains "Amazon" or "Sign-in" ✓ |

## Troubleshooting Quick Fixes

### Tests still downloading ChromeDriver?
```bash
mvn clean install
```

### Tests timing out?
Edit AmazonLoginTestAdvanced.java:
```java
private static final int WAIT_TIME = 15; // Change from 10 to 15
```

### Chrome not found?
Make sure Chrome is installed. WebDriverManager will handle the rest.

### CAPTCHA appearing?
This is normal. Amazon detects automation. Use:
- Different test email/IP if possible
- Add delays between tests
- Run in headless mode

## File Structure
```
amazon-login-automation/
├── AmazonLoginTest.java              (Basic version)
├── AmazonLoginTestAdvanced.java     (Recommended)
├── pom.xml                          (Maven config)
├── testng.xml                       (Test config)
├── README.md                        (Full docs)
├── QUICK_START.md                   (This file)
└── target/surefire-reports/        (Test reports - after running)
```

## Common Commands

| Command | Purpose |
|---------|---------|
| `mvn test` | Run all tests |
| `mvn test -Dtest=AmazonLoginTestAdvanced` | Run advanced test suite |
| `mvn clean` | Clean build files |
| `mvn compile` | Compile only |
| `mvn dependency:tree` | View all dependencies |

## Test Execution Flow

```
Start → Setup Browser → Navigate to Amazon Login →
Run Test Cases → Validate Results → Generate Report → Close Browser
```

## Important Notes

✓ Tests are read-only (no account creation/modification)
✓ Respects Amazon's Terms of Service
✓ Cross-platform (Windows, Mac, Linux)
✓ No additional software needed

## Next Steps

1. Run tests successfully
2. Review test results in HTML report
3. Customize test cases as needed
4. Integrate into CI/CD pipeline

## Support

If tests fail:
1. Check Java & Maven versions
2. Run `mvn clean install`
3. Review README.md for detailed troubleshooting
4. Check element locators in browser Dev Tools (F12)

---

Happy Testing! 🚀
