package com.alperen.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void login(String username, String password) {
        driver.findElement(By.id("nav-link-accountList")).click();

        driver.findElement(By.id("ap_email")).sendKeys(username);

        driver.findElement(By.id("continue")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.id("ap_password")).sendKeys(password);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.id("signInSubmit")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isLogin() {
        try {
            WebElement logoutLink = driver.findElement(By.id("nav-item-signout"));
            if (!logoutLink.isDisplayed()) {
                System.out.println("Logged in successfully!");
                return true;
            } else {
                System.out.println("Login failed!");
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Login failed: " + e);
            return false;
        }
    }

    public void logout() {
        WebElement element = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.xpath("//*[@id='nav-item-signout']/span")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}