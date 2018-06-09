package lib.tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    @Test
    public void testsaveFirstArticleToMyList () throws InterruptedException {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();
        navigationUI.openMyList();
        navigationUI.checkFolderIsCreated();
        myListPageObject.openFolderByName(name_of_folder);
        myListPageObject.swipeByArticleToDelete("Java (programming language)");
    }


    @Test
    public void testSaveTwoArticles_ex5_refactored() throws InterruptedException {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListPageObject myListPageObject = new MyListPageObject(driver);
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

}
