package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        String search_line = "Linkin park discography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        String search_line = "fdjhvjkhdbfv";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

//    @Test
//    public void testInputFieldHasRightText()
//    {
//        MainPageObject mainPageObject = new MainPageObject(driver);
//
//        WebElement input_field_element = mainPageObject.waitForElementPresent(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "Can't find Search Wikipedia input",
//                5
//        );
//
//        mainPageObject.assertElementHasText(
//                input_field_element,
//                "Search Wikipedia",
//                "Text in provided element isn't 'Search Wikipedia'");
//    }

    @Test
    public void testSearchForArticlesAndCancelSearch()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.assureSearchResultArticlesIncludeKeyword(3, "Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForEmptySearchImage();
        searchPageObject.waitForEmptySearchLabel();
    }

    @Test
    public void testCheckKeywordInSearchResults() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        this.hideKeyboard();
        searchPageObject.assureSearchResultArticlesIncludeKeyword(6, "Java");
    }

    @Test
    public void testSearchForArticlesWithTitleAndDescription()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        this.hideKeyboard();
        searchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
        searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        searchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
    }
}
