package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    private static final String name_of_folder2 = "Tech companies";

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyListWithOnboarding(name_of_folder);
            articlePageObject.closeArticle();
        } else {
            articlePageObject.addArticleToMySaved();
            articlePageObject.closeArticle();
            searchPageObject.clickCancelSearch();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
        } else {
            myListsPageObject.closeLoginToSyncPopup();
        }
        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticles() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Apple");
        searchPageObject.clickByArticleWithSubstring("Apple Inc.");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyListWithOnboarding(name_of_folder2);
            articlePageObject.closeArticle();
            searchPageObject.initSearchInput();
        } else {
            articlePageObject.addArticleToMySaved();
            articlePageObject.closeArticle();
            searchPageObject.clickClearButtonInSearchField();
        }

        searchPageObject.typeSearchLine("Google");
        searchPageObject.clickByArticleWithSubstring("American");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyListWithoutOnboarding(name_of_folder2);
        } else {
            articlePageObject.addArticleToMySaved();
            articlePageObject.closeArticle();
            searchPageObject.clickCancelSearch();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder2);
        } else {
            myListsPageObject.closeLoginToSyncPopup();
        }
        myListsPageObject.swipeByArticleToDelete("Apple Inc.");
    }
}
