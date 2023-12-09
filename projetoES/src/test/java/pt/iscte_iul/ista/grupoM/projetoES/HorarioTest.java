package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HorarioTest {

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
	void testGetHtmlPath() {
		fail("Not yet implemented");
	}

	@Test
	void testReadHorario() {
		Horario horario = new Horario();
		File file = new File("HorarioDeExemplo.csv");
		
		List<Aula> exptdAtrib = horario.readHorario(file);
		
		assertEquals(26019, exptdAtrib.size());
		
		for(Aula aula : exptdAtrib) {
			assertTrue(aula.getAula_curso().length() > 0);
			assertTrue(aula.getAula_UC().length() > 0);
			assertTrue(aula.getAula_turno().length() > 0);
			assertTrue(aula.getAula_turma().length() > 0);
			assertTrue(aula.getAula_iscritosTurno().length() > 0);
			assertTrue(aula.getAula_diaSemana().length() > 0);
			assertTrue(aula.getAula_data().length() > 0);
			assertTrue(aula.getAula_carateristicas_sala().length() > 0);
			assertTrue(aula.getAula_sala_atribuida().length() > 0);
			
		
			LocalTime horaInicio = aula.getAula_horaInicio();
			LocalTime horaFim = aula.getAula_horaFim();
			
			assertEquals(aula.getAula_horaInicio(), horaInicio);
			assertEquals(aula.getAula_horaFim(), horaFim);
		}
			

	}

}
