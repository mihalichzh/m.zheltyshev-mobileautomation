package lib.tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private static String name_of_folder = "Learning programming";

    @Test
    public void testsaveFirstArticleToMyList () throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListPageObject myListPageObject = MyListsPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
        articlePageObject.addArticleToMyList(name_of_folder);}
        else {
            articlePageObject.addArticleToMySaved();
        }

        articlePageObject.closeArticle();
        navigationUI.openMyList();
        Thread.sleep(5000);
        navigationUI.checkFolderIsCreated();

        if(Platform.getInstance().isAndroid()){
        myListPageObject.openFolderByName(name_of_folder);}

        myListPageObject.swipeByArticleToDelete("Java (programming language)");
    }


    @Test
    public void testSaveTwoArticles_ex5_refactored() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListPageObject myListPageObject = MyListsPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("C++");
        searchPageObject.clickByArticleWithSubstring("General purpose high-level programming language");
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToMyExistingList("Learning programming");
        articlePageObject.closeArticle();
        navigationUI.openMyList();
        navigationUI.checkFolderIsCreated();
        myListPageObject.openFolderByName(name_of_folder);
        myListPageObject.swipeByArticleToDelete("Java (programming language)");
        myListPageObject.checkTitleinMyListEqualToTitleOnArticlePage("C++");

    }

    @Test
    public void testSaveTwoArticles_ex10 () throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListPageObject myListPageObject = MyListsPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        //articlePageObject.waitForTitleElementByString("id:Java (programming language)");
        String first_article_title = articlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);}
        else {
            articlePageObject.addArticleToMySaved();
        }

        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        if(Platform.getInstance().isIOS()) {
            searchPageObject.clickCLearSearchInputButton();
        }

        searchPageObject.typeSearchLine("Test");
        searchPageObject.clickByArticleWithSubstring("Wikimedia disambiguation page");
        Thread.sleep(10000);
        //articlePageObject.waitForTitleElement();
        //articlePageObject.waitForTitleElementByString("C++");
        //String second_article_title = articlePageObject.getArticleTitleByString("id:iOS");

        if(Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyExistingList("Learning programming");}
        else {
            articlePageObject.addArticleToMySaved();
        }

        articlePageObject.closeArticle();
        navigationUI.openMyList();
        Thread.sleep(5000);
        //navigationUI.checkFolderIsCreated();

        if(Platform.getInstance().isAndroid()){
            myListPageObject.openFolderByName("Learning programming");}

        myListPageObject.swipeByArticleToDelete("Java (programming language)");
        myListPageObject.checkTitleinMyListEqualToTitleOnArticlePage("Test");
    }


}
