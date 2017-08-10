import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.util.*;

public class FirefoxProfiles {

	public static void main(String[] args) {
		
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg");
		profile.setPreference("browser.download.dir", "\\downloadedPictures");
		
		List<WebElement> we = new ArrayList<>();
		
		WebDriver driver = new FirefoxDriver();
		

	}

}
