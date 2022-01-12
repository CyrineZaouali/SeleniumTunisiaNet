import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestClass {
    private static WebDriver driver;
    private static JavascriptExecutor javaScriptExecutor;
    private static WebDriverWait driverWait;

    private static final String url = "https://www.tunisianet.com.tn/";
    private static final String productToSearch = "MacBook M1 13.3";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        javaScriptExecutor = (JavascriptExecutor) driver;
        driverWait = new WebDriverWait(driver, 3);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(url);

        try {
            //click on the user icon
            driverWait.until(ExpectedConditions.elementToBeCLickable(By.className("user-info")));
            driver.findElement(By.className("user-info")).click();

            //click on connexion
            driverWait.until(ExpectedConditions.elementToBeCLickable(By.cssSelector(".user-down > li > a"));
            driver.findElement(By.cssSelector(".user-down > li > a")).click();

            //fill out email field
            driverWait.until(ExpectedConditions.visibilityOf(By.cssSelector("input[name='email']")));
            driver.findElement(By.cssSelector("input[name='email']")).sendKeys("cyrine.zaouali@insat.ucar.tn");

            //fill out password field
            driverWait.until(ExpectedConditions.visibilityOf(By.cssSelector("input[name='password']")));
            driver.findElement(By.cssSelector("input[name='password']")).sendKeys("cyrineINSAT");

            //click on connexion
            driverWait.until(ExpectedConditions.elementToBeCLickable(By.id("submit-login"));
            driver.findElement(By.id("submit-login")).click();

            //search for the article in the search bar then hit enter
            WebElement searchBar = driver.findElement(By.id("search_query_top"));
            driverWait.until(ExpectedConditions.visibilityOf(searchBar));
            searchBar.sendKeys(productToSearch);
            searchBar.sendKeys(Keys.ENTER);

            List<WebElement> products = driver.findElements(By.className("item-product"));
            int random = (int) (Math.random() * (products.size() - 1) + 1);

            //click on add to cart
            Thread.sleep(2000);
            WebElement cartIcon = driver.findElements(By.cssSelector("button[title='Chariot']")).get(random);
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

            //fill out address input
            WebElement addressInput = driver.findElement(By.cssSelector("input[name='address1']"));
            driverWait.until(ExpectedConditions.visibilityOf(addressInput));
            addressInput.sendKeys("CUN");

            //fill out postcode input
            WebElement postcodeInput = driver.findElement(By.cssSelector("input[name='postcode']"));
            driverWait.until(ExpectedConditions.visibilityOf(postcodeInput));
            postcodeInput.sendKeys("2037");

            //fill out city input
            WebElement cityInput = driver.findElement(By.cssSelector("input[name='city']"));
            driverWait.until(ExpectedConditions.visibilityOf(cityInput));
            cityInput.sendKeys("ariana");

            //fill out phone input
            WebElement phoneInput = driver.findElement(By.cssSelector("input[name='phone']"));
            driverWait.until(ExpectedConditions.visibilityOf(phoneInput));
            phoneInput.sendKeys("12345678");

            //click on confirm button
            WebElement confirmButton = driver.findElement(By.cssSelector("button[name='confirm-addresses']"));
            driverWait.until(ExpectedConditions.visibilityOf(confirmButton));
            confirmButton.click();

            //click on confirm button
            confirmButton = driver.findElement(By.cssSelector("button[name='confirmDeliveryOption']"));
            driverWait.until(ExpectedConditions.visibilityOf(confirmButton));
            confirmButton.click();

            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("./src/test/img/scrrenshot.png");
            FileUtils.copyFile(screenshotFile, dest);

            //click on the user icon
            userIcon = driver.findElement(By.className("user-info"));
            driverWait.until(ExpectedConditions.visibilityOf(userIcon));
            javaScriptExecutor.executeScript("arguments[0].click()", userIcon);

            //click on déconnexion
            dropDown = driver.findElement(By.className("logout"));
            driverWait.until(ExpectedConditions.visibilityOf(dropDown));
            dropDown.click();

            driver.quit();

        } catch (TimeoutException | IOException | InterruptedException timeoutException) {
            System.out.println(timeoutException.toString());
        }

    }

}
