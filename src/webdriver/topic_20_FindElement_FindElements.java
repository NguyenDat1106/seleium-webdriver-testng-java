package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_20_FindElement_FindElements{
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_FindElement() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// Xét 3 trường hợp
		// 1 tìm thấy duy nhất 1 element/ node
		// tìm thấy và thao tác trực tiếp lên cái node đó
		 // vì nó tìm thấy nên ko cần phải chờ hết timeout
		driver.findElement(By.cssSelector("input#email"));
		
		
		// 2 tìm thấy nhiều hơn 1 element/ node
		// nó sẽ thao tác với node đầu tiên 
		// ko quan tâm các node còn lại 
		// trong trường hợp bắt locator sai thì tìm sai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("Automation@gmail.com");
		
		// 3 không tìm thấy element/ node nào 
		// có cơ chế tìm lại =  0.5 giây tìm lại 
		// nếu trong thời gian tìm lại mà thấy element thì thỏa mãn điều kiện
		// nếu hết thời gian timeout mà vẫn không thấy element thì
		// + Đánh faile testcare tại step này 
		// + throw ra 1 cái ngoại lệ NoSuchElementException
		driver.findElement(By.cssSelector("input[type='check']"));
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// Xét 3 trường hợp
		// 1 tìm thấy duy nhất 1 element/ node
		// tìm thấy và lưu nó vào list = 1 element
		 // vì nó tìm thấy nên ko cần phải chờ hết timeout
		List<WebElement> elelemts = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = " + elelemts.size());
		
		
		// 2 tìm thấy nhiều hơn 1 element/ node
		// tìm thấy và lưu nó vào list = element tương ứng 
		elelemts = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number = " + elelemts.size());

		// 3 không tìm thấy element/ node nào 
		// có cơ chế tìm lại =  0.5 giây timg lại 
		// nếu trong thời gian tìm lại mà thấy element thì thỏa mãn điều kiện
		// nếu hết thời gian timeout mà vẫn không thấy element thì
		// + ko đánh faile testcare, vẫn chạy step tiếp theo
		// + trả về 1 cái list rỗng (empty) = 0
		elelemts = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println("List element number = " + elelemts.size());
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
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
	
