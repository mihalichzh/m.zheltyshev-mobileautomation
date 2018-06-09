package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    public static final String
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER NAME}']",
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    private static String getFolderXpathByName (String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER NAME}", name_of_folder);
    }

    private static String getTitleXpathByName (String title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", title);
    }
    public MyListPageObject (AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName (String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Can't find and open folder " + name_of_folder,
                5
        );
    }

    public void swipeByArticleToDelete (String article_title) {
        this.waitForArticleToApearByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(getTitleXpathByName(article_title)),
                "Can't find saved article!"
        );

        this.waitForArticleToDissapearByTitle(article_title);
    }

    public void waitForArticleToDissapearByTitle (String article_title) {
        this.waitForElementIsNotPresent(
                By.xpath(getFolderXpathByName(article_title)),
                "Saved article still present with title " + article_title,
                5
        );
    }

    public void waitForArticleToApearByTitle (String article_title) {
        this.waitForElementPresent(
                By.xpath(getFolderXpathByName(article_title)),
                "Cannot find saved article by title " + article_title,
                5
        );
    }
}
