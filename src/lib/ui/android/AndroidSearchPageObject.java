package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_RESULT_BY_INDEX_WITH_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container'][@index='{INDEX}']//*[contains(@text, '{SUBSTRING}')]";
        SEARCH_EMPTY_SEARCH_TEXT = "id:org.wikipedia:id/search_empty_message";
        SEARCH_EMPTY_SEARCH_IMAGE = "id:org.wikipedia:id/search_empty_image";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}'][@index='0']/../*[@text='{DESCRIPTION}'][@index='1']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
