package io.tpd.springbootcucumber.hook;

import core.app.CSU;
import core.app.FTK;
import core.app.HRZ;
import core.app.WGU;
import core.app.abstractApps.AbstractStudentPortal;
import core.driver.DriverFactory;
import core.logger.TestLogHelper;
import io.cucumber.core.api.Scenario;
import io.cucumber.core.event.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.tpd.springbootcucumber.Config;
import io.tpd.springbootcucumber.PageKeys;
import io.tpd.springbootcucumber.ScenarioContext;
import lombok.Getter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

@Getter
public class UIHook {

    @Autowired
    private Config config;

    @Autowired
    private Environment environment;

    @Autowired()
    private ScenarioContext scenarioContext;

    private Logger logger = LoggerFactory.getLogger(UIHook.class);
    private WebDriver webDriver;
    public static final String SCREENSHOT_PATH = "target/logs/screenshots/";

    @Before(order = -5)
    public void loggerConfiguration(Scenario scenario) {
        logger.debug("Invoke before hook [order=1]");
        Config.SCENARIO_NAME = scenario.getName();
        Config.TENANT = Arrays.toString(environment.getActiveProfiles())
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");
        String scenarioNameFinal = scenario.getName()
                .replaceAll("\\s+", "-")
                .concat("-" + LocalTime.now().toSecondOfDay());
        TestLogHelper.startTestLogging(scenarioNameFinal);
        logger.info("Current testName value is : '{}'", TestLogHelper.getCurrentLogName());
    }

    @Before
    public void openBrowser(Scenario scenario) {
        webDriver = DriverFactory.openBrowser();
        scenarioContext.save(PageKeys.OPEN_DRIVER, webDriver);
        switch (Config.TENANT) {
            case "wgu":
                scenarioContext.save(PageKeys.WGU_INIT, WGU.initApp(webDriver));
            case "csu":
                scenarioContext.save(PageKeys.CSU_INIT, CSU.initApp(webDriver));
            case "ftk":
                scenarioContext.save(PageKeys.WGU_INIT, FTK.initApp(webDriver));
            case "hrz":
                scenarioContext.save(PageKeys.WGU_INIT, HRZ.initApp(webDriver));
        }
        scenarioContext.save(PageKeys.STUDENT_PORTAL_INIT, AbstractStudentPortal.initApp(webDriver));
    }

    @After
    public void closeBrowser(Scenario scenario) throws IOException {
        if (scenario.getStatus().is(Status.FAILED)) {
            final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            // embed it in the report.

            scenario.embed(takeScreenshot(webDriver), "image/png");
//            String imageName = scenario.getName().concat("_" + scenario.getStatus()).concat(String.valueOf(LocalDateTime.now().getNano()));
//            Shutterbug.shootPage(webDriver)
//                    .withName(imageName)
//                    .save(SCREENSHOT_PATH);
//            Path path = Paths.get(SCREENSHOT_PATH.concat(imageName).concat(".png"));
//            byte[] screenshot = Files.readAllBytes(path);
//            scenario.embed(screenshot, "image/png");
            logger.info(String.format("Take a screenshot %s", screenshot));
        }

        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
            logger.info("Browser was closed");
        }
    }

    public byte[] takeScreenshot(WebDriver webDriver) throws IOException {
        int pixelRatio = 1;
        if (webDriver.toString().contains("MAC")) {
            pixelRatio = 2;
        }

        final Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies
                .viewportRetina(200, 0,0,pixelRatio))
                .takeScreenshot(webDriver);
        final BufferedImage image = screenshot.getImage();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        ImageIO.write(image,"PNG",new File("target/cucumber-reports/"
                + dateFormat.format(date) + ".png"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image,"png",outputStream);

        return outputStream.toByteArray();
    }

}
