
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class MyTestCase {
	String WebsiteURL = "https://www.saucedemo.com/";

	WebDriver driver = new ChromeDriver();
	Assertion myAssert = new Assertion(); // (تأكيد )بتأكدلي اذا التيست كيس تمام ولا لا

	@BeforeClass // Pascal :The first letter of each word capital
	public void MyBeforeTest()  throws InterruptedException{
	
		String Username = "standard_user";
		String Password = "secret_sauce";

		driver.get(WebsiteURL);

		WebElement EmailInput = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
		EmailInput.sendKeys(Username);

		WebElement PasswordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		PasswordInput.sendKeys(Password);

		WebElement ButtonLogin = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
		ButtonLogin.click();
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	@Test(priority=1)
	public void AddAllItemToCart() throws InterruptedException {
		Thread.sleep(5000);
		List <WebElement> AllButtonsThatAddToCart = driver.findElements(By.className("btn_primary"));
		for (int i=0 ; i < AllButtonsThatAddToCart.size() ; i++) {
			AllButtonsThatAddToCart.get(i).click();
		}
		//System.out.println(AllButtonThatAddToCart.size() );
		
		WebElement ActualItemsInCart = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
		String actualValueString = ActualItemsInCart.getText();
		int ConvertActualToInt = Integer.parseInt(actualValueString);
		
		int ExpectedVlaue =AllButtonsThatAddToCart.size();
		myAssert.assertEquals(ConvertActualToInt ,ExpectedVlaue );
	}
	

	@Test(priority =2)
	public void RemoveAllItemsFromTheCart() throws InterruptedException {
		Thread.sleep(3000);
		List <WebElement> RemoveAllItemsFromCart = driver.findElements(By.className("btn_secondary"));
		for (int i=0 ; i < RemoveAllItemsFromCart.size() ; i++) {
			RemoveAllItemsFromCart.get(i).click();
		}
		
		List <WebElement> AllButtonsThatAddToCart = driver.findElements(By.className("btn_primary"));
		int ActualItemsInCart=  AllButtonsThatAddToCart.size()-AllButtonsThatAddToCart.size();
		
		int ExpectedItemsAfterRemove =RemoveAllItemsFromCart.size()-RemoveAllItemsFromCart.size();
		myAssert.assertEquals(ActualItemsInCart, ExpectedItemsAfterRemove);
	
	}


       @Test(priority=3)
       public void TestTheTitle() {
    	   String ExpectedTitle = "Swag Labs";
    	   String ActualTitle = driver.getTitle();
    	   //System.out.println(driver.getTitle());

    	   myAssert.assertEquals(ActualTitle, ExpectedTitle);
       }
}
