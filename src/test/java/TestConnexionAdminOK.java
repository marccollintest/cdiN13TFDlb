
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestConnexionAdminOK {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testConnexionAdminOK() throws Exception {
    // open | /dolibarr/htdocs/index.php | 
    driver.get(baseUrl + "/dolibarr/htdocs/index.php");
    // assertText | //form[@id='login']/table/tbody/tr/td | V2.5
    assertEquals("V2.5", driver.findElement(By.xpath("//form[@id='login']/table/tbody/tr/td")).getText());
    // type | id=username | admin
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    // type | id=password | admin
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // verifyTitle | Dolibarr - Espace accueil - Dolibarr 3.6.1 | 
    try {
      assertEquals("Dolibarr - Espace accueil - Dolibarr 3.6.1", driver.getTitle());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // assertText | link=admin | admin
    assertEquals("admin", driver.findElement(By.linkText("admin")).getText());
    // click | //img[@alt="DÃ©connexion"] | 
    driver.findElement(By.xpath("//img[@alt=\"Déconnexion\"]")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
