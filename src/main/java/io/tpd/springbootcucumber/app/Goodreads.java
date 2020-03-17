package io.tpd.springbootcucumber.app;

import io.tpd.springbootcucumber.core.element.WebTypifiedElement;
import io.tpd.springbootcucumber.core.page.AbstractPage;
import io.tpd.springbootcucumber.pageObject.goodreads.*;
import org.openqa.selenium.WebDriver;

public class Goodreads extends AbstractPage {

    private Home home = new Home(driver);
    private SingIn singIn = new SingIn(driver);
    private FindFriends findFriends = new FindFriends(driver);
    private SetGoal setGoal = new SetGoal(driver);
    private RateBooks rateBooks = new RateBooks(driver);
    private ViewRecommendations viewRecommendations = new ViewRecommendations(driver);
    private Search search = new Search(driver);
    private SignOut signOut = new SignOut(driver);

    public Goodreads(WebDriver driver, String url, String name) {
        super(driver, url, name);
    }

    public static Goodreads initApp(WebDriver driver) {
        return new Goodreads(driver);
    }

    public Goodreads(WebDriver driver) {
        super(driver);
    }

    public Home home() {
        return home.initOnDemand();
    }
    public SingIn singIn() {
        return singIn.initOnDemand();
    }
    public FindFriends findFriends() {
        return findFriends.initOnDemand();
    }
    public SetGoal setGoal() {
        return setGoal.initOnDemand();
    }
    public RateBooks rateBooks() {
        return rateBooks.initOnDemand();
    }
    public ViewRecommendations viewRecommendations() {
        return viewRecommendations.initOnDemand();
    }
    public Search search() {
        return search.initOnDemand();
    }
    public SignOut signOut() {
        return signOut.initOnDemand();
    }

    @Override
    public WebTypifiedElement getPageTitle() {
        return null;
    }

}
