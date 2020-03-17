package io.tpd.springbootcucumber.core.element;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.*;

@Slf4j
public class WebFileInput extends WebTypifiedElement {

    public WebFileInput(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void setFileToUpload(final String fileName) {
        WebElement fileInputElement = getNotProxiedInputElement();
        if (isOnRemoteWebDriver(fileInputElement)) {
            setLocalFileDetector((RemoteWebElement) fileInputElement);
        }
        String filePath = getFilePath(fileName);
        log.info("Sending file: {}", fileName);
        fileInputElement.sendKeys(filePath);
    }

    private String getFilePath(String fileName) {
        return existsInClasspath(fileName) ? this.getPathForResource(fileName) : this.getPathForSystemFile(fileName);
    }

    private String getPathForResource(final String fileName) {
        // FIXME: 3/16/2020 check if jenkins build or local machine and remove first slash
        return getResourceFromClasspath(fileName).getPath();
    }

    private String getPathForSystemFile(final String fileName) {
        File file = new File(fileName);
        return file.getPath();
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

}
