package io.tpd.springbootcucumber.bagcommons;

public class CommonStepDefinitions {

    //    private UIHook uiHook = new UIHook();
//    private WebDriver webDriver;
//
//    @Given("user open page {string}")
//    public void userOpenPage(String pageName) {
//        AbstractPage page = new PageCreator(webDriver).getPage(pageName);
//        page.open();
//    }
//
//    @When("user is on the {string} page")
//    public void userIsOnThePage(String pageName) {
//        AbstractPage page = new PageCreator(webDriver).getPage(pageName);
//        LoggingAssert.assertTrue(String.format("User is on the '%s' page", pageName),
//                WaitUtils.waitUntilCondition(page::isCurrentPage, true, 30));
//    }
//
//    @Then("user verify {string} message")
//    public void userVerifyStringMessage(String message) {
////        LoggingAssert.assertTrue(String.format("Message is present '%s'", message),
////                waitForMessage(() -> wgu.successMsgs, message, 15));
//    }
//
//    @When("user login on the page")
//    public void userLoginOnThePage() {
////        uiHook.getWgu().login().emailInp.sendKeys("auto+wgu-api-45778982010878@straighterline.com");
////        uiHook.getWgu().login().passwordInp.sendKeys("Secretpwd@1");
////        uiHook.getWgu().login().continueBtn.click();
//    }
//
//    @And("user logOut")
//    public void userLogOut() {
////        uiHook.getWgu().waitForClickable(uiHook.getWgu().mainMenuAuth().photoBtn, 10).click();
////        uiHook.getWgu().waitForClickable(uiHook.getWgu().mainMenuAuth().logoutLnk, 10).click();
//    }
//
//    @Before
//    public void openBrowser() {
//        webDriver = DriverFactory.openBrowser();
//        System.out.println();
////        wgu = WGU.initApp(webDriver);
////        webDriver.get(config.getUrl());
//    }

//    @cucumber.api.java.en.Given("^the bag is empty$")
//    public void the_bag_is_empty() {
//        System.out.println("HELLLLLLLLLLLLLOOOOOOOO");
//    }

}
