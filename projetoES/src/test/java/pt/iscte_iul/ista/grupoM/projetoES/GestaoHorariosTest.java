package pt.iscte_iul.ista.grupoM.projetoES;

import org.junit.jupiter.api.Test;

class GestaoHorariosTest {

	@Test
	final void testMain() {
		UserInterface window = new UserInterface();
		Salas salas= new Salas();
		Horario horario_iscte = new Horario();
		window.setHorario(horario_iscte);
		window.window_readSalas();
		window.window_readHorario();
		window.start();
		window.setSalas(salas);
	    
	}
}
