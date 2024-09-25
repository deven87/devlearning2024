package playwright.playwright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	@Test
	public void runPlaywright() {
		
		Playwright playwright = Playwright.create();
		
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		Page page = browser.newPage();
		
		page.navigate("https://www.intelligent.com");
		
		System.out.println(page.url());
		
		System.out.println(page.title());
		
	}
	

}
