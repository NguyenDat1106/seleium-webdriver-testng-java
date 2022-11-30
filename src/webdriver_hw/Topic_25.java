package webdriver_hw;
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

public class Topic_25 {
	WebDriver driver;
	WebDriverWait explitcitWait;
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
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Element_Found() {
		driver.get("https://www.facebook.com/");
		// element có xuất hiện và không chờ hết timeout
		// dù có xết 2 loại wait thì ko bị ảnh hưởng
		explitcitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// implicitwait chỉ apply cho việc findelement/findelements
		// explicitwwait cho các đk của element
		
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		explitcitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
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
		explitcitWait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		try {
			explitcitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc của explicit:" + getTimeStamp());
		}
	}

	@Test
	public void TC_04_Element_Not_Found_Explicit_By() {
		explitcitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		
		// explicit - by là tham số nhận vào của hàm explicit - visibilityByElementLocator(By)
		// Implicit = 0
		// tổng time = explicit
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		try {
			explitcitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của explicit:" + getTimeStamp());
		}
		
	}
	
	public void TC_05_Element_Not_Found_Explicit_Element() {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		explitcitWait = new WebDriverWait(driver, 5);
		
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		try {
			explitcitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
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
