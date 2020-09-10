package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            SEARCH_RESULT_BY_INDEX_WITH_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container'][@index='{INDEX}']//*[contains(@text, '{SUBSTRING}')]",
            SEARCH_EMPTY_SEARCH_TEXT = "org.wikipedia:id/search_empty_message",
            SEARCH_EMPTY_SEARCH_IMAGE = "org.wikipedia:id/search_empty_image";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementWithIndex(int index, String substring)
    {
        String xpath_with_replaced_substring = SEARCH_RESULT_BY_INDEX_WITH_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
        return xpath_with_replaced_substring.replace("{INDEX}", String.valueOf(index));
    }
    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Can't find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Can't find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Can't find search cancel button", 5);
    }
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Can't find and click  search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Can't find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Can't find search result with substring "+ substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Can't find and click search result with substring "+ substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Can't find anything by request",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Can't find empty result component", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed to find no any results");
    }

    public void assureSearchResultArticlesIncludeKeyword(int amount_of_articles, String keyword)
    {
        for (int i=0; i<amount_of_articles; i++)
        {
            String dynamic_xpath = getResultSearchElementWithIndex(i, keyword);
            int humanReadableIndex = i+1;
            this.waitForElementPresent(
                    By.xpath(dynamic_xpath),
                    String.format("There is no article #%x of %x with mention of '%s' keyword", humanReadableIndex, amount_of_articles, keyword),
                    5
            );
        }
    }

    public void waitForEmptySearchImage()
    {
        this.waitForElementPresent(
                By.id(SEARCH_EMPTY_SEARCH_IMAGE),
                "Search empty message placeholder wasn't found",
                5
        );
    }

    public void waitForEmptySearchLabel()
    {
        this.waitForElementPresent(
                By.id(SEARCH_EMPTY_SEARCH_TEXT),
                "Search empty image placeholder wasn't found",
                5
        );
    }
}
