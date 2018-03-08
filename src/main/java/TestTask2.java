import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestTask2 {
    private ChromeDriver driver;
    private WebElement loginPictogram;
    private WebElement loginPopUp;
    private WebElement loginField;
    private WebElement passField;
    private WebElement rememberMeCheckBox;
    private WebElement loginButton;

    @Test
    public void testLoginPopUp()
    {
        driver = new ChromeDriver();
        driver.get("https://homsters.kz ");

        // Verify that login pictogram is enabled
        loginPictogram = driver.findElement(By.cssSelector("div.b-complex-header__button"));
        Assert.assertTrue(loginPictogram.isEnabled());
        loginPictogram.click();

        // Verify that login popup is displayed
        loginPopUp = driver.findElement(By.cssSelector("div.b-popup"));
        Assert.assertTrue(loginPopUp.isDisplayed());

        // Set valid login and password
        loginField = driver.findElement(By.cssSelector("input#mail_or_email_login"));
        Assert.assertTrue(loginField.isEnabled());
        loginField.sendKeys("test111@te.st");
        passField = driver.findElement(By.cssSelector("input#user_password_login"));
        Assert.assertTrue(passField.isEnabled());
        passField.sendKeys("111test");

        // Uncheck rememberMe checkBox
        rememberMeCheckBox = driver.findElement(By.cssSelector("label[for='remember_me_login']"));
        Assert.assertTrue(rememberMeCheckBox.isEnabled());
        rememberMeCheckBox.click();

        // Click on login button
        loginButton = driver.findElement(By.cssSelector("button.b-button"));
        Assert.assertTrue(loginButton.isEnabled());
        loginButton.click();

        // Verify that greeting message is present on the main page
        WebElement greetingMsg = (new WebDriverWait(driver, 5))
            .until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(".//*[text()='test111, с возвращением. Последний раз вы искали:']")));
        Assert.assertTrue(greetingMsg.isDisplayed());
        driver.quit();
    }
}
