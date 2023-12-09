package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;


class SalasTest {

	@Test
	void testReadSalas() {
		File testFile = new File("CaracterizaçãoDasSalas.csv");
		List<Sala> salas = new Salas().readSalas(testFile);
		
		assertEquals(131, salas.size());
		
		for(Sala sala : salas) {
			assertTrue(sala.getNome_edificio_sala().length() > 0);
			assertTrue(sala.getNome_sala().length() > 0);
		
			int capacidade_normal = sala.getCapacidade_normal();
			int capacidade_exame = sala.getCapacidade_exame();
			
			assertEquals(sala.getCapacidade_normal(), capacidade_normal);
			assertEquals(sala.getCapacidade_exame(), capacidade_exame);
			
			
	        //para entrar no catch e dar falha na leitura do arquivo
			File file2 = new File("HorarioDeExemlo.csv");
	        List<Sala> sala2 = new Salas().readSalas(file2);
	        assertEquals(26019, sala2.size());
		}
				
	}
	
	
//	@Test
//	final void testGetSalas() {
//
//        
//
//	
//	
//	}
	
	@Test
	void testGetNum_salas() {
		Salas salas = new Salas();
		File file = new File("CaracterizaçãoDasSalas.csv");
        salas.readSalas(file);
        int num_salas = salas.getNum_salas();

        assertEquals(131,num_salas);
	}
	
	@Test
	void testGetAtributos_salas() {
		Salas salas = new Salas();
		File file = new File("CaracterizaçãoDasSalas.csv");
        salas.readSalas(file);        
        List<String> atributos_salas = salas.getAtributos_salas();

        assertEquals(salas.getAtributos_salas() ,atributos_salas);
	}

}
