package io.tpd.springbootcucumber.bagbasics;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.tpd.springbootcucumber.Config;
import io.tpd.springbootcucumber.ScenarioContext;
import io.tpd.springbootcucumber.app.Goodreads;
import io.tpd.springbootcucumber.core.element.WebButton;
import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.function.Supplier;

import static io.tpd.springbootcucumber.PageKeys.GOODREADS_INIT;
import static io.tpd.springbootcucumber.PageKeys.OPEN_DRIVER;
import static io.tpd.springbootcucumber.core.util.DateUtils.generateRandomInt;
import static io.tpd.springbootcucumber.core.util.JSUtils.scrollTo;
import static io.tpd.springbootcucumber.core.util.WaitUtils.waitUntilCondition;

//@SpringBootTest(classes = SpringBootCucumberApplication.class)
public class StepDefinitions {

    @Autowired
    private Config config;

    @Autowired
    private Environment environment;

    @Autowired
    private ScenarioContext scenarioContext;

    @Autowired
    private Faker faker;

    @And("user creates new account")
    public void userCreatesNewAccount() {
        Goodreads goodreads = (Goodreads) scenarioContext.getData(GOODREADS_INIT);
        goodreads.home().loginForm.name.sendKeys(faker.name().fullName());
        goodreads.home().loginForm.emailInp.sendKeys(faker.dog().name().toLowerCase() + "@gmail.com");
        goodreads.home().loginForm.passwordInp.sendKeys(faker.dog().gender());
        goodreads.home().loginForm.signInBtn.click();
    }

    @And("user selects favorite genres")
    public void userSelectsFavoriteGenres() {
        Goodreads goodreads = (Goodreads) scenarioContext.getData(GOODREADS_INIT);
        WebButton continueButton = goodreads.rateBooks().continueButton;
        continueButton.isNotEnabledAssertion();
        goodreads.rateBooks().favoriteGenres.genreCheckBoxes.forEach(WebTypifiedElement::click);
        continueButton.isEnabledAssertion();
        continueButton.click();
    }

    @And("user rates books that he read")
    public void userRatesBooksThatHeRead() {
        Goodreads goodreads = (Goodreads) scenarioContext.getData(GOODREADS_INIT);
        goodreads.rateBooks().rateBook.books.forEach(e -> e.star.get(generateRandomInt(0, 5)).click());
    }

    @And("user logs in with invalid credentials")
    public void userLogsInWithInvalidCredentials() {
        Goodreads goodreads = (Goodreads) scenarioContext.getData(GOODREADS_INIT);
        goodreads.home().signIn.emailInp.sendKeys(RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@gmail.com");
        goodreads.home().signIn.passwordInp.sendKeys(RandomStringUtils.randomAlphabetic(8).toLowerCase());
        goodreads.home().signIn.signInBtn.click();
    }

    @Then("user verifies error message")
    public void userVerifiesErrorMessage() {
        Goodreads goodreads = (Goodreads) scenarioContext.getData(GOODREADS_INIT);
        goodreads.singIn().errorMessage.checkIfEquals(
                "Sorry, that email or password isn't right. You can reset your password.");
    }

    @And("user finds random books")
    public void userFindsRandomBooks() {
        Goodreads goodreads = (Goodreads) scenarioContext.getData(GOODREADS_INIT);
        goodreads.home().header.searchInput.sendKeys(faker.book().genre());
        goodreads.home().header.searchInput.sendKeys(Keys.ENTER);
    }

    @And("user marks {int} first books")
    public void userMarksFirstBooks(int book) {
        Goodreads goodreads = (Goodreads) scenarioContext.getData(GOODREADS_INIT);
        WebDriver webDriver = (WebDriver) scenarioContext.getData(OPEN_DRIVER);
        int searchSize = goodreads.search().searchTable.getRows().size();
        if (book > searchSize) {
            book = 1;
        }
        // FIXME: 3/17/2020 need elegant solution with list
        for (int i = 0; i < book ; i++) {
                        int finalI = i;
            Supplier<Boolean> wantToReadIsCLicked = () -> {
                WebElement wantToRead = goodreads.search().searchTable.getRows().get(finalI).get(finalI)
                        .findElement(By.xpath("//button[@class='wtrToRead']"));
                scrollTo(webDriver, wantToRead).click();
                return true;
            };
            waitUntilCondition(wantToReadIsCLicked, true, 10);
            int finalI1 = i;
            Supplier<Boolean> dropDownIsCLicked = () -> {
                WebElement dropDown = goodreads.search().searchTable.getRows().get(finalI1).get(0)
                        .findElements(By.xpath("//button[@class='wtrShelfButton']")).get(finalI1);
                scrollTo(webDriver, dropDown).click();
                return true;
            };
            waitUntilCondition(dropDownIsCLicked, true, 10);
            Supplier<Boolean> readIsClicked = () -> {
                WebElement read = goodreads.search().searchTable.getRows().get(finalI1).get(2)
                        .findElement(By.xpath("//button[normalize-space()='Read']"));
                scrollTo(webDriver, read).click();
                return true;
            };
            waitUntilCondition(readIsClicked, true, 10);
            goodreads.search().reviewBox.yourReview.sendKeys(faker.harryPotter().quote());
            goodreads.search().reviewBox.startedAtYear.selectRandom(0);
            goodreads.search().reviewBox.startedAtMonth.selectRandom(0);
            goodreads.search().reviewBox.startedAtDay.selectRandom(0);
            goodreads.search().reviewBox.postButton.click();
        }
    }

    @And("user logs out")
    public void userLogsOut() {
        Goodreads goodreads = (Goodreads) scenarioContext.getData(GOODREADS_INIT);
        goodreads.home().header.profileMenu.click();
        goodreads.home().header.signOut.click();
    }

    @And("user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        Goodreads goodreads = (Goodreads) scenarioContext.getData(GOODREADS_INIT);
        goodreads.home().signIn.emailInp.sendKeys("moshneagaoleg@gmail.com");
        goodreads.home().signIn.passwordInp.sendKeys("Oleg951709");
        goodreads.home().signIn.signInBtn.click();
    }
}
