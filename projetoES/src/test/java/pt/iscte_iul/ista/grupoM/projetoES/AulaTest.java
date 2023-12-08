package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

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
		assertEquals("30", aula.getAula_iscritosTurno());
		assertEquals("Sex",aula.getAula_diaSemana());
		assertEquals(horaInicioEsperada ,aula.getAula_horaInicio());
		assertEquals(horaFimEsperada,aula.getAula_horaFim());
		assertEquals("02/12/2022",aula.getAula_data());
		assertEquals("Sala Aulas Mestrado", aula.getAula_carateristicas_sala());
		assertEquals("AA2.25", aula.getAula_sala_atribuida());	
	
	}

}
