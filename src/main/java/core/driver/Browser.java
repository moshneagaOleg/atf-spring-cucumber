package core.driver;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

@AllArgsConstructor
public enum Browser {

    CHROME("chrome", Browser.OS_NAME_PLACEHOLDER + "/chromedriver", "webdriver.chrome.driver"),
    FIREFOX("firefox", Browser.OS_NAME_PLACEHOLDER + "/geckodriver", "webdriver.gecko.driver"),
    SAFARI("safari", "/usr/bin/safaridriver", "webdriver.safari.driver"),
    EDGE("edge", Browser.OS_NAME_PLACEHOLDER + "/msedgedriver", "webdriver.edge.driver");

    static final String OS_NAME_PLACEHOLDER = "$osname";
    String name;
    String driverLocation;
    String driverProperty;

    @SneakyThrows
    public static Browser get(String browserName) {
        return Stream.of(Browser.values())
                .filter(browser -> StringUtils.equalsIgnoreCase(browser.name(), browserName))
                .findFirst().orElseThrow(() -> new RuntimeException(
                        String.format("[%s] browser does not match with any of types in the [%s] enum",
                                browserName, Browser.class.getSimpleName())));
    }

}
