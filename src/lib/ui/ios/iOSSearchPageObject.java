package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_BY_INDEX_WITH_SUBSTRING_TPL = "xpath://XCUIElementTypeCell[{INDEX}]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]/../XCUIElementTypeStaticText[contains(@name,'{DESCRIPTION}')]";
    }

    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
