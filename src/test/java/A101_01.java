import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

public class A101_01 extends TestBase{
    @Test
    public void test1(){
// Uçtan uca ödeme ekranına kadar Selenium’da java dili ile
// chrome browser kullanarak test otomasyon ödevi yapılacak.
// Ödeme ekranı doldurulmayacak.
// Aşağıdaki senaryoyu web ve mobil olmak üzere 2 çeşit oluşturabilirlerse çok iyi olur.
// En az Web’de yapmak zorunlu.
//        Bir yeni müşteri kaydı oluşturulacak.
        driver.get("http://www.a101.com.tr");
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
                //Create a map to store  preferences
        Map<String, Object> prefs = new HashMap<String, Object>();
                //add key and value to map as follow to switch off browser notification
                //Pass the argument 1 to allow and 2 to block
        prefs.put("profile.default_content_setting_values.notifications", 2);
                //Create an instance of ChromeOptions
        ChromeOptions options = new ChromeOptions();
                // set ExperimentalOption - prefs
        options.setExperimentalOption("prefs", prefs);
    //    driver.navigate().refresh();

      //  WebElement dropDown=driver.findElement(By.xpath("//a[@class='login-btn account-dropdown']"));
        boolean flag=true;
        if (flag){
            driver.findElement(By.xpath("//a[@class='login-btn account-dropdown open']")).click();
            driver.findElement(By.partialLinkText("Yap")).click();

//            WebElement dropDown=driver.findElement(By.partialLinkText("Yap"));
//            Select select=new Select(dropDown);
//            select.selectByIndex(0);
        }else {
            driver.findElement(By.xpath("//*[@class='js-account-toggle']")).click();
        }



       //
//  1.      Login olduktan sonra
//        driver.findElement(By.xpath("//a[@class='js-account-toggle']")).click();
        driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("5442398175");
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
//  2.      Kategoriler ---Kadın---Giyim---bluz bölümüne girip
//  3.      Filtreleme bölümünden renk seçeneği siyah seçilecek.
//  4.      Sonuçlar listele yapılıp ,gelen ürün sonuçlarından biri seçilip ,üstüne tıklanacak.
//  5.      Beden medium seçilecek ,sepete ekle butonuna tıklanacak.
//  6.      Sepetim’e gidilecek ,ürünün adı ,adeti ,bedeni kontrol edilecek.
//  7.      Ödeme yap butonuna tıklayıp,ödeme ekranına gidildiği ,doğru ekrana yönlendiklerini kontrol edecekler.
      //  driver.findElement(By.xpath("//div[@class='closePopupPersona']")).click();

    }


}
