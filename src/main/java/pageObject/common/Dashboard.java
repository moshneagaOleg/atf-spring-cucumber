package pageObject.common;

import core.annotations.PageAccessor;
import core.element.YandexElement;
import core.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "Dashboard", url = "account/dashboard")
public final class Dashboard extends AbstractPage {

    @Name("Page Title")
    @Timeout(30)
    @FindBy(xpath = "//h4[text()='My Courses']")
    public YandexElement myCoursesLnk;

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    public Dashboard(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

}
