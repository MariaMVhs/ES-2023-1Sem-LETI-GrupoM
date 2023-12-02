package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

class TestSelenium {
	
	static WebDriver driver;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		driver=new FirefoxDriver();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		try {
//			Thread.sleep(200);			//isto está em milisegundos
//			} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 driver.quit();
	}

	@Test
	void test() {
//		driver.get("https://www.google.com/");
//
//		 //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//		
//		 WebElement textBox = driver.findElement(By.name("q"));
//	        WebElement submitButton = driver.findElement(By.name("btnK"));
//
//	       textBox.sendKeys("iscte-iul.pt");
//	       submitButton.click();
//
//	       driver.getTitle();
	}

}
