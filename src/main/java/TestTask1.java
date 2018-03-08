import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestTask1 {
    private ChromeDriver driver;
    private WebElement loginField;
    private WebElement passField;
    private WebElement loginButton;

    @Before
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.get("https://homsters.kz/account/login ");
        loginField=driver.findElement(By.cssSelector("input#UserName"));
        passField=driver.findElement(By.cssSelector("input#Password"));
        loginButton=driver.findElement(By.cssSelector("form#loginForm button[type='submit']"));
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testLoginUIElements() {
        // Verify that all UI elements in login form are enabled
        Assert.assertTrue(loginField.isEnabled());
        Assert.assertTrue(passField.isEnabled());
        Assert.assertTrue(loginButton.isEnabled());

        WebElement rememberMe=driver.findElement(By.cssSelector("input#RememberMe"));
        Assert.assertTrue(rememberMe.isEnabled());
    }

    @Test
    public void testLoginIncorrect() {
        // Login with incorrect credentials
        loginField.sendKeys("abc@gmail.com");
        passField.sendKeys("123456");
        loginButton.click();
        // Verify that error message is displayed
        WebElement incorrectNameErrorMSG = driver.findElement(By.cssSelector("form#loginForm span.field-validation-error"));
        Assert.assertTrue(incorrectNameErrorMSG.isDisplayed());
    }

    @Test
    public void testLoginWithEmptyFields() {
        // Login with empty fields
        loginButton.click();
        // Verify that error message about empty login field is displayed
        WebElement loginErrorMsg = driver.findElement(By.cssSelector("span#UserName-error"));
        Assert.assertTrue(loginErrorMsg.isDisplayed());
        // Verify that error message about empty password field is displayed
        WebElement passErrorMsg = driver.findElement(By.cssSelector("span#Password-error"));
        Assert.assertTrue(passErrorMsg.isDisplayed());
    }

    @Test
    public void testLogin() {
        // Login with correct credentials
        loginField.sendKeys("test111@te.st");
        passField.sendKeys("111test");
        loginButton.click();
        // Verify that greeting message is present on the main page
        WebElement greetingMsg = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(".//*[text()='test111, с возвращением. Последний раз вы искали:']")));
    }
}
