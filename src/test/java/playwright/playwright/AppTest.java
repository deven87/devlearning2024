package playwright.playwright;

import java.nio.file.Paths;
import java.util.List;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.SelectOption;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	@Test(description="basic playwright test", enabled = false)
	public void runPlaywright() {
		
		Playwright playwright = Playwright.create();
		
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		Page page = browser.newPage();
		
		page.navigate("https://www.intelligent.com");
		
		System.out.println(page.url());
		
		System.out.println(page.title());
		
	}
	
	@Test(description="basic playwright test with tracing", enabled = false)
    public void runPlaywrightWithTrace() {
        
        Playwright playwright = Playwright.create();
        
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        
        BrowserContext bc = browser.newContext();
        
        bc.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
        
        Page page = bc.newPage();
        
        page.navigate("https://www.intelligent.com");
        
        System.out.println(page.url());
        
        System.out.println(page.title());
        
        bc.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("mytrace.zip")));
        
        bc.close();
        browser.close();
        playwright.close();
        
    }
	
	@Test(description = "playwright with browser context", enabled = false)
	public void playwrightTestBC() {
	    
	    Playwright py = Playwright.create();
	    Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	    BrowserContext bc1 = browser.newContext();
	    BrowserContext bc2 = browser.newContext();
	    
	    // cookies and cache are not shared between different browser contexts
	    Page p1 = bc1.newPage();
	    
	    Page p2 = bc2.newPage();
	    
	    p1.navigate("https://www.google.com");
	    
	    p2.navigate("https://www.google.co.in");
	    
	    
	}
	
	@Test(description = "playwright with WebElement/Locator", enabled = false)
    public void playwrightTestElements() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                
        p1.navigate("https://www.google.com");
        
        Locator lc = p1.locator(".gLFyf");
        
        lc.fill("devendra");
        
        System.out.println(lc.count());
        
       // lc.first()
        
       // lc.last()
         
      // lc.nth(0)
      //  lc.nth(0).textContent()
      //  lc.nth(0).getAttribute("class")
        
       List<String> allTexts =  lc.allTextContents();
       
       allTexts.forEach(str-> System.out.println(str));
        
        
    }
	
	@Test(description = "playwright with selectors", enabled = false)
    public void playwrightTestSelectors() {
        
	    
	    // text
	    // css
	    //xpath
	    // nth selector
	    // react
	    // if multiple elements found, it will fail
	    
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                
      //  p1.navigate("https://www.google.com");
        
        p1.navigate("https://selectorshub.com/xpath-practice-page/");
        
        // text
//        Locator textLocator1 = p1.locator("text = mytext");
//        
//        Locator textLocator2 = p1.locator("h1:has-text('my text')");
//        
//        Locator textLocator3 = p1.locator("'mytext'");  // same as textLocator1
//        
//        // this will give locator for input field which is inside shadow dom which is in parentelementofshadowdom
//        Locator shadowDom = p1.locator("parentelementofshadowdom #input");
        
        // get only visible button locator
        
       // p1.locator("button:visible"); // will get button which is visible
        
       // p1.locator("button >> visible = true"); // will get button which is visible
       
        
        // righ-of
        // left-of
        // above
        // below
        // near
        
        p1.locator("input[type='checkbox']:left-of(:text('Joe.Root'))").first().click();
        
        // give me all td near test joe.root within 200 pixels
        p1.locator("td:near(:text('Joe.Root'), 200)").first().click();
        
        // first element
      p1.locator("button >> n-th = 0"); // will get button which is visible
      
      // last element
      p1.locator("button >> n-th = -1"); // will get button which is visible
        
        
        
	}
	

    @Test(description = "playwright with frame", enabled = false)
    public void playwrightTestFrames() {
        
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                
        p1.navigate("https://www.google.com");
        
        Locator frameInputLocator = p1.frameLocator("name=['myframe']").locator("selector for element inside frame");
        
       // p1.frame("name of frame") // use frame instead of framelocator if frame name is available
        
    }
    
    @Test(description = "playwright with sub elements", enabled = false)
    public void playwrightTestSubElements() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                
        p1.navigate("https://demoqa.com/select-menu");
        
        //p1.locator("#oldSelectMenu").selectOption("4"); //select by value
        
      //  p1.selectOption("#oldSelectMenu", new SelectOption().setIndex(4));
        
      //  p1.selectOption("#oldSelectMenu", new SelectOption().setLabel("hello"));
        
      //  p1.selectOption("#oldSelectMenu", new SelectOption().setValue("hello"));
        
        //p1.locator("#oldSelectMenu")
        
        // p1.locator("#oldSelectMenu:has(option[value='Red'])") // get element inside element

  
    }
    
    @Test(description = "playwright FindAllAlternative", enabled = false)
    public void playwrightTestFindAllAlternative() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                
        p1.navigate("https://demoqa.com/buttons");
        
        Locator multipleLocatorsCss = p1.locator("text='Click Me', text='Click me'");
        
        Locator multipleLocatorsXpath = p1.locator("//*[contains(text(),'Click Me')] | //*[contains(text(),'Click me')]");

        multipleLocatorsCss.click();
    }
    
    @Test(description = "playwright react locators", enabled = false)
    public void playwrightTestFindAllReactLocator() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                
        p1.navigate("https://netflix.com/in/");
        
        p1.locator("_react=component_name[attr = 'value']");
        
        
    }
    
    @Test(description = "playwright table dynamic", enabled = true)
    public void playwrightTestDynamicTable() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                
        p1.navigate("https://netflix.com/in/");
        
        p1.locator("_react=component_name[attr = 'value']");
        
        
    }
    
    


}
