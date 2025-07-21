package com.automation.steps.web;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckoutSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "https://www.saucedemo.com/";
    private String username;
    private String password;
    private String itemName;

    @Before("@web")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions()
            .addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After("@web")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I have valid username {string} and password {string}")
    public void setCredentials(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    @When("I login with valid credentials")
    public void loginWithValidCredentials() {
        // Navigate to login page first
        driver.get(baseUrl);
        
        WebElement userInput = driver.findElement(By.id("user-name"));
        userInput.clear();
        userInput.sendKeys(username);
        WebElement passInput = driver.findElement(By.id("password"));
        passInput.clear();
        passInput.sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @When("I open the menu")
    public void openMenu() {
        // Click the burger menu button
        driver.findElement(By.id("react-burger-menu-btn")).click();
        
        // Wait for the menu container to be visible and for the transition to complete
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));
        shortWait.until(ExpectedConditions.attributeContains(
            By.className("bm-menu-wrap"), "aria-hidden", "false"
        ));
    }

    @Then("the Logout link should be visible")
    public void verifyLogoutLink() {
        // Wait until menu is open and logout link is visible
        WebElement logoutLink = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link"))
        );
        assertTrue(logoutLink.isDisplayed());
    }

    @Then("I should see the page title {string}")
    public void verifyTitle(String expectedTitle) {
        WebElement title = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("title"))
        );
        assertEquals(expectedTitle, title.getText());
    }

    @When("I navigate to the first product detail page")
    public void goToFirstProductDetail() {
        // Wait for products to load and get the first item
        WebElement firstItem = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector(".inventory_item_name")
            )
        );
        itemName = firstItem.getText();
        
        // Click the item name
        firstItem.click();
        
        // Wait for detail page to load using multiple possible selectors
        wait.until(driver -> {
            try {
                WebElement container = driver.findElement(By.className("inventory_details_container"));
                if (container.isDisplayed()) return container;
            } catch (Exception e) {}
            
            try {
                WebElement details = driver.findElement(By.cssSelector("[data-test='inventory_details']"));
                if (details.isDisplayed()) return details;
            } catch (Exception e) {}
            
            return null;
        });
    }

    @Then("I should see the product name on detail page")
    public void verifyProductNameOnDetail() {
        // Wait for the detail page to load with multiple possible selectors
        WebElement detailName = wait.until(driver -> {
            try {
                WebElement element = driver.findElement(By.className("inventory_details_name"));
                if (element.isDisplayed()) return element;
            } catch (Exception e) {}
            
            try {
                WebElement element = driver.findElement(By.cssSelector(".inventory_details_container .inventory_item_name"));
                if (element.isDisplayed()) return element;
            } catch (Exception e) {}
            
            return null;
        });
        
        // Verify the product name matches
        assertTrue(detailName != null, "Product name element not found on detail page");
        assertEquals(itemName, detailName.getText());
    }

    @When("I add the product to the cart")
    public void addProductToCart() {
        driver.findElement(By.cssSelector("button.btn_primary.btn_inventory")).click();
    }

    @Then("I should see a {string} button for that product")
    public void verifyButtonText(String expectedButton) {
        WebElement btn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("button.btn_secondary.btn_inventory")
            )
        );
        assertEquals(expectedButton, btn.getText());
    }

    @When("I go to the cart page")
    public void goToCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    @Then("I should see the product {string} in the cart")
    public void verifyProductInCart(String expectedProduct) {
        WebElement cartItem = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name"))
        );
        assertEquals(expectedProduct, cartItem.getText());
    }

    @When("I click the checkout button")
    public void clickCheckout() {
        driver.findElement(By.id("checkout")).click();
    }

    @Then("I should be on the checkout information page")
    public void verifyCheckoutPage() {
        assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
    }

    @When("I enter first name {string} and last name {string} and postal code {string}")
    public void fillCheckoutInformation(String first, String last, String postal) {
        driver.findElement(By.id("first-name")).sendKeys(first);
        driver.findElement(By.id("last-name")).sendKeys(last);
        driver.findElement(By.id("postal-code")).sendKeys(postal);
    }

    @When("I continue to the overview page")
    public void continueToOverview() {
        driver.findElement(By.id("continue")).click();
    }

    @Then("I should be on the checkout overview page")
    public void verifyOverviewPage() {
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"));
    }

    @Then("I should see the product {string} in overview")
    public void verifyProductInOverview(String expectedProduct) {
        WebElement overviewItem = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name"))
        );
        assertEquals(expectedProduct, overviewItem.getText());
    }

    @When("I logout from the application")
    public void logout() {
        // Click menu button and wait for menu transition
        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait.until(ExpectedConditions.attributeContains(
            By.className("bm-menu-wrap"), 
            "aria-hidden", 
            "false"
        ));
        
        // Wait for logout link and click it
        WebElement logoutLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))
        );
        logoutLink.click();
    }

    @Then("I should be on the login page")
    public void verifyOnLoginPage() {
        assertTrue(driver.getCurrentUrl().equals(baseUrl));
    }
}
