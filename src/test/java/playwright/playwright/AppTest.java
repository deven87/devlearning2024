package playwright.playwright;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
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
    
    @Test(description = "playwright table dynamic", enabled = false)
    public void playwrightTestDynamicTable() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                
        p1.navigate("https://datatables.net/");
        
        // get all the trs
        Locator table = p1.locator("table#example tr");
        
        // get scope for the whole table
        List<String> innerText = table.locator(":scope").allInnerTexts();
        
        //innerText.forEach(str -> System.out.println(str));
        
        // scope only a given row based on rows text and then find locator in that row only
        String cellValueForGivenRowHavingAText = table.locator(":scope", new Locator.LocatorOptions().setHasText("Ashton Cox")).locator(".dt-type-numeric").first().textContent();
        
        System.out.println(cellValueForGivenRowHavingAText);
        
    }
    
    
    @Test(description = "playwright login state save", enabled = false)
    public void playwrightTestLoginStateSave() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                

        p1.navigate("https://app.resume.org/login/");
              p1.fill("input[type='email']", "devendra.singh@intelligent.com");
              p1.fill("input[type='password']", "Test@531");
              p1.locator("#submit_button").click();
              
              try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
              
              // store the login state in json file
              bc.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("applogin.json")));
    }
    
    @Test(description = "playwright login state using", enabled = false)
    public void playwrightTestLoginStateUsing() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        
        // use the previous saved login state json file here
        BrowserContext bc = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("applogin.json")));
        
        // cookies and cache are not shared between different browser contexts
        Page p1 = bc.newPage();
                

        p1.navigate("https://app.resume.org/my-account/");
        
      
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
             }
    
    
    @Test(description = "playwright alerts", enabled = false)
    public void playwrightTestAlerts() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        
        BrowserContext bc = browser.newContext();
        
        Page p1 = bc.newPage();
        
        p1.onDialog(dialog -> {
            
            String text = dialog.message();
            System.out.println(text);
            dialog.accept("hello i am prompt message");
            
        });
                

        p1.navigate("https://demoqa.com/alerts");
        
    //    p1.locator("#alertButton").click();
        
       // p1.locator("#confirmButton").click();
        
        p1.locator("#promtButton").click();
        
        
        
        
        // alert will be auto clicked in playwright
        
        
        
        
        
        
        
    }
    
    @Test(description = "playwright fileupload", enabled = false)
    public void playwrightTestfileupload() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        
        BrowserContext bc = browser.newContext();
        
        Page p1 = bc.newPage();
        
        p1.navigate("https://demoqa.com/automation-practice-form");
        p1.setInputFiles("input[type='file']", Paths.get("applogin.json"));
        
        p1.setInputFiles("input[type='file']", new Path[0]); // this will deselect the file
        
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    @Test(description = "playwright filedownload", enabled = false)
    public void playwrightTestfileDownload() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        
        BrowserContext bc = browser.newContext();
        
        Page p1 = bc.newPage();
        
        p1.navigate("https://demo.automationtesting.in/FileDownload.html");
        
        Download download = p1.waitForDownload(()->{
            
            p1.click("button:has-text('Download')");
        });
        
        System.out.println(download.url());
        
        System.out.println(download.path());
        
        download.saveAs(Paths.get("new path"));
        
        download.failure(); // get failure message
        
        
        
        
        
    }
    
    @Test(description = "playwright maximize", enabled = false)
    public void playwrightMaximize() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        
        BrowserContext bc = browser.newContext(new Browser.NewContextOptions().setViewportSize(1500, 1500));
        
        Page p1 = bc.newPage();
        
        p1.navigate("https://demo.automationtesting.in/FileDownload.html");
     
        
        
    }
    
    @Test(description = "playwright screen recording", enabled = false)
    public void playwrightScreenRecord() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        
        BrowserContext bc = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("myvideo")));
        
        Page p1 = bc.newPage();
        
        p1.navigate("https://demo.automationtesting.in/FileDownload.html");
     
        
        
    }
    
    @Test(description = "playwright tab window", enabled = true)
    public void playwrightTabWindow() {
        
        Playwright py = Playwright.create();
        Browser browser = py.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        
        BrowserContext bc = browser.newContext();
        
        Page p1 = bc.newPage();
        
        p1.navigate("https://the-internet.herokuapp.com/windows");
        
        Page newPage = p1.waitForPopup(()->{
         
            p1.click("a[href='/windows/new']");
        });
        
        newPage.waitForLoadState();
        
        newPage.navigate("https://the-internet.herokuapp.com/frames");
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     
        
        
    }

}
