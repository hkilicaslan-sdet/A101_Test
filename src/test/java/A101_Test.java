import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class A101_Test {
    @Test
    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

//        Ödeme ekranı doldurulmayacak. Aşağıdaki senaryoyu web ve mobil olmak üzere
//        2 çeşit oluşturabilirlerse çok iyi olur. En az Web’de yapmak zorunlu.
//- Senaryoya üye kaydı oluşturmadan devam edilecek.
//- Giyim--> Aksesuar--> Kadın İç Giyim-->Dizaltı Çorap bölümüne girilir.
        driver.get("http://www.a101.com.tr");
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
        WebElement hooverArea = driver.findElement(By.xpath("(//li[@data-pk='37a6c1c8-a180-4b87-9ec9-3a29f6453a0c'])[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hooverArea).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@title='Dizaltı Çorap']")).click();

// // siyah renk seçimi (siyah renk kutucuğu tuıklandı)
        driver.findElement(By.xpath("//label[@for='attributes_integration_colourSİYAH']")).click();

// (siyah renk kutucuğu tuıklandıktan sonra) Açılan ürünün siyah olduğu doğrulanır.(başlıktan doğrulama)
        String header=driver.findElement(By.xpath("//div[@class='product-desc ']")).getText();
        Assert.assertTrue(header.contains("Siyah"));

// Sepete ekle butonuna tıklanır.( 1 adet ekle butonu tıklanabilir.)
    // (Bu butonun tıklanması ile pop-up açılıyor "Alışverişe devam et" ve "sepeti görüntüle" seçenekleri mevcut)
        driver.findElement(By.xpath("//div[@data-pk='35700']")).click();

// - Sepeti Görüntüle butonuna tıklanır.
        driver.findElement(By.partialLinkText("Görüntüle")).click();

// - Sepeti Onayla butonuna tıklanır.
        driver.findElement(By.xpath("//a[@class='button green checkout-button block js-checkout-button']")).click();

// - Üye olmadan devam et butonuna tıklanır.
        driver.findElement(By.xpath("//a[@class='auth__form__proceed js-proceed-to-checkout-btn']")).click();

// - Mail ekranı gelir.(mail gönderilerek "Devam Et" tıklanır )
        WebElement mail=driver.findElement(By.xpath("//input[@name='user_email']"));
        Faker faker=new Faker();
        mail.sendKeys(faker.internet().emailAddress());
        driver.findElement(By.xpath("//button[@class='button block green']")).click();

// - Sonrasında adres ekranı gelir. Adres oluştur dedikten sonra ödeme ekranı gelir.

        driver.findElement(By.xpath("//a[@class='new-address js-new-address']")).click();
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Ev");
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys(faker.name().firstName());
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys(faker.name().lastName());
        driver.findElement(By.xpath("//input[@name='phone_number']")).
                sendKeys(faker.number().digits(11).formatted("(5##)###-####"));

        WebElement drop_il=driver.findElement(By.xpath("//select[@name='city']"));
            Select il=new Select(drop_il);
            il.selectByIndex(2);
        WebElement drop_ilce=driver.findElement(By.xpath("//select[@name='township']"));
            Select ilce=new Select(drop_ilce);
            ilce.selectByIndex(9);
        Thread.sleep(1000);
        WebElement drop_mah=driver.findElement(By.xpath("//select[@name='district']"));
            Select mah=new Select(drop_mah);
            mah.selectByIndex(5);

        driver.findElement(By.xpath("//textarea[@class='js-address-textarea']")).sendKeys(faker.address().fullAddress());
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(faker.address().zipCode());
        driver.findElement(By.xpath("//button[@class='button green js-set-country js-prevent-emoji']")).click();

// - İsim adres bilgileri girildikten sonra kargo seçmek gerekiyor! Bu adım görevlerde tanımlanmamış.
        // her defasında farklı kargolar, tek yada birkaç kargo firması gelebiliyor.
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[@data-slug='05']")).click();

    // kargo firması seçildikten sonra "Kaydet ve Devam Et" butonuna basılır.
        driver.findElement(By.xpath("//button[@data-index='1']")).click();

// - Siparişi tamamla butonuna tıklayarak, ödeme ekranına gidildiği ,doğru ekrana yönlendiklerini kontrol edecekler.
        Assert.assertEquals("https://www.a101.com.tr/orders/checkout/",driver.getCurrentUrl());
        WebElement odeme=driver.findElement(By.xpath("//div[@data-pk='37']"));
        //System.out.println(odeme.getText());
        Assert.assertEquals(odeme.getText(),"Kart ile ödeme");
    }

}
// 101 satırda A101 web-sayfası testi :)