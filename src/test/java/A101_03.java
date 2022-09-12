import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class A101_03 extends TestBase {
    @Test
    public void test() throws InterruptedException {
        driver.get("http://www.a101.com.tr");
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();

//        Map<String, Object> prefs = new HashMap<String, Object>();
//        prefs.put("profile.default_content_setting_values.notifications", 2);
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", prefs);

//        driver.findElement(By.xpath("//a[@class='login-btn account-dropdown']")).click();
//        driver.findElement(By.partialLinkText("Yap")).click();
//        driver.findElement(By.xpath("(//*[@title='Hesabım'])[1]")).click();
//        driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("442398175");
//        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();

////  3.      Kategoriler ---Kadın---Giyim---Dizaltı çorap bölümüne girip

        WebElement hooverArea=driver.findElement(By.xpath("(//li[@data-pk='37a6c1c8-a180-4b87-9ec9-3a29f6453a0c'])[1]"));
//        When use click on “Account” link
        Actions actions=new Actions(driver);
        actions.moveToElement(hooverArea).perform();
        Thread.sleep(500);
        //    driver.findElement(By.linkText("Kadın Giyim")).click();
        driver.findElement(By.xpath("//a[@title='Kadın Tişört']")).click();
////  4.      Filtreleme bölümünden renk seçeneği siyah seçilecek.

        driver.findElement(By.xpath("//label[@for='attributes_integration_colourSİYAH']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
////  5.      Sonuçlar listele yapılıp ,gelen ürün sonuçlarından biri seçilip ,üstüne tıklanacak.

        driver.findElement(By.xpath("//img[@alt='Nike DC5296-010 Kadın Tişört']")).click();
////  6.      Beden medium seçilecek ,sepete ekle butonuna tıklanacak.

        driver.findElement(By.xpath("//a[@data-pk='40636']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement addBasket=driver.findElement(By.xpath("//button[@class='add-to-basket button green block with-icon js-add-basket']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        addBasket.click();

////  7.      Sepetim’e gidilecek ,ürünün adı ,adeti ,bedeni kontrol edilecek.

        driver.findElement(By.linkText("Sepeti Görüntüle")).click();

////  8.      Ödeme yap butonuna tıklayıp,ödeme ekranına gidildiği ,doğru ekrana yönlendiklerini kontrol edecekler.


    }



}
//  1.      Bir yeni müşteri kaydı oluşturulacak.
//  2.      Login olduktan sonra

