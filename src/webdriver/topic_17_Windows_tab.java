package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_17_Windows_tab{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	 
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
	
	public void TC_01_Basic_Form() {
		
		// trang A
		driver.get("https://automationfc.github.io/basic-form/");
		
		// Lấy ra ID của driver đang đứng tại Tab/ window (Active)
		String formTabID = driver.getWindowHandle();
		System.out.println("Form tab ID = " + formTabID);

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		switchToWindowByID(formTabID);
		
		// đang ở trang B
		driver.findElement(By.name("q")).sendKeys("selenium");
		sleepInSecond(5);
		String googleTabID = driver.getWindowHandle();
	
		// Quay về trang A
		switchToWindowByID(googleTabID);
		
		// Driver đang ở trang A
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	
	}
	

	
	public void TC_02_Basic_Form_II() {
		// trang A
		driver.get("https://automationfc.github.io/basic-form/");
		
		String parentID = driver.getWindowHandle();

		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		
		switchToWindowByTitle("Google");
		sleepInSecond(2);
		
		// đang ở trang B
		driver.findElement(By.name("q")).sendKeys("selenium");
		
		
		// Quay về trang A
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		// Driver đang ở trang A
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(2);
		// đang ở trang C
		driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("0987654321");

		// Quay về trang A
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		
		// Driver đang ở trang A
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);

		// switch qua trang D
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(2);
		

		closeAllWindowWithoutParrent(parentID);
		sleepInSecond(3);

	}
	
	public void TC_03_TechPanda() {
	
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(3);

		
        // switch qua window này
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        
        Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title h1")).isDisplayed());
        
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
		sleepInSecond(3);
		
        switchToWindowByTitle("Mobile");

		driver.findElement(By.id("search")).sendKeys("automationfc.net");
		sleepInSecond(5);	
	}
	
	@Test
	public void TC_04_Cambridge() {
	
		driver.get("https://dictionary.cambridge.org/vi/");
		
		driver.findElement(By.xpath("//span[@class='lpr-0 hbtn hbtn-t lmt-5 fs15 cdo-login-button']")).click();
		sleepInSecond(3);

        switchToWindowByTitle("Login");
        Assert.assertTrue(driver.findElement(By.cssSelector("div#login h1")).isDisplayed());
        
       driver.findElement(By.xpath("//input[@value='Log in']")).click();
	   sleepInSecond(3);
       Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gigya-composite-control gigya-composite-control-textbox']/child::span[text()='This field is required']")).isDisplayed());
       Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gigya-composite-control gigya-composite-control-password']/child::span[text()='This field is required']")).isDisplayed());

      driver.findElement(By.xpath("(//input[@name='username'])[3]")).sendKeys("automationfc.com@gmail.com");
	  sleepInSecond(3);
      driver.findElement(By.xpath("(//input[@name='password'])[3]")).sendKeys("Automation000***");
	  sleepInSecond(3);
      driver.findElement(By.xpath("//input[@value='Log in']")).click();

      switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
      Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Automation FC']")).isDisplayed());
	  


		
	}
		
	
	// nó chỉ dùng cho duy nhất 2 tab/ 2 window 
    public void switchToWindowByID(String parentID) {
    	// Lấy ra tất cả các ID của Tab/ Window đang đứng 
	Set<String> allWindowIDs = driver.getWindowHandles();
        // Dùng vòng lặp để duyệt qua từng ID 
	for (String id : allWindowIDs) { 
		
		// Nếu như có ID nào mà khác parentID
		if (!id.equals(parentID)) {
			// Switch vào 
			driver.switchTo().window(id);	
			sleepInSecond(3);

		}
	}
	
	
}
   
    public void switchToWindowByTitle(String expectedPageTitle) {
    	// Lấy ra tất cả các ID của Tab/ Window đang đứng 
    	Set<String> allWindowIDs = driver.getWindowHandles();
    
        // Dùng vòng lặp để duyệt qua từng ID 
    	for (String id : allWindowIDs) { 
    		// Switch trước 
    		driver.switchTo().window(id);
    		
    		// lấy ra cái title của page đã switch vào rồi 
    		String currentPageTitle = driver.getTitle();
    		
    		if (currentPageTitle.equals(expectedPageTitle)) {
    			// thoát khỏi vòng lặp ko duyêt nữa
    			break;
			}
    	}
    }
    
    public void closeAllWindowWithoutParrent(String parentID) {
    	// Lấy ra tất cả các ID của Tab/ Window đang đứng 
    	Set<String> allWindowIDs = driver.getWindowHandles();
    	
    	for (String id : allWindowIDs) { 
    		if (!id.equals(parentID)) {
    			driver.switchTo().window(id);
    			driver.close();
				
			}	
    	}
    	// Vẫn còn lại Parent 
    	driver.switchTo().window(parentID);
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
	
