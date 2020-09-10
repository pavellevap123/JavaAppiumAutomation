package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            NAME_OF_FOLDER_TPL = "//*[@text='{SUBSTRING}']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getNameOfFolderXpath(String substring)
    {
        return NAME_OF_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Can't find title of the article", 15);
    }

    public String getArticleTitle()
    {
        return waitForTitleElement().getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Can't find the end of article", 20);
    }

    public void addArticleToMyListWithOnboarding(String name_of_folder) throws InterruptedException {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can't find element to open article options",
                5
        );

        this.waitForOneSecond();

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can't find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Can't find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Can't find overlay input field",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Can't find overlay input field to enter provided text",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Can't press OK button",
                5
        );
    }

    public void addArticleToMyListWithoutOnboarding(String name_of_folder) throws InterruptedException
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can't find element to open article options",
                5
        );

        this.waitForOneSecond();


        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can't find option to add article to reading list",
                5
        );

        String xpath_with_name_of_folder = getNameOfFolderXpath(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(xpath_with_name_of_folder),
                "Folder '" + name_of_folder + "' with articles wasn't found",
                15
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Can't close article by clicking X button",
                5
        );
    }

    public void assertTitlePresent()
    {
        this.waitForTitleElement();
        this.assertElementPresent(By.id(TITLE), "Title wasn't found on the page");
    }
}
