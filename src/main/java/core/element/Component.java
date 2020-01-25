package core.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Component {

    String getXpath();

    Component getParent();

    WebElement find();

    WebDriver getBrowser();

    List<WebElement> findAll();

    String getText();

    String getValue();

    String getFullXpath();

    default boolean isReady() {
        return true;
    }
}
