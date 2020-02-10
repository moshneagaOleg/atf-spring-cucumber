package io.tpd.springbootcucumber.core.element;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.utils.HtmlElementUtils;

import java.io.File;

public class WebFileInput extends WebTypifiedElement {

    private Logger logger = LoggerFactory.getLogger(WebFileInput.class.getSimpleName());

    public WebFileInput(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void setFileToUpload(String fileName) {
        WebElement fileInputElement = this.getNotProxiedInputElement();
        if (HtmlElementUtils.isOnRemoteWebDriver(fileInputElement)) {
            this.setLocalFileDetector((RemoteWebElement) fileInputElement);
        }
        logger.info("Sending file: {}", fileName);
        String filePath = this.getFilePath(fileName);
        fileInputElement.sendKeys(filePath);
    }

    @Override
    public void submit() {
        this.getWrappedElement().submit();
    }

    private WebElement getNotProxiedInputElement() {
        return this.getWrappedElement().findElement(By.xpath("."));
    }

    private void setLocalFileDetector(RemoteWebElement element) {
        element.setFileDetector(new LocalFileDetector());
    }

    private String getFilePath(String fileName) {
        return HtmlElementUtils.existsInClasspath(fileName) ? this.getPathForResource(fileName) : this.getPathForSystemFile(fileName);
    }

    private String getPathForResource(String fileName) {
        String path = HtmlElementUtils.getResourceFromClasspath(fileName).getPath();
        return StringUtils.isBlank(path) ? path : path.replaceFirst("^/", "");
    }

    private String getPathForSystemFile(String fileName) {
        File file = new File(fileName);
        return file.getPath();
    }

}
