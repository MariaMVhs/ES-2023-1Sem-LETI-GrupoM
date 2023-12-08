package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.failsafe.internal.util.Assert;

class TestSelenium {
	
	static WebDriver driver;

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
		driver.get(System.getProperty("user.dir") + File.separator + "output.html"); //por link do nosso site
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		
		String title=  driver.getTitle();
	      System.out.println(title);
		

	     //Verifica titulo
	        WebElement header = driver.findElement(By.cssSelector("h1"));
	        assertEquals(header.getText(), "Horario ISCTE-IUL");

	      //Verifica tabela
	        
	        //vê linhas da tabela
	        List<WebElement> linhas = driver.findElements(By.cssSelector(".tabulator-row"));
	        
	        //verifica se existem dados nas linhas da tabela
	        for (WebElement linha : linhas) {
	            List<WebElement> celulas = linha.findElements(By.cssSelector(".tabulator-cell"));
	            for (WebElement celula : celulas) {
	                assertTrue(celula.getText().length() > 0);
	            }
	        }       
	}
	

}
