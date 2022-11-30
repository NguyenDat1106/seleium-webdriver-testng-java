package webdriver_hw;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24 {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	//image name
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
		explicitWait = new WebDriverWait(driver, 3);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		
		// click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		// thiếu thời gian để cho element tiết theo hoạt động
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
		
		// get text vào verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");

	}

	
	
	public void TC_02_Invisible() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 5);

		// click vào start button
		driver.findElement(By.cssSelector("div#start button")).click();
		
		// Wait cho loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		

		// Get text vào verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}

	
	public void TC_03_Ajax_loading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver,15);
        
        // Wait cho Date picker dc hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
        
        // Wait cho ngày 27 dc phép dc click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='27']")));
        
        // click vào ngày 27
        driver.findElement(By.xpath("//a[text()='27']")).click();
        
        // Wait cho Ajax icon loading biến mất (Invisible)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
        
        // Wait cho ngày vừa chọn là được phép click trở lại
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='27']")));
        
        // veriffy cho selected Dates là ngày dc chọn
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Tuesday, September 27, 2022");
	}
	
	@Test
	public void TC_04_Upload_File() {
	driver.get("https://gofile.io/uploadFiles");
	explicitWait = new WebDriverWait(driver, 15);
		
	// Wait cho Add Files button được visible
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button.uploadButton")));
	
	driver.findElement(By.cssSelector("input[type='file']")).sendKeys(vietnamFilePath + "\n" + thailanFilePath + "\n" + indonesiaFilePath);
	// Wait loading icon từng file biến mất
	explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#rowUploadProgress-list div.progress"))));
	
	// Wait cho upload message thành công dc visible
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
	
	// verify message này displayed
	Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
	
	//Wait + click cho show file button dc clickable
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#rowUploadSuccess-downloadPage"))).click();
	
	
	// vừa Wait vừa trả về 1 element: cho file name vs button download hiển thị 
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + vietnam + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + thailan + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + indonesia + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());

	// vừa Wait vừa trả về 1 element: cho file name vs button play hiển thị 
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + vietnam + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + thailan + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + indonesia + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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
