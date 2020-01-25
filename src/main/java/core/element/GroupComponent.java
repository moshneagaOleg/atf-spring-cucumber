package core.element;

import core.util.ReflectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static core.factory.ComponentCreator.createContent;

public class GroupComponent<T extends AbstractComponent> extends AbstractComponent {

    private Class<T> type;

    /**
     * Refers to the current instance of the parent class
     *
     * @param browser Browser we use
     * @param name    Element name
     * @param parent  The parent component of the element
     * @param xpath   xpath of the element
     * @param type    Element class
     */
    public GroupComponent(WebDriver browser, String name, Component parent, String xpath, Class<T> type) {
        super(browser, name, parent, xpath);
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    public int getSize() {
        return findAll().size();
    }

    public List<T> getAllElements() throws Exception {
        List<T> all = new ArrayList<>();
        int size = getSize();
        String xpath;
        xpath = "%s[%s]";
        for (int i = 1; i <= size; i++) {
            all.add(createChildInstance(type, String.format(xpath, getXpath(), i)));
        }
        return all;
    }

    public List<T> getAllElementsWithReverseFinding() throws Exception {
        List<T> all = new ArrayList<>();
        int size = getSize();
        String xpath;
        xpath = "[%s]%s";

        for (int i = 1; i <= size; i++) {
            all.add(createChildInstance(type, String.format(xpath, i, getXpath())));
        }

        return all;
    }

    public T getElementByIndex(int index) throws Exception {
        String xpath = String.format("%s[%s]", getXpath(), index);
        return createChildInstance(type, xpath);
    }

    public T getElementByText(String text) throws Exception {
        String childTextXpath = String.format("[text()='%s' or .//*[text()='%s']]", text, text);
        return createChildInstance(type, getXpath() + childTextXpath);
    }

    public T getElementByChildAtribute(String attribute, String value) throws Exception {
        String atributeXpath = String.format(".//*[@%s='%s']", attribute, value);
        return createChildInstance(type, getXpath() + atributeXpath);
    }

    public T getElementByAtribute(String attribute, String value) throws Exception {
        String atributeXpath = String.format("[@%s='%s']", attribute, value);
        return createChildInstance(type, getXpath() + atributeXpath);
    }

    private T createChildInstance(Class<T> type, String xpath) throws Exception {
        try {
            T t;
            if (type.equals(GroupComponent.class)) {
                t = createChildInstance(type, xpath);
            } else {
                t = ReflectionUtils.newInstance(type, this.getBrowser(), this.getName(), this.getParent(), this.getXpath());
            }
            createContent(this.getBrowser(), t, t);
            t.setXpath(xpath);
            return t;
        } catch (Exception e) {
            throw new Exception("Failed to clone object{" + type + "]", e);
        }
    }

    public T getElementByPartialText(String text) throws Exception {
        String childTextXpath = String.format("[contains(text(),'%s') or .//*[contains(text(),'%s')]]", text, text);
        return createChildInstance(type, getXpath() + childTextXpath);
    }

    public WebElement container() {
        return this.find().findElement(By.xpath(".."));
    }


    @Override
    public YandexElement find() {
        return null;
    }

//    @Override
//    public List<YandexElement> findAll() {
//        return null;
//    }

//    /**
//     * Click on current element.
//     */
//    @Override
//    public void click() {
//        (new ClickAction(this)).execute();
//    }

}
