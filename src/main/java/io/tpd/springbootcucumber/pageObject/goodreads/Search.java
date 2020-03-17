package io.tpd.springbootcucumber.pageObject.goodreads;

import io.tpd.springbootcucumber.core.annotations.PageAccessor;
import io.tpd.springbootcucumber.core.element.WebButton;
import io.tpd.springbootcucumber.core.element.WebTable;
import io.tpd.springbootcucumber.core.element.WebTextBlock;
import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import io.tpd.springbootcucumber.pageObject.compoment.ReviewBox;
import io.tpd.springbootcucumber.pageObject.compoment.SearchList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;

import static io.tpd.springbootcucumber.Config.BIG_WAIT_TIMEOUT;

@Timeout(BIG_WAIT_TIMEOUT)
@PageAccessor(name = "Search", url = "search")
public class Search extends AbstractPage {

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//h1[normalize-space()='Search']")
    public WebTextBlock pageTitle;

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//table[@class='tableList']")
    public WebTable searchTable;

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//tr[%s]//button[contains(text(), 'Want to Read')]")
    public WebButton wantToRead;

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//tr[%s]//button[@class='wtrShelfButton']")
    public WebButton dropDown;

    @Timeout(BIG_WAIT_TIMEOUT)
    @FindBy(xpath = "//tr[%s]//button[@class='wtrExclusiveShelf' and normalize-space()='Read']")
    public WebButton read;

    public SearchList searchList;
    public ReviewBox reviewBox;

    public Search(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public Search(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebTypifiedElement getPageTitle() {
        return pageTitle;
    }
}
