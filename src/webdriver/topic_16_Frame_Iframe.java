package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_16_Frame_Iframe{
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

	
	public void TC_01_Iframe_Kyna() {
		
		//A
		driver.get("https://kyna.vn/");
		
		// switch to
		// alert 
		// frame/iframe
		// windows/ tab
		
		// Index
		//driver.switchTo().frame(0);
		
		// ID/ Name
		//driver.switchTo().frame("");
		
		// Welement: all cases
		//A->B
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage  iframe")));
		
		//B
		String facebookLikeNumber = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(facebookLikeNumber, "165K likes");
		
		// ko support nhảy từ iframe B qua iframe C ( 2 Iframe này thuộc A)
		
		//B->A
		driver.switchTo().defaultContent();
		
		//A->C
		driver.switchTo().frame("cs_chat_iframe");
		
		//C (element thuộc c)
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Wick");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea.meshim_widget_widgets_TextArea")).sendKeys("Testing");
        sleepInSecond(5);
        
        //C->A
		driver.switchTo().defaultContent();
		
		//A
		String ketword = "Excel";
		driver.findElement(By.cssSelector(" input#live-search-bar")).sendKeys(ketword);
		driver.findElement(By.cssSelector("button.search-button")).click();
        sleepInSecond(5);
        
        // verify
        List<WebElement> courseNames = driver.findElements(By.cssSelector("div.content h4"));
        
        // Number
        Assert.assertEquals(courseNames.size(), 9);
          for (WebElement course : courseNames) {
        	  System.out.println(course.getText());
        	  // course name catains keyword
        	  Assert.assertTrue(course.getText().contains(ketword));
			
		}
        
        


		
		
	}

	@Test
	public void TC_02_() {
	driver.get("https://netbanking.hdfcbank.com/netbanking/");

	driver.switchTo().frame("login_page");
	driver.findElement(By.name("fldLoginUserId")).sendKeys("automationfc");
	driver.findElement(By.cssSelector("a.btn")).click();
	sleepInSecond(3);
	
	WebElement passwordTextbox = driver.findElement(By.cssSelector("input#fldPasswordDispId"));
	Assert.assertTrue(passwordTextbox.isDisplayed());
	
	passwordTextbox.sendKeys("automationfc");
	sleepInSecond(3);

		
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
	
