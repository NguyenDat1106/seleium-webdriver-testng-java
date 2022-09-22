package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03{
	// Bước 1 mở Browser lên
	// Bước 2 Nhập vào Url
	// Bước 3 Click vào My account để mở trang login ra
	// Bước 4 Click login
	// Bước 5 Verify lỗi hiển thị
	// Bước 6 Đóng Browser
	
	// Khai báo 1 biến để đại diện cho thư viện Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Bước 1 mở Browser lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Bấm cho full Browser lên 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_() {
		// Bước 2 Nhập vào Url
		driver.get("http://live.techpanda.org/index.php/customer/login/");
		
		// Bước 3 Click vào My account để mở trang login ra
		// HTML của Element (Email Textbox)
		// <input type="mail" autocapitalize="none"
		// autocorrect="of" spellcheck="false"
		// name="logim[username]" value=""
		// id="email" class="input-test required-entry validate-email"
		// title="Email Address">
		
		// iput - thẻ của element này (tagname)
		// attribute - type autocapitalize autocorrect spellcheck name value id class title  
		// attribute value - mail none of ...
		
		// Xpath Format
		// tagname[@attribute-name='attribute-value']
		
		// input[@id="email"] -> *
		// input[@name="logim[username]"] -> *
		// input[@title="Email Address"] -> *
		
		// CSS format   tagname[@attribute-name='attribute-value']
		
		// ID 
		driver.findElement(By.id("email"));

		
		// class - New User form
		// 1 - giá trị không có không có khoảng trống -> lấy hêt
		// 2 - Giá trị chứa khoảng trống -> lấy 1 phần 
		driver.findElement(By.className("new-users"));
		
		
		// name - Email textbox
		driver.findElement(By.name("logim[username]")); 
		
		// tagname  tìm xem có bao nhiêu cái element/ page
		driver.findElement(By.tagName("a"));
		
		// linktext (link) - Text tuyệt đối
		driver.findElement(By.linkText("SEARCH TERMS")); 
		
		// partial linktext - text tương dối/ tuyệt đối
		driver.findElement(By.linkText("SEARCH TERMS"));
		driver.findElement(By.linkText("CH TER"));
		driver.findElement(By.linkText("SEARCH"));
		driver.findElement(By.linkText("TERMS"));
		
		// css - cover được hết 6 loại trên 
		
		driver.findElement(By.xpath("input[name='logim[username]']"));
		driver.findElement(By.xpath("input[id='email']"));
		driver.findElement(By.xpath("input[title='Email Address']"));
		
		// xpath
		
		driver.findElement(By.xpath("// input[@name='logim[username]']"));
		driver.findElement(By.xpath("// input[@id='email']"));
		driver.findElement(By.xpath("	// input[@title='Email Address']"));
	// tìm nhiều Element
		// driver.findElements
		// click lên 
		
		
	}
	
	
	@Test
	public void TC_02_() {
		
	}

	

	@AfterClass
	public void afterClass() {
		// Bước 6 Đóng Browser
		driver.quit();
	}
}
