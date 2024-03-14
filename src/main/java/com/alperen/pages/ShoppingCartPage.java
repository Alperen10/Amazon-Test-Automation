package com.alperen.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.util.List;

public class ShoppingCartPage {
    private WebDriver driver;
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }
    public void goToShoppingCart() {
        driver.findElement(By.id("nav-cart")).click();
    }
    public boolean isCorrectCalculation() {
        List<WebElement> productPrices = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']"));
        WebElement totalPriceElement = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']"));

        double totalPrice = 0.0;

        for (WebElement productPrice : productPrices) {
            String priceText = productPrice.getText().replaceAll("[^\\d.]+", "");
            double price = Double.parseDouble(priceText);
            totalPrice += price;
        }

        // Extracting text from totalPriceElement
        String totalPriceText = totalPriceElement.getText().replaceAll("[^\\d.]+", "");
        double totalPriceFromElement = Double.parseDouble(totalPriceText);

        // Comparing calculated total price with the price from the element
        boolean isCorrect = totalPrice == totalPriceFromElement;

        System.out.println("Total price: " + totalPrice);
        System.out.println("Total price from element: " + totalPriceFromElement);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return isCorrect;

    }
}