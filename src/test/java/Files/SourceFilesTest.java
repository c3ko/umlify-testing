package Files;
import Driver.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;

public class SourceFilesTest {

    private static WebDriver driver;
    @BeforeAll
    public static void setUpAll(){
        WebDriverManager.setUpChromeWebDriver();
        driver = WebDriverManager.getWebDriver();


    }

    @BeforeEach
    public void SetUpEach(){
        driver.get("http://umlify.com");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void testInitialLoad(){
        WebElement aceEditorDiv = driver.findElement(By.id("ace-editor"));
        //Click on aceEditorDiv to "Focus"
        aceEditorDiv.click();
        // Test if Text area is readOnly
        WebElement editorTextArea = driver.findElement(new By.ByCssSelector("textarea.ace_text-input"));
        Assertions.assertTrue(editorTextArea.getAttribute("readOnly").equals("true"), "Element Read Only");

    }


    @Test
    public void testCreateFile(){
        String fileName = "Dog.java";
        String code = "public class Dog { public int x; private String y; protected bool z; }";


    }

    @Test
    public void testWriteCode(){

    }

    @AfterAll
    public static void tearDown(){

    }



}
