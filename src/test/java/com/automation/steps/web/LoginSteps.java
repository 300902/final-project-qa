package com.automation.steps.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {

    private WebDriver driver;
    private final String baseUrl = "https://www.saucedemo.com/";

    @Before("@web")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @After("@web")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the SauceDemo login page")
    public void openLoginPage() {
        driver.get(baseUrl);
    }

    @When("I enter username {string}")
    public void enterUsername(String user) {
        WebElement el = driver.findElement(By.id("user-name"));
        el.clear();
        el.sendKeys(user);
    }

    @When("I enter password {string}")
    public void enterPassword(String pwd) {
        WebElement el = driver.findElement(By.id("password"));
        el.clear();
        el.sendKeys(pwd);
    }

    @When("I click the login button")
    public void clickLogin() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be on the Products page")
    public void verifyOnProductsPage() {
        assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }

    @Then("I should see a title {string}")
    public void verifyTitle(String expected) {
        String actual = driver.findElement(By.className("title")).getText();
        assertEquals(expected, actual);
    }

    @Then("I should see login error message")
    public void verifyLoginError() {
        WebElement err = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(err.isDisplayed());
    }

    @When("I clear username field")
    public void clearUsername() {
        driver.findElement(By.id("user-name")).clear();
    }

    @When("I clear password field")
    public void clearPassword() {
        driver.findElement(By.id("password")).clear();
    }

    @Then("I should see validation error message")
    public void verifyValidationError() {
        WebElement err = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(err.isDisplayed());
    }

    @Then("I should still be on the login page")
    public void verifyStillOnLoginPage() {
        // Ensure URL is still login page
        assertTrue(driver.getCurrentUrl().equals(baseUrl));
    }
}
