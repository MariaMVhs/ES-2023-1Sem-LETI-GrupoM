package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalasTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testReadSalas() {
		String testFile = "CaracterizaçãoDasSalas.csv";
		List<Sala> salas = new Salas().readSalas(testFile);
		
		assertEquals(131, salas.size());
		
		for(Sala sala : salas) {
			assertTrue(sala.getNome_edificio_sala().length() > 0);
			assertTrue(sala.getNome_sala().length() > 0);
		
			int capacidade_normal = sala.getCapacidade_normal();
			int capacidade_exame = sala.getCapacidade_exame();
			
			assertEquals(sala.getCapacidade_normal(), capacidade_normal);
			assertEquals(sala.getCapacidade_exame(), capacidade_exame);
		}
		
		
	}

}
