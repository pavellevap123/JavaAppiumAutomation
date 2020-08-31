import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/pavelpaddubotski/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't find search input",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language' topic searching by Java",
                15
        );
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Search field wasn't found",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Search field wasn't found",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find X to close page",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the screen",
                5
        );
    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't find search input",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language' topic searching by Java",
                15
        );

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title of the article",
                15
        );

        assertElementHasText(title_element, "Java (programming language)", "Titles aren't equal");
    }

    @Test
    public void testSwipeArticle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Can't find search input",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Can't find 'Object-oriented programming language' topic searching by Java",
                15
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title of the article",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Can't find the end of the article",
                20
        );
    }

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can't find search input",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language' topic searching by Java",
                15
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title of the article",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find element to open article options",
                5
        );

        Thread.sleep(1000);

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can't find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find overlay input field",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can't find overlay input field to enter provided text",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't close article by clicking X button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't redirect to 'My lists' screen",
                5
        );

        Thread.sleep(1000);

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can't find created folder ",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't swipe cell element to delete it"
        );

        waitForElementNotPresent(
                By.xpath("//*[text='Java (programming language)']"),
                "Can't delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        String search_line = "Linkin park discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can't find search input",
                15
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Can't find anything by request" + search_line,
                15
        );

        int amountOfSearchResults = findAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results!",
                amountOfSearchResults > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        String search_line = "zhjxjhafrjfgjrbg";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can't find search input",
                15
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Can't find empty result label by the request " + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );

    }

    @Test
    public void testInputFieldHasRightText()
    {
            WebElement input_field_element = waitForElementPresent(
                    By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                    "Can't find Search Wikipedia input",
                    5
            );

        assertElementHasText(
                input_field_element,
                "Search Wikipedia",
                "Text in provided element isn't 'Search Wikipedia'");
    }

    @Test
    public void testSearchForArticlesAndCancelSearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't find search input",
                5
        );


        for (int i=0; i<3; i++)
        {
            String dynamic_xpath = String.format("//*[@resource-id='org.wikipedia:id/page_list_item_container'][@index='%x']//*[contains(@text, 'Java')]", i);
            int humanReadableIndex = i+1;
            waitForElementPresent(
                    By.xpath(dynamic_xpath),
                    String.format("There is no article #%x of 3 with mention of 'Java' keyword", humanReadableIndex),
                    5
            );
        }
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Search cancel button wasn't found",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_image"),
                "Search empty image placeholder wasn't found",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_message"),
                "Search empty message placeholder wasn't found",
                5
        );
    }

    @Test
    public void testCheckKeywordInSearchResults() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't find search input",
                5
        );

        driver.hideKeyboard();

        for (int i=0; i<6; i++){
            String dynamic_xpath = String.format("//*[@resource-id='org.wikipedia:id/page_list_item_container'][@index='%x']//*[contains(@text, 'Java')]", i);
            int humanReadableIndex = i+1;
            waitForElementPresent(
                    By.xpath(dynamic_xpath),
                    String.format("Article #%x dosn't contain mention of 'Java' word", humanReadableIndex),
                    5
            );
        }
    }

    @Test
    public void testSaveTwoArticlesInOneFolder() throws InterruptedException {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        String first_search = "Apple";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                first_search,
                "Can't find search input",
                5
        );

        String first_search_results_text = "American multinational technology company";
        waitForElementAndClick(
                By.xpath("//*[@text='" + first_search_results_text + "']"),
                "Search result with text '" + first_search_results_text + "' wasn't found",
                15
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title of the article",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find element to open article options",
                5
        );

        waitForOneSecond();

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find option to add an article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can't find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find overlay input field",
                5
        );

        String name_of_folder = "Tech companies";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can't find overlay input field to enter provided text",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't close article by clicking X button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        String second_search = "Google";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                second_search,
                "Can't find search input",
                5
        );

        String second_search_results_text = "American multinational Internet and technology corporation";
        waitForElementAndClick(
                By.xpath("//*[@text='" + second_search_results_text + "']"),
                "Search result with text '" + second_search_results_text + "' wasn't found",
                15
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title of the article",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find element to open article options",
                5
        );

        waitForOneSecond();

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Folder '" + name_of_folder + "' with articles wasn't found",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't close article by clicking X button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't redirect to 'My lists' screen",
                5
        );

        waitForOneSecond();

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can't find created folder with name '" + name_of_folder,
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='" + second_search_results_text + "']"),
                "Can't swipe cell element to delete it"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='" + second_search_results_text + "']"),
                "Article with text '" + second_search_results_text + "' was found",
                5
        );
    }

    @Test
    public void testTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "some error",
                5
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(WebElement element, String expectedText, String error_message)
    {
        String element_text = element.getAttribute("text");
        Assert.assertEquals(error_message, expectedText, element_text);
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) ( size.height * 0.8);
        int end_y = (int) ( size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int findAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amountOfElements = findAmountOfElements(by);
        if (amountOfElements > 0) {
           String defaultMessage = "An element '" + by.toString() + "' supposed to be not present";
           throw new AssertionError(defaultMessage + " " + error_message);
        }
    }

    private void waitForOneSecond() throws InterruptedException {
        Thread.sleep(1000);
    }
}
