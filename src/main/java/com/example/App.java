package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class App {

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();

        // Enable headless only in Jenkins
        boolean isJenkins = System.getenv("JENKINS_HOME") != null;

        if (isJenkins) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);

        // Maximize only locally
        if (!isJenkins) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));

        try {

            // ---------------- TAB 1 ----------------
            driver.get("https://www.saucedemo.com/");

            driver.findElement(By.id("user-name"))
                  .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                  .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                  .click();

            System.out.println("SauceDemo login success");

            Thread.sleep(3000);

            // ---------------- TAB 2 ----------------
            driver.switchTo().newWindow(WindowType.TAB);

            driver.get(
                "https://practicetestautomation.com/practice-test-login/"
            );

            driver.findElement(By.id("username"))
                  .sendKeys("student");

            driver.findElement(By.id("password"))
                  .sendKeys("Password123");

            driver.findElement(By.id("submit"))
                  .click();

            System.out.println("Practice Test login success");

            Thread.sleep(3000);

            // ---------------- TAB 3 ----------------
            driver.switchTo().newWindow(WindowType.TAB);

            driver.get("https://automationexercise.com/products");

            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement product =
                driver.findElement(
                    By.cssSelector("[data-product-id='1']")
                );

            js.executeScript(
                "arguments[0].scrollIntoView(true);",
                product
            );

            Thread.sleep(1000);

            // safer click
            js.executeScript(
                "arguments[0].click();",
                product
            );

            System.out.println("Product added to cart");

            // Keep browser open locally
            Thread.sleep(10000);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            // Close browser only in Jenkins
            if (isJenkins) {
                driver.quit();
            }
        }
    }
}
