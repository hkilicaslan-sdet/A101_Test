import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class A101_02 extends TestBase{
    @Test
    public void test1() throws InterruptedException {
        driver.get("http://www.a101.com.tr");
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver.findElement(By.xpath("//a[@class='login-btn account-dropdown open']")).click();
        driver.findElement(By.partialLinkText("Yap")).click();
        driver.findElement(By.xpath("(//*[@title='Hesabım'])[1]")).click();
        driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("442398175");
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();

        // Burada telefona gelen şifre manuel girilmek zorunda

//        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2));
//        //wait is doig two things; waiting for the text appear; 2. return the result as WebElement
//        WebElement enter= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='js-send-verification button green']")));


//  2.      Kategoriler ---Kadın---Giyim---bluz bölümüne girip
        WebElement hooverArea=driver.findElement(By.xpath("(//li[@data-pk='37a6c1c8-a180-4b87-9ec9-3a29f6453a0c'])[1]"));
//        When use click on “Account” link
        Actions actions=new Actions(driver);
        actions.moveToElement(hooverArea).perform();
        Thread.sleep(500);
    //    driver.findElement(By.linkText("Kadın Giyim")).click();
        driver.findElement(By.xpath("//a[@title='Kadın Tişört']")).click();

//  3.      Filtreleme bölümünden renk seçeneği siyah seçilecek.
        driver.findElement(By.xpath("//label[@for='attributes_integration_colourSİYAH']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

//  4.      Sonuçlar listele yapılıp ,gelen ürün sonuçlarından biri seçilip ,üstüne tıklanacak.
        driver.findElement(By.xpath("(//img[@class=' ls-is-cached lazyloaded'])[4]")).click();

//  5.      Beden medium seçilecek ,sepete ekle butonuna tıklanacak.
        driver.findElement(By.xpath("//a[@data-pk='23993']")).click();

        driver.findElement(By.xpath("//button[@data-sku='25003203014']")).click();
//  6.      Sepetim’e gidilecek ,ürünün adı ,adeti ,bedeni kontrol edilecek
        driver.findElement(By.linkText("Sepeti Görüntüle")).click();

//  7.      Ödeme yap butonuna tıklayıp,ödeme ekranına gidildiği ,doğru ekrana yönlendiklerini kontrol edecekler.

        //  driver.findElement(By.xpath("//div[@class='closePopupPersona']")).click();



    }
}
