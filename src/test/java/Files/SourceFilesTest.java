package Files;
import Driver.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SourceFilesTest {

    private static WebDriver driver;

    private static WebDriverWait wait;



    @BeforeAll
    public static void setUpAll(){
        WebDriverManager.setUpChromeWebDriver();
        driver = WebDriverManager.getWebDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @BeforeEach
    public void setUpEach(){
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

        // Click Add New File (+) Button
        WebElement addButton = driver.findElement(new By.ByCssSelector("span.file-panel-toolbar button:nth-child(1)"));

        addButton.click();

        // Check current focused input is correct
        WebElement focusedElement = driver.switchTo().activeElement();
        WebElement fileListItem = driver.findElement(new By.ByCssSelector("input.file-list-item-input"));
        Assertions.assertTrue(fileListItem.equals(focusedElement), "New file Item input focused");

        // Enter File Name and create Enter, test if File Name has been added to File List
        focusedElement.sendKeys("Dog.java");
        focusedElement.sendKeys(Keys.ENTER);
        // Find new FileListItem (should be selected)
        WebElement createdFileListItem = driver.findElement(new By.ByCssSelector("input.file-list-item-input.selected"));
        Assertions.assertEquals("Dog.java", createdFileListItem.getAttribute("value"), "Dog.java file added to panel");


        // Check that code input is focused after creating file name
        WebElement editorTextArea = driver.findElement(new By.ByCssSelector("textarea.ace_text-input"));
        focusedElement = driver.switchTo().activeElement();

        Assertions.assertTrue(editorTextArea.equals(focusedElement), "New file code input is focused");



        // Input Code in to code input text area and submit
        editorTextArea.sendKeys(code);
        // Input



        //
    }

    @Test
    public void testAddFileFromLocal(){

    }

    @Test
    public void testModifyFileName(){

    }





    @Test
    public void testFileDeletion(){

    }


    @Test
    public void testAllFileDeletion(){

    }


    @Test
    public void testSubmission(){
        String fileName = "Dog.java";
        String code = "public class Dog { public int x; private String y; protected bool z; }";

        // Click Add New File (+) Button
        WebElement addButton = driver.findElement(new By.ByCssSelector("span.file-panel-toolbar button:nth-child(1)"));

        addButton.click();

        // Check current focused input is correct
        WebElement focusedElement = driver.switchTo().activeElement();

        // Enter File Name and create Enter, test if File Name has been added to File List
        focusedElement.sendKeys(fileName);
        focusedElement.sendKeys(Keys.ENTER);

        // Switch to Code Input
        focusedElement = driver.switchTo().activeElement();
        focusedElement.sendKeys(code);

        WebElement submitButton = driver.findElement(new By.ByCssSelector("button.reset-button"));
        submitButton.click();


        // Check if SVG is generated
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement svg = wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByCssSelector(".uml-panel-container svg")));
        Assertions.assertNotNull(svg, "SVG Generated");
    }

    @Test
    public void testDiagramDownload(){
        String fileName = "Dog.java";
        String code = "public class Dog { public int x; private String y; protected bool z; }";

        // Click Add New File (+) Button
        WebElement addButton = driver.findElement(new By.ByCssSelector("span.file-panel-toolbar button:nth-child(1)"));

        addButton.click();

        // Check current focused input is correct
        WebElement focusedElement = driver.switchTo().activeElement();

        // Enter File Name and create Enter, test if File Name has been added to File List
        focusedElement.sendKeys(fileName);
        focusedElement.sendKeys(Keys.ENTER);

        // Switch to Code Input
        focusedElement = driver.switchTo().activeElement();
        focusedElement.sendKeys(code);

        WebElement submitButton = driver.findElement(new By.ByCssSelector("button.reset-button"));
        submitButton.click();


        // Download Content is now clickable and SVG is downloadable



    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }



}
