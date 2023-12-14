package Project1.Project1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TableAutomationTest {

    private WebDriver driver;

    @Before
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
    }

    @Test
    public void testTableAutomation() {
        // Step 2: Click on Table Data button
        WebElement tableDataButton = driver.findElement(By.id("add"));
        tableDataButton.click();

        // Step 3: Insert data in input text box and click Refresh Table button
        String jsonData = "[{\"name\": \"Bob\", \"age\": 20, \"gender\": \"male\"}, " +
                "{\"name\": \"George\", \"age\": 42, \"gender\": \"male\"}, " +
                "{\"name\": \"Sara\", \"age\": 42, \"gender\": \"female\"}, " +
                "{\"name\": \"Conor\", \"age\": 40, \"gender\": \"male\"}, " +
                "{\"name\": \"Jennifer\", \"age\": 42, \"gender\": \"female\"}]";

        WebElement dataInputBox = driver.findElement(By.id("data"));
        dataInputBox.sendKeys(jsonData);

        WebElement refreshTableButton = driver.findElement(By.id("refresh"));
        refreshTableButton.click();

        // Step 4 and 5: Assert the data in the table
        WebElement table = driver.findElement(By.id("testTable"));
        String tableText = table.getText();

        Assert.assertTrue(tableText.contains("Bob") && tableText.contains("20") && tableText.contains("male"));
        Assert.assertTrue(tableText.contains("George") && tableText.contains("42") && tableText.contains("male"));
        Assert.assertTrue(tableText.contains("Sara") && tableText.contains("42") && tableText.contains("female"));
        Assert.assertTrue(tableText.contains("Conor") && tableText.contains("40") && tableText.contains("male"));
        Assert.assertTrue(tableText.contains("Jennifer") && tableText.contains("42") && tableText.contains("female"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

