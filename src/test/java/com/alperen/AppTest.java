package com.alperen;

import com.alperen.pages.SearchResultsPage;
import com.alperen.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.alperen.pages.HomePage;
import com.alperen.pages.LoginPage;

public class AppTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchResultsPage searchResultsPage;
    private ShoppingCartPage cartPage;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver and initialize page objects
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver(co);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        cartPage = new ShoppingCartPage(driver);
    }

    @Test
    public void testAmazon() {
        homePage.openAmazonWebsite();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        homePage.acceptCookies();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page not opened");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        loginPage.login("your-email","your-password");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(loginPage.isLogin(), "Could not login");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        homePage.selectCategory();
        Assert.assertTrue(homePage.isCategorySelected("Bebek"), "Category not selected");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        homePage.searchForProduct("Sleepy");
        Assert.assertTrue(homePage.isSearchPerformed("Sleepy"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        searchResultsPage.goToSearchResultsPage();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int currentPageNumber = searchResultsPage.getCurrentPageNumber();
        Assert.assertEquals(currentPageNumber,3,"Expected to be on page 3, but actually on page " + currentPageNumber);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        searchResultsPage.addFirstTwoProductsToCart();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        cartPage.goToShoppingCart();
        Assert.assertTrue(searchResultsPage.isAddFirstTwoProducts(2),"Products could not be added");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(cartPage.isCorrectCalculation(), "Incorrect calculation in the shopping cart");


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        loginPage.logout();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown() {
        // Clean up resources
        driver.quit();
    }
}