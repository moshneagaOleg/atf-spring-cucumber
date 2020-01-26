package core.driver;

import lombok.NonNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.concurrent.TimeUnit;

import static core.driver.Browser.OS_NAME_PLACEHOLDER;
import static java.lang.String.format;

public class DriverFactory {

    private static final String DRIVERS = "drivers";
    //    private static final String BROWSER_NAME = Config.getString("browser");
    private static final String BROWSER_NAME = "chrome";
    private static final String CI_MODE = "ci";
//    private static Logger logger = LoggerFactory.getLogger(STRAssert.class);

    public DriverFactory() {
    }

    /**
     * Open new Driver instance for respective Browser
     *
     * @param arguments String... Ex: "--start-maximized", "--disable-notifications"
     * @return WebDriver
     */
    public static WebDriver openBrowser(String... arguments) {
        return openBrowser(Browser.get(BROWSER_NAME), arguments);
    }

    /**
     * Open new Driver instance for respective Browser
     *
     * @param browser   Browser Ex: Browser.CHROME...
     * @param arguments String... Ex: "--start-maximized", "--disable-notifications"
     * @return WebDriver
     */
    public static WebDriver openBrowser(Browser browser, String... arguments) {
        WebDriver driver = instantiateDriver(browser, arguments);
//        logger.info("Browser '{}' was opened", browser.name.toUpperCase());
        setCustomDriverSettings(driver);
        return driver;
    }

    /**
     * Get new Driver instance for respective Browser
     *
     * @param browser Browser Ex: Browser.CHROME
     * @return WebDriver
     */
    public static WebDriver getDriver(@NonNull Browser browser) {
//        logger.info("Current browser is {}", browser.name);
        return instantiateDriver(browser);
    }

    private static WebDriver instantiateDriver(@NonNull Browser browser, String... arguments) {
        if (!CI_MODE.equalsIgnoreCase(System.getProperty("runMode"))) {
//            String pathToDriver = getDriverPath(browser);
            String pathToDriver = "C:\\Users\\omosneaga\\Desktop\\spring-boot-cucumber-master\\src\\main\\resources\\drivers\\windows\\chrome\\chromedriver.exe";
            System.setProperty(browser.driverProperty, pathToDriver);
        }
        switch (browser) {
            case CHROME:
                return new ChromeDriver(new ChromeOptions().addArguments(arguments));
            case FIREFOX:
                return new FirefoxDriver(new FirefoxOptions().addArguments(arguments));
            case SAFARI:
                return new SafariDriver(new SafariOptions());
            case EDGE:
                return new EdgeDriver(new InternetExplorerOptions());
            default:
                throw new RuntimeException(format("Could not create new instance of driver for %s", browser.name));
        }
    }

    /**
     * Set common driver configs
     *
     * @param driver WebDriver
     */
    private static void setCustomDriverSettings(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
//        logger.info("Were installed implicitlyWait: '{}', pageLoadTimeout: '{}', scriptTimeout: '{}'",
//                5, 120, 120);
    }

    /**
     * Get path to driver for respective browser
     *
     * @param browser Browser Ex: Browser.CHROME...
     * @return String driver path Ex: drivers/windows/chromedriver.exe
     */
    private static String getDriverPath(Browser browser) {
        OSType os = OSType.detect();

        switch (browser) {
            case CHROME:
            case FIREFOX:
            case EDGE: {
                return String.join("/",
                        DRIVERS,
                        browser.driverLocation.replace(OS_NAME_PLACEHOLDER, os.name)
                                + os.executableExtention);
            }
            case SAFARI: {
                return browser.driverLocation;
            }
            default:
                throw new RuntimeException(format("Could not generate path to driver for %s", browser));
        }
    }


//    @SneakyThrows
//    public void closeBrowser() {
//        if (driver != null) {
//            driver.close();
//            driver.quit();
//            logger.info("Browser was closed");
//        }
//    }

}
