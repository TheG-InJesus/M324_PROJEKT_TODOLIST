package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("http://localhost:5173");
        driver.manage().window().maximize();
    }


    @Test
    public void ulElementAppearsAfterSubmitting() {
        driver.findElement(By.id("taskdescription")).sendKeys("Selenium, Bitches");
        driver.findElement(By.className("submit-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement ul = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("todo-list")));

        boolean isFound = ul.findElements(By.tagName("li")).stream()
            .anyMatch(li -> li.getText().contains("Selenium, Bitches"));

        assertTrue(isFound, "Submitted task was not found in the list.");

    }

    @AfterEach
    public void stopTest() {
        if (driver != null) {
            driver.quit();
        }
    }

}
