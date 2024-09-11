import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Setup method to initialize WebDriver and open the browser
    @BeforeMethod
    public void setup() {
        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        // Initialize WebDriverWait for use in waiting for elements to be visible
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Test for successful login
    @Test(priority = 1)
    public void testSuccessfulLogin() {
        // Maximize the browser window
        driver.manage().window().maximize();
        
        // Navigate to the login page
        driver.get("https://app.germanyiscalling.com/common/login/?next=https%3A%2F%2Fapp.germanyiscalling.com%2Fcv%2Fhome%2F");
        
        // Enter valid email into the username field using XPath to locate the element
        driver.findElement(By.xpath("//input[@type='text'][1]")).sendKeys("testdemouser96@gmail.com");
        
        // Enter valid password into the password field using XPath
        driver.findElement(By.xpath("//input[@type='password'][1]")).sendKeys("test12345");
        
        // Click the login button, identified using CSS selector
        driver.findElement(By.cssSelector(".btn-danger")).click();
        
        // Get the page title after login and store it in a variable
        String actual_title = driver.getTitle();
        String expected_title = "Upload your CV | Germany Is Calling";
        
        // Check if the login is successful by comparing the expected and actual title
        if(expected_title.equals(actual_title)) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        }

        // Assert if login was successful based on the page title
        Assert.assertEquals(actual_title, expected_title, "Login Failed!");
    }

 @Test(priority=2)
    
    public void testSpecialCharacters() {
    	
        driver.manage().window().maximize();

        driver.get("https://app.germanyiscalling.com/common/login/?next=https%3A%2F%2Fapp.germanyiscalling.com%2Fcv%2Fhome%2F");
        
        // Enter special characters in the username field
        driver.findElement(By.xpath("//input[@type='text'][1]")).sendKeys("@#$&(");
        // Enter special characters in the username field

        
        driver.findElement(By.xpath("//input[@type='password'][1]")).sendKeys("^&*");
        
        // Click the login button
        driver.findElement(By.cssSelector(".btn-danger")).click();
        
        // Check for the error message
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li"));
        Assert.assertEquals(errorMessage.getText(), "Please enter a correct username and password. Note that both fields may be case-sensitive.");
    }

    // Test for unsuccessful login
    @Test(priority = 3)
    public void testUnsuccessfulLogin() {
        // Maximize the browser window again 
        driver.manage().window().maximize();
        
        // Navigate back to the login page
        driver.get("https://app.germanyiscalling.com/common/login/?next=https%3A%2F%2Fapp.germanyiscalling.com%2Fcv%2Fhome%2F");
        
        // Enter an invalid email into the username field (invalid email format)
        driver.findElement(By.xpath("//input[@type='text'][1]")).sendKeys("testdemouser94446mail.com");
        
        // Enter an invalid password into the password field
        driver.findElement(By.xpath("//input[@type='password'][1]")).sendKeys("test1332345");
        
        // Click the login button
        driver.findElement(By.cssSelector(".btn-danger")).click();
        

        String error_text=driver.findElement(By.xpath("//div[@class='alert alert-danger']//li")).getText();
        
        // Verify the content of the error message matches the expected text
        String expErrorMessage = "Please enter a correct username and password. Note that both fields may be case-sensitive.";
        
        if(expErrorMessage.equals(error_text)) {
            System.out.println("Expected error message is displayed");
        } else {
            System.out.println("Incorrect error message displayed");
        }

      
    }
    
    // Teardown method to close the browser
    @AfterMethod
    public void teardown() {
        // Close the browser after each test to free resources
        if (driver != null) {
            driver.quit();
        }
    }
}
