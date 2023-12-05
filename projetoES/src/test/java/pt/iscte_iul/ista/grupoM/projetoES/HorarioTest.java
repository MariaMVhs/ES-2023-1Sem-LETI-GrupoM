/**
 * 
 */
package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 
 */
class HorarioTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link pt.iscte_iul.ista.grupoM.projetoES.Horario#Horario(java.lang.String, java.util.List)}.
	 */
	
	@Test
	final void testHorario() {
		Horario test = new Horario(); 
		String time = "13:30:00";
		LocalTime exptdTime = LocalTime.parse(time);
		
	//	Horario h = new Horario(time);
		
		LocalTime accurateTime = test.getLocalTime();
		
		assertEquals(exptdTime, accurateTime, "Time mismatch: Expected time and returned time are differents");
	}

	/**
	 * Test method for {@link pt.iscte_iul.ista.grupoM.projetoES.Horario#getPath()}.
	 */
	@Test
	final void testGetPath() {
		Horario test1 = new Horario();
		String exptdPath = System.getProperty("user.dir") + File.separator + "output.html"; //inserir o path esperado para comparar
		String correctPath = test1.getPath();
		
		assertEquals(correctPath, exptdPath, "Path mismatch");
	}

}
