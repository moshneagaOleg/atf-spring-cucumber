package pageObject.wgu.helpCenter;

import core.annotations.PageAccessor;
import core.assertation.VTFAssert;
import core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import pageObject.abstractPageObject.helpCenter.AbstractETextBook;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

@Timeout(30)
@PageAccessor(name = "eTextbooks", url = "help-center/etextbooks")
public class ETextBook extends AbstractETextBook {

    public ETextBook(WebDriver driver) {
        super(driver);
    }

    public ETextBook(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    @Override
    public void validatePageTitle() {
        VTFAssert.assertThat("Validate page title",
                WaitUtils.waitUntilCondition(() -> gnrcPageTitle.resolveLocator("Help Center | eTextbooks").isDisplayed(),
                        true, 10));
    }
}
