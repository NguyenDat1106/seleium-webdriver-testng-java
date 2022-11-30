package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_19_Upload_Sendkey{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firefoxSinglePath = projectPath + "\\autoITScripts\\" + "firefoxUploadOneTime.exe"; 
	String chromeSinglePath = projectPath + "\\autoITScripts\\" + "chromeUploadOneTime.exe"; 
	String firefoxMultiplePath = projectPath + "\\autoITScripts\\" + "firefoxUploadMultiple.exe"; 
	String chromeMultiplePath = projectPath + "\\autoITScripts\\" + "chromeUploadMultiple.exe"; 

	
	// image name
	String vietnam = "vietnam.jpg";
	String thailan = "thailan.jpg";
	String indonesia = "indonesia.jpg";
	
	// Upload file foder
	String uploadFileFoderPath = projectPath + File.separator + "uploadFiles" + File.separator;
	
	// image path
	String vietnamFilePath = uploadFileFoderPath + vietnam;
	String thailanFilePath = uploadFileFoderPath + thailan;
	String indonesiaFilePath = uploadFileFoderPath + indonesia;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	public void TC_01_Upload_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// load file lên
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(vietnamFilePath);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(thailanFilePath);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(indonesiaFilePath);
		
		// verify image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + indonesia + "']")).isDisplayed());
		
		// thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(3);
		}
		// verify image upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + indonesia + "']")).isDisplayed());

	}
	
	public void TC_02_Upload_Multiple_File_Per_Time() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// load file lên
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(vietnamFilePath + "\n" + thailanFilePath + "\n" + indonesiaFilePath);
		
		
		// verify image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + indonesia + "']")).isDisplayed());
		
		// thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(3);
		}
		// verify image upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + indonesia + "']")).isDisplayed());
	}
	
	public void TC_03_Upload_One_File_Time_AutoIT () throws IOException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        
        driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		
		// load file bằng AutoIT
		if (driver.toString().contains("firefox")) {
		Runtime.getRuntime().exec(new String [] {firefoxSinglePath, vietnamFilePath}); 
		}
		else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] {chromeSinglePath, vietnamFilePath});
			
		}
			
		
		//Runtime.getRuntime().exec(new String [] {firefoxSinglePath, vietnamFilePath}); 

		// verify image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + vietnam + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thailan + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + indonesia + "']")).isDisplayed());
				
		// thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
				
		for (WebElement startButton : startButtons) {
		startButton.click();
		sleepInSecond(3);
		}
		// verify image upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thailan + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + indonesia + "']")).isDisplayed());
	}
	
	
	public void TC_04_Upload_Multiple_File_Time_AutoIT () throws IOException {
		 driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	        
	        driver.findElement(By.cssSelector("span.btn-success")).click();
			sleepInSecond(3);
			
			// load file bằng AutoIT
			if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String [] {firefoxMultiplePath, vietnamFilePath, thailanFilePath, indonesiaFilePath}); 
			}
			else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
				Runtime.getRuntime().exec(new String[] {chromeMultiplePath, vietnamFilePath});
				
			}
			// verify image load lên thành công
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + vietnam + "']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thailan + "']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + indonesia + "']")).isDisplayed());
			
			// thực hiện upload
			List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
			
			for (WebElement startButton : startButtons) {
				startButton.click();
				sleepInSecond(3);
			}
			// verify image upload lên thành công
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thailan + "']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + indonesia + "']")).isDisplayed());	
	}
	
	@Test
	public void TC_05_Upload_One_File_Time_Java_Robot () throws IOException, AWTException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// load file bằng Robot
		// giả lập hành vi là copy paste 1 file bất kì -> Java support
		// giả lập hành vi paste và enter vào open file dialog
		
		
		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		
		loadFileByRobot(vietnamFilePath);
		
		//Runtime.getRuntime().exec(new String [] {firefoxSinglePath, vietnamFilePath}); 

		// verify image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + vietnam + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thailan + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + indonesia + "']")).isDisplayed());
				
		// thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
				
		for (WebElement startButton : startButtons) {
		startButton.click();
		sleepInSecond(3);
		}
		// verify image upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thailan + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + indonesia + "']")).isDisplayed());
	}
	@AfterClass
	public void afterClass() {
	driver.quit();
	}
	
	public void loadFileByRobot(String filePath) throws AWTException {
		StringSelection select = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		 // load file 
		Robot robot = new Robot();
		sleepInSecond(1);
		
		
		// nhấn xuống ctrl + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		// nha ctrl + V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);

		// nhan enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(1);

	}
	
	// Sleep cứng (Static wait)	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
	}
}
//HTML Format
	//<input class='text form-control'
	// id='txtemail' name='txtname'
	// placeholder='Địa chỉ email'
	// type='email' value=''>
	
	// input[@class='text form-control']
	// input [@id='txtEmail'] -> **
	// input [@name='txtEmail'] -> **
	// input [@placeholder='Địa chỉ email']
	// input [@type='email']
	// input [@value=''] 
	
	
	// 1 - < hoặc <>
	// 2 - tên thẻ (tagname): input/himl/head/body/form/label/span/...
	// 3 - thuộc tính (Attribute name): class/id/name/placeholder/type/value
	// 4 - giá trị của thuộc tính (Attribuute value): text form-control/txtemail/...
	// 5 - > hoặc </>
	
	// tagname - attribute name - attribute value
	
	// Xpatth Format Basic
	// Absolute Xpath: /html/body/...
	// Relative Xpath: //tagname[@attribute-name='attribute-value']
	
	
	// CSS Format Basic
		// Relative Xpath:tagname[attribute-name='attribute-value']
	
