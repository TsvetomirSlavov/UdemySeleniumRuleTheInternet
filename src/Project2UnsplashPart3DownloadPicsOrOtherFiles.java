import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Project2UnsplashPart3DownloadPicsOrOtherFiles {
	
	WebDriver driver;		
	Actions action;
	FirefoxProfile profile;
	
	@Before
	public void setup() throws InterruptedException{		
					
		profile = new FirefoxProfile();
		File file = new File("double_click_image_downloader-5.4.2-fx.xpi");
		profile.addExtension(file);		
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", "\\downloadedPictures");

		driver = new FirefoxDriver(profile);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		driver.manage().window().setPosition(new Point(1920, 0));
		driver.manage().window().setSize(new Dimension(1920/2, 1080)); //width and height
		driver.get("http://www.unsplash.com");
		
	}
	
	@Test
	public void chechNumberOfFollowers() throws InterruptedException{	
		
		driver.findElement(By.xpath("//*[@id='app']/div/div[3]/div/div[3]/div/span[2]/div/button[2]")).click();
		action = new Actions(driver);
		for(int i = 0; i < 3; i ++){			
			action.sendKeys(Keys.PAGE_DOWN).perform();
		}
		
		//Pattern p = Pattern.compile("/?photo=eKTUtA74uN0");  replace the changing part of a String with    'LIKE OPERATOR' (.*?)      to find similar Strings
		Pattern p = Pattern.compile("/?photo=(.*?)\""); // ESCAPE " BECAUSE I NEED SOMETHING IN THE END TO BE ABLE TO WORK WITH THIS CODE
		Matcher m = p.matcher(driver.getPageSource());

		while(m.find()){
			driver.get("https://unsplash.com/?" + m.group().replace('"', ' ')); // replace the quotes that I added in order for the regex that I get returned to exclude the quotes so I can go to this string as a URL
			
			// TO ACHIEVE THE IMAGE DOWLOAD SEARCH GOOGLE FOR DOUBLE CLICK IMAGE DOWNLOADER ADD ON AND INSTALL IT, TEST IT AND THAN ADD IT IN THE CODE
			// GO TO THE INSTALLATION PAGE OF THE EXTENSION, RIGHT CLICK ON THE INSTALL BUTTON, SAVE AS AND SAVE THE ACTUAL FILE OF THIS EXTENSION SO I CAN INSTALL IT IN THE NEW INSTANCE OF DRIVER
			
			Robot robot = null;
			try {
				robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_S);
				// press Enter
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);	
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// press Ctrl+S the Robot's way
			
			System.out.println(m.group());
		}
	}
	
	@After
	public void tearDown(){		
		
		driver.manage().deleteAllCookies();
		driver.quit();
		
	}

	
	




}
