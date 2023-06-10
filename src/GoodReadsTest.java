import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class GoodReadsTest {
    private static WebDriver driver;

    public static void main(String[] args) {
        setupWebDriver();
        login("swarnikaverma1999@gmail.com","Swarnika@1999");
        searchBook("Hellen Keller's Teacher");
        addToRead();
        navigateToMyBooks();
        removeBookFromMyBooks();
        signOut();
        quitWebDriver();
    }

    private static void setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/swarnikaverma/Downloads/chromedriver_mac_arm64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
        driver.get("https://www.goodreads.com/");
    }

    private static void login(String email, String password) {
        driver.findElement(By.xpath("//a[.='Sign In']")).click();
        driver.findElement(By.xpath("//button[@class='gr-button gr-button--dark gr-button--auth authPortalConnectButton authPortalSignInButton']")).click();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
    }

    private static void searchBook(String bookTitle) {
        WebElement searchInput = driver.findElement(By.xpath("//input[@class='searchBox__input searchBox__input--navbar']"));
        searchInput.sendKeys(bookTitle);
        searchInput.sendKeys(Keys.ENTER);
    }

    private static void addToRead() {
        driver.findElement(By.xpath("(//button[@class='wtrToRead'])[1]")).click();
    }

    private static void navigateToMyBooks() {
        driver.findElement(By.xpath("(//a[@class='siteHeader__topLevelLink'])[2]")).click();
        driver.navigate().refresh();
    }

    private static void removeBookFromMyBooks() {
        driver.findElement(By.xpath("//img[@title='Remove from my books']")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    private static void signOut() {
        driver.findElement(By.xpath("(//img[@class='circularIcon circularIcon--border'])[1]")).click();
        driver.findElement(By.xpath("(//a[.='Sign out'])[1]")).click();
    }

    private static void quitWebDriver() {
        driver.quit();
    }
}