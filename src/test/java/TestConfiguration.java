/**
 * Configuration Constants for Amazon Login Automation Tests
 *
 * This file contains commonly configured constants that can be
 * modified based on your testing requirements.
 */

public class TestConfiguration {

    // ===== Application URLs =====
    public static final String AMAZON_BASE_URL = "https://www.amazon.com";
    public static final String AMAZON_LOGIN_URL = "https://www.amazon.com/ap/signin";

    // Alternative Amazon URLs
    public static final String AMAZON_UK_URL = "https://www.amazon.co.uk";
    public static final String AMAZON_INDIA_URL = "https://www.amazon.in";
    public static final String AMAZON_CANADA_URL = "https://www.amazon.ca";

    // ===== WebDriver Configuration =====
    public static final int EXPLICIT_WAIT_TIME = 10;  // seconds
    public static final int IMPLICIT_WAIT_TIME = 5;   // seconds
    public static final int PAGE_LOAD_TIMEOUT = 30;   // seconds

    // Chrome Driver Path (only needed for basic AmazonLoginTest.java)
    public static final String CHROME_DRIVER_PATH = "./drivers/chromedriver.exe";
    // For Mac: "./drivers/chromedriver"
    // For Linux: "./drivers/chromedriver"

    // ===== Browser Options =====
    public static final boolean HEADLESS_MODE = false;  // Set to true for headless execution
    public static final boolean MAXIMIZE_WINDOW = true;
    public static final boolean DISABLE_NOTIFICATIONS = true;
    public static final boolean DISABLE_POPUPS = true;
    public static final boolean DISABLE_GPU = false;  // Set to true for headless mode

    // Custom User Agent
    public static final String USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36";

    // Proxy Settings (optional)
    public static final boolean USE_PROXY = false;
    public static final String PROXY_ADDRESS = "127.0.0.1:8080";  // Format: IP:PORT

    // ===== Test Data =====
    public static final String TEST_EMAIL_VALID = "test@example.com";
    public static final String TEST_EMAIL_INVALID = "invalidemail";
    public static final String TEST_EMAIL_EMPTY = "";
    public static final String TEST_PASSWORD = "TestPassword123";

    // ===== Screenshot Configuration =====
    public static final boolean TAKE_SCREENSHOT_ON_FAILURE = true;
    public static final String SCREENSHOT_DIR = "./screenshots";
    public static final String SCREENSHOT_FORMAT = "png";  // png or jpg

    // ===== Log Configuration =====
    public static final String LOG_LEVEL = "INFO";  // INFO, DEBUG, WARN, ERROR
    public static final String LOG_FILE = "./logs/test_execution.log";

    // ===== Retry Configuration =====
    public static final int MAX_RETRIES = 0;  // Number of times to retry failed tests
    public static final long RETRY_DELAY = 1000;  // milliseconds between retries

    // ===== Test Execution Configuration =====
    public static final int THREAD_COUNT = 1;  // Number of parallel threads
    public static final boolean PARALLEL_TESTS = false;

    // ===== Speed Configuration =====
    public static final long ELEMENT_INTERACTION_DELAY = 0;  // milliseconds between actions
    public static final long PAGE_TRANSITION_DELAY = 0;      // milliseconds after page navigation

    // ===== Report Configuration =====
    public static final String REPORT_DIR = "./reports";
    public static final boolean GENERATE_HTML_REPORT = true;
    public static final boolean GENERATE_JSON_REPORT = false;
    public static final boolean GENERATE_EXCEL_REPORT = false;

    // ===== Email Notification Configuration =====
    public static final boolean SEND_EMAIL_REPORT = false;
    public static final String SMTP_SERVER = "smtp.gmail.com";
    public static final int SMTP_PORT = 587;
    public static final String EMAIL_FROM = "your-email@gmail.com";
    public static final String EMAIL_PASSWORD = "your-app-password";
    public static final String EMAIL_TO = "recipient@example.com";

    // ===== Database Configuration (for test results storage) =====
    public static final boolean USE_DATABASE = false;
    public static final String DB_URL = "jdbc:mysql://localhost:3306/automation_results";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "password";

    // ===== Features & Flags =====
    public static final boolean ENABLE_VIDEO_RECORDING = false;
    public static final boolean ENABLE_NETWORK_LOGGING = false;
    public static final boolean ENABLE_ACCESSIBILITY_CHECKS = false;
    public static final boolean ENABLE_PERFORMANCE_METRICS = false;

    // ===== Locator Configuration =====
    // These are fallback locators if element structure changes
    public static final String EMAIL_INPUT_LOCATOR = "id=ap_email";
    public static final String CONTINUE_BUTTON_LOCATOR = "id=continue";
    public static final String PASSWORD_INPUT_LOCATOR = "id=ap_password";
    public static final String SIGNIN_BUTTON_LOCATOR = "id=signInSubmit";

    // ===== Timeout Values =====
    public static final long ELEMENT_VISIBILITY_TIMEOUT = 10;  // seconds
    public static final long ELEMENT_CLICKABILITY_TIMEOUT = 10;  // seconds
    public static final long ELEMENT_PRESENCE_TIMEOUT = 5;  // seconds
    public static final long ELEMENT_INVISIBILITY_TIMEOUT = 5;  // seconds

    // ===== Polling Configuration =====
    public static final long POLLING_INTERVAL = 250;  // milliseconds

    /**
     * Method to get browser options based on configuration
     */
    public static String getBrowserOptions() {
        StringBuilder options = new StringBuilder();
        if (HEADLESS_MODE) options.append("--headless ");
        if (DISABLE_NOTIFICATIONS) options.append("--disable-notifications ");
        if (DISABLE_POPUPS) options.append("--disable-popup-blocking ");
        if (DISABLE_GPU) options.append("--disable-gpu ");
        return options.toString().trim();
    }

    /**
     * Method to get proxy settings
     */
    public static String getProxySettings() {
        if (USE_PROXY) {
            return PROXY_ADDRESS;
        }
        return null;
    }

    /**
     * Print current configuration
     */
    public static void printConfiguration() {
        System.out.println("\n========== TEST CONFIGURATION ==========");
        System.out.println("Base URL: " + AMAZON_BASE_URL);
        System.out.println("Wait Time: " + EXPLICIT_WAIT_TIME + "s");
        System.out.println("Headless Mode: " + HEADLESS_MODE);
        System.out.println("Thread Count: " + THREAD_COUNT);
        System.out.println("Screenshot on Failure: " + TAKE_SCREENSHOT_ON_FAILURE);
        System.out.println("Browser Options: " + getBrowserOptions());
        System.out.println("========================================\n");
    }
}
