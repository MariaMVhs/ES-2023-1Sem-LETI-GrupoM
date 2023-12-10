package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AulaTest {
	
	private Aula aula;

	@Test
	final void testSet_aula() {
		aula = new Aula();
		aula.set_aula("ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;02/12/2022;Sala Aulas Mestrado;AA2.25");
		
		String time = "13:00:00";
		String time2 = "14:30:00";
		LocalTime horaInicioEsperada = LocalTime.parse(time);
		LocalTime horaFimEsperada = LocalTime.parse(time2);
		//LocalTime accurateTime = test.getLocalTime();

		
		assertEquals("ME", aula.getAula_curso());
		assertEquals("Teoria dos Jogos e dos Contratos", aula.getAula_UC());
		assertEquals("01789TP01", aula.getAula_turno());
		assertEquals("MEA1", aula.getAula_turma());
		assertEquals("30", aula.getAula_inscritosTurno());
		assertEquals("Sex",aula.getAula_diaSemana());
		assertEquals(horaInicioEsperada ,aula.getAula_horaInicio());
		assertEquals(horaFimEsperada,aula.getAula_horaFim());
		assertEquals("02/12/2022",aula.getAula_data());
		assertEquals("Sala Aulas Mestrado", aula.getAula_carateristicas_sala());
		assertEquals("AA2.25", aula.getAula_sala_atribuida());	
	
	}
	
	@Test
	final void testGetAula_curso() {
		aula = new Aula();
		assertNull(aula.getAula_curso());
	}
	
	@Test
	final void testGetAula_UC() {
		aula = new Aula();
		assertNull(aula.getAula_UC());
	}
	
	@Test
	final void testGetAula_turno() {
		aula = new Aula();
		assertNull(aula.getAula_turno());
	}
	
	@Test
	final void testGetAula_turma() {
		aula = new Aula();
		assertNull(aula.getAula_turma());
	}
	
	@Test
	final void testGetAula_iscritosTurno() {
		aula = new Aula();
		assertNull(aula.getAula_inscritosTurno());
	}
	
	@Test
	final void testGetAula_diaSemana() {
		aula = new Aula();
		assertNull(aula.getAula_diaSemana());
	}
	
	@Test
	final void getAula_horaInicio() {
		aula = new Aula();
		assertNull(aula.getAula_horaInicio());
	}
	
	@Test
	final void getAula_horaFim() {
		aula = new Aula();
		assertNull(aula.getAula_horaFim());
	}

	@Test
	final void getAula_data() {
		aula = new Aula();
		assertNull(aula.getAula_data());
	}
	
	@Test
	final void getAula_caracteristicas_sala() {
		aula = new Aula();
		assertNull(aula.getAula_carateristicas_sala());
	}
	
	@Test
	final void getAula_sala_atribuida() {
		aula = new Aula();
		assertNull(aula.getAula_sala_atribuida());
		
		//para entrar no catch
		Aula aula2 = new Aula();
		aula2.set_aula("ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;02/12/2022;Sala Aulas Mestrado;");
		assertEquals("", aula2.getAula_sala_atribuida());
	}
	
	@Test
	final void testGetNum_atributos() {
		aula = new Aula();
        aula.set_aula("ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;02/12/2022;Sala Aulas Mestrado;AA2.25");
        int atributos = aula.getNum_atributos();

        assertEquals(11,atributos);
	}
	
	@Test
	final void testGetAtributos() {
		aula = new Aula();
        aula.set_aula("ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;02/12/2022;Sala Aulas Mestrado;AA2.25");
        List<String> atributos = aula.getAtributos();

       // assertEquals("ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Sex;13:00:00;14:30:00;02/12/2022;Sala Aulas Mestrado;AA2.25", atributos);
	}
	
}
