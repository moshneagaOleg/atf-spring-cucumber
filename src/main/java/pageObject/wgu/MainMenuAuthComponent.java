package pageObject.wgu;

import core.element.AbstractComponent;
import core.element.Component;
import core.element.Module;
import core.element.YandexElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Getter
@Setter
public final class MainMenuAuthComponent extends AbstractComponent implements Module {

    private final static String DIV_CONTAINS = "//div[@role='menu']/a[contains(text(),";

    @Name("Photo Button")
    @Timeout(30)
    @FindBy(xpath = "//button[div[contains(@class,'photo')]]")
    public YandexElement photoBtn;

    @Name("Logout")
    @Timeout(30)
    @FindBy(xpath = DIV_CONTAINS + "'Logout')]")
    public YandexElement logoutLnk;

    public MainMenuAuthComponent(WebDriver browser, String name, Component parent, String xpath) {
        super(browser, name, parent, xpath);
    }

}
