package webdriver_hw;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21 {
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

	
	public void TC_01_FindElement() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// xét 3 trường hợp 
		// 1 tìm thấy duy nhất 1 element/node
		// tìm thấy và thao tác trực tiếp trên cái node đó
		// vì nó timg thấy nên không cần phải chờ hết timeout
		driver.findElement(By.className("input#email"));
		
		// 2 tìm thấy nhiều hơn 1 element/ node
		// nó sẽ thao tác với node đầu tiên
		// ko quan tâm các node còn lại
		// trong trường hợp bắt locator sai thì tìm sai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("Automation@gmail.com");
		
		// 3 không tìm thấy element/ node nào 
		// có cơ chế tìm lại = o.5 giây thì tìm lại 1 lần
		// nếu trong thời gian tìm lại element mà thấy thì thỏa mãn điều kiện
		// nếu hết thời gian timeout mà vẫn không thấy element thi
		// + đánh faile testcase tại step này
		// + throw ra 1 cái ngoại lệ NoSearchElementException
		driver.findElement(By.cssSelector("input[type='check']"));
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// xét 3 trường hợp 
		// 1 tìm thấy duy nhất 1 element/ node
		// tìm thấy và lưu vào list = 1 element
		// vì nó tìm thấy nên không càn phải chờ hết timeout
		List<WebElement> elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number = " + elements.size());
		
		// 2 tìm thấy nhiều hơn 1 element/ node
		// tìm thấy và lưu nó vào list= element tương ứng
		elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number = " + elements.size());
		
		// 3 không tìm thấy element/ node nào
		// có cơ chế tìm lại = 0.5 giây sẽ tìm lại 1 lần
		// nếu trong thời gian timd lại thấy element thì thảo mãn điều kiện
		// nếu hết thời gian timeout mà vẫn không tìm thấy thì
		// + ko đánh faile testcare, vẫn chạy step tiếp theo
		// + trả về 1 cái list rỗng (empty) = 0
		elements = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println("List element number = " + elements.size());


		
		

		
		
		
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
