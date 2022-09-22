package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_15_Fired_Popup{
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		;
		
	}

	
	public void TC_01_NgoaiNgu24h() {
		driver.get("https://ngoaingu24h.vn/");
		
		WebElement loginPopup = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
		
		// verify login popup is undisplayed
		Assert.assertFalse(loginPopup.isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);
		
		// verify login popup is displayed
		Assert.assertTrue(loginPopup.isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
		
		//click to popup
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='close']")).click();
		sleepInSecond(3);
		
		// verify login popup is undisplayed
		Assert.assertFalse(loginPopup.isDisplayed());
	}
	
	public void TC_02_Fixed_In_Dom_Kyna() {
		driver.get("https://kyna.vn/");
		
		WebElement loginPopup = driver.findElement(By.cssSelector("div#k-popup-account-login"));
		
		Assert.assertFalse(loginPopup.isDisplayed());
		
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		Assert.assertTrue(loginPopup.isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

		//click to popup
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(3);
				
		// verify login popup is undisplayed
		Assert.assertFalse(loginPopup.isDisplayed());
			}
	@Test
	public void TC_03_Fixed_Not_In_Dom_Tiki() {
		driver.get("https://tiki.vn/");
		
		// trong trường hợp popup ko có trong Dom thì elements sẽ tìm thấy 0 element 
		// và cũng chờ hết timeout của implicitWait nhưng ko có đánh false testcase và cũng ko show exception
		// trả về 1 empty list = 0 element
		List<WebElement> loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
	
		Assert.assertEquals(loginPopup.size(), 0);
		
		// click vào đăng nhập để show popup lên
		driver.findElement(By.cssSelector("span.Userstyle__NoWrap-sc-6e6am-12")).click();
		sleepInSecond(3);
		
		// Displayed (Single element: findelement)
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
		
		// Displayed (Multiple element: findelements)
		loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		Assert.assertEquals(loginPopup.size(), 1);
		Assert.assertTrue(loginPopup.get(0).isDisplayed());
		
		// click để đóng popup 
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(3);
		
		// Undisplayed
		loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		Assert.assertEquals(loginPopup.size(), 0);

	}
	
	
	public void TC_04_Radom_In_Dom_KMPlayer() {
		driver.get("https://www.kmplayer.com/home");
				
		WebElement popupPlayer = driver.findElement(By.cssSelector("div.pop-layer"));
		
		// phải chạy testcase dù popup có hiển thị hay ko
		// đang trong đợt sale nó hiển thị thì cript thì mình phải đóng nó để chạy tiếp
		// hết đợt sale ko hiển thị thì cript chạy luôn
		// muốn test thử thì sẽ cố tình tắt popup đi trước khi gọi tới hàm check displayer
		if (popupPlayer.isDisplayed()) {
			// close nó đi để chạy tiếp cái script tiếp theo
			System.out.println("------- step để close popup ------- ");
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSecond(3);
		 
			System.out.println("------- step tiếp theo ------- ");
			
			driver.findElement(By.cssSelector("p.donate-coffee")).click();
			sleepInSecond(3);
			
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.buymeacoffee.com/kmplayer?ref=hp&kind=top");	

		}
         else {
		}	
	}
	
	
	public void TC_05_Radom_Not_In_Dom() {
		
		// độ phân giải màn mình thấp 1366x768
		driver.manage().window().setSize(new Dimension(1366, 768));
		
		driver.get("https://dehieu.vn/");
		sleepInSecond(3);

		// đang trong đợt sale nó hiển thị thì cript thì mình phải đóng nó để chạy tiếp
		// hết đợt sale ko hiển thị thì cript chạy luôn
		List<WebElement> contentPopup = driver.findElements(By.cssSelector("div.popup-content"));
		
		// Nếu Element > 0 (tương ứng 1 trở lên 
		if (contentPopup.size() > 0 && contentPopup.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("John Wick");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("JohnWick2022@gmail.net");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0987654321");
			sleepInSecond(10);

			
			// close popup đi 
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
			sleepInSecond(3);
		}
		
		// step tiếp theo
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");	

		
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
	
