package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalaTest {
	
	private Sala sala;

	@Test
	final void testSet_sala() {
		sala = new Sala();
        sala.set_sala("Edifício Sedas nunes (ISCTE-IUL);1NE00;1;0");

        assertEquals("Edifício Sedas nunes (ISCTE-IUL)", sala.getNome_edificio_sala());
        assertEquals("1NE00", sala.getNome_sala());
        assertEquals(1, sala.getCapacidade_normal());
        assertEquals(0, sala.getCapacidade_exame());
        
        Sala sala2 = new Sala();
        sala2.set_sala("Edifício II (ISCTE-IUL);C2.03;6;0");
        
        assertEquals("Edifício II (ISCTE-IUL)", sala2.getNome_edificio_sala());
        assertEquals("C2.03", sala2.getNome_sala());
        assertEquals(6, sala2.getCapacidade_normal());
        assertEquals(0, sala2.getCapacidade_exame());
        
        Sala sala3 = new Sala();
        sala3.set_sala("Ala Autónoma (ISCTE-IUL);AA3.23;50;23");
        
        assertEquals("Ala Autónoma (ISCTE-IUL)", sala3.getNome_edificio_sala());
        assertEquals("AA3.23", sala3.getNome_sala());
        assertEquals(50,sala3.getCapacidade_normal());
        assertEquals(23, sala3.getCapacidade_exame());
             
	}

	@Test
	final void testGetNome_edificio_sala() {
		sala = new Sala();
		assertNull(sala.getNome_edificio_sala());
	}
		

	@Test
	final void testGetNome_sala() {
		sala = new Sala();
		assertNull(sala.getNome_sala());	
	}

	@Test
	final void testGetCapacidade_normal() {
		sala = new Sala();
        assertEquals(0, sala.getCapacidade_normal());
	}

	@Test
	final void testGetCapacidade_exame() {
		sala = new Sala();
		assertEquals(0, sala.getCapacidade_exame());	
	}

}
