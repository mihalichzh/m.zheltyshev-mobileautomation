package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_ITEM_CONTAINER;

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER NAME}", name_of_folder);
    }

    private static String getTitleXpathByName(String title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", title);
    }

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Can't find and open folder " + name_of_folder,
                10
        );
    }

    public void swipeByArticleToDelete(String article_title) throws InterruptedException {
        this.waitForArticleToApearByTitle(article_title);
        this.swipeElementToLeft(
                getTitleXpathByName(article_title),
                "Can't find saved article with title " +article_title
        );

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(
                    getTitleXpathByName(article_title),
                    "Can't tap on delete article button!"
            );
        }
        Thread.sleep(5000);
        this.waitForArticleToDissapearByTitle(article_title);
    }

    public void checkArticleIsStillPresentInList(int hash_code_of_element) throws InterruptedException {
        List<Integer> hashcodes_of_articles_list = new ArrayList();
        Thread.sleep(10000);
        List<WebElement> articles_list = driver.findElements(By.xpath(ARTICLE_ITEM_CONTAINER));
        for (WebElement element : articles_list) {
            hashcodes_of_articles_list.add(element.hashCode());
            }
            if(Platform.getInstance().isIOS()) {
                assertTrue("Not expected: Second article is not in the list!", hashcodes_of_articles_list.contains(hash_code_of_element));
            } else {
                assertTrue("Not expected: Second article is not in the list!", hashcodes_of_articles_list.contains(hash_code_of_element+3));
            }
        }


    public int getHashCodeOfArticleElement(String article_title) {
        if(Platform.getInstance().isIOS()){
        return this.waitForElementPresent(getTitleXpathByName(article_title)+"/..",
                "Can't find target article with title" + article_title,
                30
        ).hashCode();}
        else {
            return this.waitForElementPresent(getTitleXpathByName(article_title)+"/../../..",
                    "Can't find target article with title" + article_title,
                    30
            ).hashCode();
        }
    }

    public void getHashCodeOfArticleElement_debug(String level) {
            System.out.println(this.waitForElementPresent("xpath://*[@text='BASIC']" + level,
                    "Can't find target article with title" + level,
                    30
            ).hashCode());
        }

    public void waitForArticleToDissapearByTitle(String article_title) {
        this.waitForElementIsNotPresent(
                getTitleXpathByName(article_title),
                "Saved article still present with title " + article_title,
                30
        );
    }

    public void waitForArticleToApearByTitle(String article_title) {
        this.waitForElementPresent(
                getTitleXpathByName(article_title),
                "Cannot find saved article by title " + article_title,
                30
        );
    }

    public void checkTitleinMyListEqualToTitleOnArticlePage(String name) {
        String title_in_list = waitForElementPresent(
                "xpath://*[@text='" + name + "']",
                "Can't find second's article entry in saved articles list!",
                30
        ).getAttribute("text");

/*        waitForElementAndClick(
                "xpath://*[@text='" + name + "']",
                "Can't click on second's article entry in saved articles list!",
                5
        );*/

        waitForElementAndClick(
                "xpath://*android.widget.TextView[@text='" + name + "']",
                "Can't click on second's article entry in saved articles list!",
                30
        );


        //*android.widget.TextView[@text='" + name + "'];

        String title_on_articles_page = waitForElementPresent(
                "id:org.wikipedia:id/view_page_title_text",
                "Can't find " + name + " article's title on the page!",
                30
        ).getAttribute("text");

        assertEquals("Title on the articles list and title on article's page are not equals!",
                title_in_list,
                title_on_articles_page);
    }
}

