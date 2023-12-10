package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestaoHorariosTest {

	@Test
	final void testMain() {
		UserInterface window = new UserInterface();
		Horario horario_iscte = new Horario();
		window.setHorario(horario_iscte);
		window.window_readSalas();
		window.window_readHorario();
		window.start();
	}

}
