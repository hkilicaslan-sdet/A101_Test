import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class A101_04 extends TestBase{
    @Test
    public void test() throws InterruptedException {
        driver.get("http://www.a101.com.tr");
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();

////  3.      Kategoriler ---Kadın---Giyim---Dizaltı çorap bölümüne girip

        WebElement hooverArea=driver.findElement(By.xpath("(//li[@data-pk='37a6c1c8-a180-4b87-9ec9-3a29f6453a0c'])[1]"));

        Actions actions=new Actions(driver);
        actions.moveToElement(hooverArea).perform();
        Thread.sleep(500);

        //    driver.findElement(By.linkText("Kadın Giyim")).click();
        driver.findElement(By.xpath("//a[@title='Dizaltı Çorap']")).click();
//////  4.      Filtreleme bölümünden renk seçeneği siyah seçilecek.

        driver.findElement(By.xpath("//label[@for='attributes_integration_colourSİYAH']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//  5.      Sonuçlar listele yapılıp ,gelen ürün sonuçlarından biri seçilip ,üstüne tıklanacak.

        driver.findElement(By.xpath("//img[@alt='Doremi Kadın Desenli Dizaltı Çorap Fit15 Denye Siyah']")).click();
////  6.      Beden medium seçilecek ,sepete ekle butonuna tıklanacak.
        // bu ürünlerin hiç birinde beden seçimi seçeneği yok
        driver.findElement(By.xpath("//button[@data-sku='25003594001']")).click();
        driver.findElement(By.xpath("//a[@class='go-to-shop']")).click();

////  7.      Sepetim’e gidilecek ,
            // ürünün adı ,
        String urunAdı=driver.findElement(By.xpath("(//a[@title='Doremi Kadın Desenli Dizaltı Çorap Fit15 Denye Siyah'])[3]")).getText();
        System.out.println("urunAdı " +urunAdı);
            // adeti ,
        WebElement urunAdet=driver.findElement(By.xpath("//input[@data-id='35700']"));
        System.out.println(urunAdet.getText());

            // bedeni kontrol edilecek.



////  8.      Ödeme yap butonuna tıklayıp,ödeme ekranına gidildiği ,doğru ekrana yönlendiklerini kontrol edecekler.
        driver.findElement(By.xpath("//a[@class='button green checkout-button block js-checkout-button']")).click();


    }


}
