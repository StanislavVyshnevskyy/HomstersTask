import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import java.util.logging.Level;

public class TestTask3 {
    private ChromeDriver driver;
    private Logs logs;
    private LogEntries logEntries;
    private String[] linksArr = {"https://homsters.kz/",
            "https://homsters.kz/estate/search",
            "https://homsters.kz/tau-development/abay-130",
            "https://homsters.kz/reviews",
            "https://homsters.kz/leya-komfort-review"};

    @Test
    public void testLogs()
    {
        for (String link:linksArr)
        {
            driver = new ChromeDriver();
            driver.get(link);
            printLogs(driver);
            driver.quit();
        }
    }

    private void printLogs(ChromeDriver driver)
    {
        this.driver.setLogLevel(Level.ALL);
        logs = this.driver.manage().logs();

        logEntries = logs.get(LogType.BROWSER);

        for (LogEntry logEntry : logEntries)
        {
            System.out.println(logEntry.getMessage());
        }
    }

}
