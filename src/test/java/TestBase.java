import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestBase {
    protected static WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }
//    @After
//    public void tearDown() throws InterruptedException {
//        Thread.sleep(3000);
//        driver.quit();
//    }

    public void takeScreenshot() throws IOException {
// Taking screenshot
        File image=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //or seperate syntax      TakesScreenshot ts= (TakesScreenshot) driver;
        //        File image2=ts.getScreenshotAs(OutputType.FILE);

// Saving screenshot
        // we can add a time sekans to change the image names to get new & keep every one
        String imageName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // we need to define the path ;
        String path = System.getProperty("user.dir")+"/test-output/Screenshots/"+imageName+"image.png";
        FileUtils.copyFile(image,new File(path));
    }


    //  from Day 11 // creating a reusable method to scroll into  view of an element
    //   *** IMPORTANT ***
    public void  scrollIntoViewJS(WebElement element){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)",element);
    }

    //   same with previous; Scrolls onto a specific element. Param : webelement
    //    public void scrollIntoViewByJS(WebElement element){
    //        JavascriptExecutor js = (JavascriptExecutor)driver;
    //        js.executeScript("arguments[0].scrollIntoView(true);",element);
    //    }

    //    Scroll all the way down of a page
    public void scrollAllDownByJS(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    //    Scroll al the way up of a page
    public void scrollAllUpByJS(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    //    Click on a specific element. Param: WebElement
    //   *** IMPORTANT ***     ==> generally when the selenium click has trouble
    public void clickByJS(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()",element);
    }

    //    Set the value of an input using js executor. Params: WebElement element, String text
//    This method changes the value attribute of an element.
//    It changes the input text  NOT SO COMMON
    public void setValueByJS(WebElement element, String text){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('value','"+text+"')",element);
    }

    //  To  get the value of an input. param: idOfElement
    //   *** A LITTLE IMPORTANT ***
    public String getValueByJS(String idOfElement){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String value=js.executeScript("return document.getElementById('"+idOfElement+"').value").toString();
        System.out.println(value);
        //        How you get get the value of an input box?
        //        We can js executor.
        //        How?
        //        I can get the element using js executor, and get teh value of the element.
        //        For example, I can get the element by id, and use value attribute to get the value of in an input
        //        I have to do this, cause getText in this case does not return teh text in an input
        return value;
    }

    //    Changes the changeBackgroundColorByJS of an element. Params: WebElement element, String color
    public void changeBackgroundColorByJS(WebElement element, String color){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].style.backgroundColor='"+color+"'",element);
    }

    // NOT COMMON
    public void addBorderWithJS(WebElement element, String borderStyle){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].style.border='"+borderStyle+"'",element);
    }
}
