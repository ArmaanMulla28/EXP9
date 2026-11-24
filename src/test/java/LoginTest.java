
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

public class LoginTest {

    private WebDriver driver;

    @BeforeEach
void setUp() {
    ChromeOptions options = new ChromeOptions();

    options.setBinary(
        "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"
    );

   options.addArguments("--headless=new");
options.addArguments("--disable-gpu");
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--user-data-dir=C:\\selenium\\chrome-profile");
    driver = new ChromeDriver(options);

    driver.manage().window().maximize();

    Path page = Paths.get("webapp", "index.html").toAbsolutePath();
    driver.get(page.toUri().toString());
}

    @Test
    public void validLoginShouldShowSuccessMessage() throws Exception {

        // TODO 5: Enter valid username
        driver.findElement(By.id("username")).sendKeys("student");

        // TODO 6: Enter valid password
        driver.findElement(By.id("password")).sendKeys("Selenium@123");

        // TODO 7: Click login button
        driver.findElement(By.id("loginBtn")).click();

        // TODO 8: Retrieve response message
        String message = driver.findElement(By.id("message")).getText();

        // TODO 9: Verify success message
        assertTrue(message.contains("Login successful"));

        // TODO 10: Capture screenshot
        saveScreenshot("successful-login.png");
    }

    @Test
    public void invalidLoginShouldShowErrorMessage() throws Exception {

        // TODO 11: Enter invalid credentials
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpassword");

        driver.findElement(By.id("loginBtn")).click();

        // TODO 12: Verify error message
        String message = driver.findElement(By.id("message")).getText();

        assertTrue(message.contains("Invalid"));

        // TODO 13: Capture screenshot
        saveScreenshot("invalid-login.png");
    }

    private void saveScreenshot(String fileName) throws Exception {

        // TODO 14: Capture and save screenshot
        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        Files.createDirectories(Paths.get("screenshots"));

        File destination = new File("screenshots/" + fileName);

        FileHandler.copy(source, destination);
    }

    @AfterEach
    public void tearDown() {

        // TODO 15: Close browser safely
        if (driver != null) {
            driver.quit();
        }
    }
}

