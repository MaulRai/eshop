package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String createProductUrl;

    @BeforeEach
    void setupTest() {
        createProductUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);

    }

    @Test
    void checkIsAddProductSuccess(ChromeDriver driver) throws Exception {
        driver.get(createProductUrl);
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.clear();
        String name = "Sabun Dettol";
        nameInput.sendKeys(name);
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.clear();
        String qty = "10";
        quantityInput.sendKeys(qty);

        // click
        driver.findElement(By.id("submit")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/product/list"));

        List<WebElement> productRows = driver.findElements(By.xpath("//tbody/tr"));

        boolean productExists = productRows.stream().anyMatch(row -> {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            return cells.size() > 0 && cells.get(0).getText().equals(name);
        });

        assertTrue(productExists, "Produkna teu aya.");
    }
}
