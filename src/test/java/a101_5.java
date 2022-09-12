import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class a101_5 extends TestBase{

    @Test
    public void test() throws InterruptedException {

//        Ödeme ekranı doldurulmayacak. Aşağıdaki senaryoyu web ve mobil olmak üzere
//            2 çeşit oluşturabilirlerse çok iyi olur. En az Web’de yapmak zorunlu.
//        - Senaryoya üye kaydı oluşturmadan devam edilecek.
//        - Giyim--> Aksesuar--> Kadın İç Giyim-->Dizaltı Çorap bölümüne girilir.
        driver.get("http://www.a101.com.tr");
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
        //driver.navigate().refresh();

        WebElement hooverArea=driver.findElement(By.xpath("(//li[@data-pk='37a6c1c8-a180-4b87-9ec9-3a29f6453a0c'])[1]"));

        Actions actions=new Actions(driver);
        actions.moveToElement(hooverArea).perform();
        Thread.sleep(500);

        driver.findElement(By.xpath("//a[@title='Dizaltı Çorap']")).click();

//        - Açılan ürünün siyah olduğu doğrulanır.
        // siyah renk seçimi
        driver.findElement(By.xpath("//label[@for='attributes_integration_colourSİYAH']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // ürün seçimi
       // driver.findElement(By.xpath("//img[@alt='Doremi Kadın Desenli Dizaltı Çorap Fit15 Denye Siyah']")).click();
        driver.findElement(By.xpath("//div[@data-pk='35700']")).click();
        // siyah onayı
        WebElement urunRenk=driver.findElement(By.xpath("//h1[@class='product-name js-name']"));
        urunRenk.getText();
        Assert.assertTrue(urunRenk.getText().contains("Siyah"));

//        - Sepete ekle butonuna tıklanır.
        driver.findElement(By.xpath("//button[@data-sku='25003594001']")).click();
    //    Thread.sleep(2000);
//        - Sepeti Görüntüle butonuna tıklanır.
        driver.findElement(By.xpath("//a[@class='go-to-shop']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        - Sepeti Onayla butonuna tıklanır.
        driver.findElement(By.xpath("//a[@class='button green checkout-button block js-checkout-button']")).click();

//        - Üye olmadan devam et butonuna tıklanır.
        driver.findElement(By.xpath("//a[@class='auth__form__proceed js-proceed-to-checkout-btn']")).click();
//        - Mail ekranı gelir.
        WebElement mail=driver.findElement(By.xpath("//input[@name='user_email']"));
        Faker faker=new Faker();
        mail.sendKeys(faker.internet().emailAddress());
        driver.findElement(By.xpath("//button[@class='button block green']")).click();

//        - Sonrasında adres ekranı gelir. Adres oluştur dedikten sonra ödeme ekranı gelir.
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
        ilce.selectByIndex(3);

        Thread.sleep(2000);
        WebElement drop_mah=driver.findElement(By.xpath("//select[@name='district']"));
        Select mah=new Select(drop_mah);
        mah.selectByIndex(6);



        driver.findElement(By.xpath("//textarea[@class='js-address-textarea']")).sendKeys(faker.address().fullAddress());


        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(faker.address().zipCode());
        driver.findElement(By.xpath("//button[@class='button green js-set-country js-prevent-emoji']")).click();

    //Thread.sleep(2000);
//        //    driver.findElement(By.xpath("//input[@value='267']")).click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//      driver.findElement(By.xpath("(//label[@class='js-checkout-cargo-item'])[1]")).click();
//        driver.findElement(By.xpath("//button[@class='button block green js-proceed-button']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement cargoRadio=driver.findElement(By.xpath("//input[@class='js-shipping-radio']"));
        if(!cargoRadio.isSelected()){
            driver.findElement(By.xpath("(//label[@class='js-checkout-cargo-item'])[1]")).click();
        }
        driver.findElement(By.partialLinkText("Kaydet ")).click();


//        - Siparişi tamamla butonuna tıklayarak, ödeme ekranına gidildiği ,doğru ekrana yönlendiklerini kontrol edecekler.

        Assert.assertEquals("https://www.a101.com.tr/orders/checkout/",driver.getCurrentUrl());

        WebElement odeme=driver.findElement(By.xpath("//div[@data-pk='37']"));
        System.out.println(odeme.getText());
        Assert.assertEquals(odeme.getText(),"Kart ile ödeme");

    }
}
