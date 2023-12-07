package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class TestSelenium {
	
	static WebDriver driver;
	Horario_old h;

	
	 public Horario_old getH() {
		return h;
	}

	public void setH(Horario_old h) {
		this.h = h;
	}


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		driver=new ChromeDriver();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			Thread.sleep(200);			//isto está em milisegundos
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 driver.quit();
	}

	@Test
	void test() {
		driver.get(getH().getPath()); //por link do nosso site

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
////		
////		 WebElement textBox = driver.findElement(By.name("q"));
	        WebElement title = driver.findElement(By.name("Horario ISCTE-IUL"));
//
//	       textBox.sendKeys("iscte-iul.pt");
//	       submitButton.click();

	      driver.getTitle();
	     	       
	}

}
