package com.example.demo;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void setup() {

        // Für firefox setup
        WebDriverManager.firefoxdriver().setup();

        // Neue Driver initialisieren
        driver = new FirefoxDriver();

        // Driver greift auf Vite-Standard URL 
        driver.get("http://localhost:5173");
    }

    @Test
    public void ulElementIsCreated() {
        // Find Input field and type "Selenium, Testing" then click on submit button
        driver.findElement(By.id("taskdescription")).sendKeys("Selenium, Testing");
        driver.findElement(By.className("submit-button")).click();

        // Simulate a lag
        WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement ul = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("todo-list")));

        // Check if any List-Element was created

        boolean isFound = ul.findElements(By.tagName("li")).stream().anyMatch(
            li -> li.getText().contains("Selenium, Testing")
        );

        assertTrue(isFound, "Submitted Todo didn't show up in a List-Element");

    }

    @AfterEach
    public void stopTestPhase() {
        if( driver != null) {
            driver.quit();
        }
    }
    
}
