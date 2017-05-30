package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class LoadTest {
    public static WebDriver driver;







    @Test
    @Parameters({"url"})

        public void searchTest(String url) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Получаем время Load Event End (окончание загрузки страниы)
        long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        // Получаем Navigation Event Start (начало перехода)
        long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        // Разница между Load Event End и Navigation Event Start - это время загрузки страницы
        long loadTime = (loadEventEnd - navigationStart) / 1000;
        System.out.println("Page Load Time is " + loadTime + " seconds.");
        assertTrue(loadTime < 10);
    }

    @AfterTest
    public void brorserQuit() {
        driver.quit();
    }


}
