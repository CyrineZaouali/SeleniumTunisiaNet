import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestClass {
    private static WebDriver driver;
    private static JavascriptExecutor javaScriptExecutor;
    private static WebDriverWait driverWait;

    private static final String url = "https://www.tunisianet.com.tn/";
    private static final String productToSearch = "MacBook M1 13.3";

    @BeforeAll
    public static void initAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void prepareDriver() {
        driver = new ChromeDriver();
        javaScriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void buyProcessTestCase() {
        driver.get(url);
        driverWait = new WebDriverWait(driver, 3);

        try {
            login();
            searchProduct(productToSearch);
            orderProduct();
            logout();
        } catch (TimeoutException timeoutException) {
            System.out.println(timeoutException.toString());
        }
    }

    public void login() {
        //click on the user icon
        WebElement userIcon = driver.findElement(By.className("user-info"));
        driverWait.until(ExpectedConditions.visibilityOf(userIcon));
        javaScriptExecutor.executeScript("arguments[0].click()", userIcon);

        //click on connexion
        WebElement dropDown = driver.findElement(By.cssSelector(".user-down > li > a"));
        driverWait.until(ExpectedConditions.visibilityOf(dropDown));
        dropDown.click();

        //fill out email field
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        driverWait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys("cyrine.zaouali@insat.ucar.tn");

        //fill out password field
        WebElement passwordInput = driver.findElement(By.cssSelector("input[name='password']"));
        driverWait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys("cyrineINSAT");

        //click on connexion
        WebElement submitButton = driver.findElement(By.id("submit-login"));
        driverWait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();
    }

    public void searchProduct(String product) {
        //search for the article in the search bar then hit enter
        WebElement searchBar = driver.findElement(By.id("search_query_top"));
        driverWait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(product);
        searchBar.sendKeys(Keys.ENTER);
    }

    public void orderProduct() {
        //choose random article from list
        List<WebElement> products = driver.findElements(By.className("item-product"));
        int random = (int) (Math.random() * (products.size() - 1) +1);

        //click on add to cart
        WebElement cartIcon = driver.findElements(By.className("add-to-cart")).get(random - 1);
        driverWait.until(ExpectedConditions.visibilityOf(cartIcon));
        cartIcon.click();

        //click on order
        WebElement orderButton = driver.findElement(By.cssSelector(".cart-content-btn > a"));
        driverWait.until(ExpectedConditions.visibilityOf(orderButton));
        orderButton.click();

        //click on order after redirect
        orderButton = driver.findElement(By.cssSelector(".checkout > div > a"));
        driverWait.until(ExpectedConditions.visibilityOf(orderButton));
        orderButton.click();
    }

    public void logout() {
        //click on the user icon
        WebElement userIcon = driver.findElement(By.className("user-info"));
        driverWait.until(ExpectedConditions.visibilityOf(userIcon));
        javaScriptExecutor.executeScript("arguments[0].click()", userIcon);

        //click on déconnexion
        WebElement dropDown = driver.findElement(By.className("logout"));
        driverWait.until(ExpectedConditions.visibilityOf(dropDown));
        dropDown.click();
    }

    @AfterAll
    static void quit() {
        driver.quit();
    }

}
