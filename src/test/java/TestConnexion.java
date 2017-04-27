
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestConnexion {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost/";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
    // click | //img[@alt="Déconnexion"] | 
    driver.findElement(By.xpath("//img[@alt=\"D�connexion\"]")).click();
  }

  @Test
  public void testCnxUtDesactive() throws Exception {
    // deleteAllVisibleCookies |  | 
    // ERROR: Caught exception [ERROR: Unsupported command [deleteAllVisibleCookies |  | ]]
    // open | /dolibarr/htdocs/user/logout.php | 
    driver.get(baseUrl + "/dolibarr/htdocs/user/logout.php");
    // Connexion avec l'utilisateur test
    // open | /dolibarr/htdocs/index.php | 
    driver.get(baseUrl + "/dolibarr/htdocs/index.php");
    // type | id=username | test
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("test");
    // type | id=password | test
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    // click | //div[@id='login_line2']/input | 
    driver.findElement(By.xpath("//div[@id='login_line2']/input")).click();
    // Vérification de la bonne connexion
    // assertTitle | Dolibarr - Espace accueil - Dolibarr 3.6.1 | 
    assertEquals("Dolibarr - Espace accueil - Dolibarr 3.6.1", driver.getTitle());
    // click | css=img.login | 
    driver.findElement(By.cssSelector("img.login")).click();
    // Connexion en Admin
    // type | id=username | admin
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    // type | id=password | admin
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // Désactivation de l'utilisateur test
    // click | link=Utilisateurs & Groupes | 
    driver.findElement(By.linkText("Utilisateurs & Groupes")).click();
    // click | link=Utilisateurs | 
    driver.findElement(By.linkText("Utilisateurs")).click();
    // click | css=tr.pair > td > a > img[alt="Afficher utilisateur"] | 
    driver.findElement(By.cssSelector("tr.pair > td > a > img[alt=\"Afficher utilisateur\"]")).click();
    // click | link=Désactiver | 
    driver.findElement(By.linkText("D�sactiver")).click();
    // click | //button[@type='button'] | 
    driver.findElement(By.xpath("//button[@type='button']")).click();
    // Vérification de la désactivation
    // assertText | //div[@id='id-right']/div/div[2]/table/tbody/tr[15]/td[2] | Désactivé
    assertEquals("D�sactiv�", driver.findElement(By.xpath("//div[@id='id-right']/div/div[2]/table/tbody/tr[15]/td[2]")).getText());
    // click | css=img.login | 
    driver.findElement(By.cssSelector("img.login")).click();
    // Connexion avec test (Désactivé)
    // type | id=username | test
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("test");
    // type | id=password | test
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // Vérif de la non-connexion
    // assertText | css=div.error | ErrorLoginDisabled
    assertEquals("ErrorLoginDisabled", driver.findElement(By.cssSelector("div.error")).getText());
    // Suppression des cookies
    // deleteAllVisibleCookies |  | 
    // ERROR: Caught exception [ERROR: Unsupported command [deleteAllVisibleCookies |  | ]]
    // Reconnexin en tant qu'admin
    // open | /dolibarr/htdocs/index.php | 
    driver.get(baseUrl + "/dolibarr/htdocs/index.php");
    // type | id=username | admin
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    // type | id=password | admin
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // Réactivation de l'utilisateur test
    // click | link=Utilisateurs & Groupes | 
    driver.findElement(By.linkText("Utilisateurs & Groupes")).click();
    // click | link=Utilisateurs | 
    driver.findElement(By.linkText("Utilisateurs")).click();
    // click | link=test | 
    driver.findElement(By.linkText("test")).click();
    // click | link=Réactiver | 
    driver.findElement(By.linkText("R�activer")).click();
    // click | //button[@type='button'] | 
    driver.findElement(By.xpath("//button[@type='button']")).click();
    // assertText | //div[@id='id-right']/div/div[2]/table/tbody/tr[15]/td[2] | Actif
    assertEquals("Actif", driver.findElement(By.xpath("//div[@id='id-right']/div/div[2]/table/tbody/tr[15]/td[2]")).getText());
    // click | css=img.login | 
    driver.findElement(By.cssSelector("img.login")).click();
    // Connexion avec l'utilisateur test (Activé)
    // type | id=username | test
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("test");
    // type | id=password | test
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    // click | //div[@id='login_line2']/input | 
    driver.findElement(By.xpath("//div[@id='login_line2']/input")).click();
    // Vérification de la bonne connexion
    // assertTitle | Dolibarr - Espace accueil - Dolibarr 3.6.1 | 
    assertEquals("Dolibarr - Espace accueil - Dolibarr 3.6.1", driver.getTitle());
    // click | //img[@alt="Déconnexion"] | 
    driver.findElement(By.xpath("//img[@alt=\"D�connexion\"]")).click();
    // deleteAllVisibleCookies |  | 
    // ERROR: Caught exception [ERROR: Unsupported command [deleteAllVisibleCookies |  | ]]
  }
  @Test
  public void testConnexionUserOKMDPNOK() throws Exception {
    // open | /dolibarr/htdocs/user/logout.php | 
    driver.get(baseUrl + "/dolibarr/htdocs/user/logout.php");
    // open | /dolibarr/htdocs/index.php | 
    driver.get(baseUrl + "/dolibarr/htdocs/index.php");
    // type | id=username | Inconnu
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("Inconnu");
    // type | id=password | rien
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("rien");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // assertElementPresent | css=div.error | 
    assertTrue(isElementPresent(By.cssSelector("div.error")));
    // assertText | css=div.error | Identifiants login ou mot de passe incorrect
    assertEquals("Identifiants login ou mot de passe incorrect", driver.findElement(By.cssSelector("div.error")).getText());
  }

  @Test
  public void testDesactivationUtilisateur() throws Exception {
    // deleteAllVisibleCookies |  | 
    // ERROR: Caught exception [ERROR: Unsupported command [deleteAllVisibleCookies |  | ]]
    // open | /dolibarr/htdocs/user/logout.php | 
    driver.get(baseUrl + "/dolibarr/htdocs/user/logout.php");
    // open | /dolibarr/htdocs/index.php | 
    driver.get(baseUrl + "/dolibarr/htdocs/index.php");
    // Première tentative
    // type | id=username | test
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("test");
    // type | id=password | rien
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("rien");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // verifyText | css=div.error | Identifiants login ou mot de passe incorrect
      assertEquals("Identifiants login ou mot de passe incorrect", driver.findElement(By.cssSelector("div.error")).getText());
    // type | id=username | admin
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    // type | id=password | admin
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // assertText | link=Utilisateurs & Groupes | Utilisateurs & Groupes
    assertEquals("Utilisateurs & Groupes", driver.findElement(By.linkText("Utilisateurs & Groupes")).getText());
    // click | link=Utilisateurs & Groupes | 
    driver.findElement(By.linkText("Utilisateurs & Groupes")).click();
    // click | link=Utilisateurs | 
    driver.findElement(By.linkText("Utilisateurs")).click();
    // click | link=test | 
    driver.findElement(By.linkText("test")).click();
    // assertText | css=img[alt="Actif"] | 
    assertEquals("", driver.findElement(By.cssSelector("img[alt=\"Actif\"]")).getText());
    // click | css=img.login | 
    driver.findElement(By.cssSelector("img.login")).click();
    // tentative 2
    // type | id=username | test
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("test");
    // type | id=password | rien
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("rien");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // verifyText | css=div.error | Identifiants login ou mot de passe incorrect
      assertEquals("Identifiants login ou mot de passe incorrect", driver.findElement(By.cssSelector("div.error")).getText());
    // type | id=username | admin
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    // type | id=password | admin
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // click | link=Utilisateurs & Groupes | 
    driver.findElement(By.linkText("Utilisateurs & Groupes")).click();
    // click | link=Utilisateurs | 
    driver.findElement(By.linkText("Utilisateurs")).click();
    // click | link=test | 
    driver.findElement(By.linkText("test")).click();
    // assertText | css=img[alt="Actif"] | 
    assertEquals("", driver.findElement(By.cssSelector("img[alt=\"Actif\"]")).getText());
    // click | css=img.login | 
    driver.findElement(By.cssSelector("img.login")).click();
    // tentative 3
    // type | id=username | test
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("test");
    // type | id=password | rien
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("rien");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // verifyText | css=div.error | Identifiants login ou mot de passe incorrect
    try {
      assertEquals("Identifiants login ou mot de passe incorrect", driver.findElement(By.cssSelector("div.error")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // type | id=username | admin
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    // type | id=password | admin
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    // click | css=input.button | 
    driver.findElement(By.cssSelector("input.button")).click();
    // click | link=Utilisateurs & Groupes | 
    driver.findElement(By.linkText("Utilisateurs & Groupes")).click();
    // click | link=Utilisateurs | 
    driver.findElement(By.linkText("Utilisateurs")).click();
    // click | link=test | 
    driver.findElement(By.linkText("test")).click();
    // verifyElementPresent | css=img[alt="Désactivé"] | 
    try {
      assertTrue(isElementPresent(By.cssSelector("img[alt=\"D�sactiv�\"]")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // click | css=img.login | 
    driver.findElement(By.cssSelector("img.login")).click();
  }
  @Test
  public void testGestUtilAdmin() throws Exception {
    // open | /dolibarr/htdocs/index.php?mainmenu=home | 
    driver.get(baseUrl + "/dolibarr/htdocs/index.php?mainmenu=home");
    // type | id=username | admin
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    // type | id=password | admin
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    // click | //div[@id='login_line2']/input | 
    driver.findElement(By.xpath("//div[@id='login_line2']/input")).click();
    // assertTitle | Dolibarr - Espace accueil - Dolibarr 3.6.1 | 
    assertEquals("Dolibarr - Espace accueil - Dolibarr 3.6.1", driver.getTitle());
    // assertElementPresent | link=Utilisateurs & Groupes | 
    assertTrue(isElementPresent(By.linkText("Utilisateurs & Groupes")));
    // click | link=Utilisateurs & Groupes | 
    driver.findElement(By.linkText("Utilisateurs & Groupes")).click();
    // assertTitle | Dolibarr | 
    assertEquals("Dolibarr", driver.getTitle());
    // assertElementPresent | //div[@id='id-right']/div/table/tbody/tr/td[2]/div | 
    assertTrue(isElementPresent(By.xpath("//div[@id='id-right']/div/table/tbody/tr/td[2]/div")));
    // click | //body[@id='mainbody']/div[2]/div[2]/div/a/img | 
    driver.findElement(By.xpath("//body[@id='mainbody']/div[2]/div[2]/div/a/img")).click();
  }
  @Test
  public void testGestUtilNonAdmin() throws Exception {
    // open | /dolibarr/htdocs/user/logout.php | 
    driver.get(baseUrl + "/dolibarr/htdocs/user/logout.php");
    // open | /dolibarr/htdocs/index.php?mainmenu=home | 
    driver.get(baseUrl + "/dolibarr/htdocs/index.php?mainmenu=home");
    // type | id=username | test
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("test");
    // type | id=password | test
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    // click | //div[@id='login_line2']/input | 
    driver.findElement(By.xpath("//div[@id='login_line2']/input")).click();
    // assertTitle | Dolibarr - Espace accueil - Dolibarr 3.6.1 | 
    assertEquals("Dolibarr - Espace accueil - Dolibarr 3.6.1", driver.getTitle());
    // assertElementPresent | link=Utilisateurs & Groupes | 
    assertTrue(isElementPresent(By.linkText("Utilisateurs & Groupes")));
    // click | link=Utilisateurs & Groupes | 
    driver.findElement(By.linkText("Utilisateurs & Groupes")).click();
    // assertNotTitle | Dolibarr | 
    assertThat("Dolibarr", is(not(driver.getTitle())));
    // assertElementNotPresent | //div[@id='id-right']/div/table/tbody/tr/td[2]/div | 
    assertFalse(isElementPresent(By.xpath("//div[@id='id-right']/div/table/tbody/tr/td[2]/div")));
    // click | //body[@id='mainbody']/div[2]/div[2]/div/a/img | 
    driver.findElement(By.xpath("//body[@id='mainbody']/div[2]/div[2]/div/a/img")).click();
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
