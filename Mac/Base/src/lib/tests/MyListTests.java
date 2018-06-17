package lib.tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyListTests extends CoreTestCase {

    private static String name_of_folder = "Learning programming";

    @Test
    public void testsaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListPageObject myListPageObject = MyListsPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticleToMySaved();
        }

        articlePageObject.closeArticle();
        navigationUI.openMyList();

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
        }

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
    public void testSaveTwoArticles_ex10() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListPageObject myListPageObject = MyListsPageObjectFactory.get(driver);

        //Делаем тап по поисковой строке
        searchPageObject.initSearchInput();

        //Вбиваем запрос
        searchPageObject.typeSearchLine("Java");

        //Тапаем по результату поиска с соответствующим подзаголовком
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        //Ждем загрузки статьи по появлению тайтла
        articlePageObject.waitForTitleElement();

        //Ветка добавления открытой статьи в избранное для разных платформ
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticleToMySaved();
        }

        //После добавления закрываем статью
        articlePageObject.closeArticle();

        //Тапаем по поисковое строке
        searchPageObject.initSearchInput();

        //Для iOS перед вводом нового запроса тапаем по кнопке очистки поля от предыдущего запроса
        if (Platform.getInstance().isIOS()) {
            searchPageObject.clickCLearSearchInputButton();
        }

        //Вбиваем запрос
        searchPageObject.typeSearchLine("BASIC");

        //Тапаем по результату поиска с соответствующим подзаголовком
        searchPageObject.clickByArticleWithSubstring("Programming language");

        //
        articlePageObject.waitForTitleElement();

        //Ветка добавления открытой статьи в избранное для разных платформ
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyExistingList(name_of_folder);
        } else {
            articlePageObject.addArticleToMySaved();
        }

        //После добавления закрываем статью
        articlePageObject.closeArticle();

        //Открываем список избранных статей
        navigationUI.openMyList();

        /*Реализовано две разные проверки для iOS (по наличию элемента статьи в списке после удаления)
        и Android (по одной из строк в содержании статьи)*/
        if (Platform.getInstance().isAndroid()) {
            //Для Android предварительно заходим в папку с соответствующим названием
            myListPageObject.openFolderByName(name_of_folder);

            //Удаляем статью с соответствующим заголовком
            myListPageObject.swipeByArticleToDelete("Java (programming language)");

            //Проверяем оставшуюся статью в списке по одной из строк в содержании
            myListPageObject.checkArticleIsStillPresentByContentsItem("BASIC", "IBM PC and compatibles");
        } else {
            //В случае iOS запоминаем элемент той статьи, которую не будем удалять (чтобы потом проверить, осталась ли она)
            WebElement article_to_stay_element = myListPageObject.getArticleElementByName("BASIC");

            //Удаляем статью с соответствующим заголовком ( - НЕ РАБОТАЕТ ПОЛНОСТЬЮ В IOS)
            myListPageObject.swipeByArticleToDelete("Java (programming language)");

            //Для iOS в данном методе я сначала получаю все оставшиеся после удаления элементы-контейнеры для статей, заполняю ими ArrayList и ищу среди этого списка элемент статьи, которая должна была остаться
            myListPageObject.checkArticleElementIsStillPresentInList(article_to_stay_element);
        }
    }
}

