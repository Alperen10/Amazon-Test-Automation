package com.alperen.pages;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SearchResultsPage {
    private WebDriver driver;
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }
    public void goToSearchResultsPage() {
        driver.findElement(By.xpath("//span//span//a[2]")).click();
    }
    public void addFirstTwoProductsToCart() {
        List<WebElement> productLinks = driver.findElements(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']"));

        String mainWindowHandle = driver.getWindowHandle();

        for (int i = 0; i < Math.min(2, productLinks.size()); i++) {
            WebElement productLink = productLinks.get(i);
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL).click(productLink).keyUp(Keys.CONTROL).perform();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
                    addToCartButton.click();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Add to Cart button not found in window: " + windowHandle);
                }

                driver.close();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        driver.switchTo().window(mainWindowHandle);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isAddFirstTwoProducts(int cartCount) {
        return cartCount == getCartItemCount();
    }
    private int getCartItemCount() {
        WebElement cartCountElement = driver.findElement(By.id("nav-cart-count"));
        String cartCountText = cartCountElement.getText();
        return Integer.parseInt(cartCountText);
    }
    public int getCurrentPageNumber() {
        WebElement currentPage = driver.findElement(By.xpath("//span[@class='s-pagination-item s-pagination-selected']"));
        int actualPageNumber = Integer.parseInt(currentPage.getText());
        return actualPageNumber;
    }
}
