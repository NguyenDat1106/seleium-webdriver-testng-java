package webdriver;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_25_Mix_Implicit_Explicit{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
	
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
	}
	
	public void TC_01_Element_Found() {
		// element có xuất hiện và không chờ hết timeout
		// dù có xét 2 loại wait thì ko bị ảnh hưởng 
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Implicit wait: chỉ apply cho việc findelement/findelements
		// explicit wait: cho các điều kiện của element 
		
		driver.get("https://www.facebook.com/");
		
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("Thời gian kết thúc của explicit:" + getTimeStamp());
		
		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Thời gian kết thúc của implicit:" + getTimeStamp());


	}

	
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");

		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc của implicit:" + getTimeStamp());
		}
			
	}

	
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		// 3.1 Implicit = Explicit
		// 3.2 Implicit < Explicit
		// 3.3 Implicit > Explicit

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		
		
		// Explicit
				System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
				try {
					explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Thời gian kết thúc của explicit:" + getTimeStamp());
				}
	}

	
	public void TC_04_Element_Not_Found_Explicit_By() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		
		// Explicit - By là tham số nhận vào của hàm explicit - visibilityByElementLocated(By)
		// Inplicit = 0
		// tổng time = Explicit
				System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
				try {
					explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Thời gian kết thúc của explicit:" + getTimeStamp());
				}
	}

	@Test
	public void TC_05_Element_Not_Found_Explicit_Element() {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit:" + getTimeStamp());
		}
	}
	
	@AfterClass
	public void afterClass() {
   driver.quit();
}
	// Show ra cái time-stamp tại thời điểm gọi hàm này 
	// time-stamp: ngày-giờ-phút-giây
	public String getTimeStamp() {
	  Date date = new Date();
	 return date.toString();

			
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
	
