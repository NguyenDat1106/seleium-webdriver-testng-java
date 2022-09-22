package webdriver_hw;
import java.io.File;
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

public class Topic_19 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	// image name 
	String vietnam = "vietnam.jpg";
	String thailan = "thailan.jpg";
	String indonesia = "indonesia.jpg";
	
	// upload file foder
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
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='vietnam.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='thailan.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='indonesia.jpg']")).isDisplayed());
		
		// thực hiên upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		
		// dùng vòng lặp để thực hiện
		for (WebElement startButton : startButtons) {
		startButton.click();
		sleepInSecond(3);
		
		}
		// verify image upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='vietnam.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='thailan.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='indonesia.jpg']")).isDisplayed());
	}

	@Test
	public void TC_02_() {
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
