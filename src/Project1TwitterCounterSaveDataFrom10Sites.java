import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Project1TwitterCounterSaveDataFrom10Sites {	
	
	WebDriver driver;	
	
	
	@Before
	public void setup(){		

		driver = new FirefoxDriver();		
		
	}
	
	@Test
	public void chechNumberOfFollowers(){
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter("top1000.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i = 0; i < 10; i++){
			driver.get("https://twittercounter.com/pages/100/" + (i * 100));
			List<WebElement> listOfUserNames = driver.findElements(By.className("uname"));
			for(WebElement e : listOfUserNames){
				writer.println(e.getAttribute("href").replaceAll("https://twittercounter.com/", "http://twitter.com/"));
			}			
		}
		
		writer.close();
		
		
	}
	
	@After
	public void tearDown(){		
		
		driver.manage().deleteAllCookies();
		driver.quit();
		
	}

}
