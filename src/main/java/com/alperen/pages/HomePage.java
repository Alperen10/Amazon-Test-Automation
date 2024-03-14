package com.alperen.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    private WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void openAmazonWebsite() {
        driver.get("https://www.amazon.com.tr/");
    }
    public void acceptCookies() {
        driver.findElement(By.id("sp-cc-accept")).click();
    }
    public void selectCategory() {
        WebElement dropdown = driver.findElement(By.id("searchDropdownBox"));
        Select select = new Select(dropdown);
        select.selectByValue("search-alias=baby");
        select.selectByVisibleText("Bebek");
    }
    public boolean isHomePageOpened() {
        return driver.findElement(By.id("nav-logo-sprites")).isDisplayed();
    }
    public void searchForProduct(String productName) {
        WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys(productName);
        searchField.submit();
    }
    public boolean isSearchPerformed(String productName) {
        return driver.findElement(By.linkText(productName)).isDisplayed();
    }
    public boolean isCategorySelected(String category) {
        WebElement selectedCategory = driver.findElement(By.id("nav-search-label-id"));
        return selectedCategory.getText().equalsIgnoreCase(category);

    }
}