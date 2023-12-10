package pt.iscte_iul.ista.grupoM.projetoES;

import java.io.File;
import java.util.List;

/**
 * A classe GestaoHorarios é a classe principal que contem o metodo main para iniciar a aplicacao.
 * A execucao inicia a interface para interacao com o sistema
 * 
 */
public class GestaoHorarios {

	 /**
     * Metodo principal que inicia a aplicacao.
     *
     * @param args Argumentos de linha de comando (nao utilizados).
     */
	public static void main(String[] args) {
	
		File fileHorario = new File("HorarioDeExemplo.csv");
//		Salas salas_iscte = new Salas();
		Horario horario_iscte = new Horario();
		UserInterface window = new UserInterface();
//		window.setSalas(salas_iscte);
		window.setHorario(horario_iscte);
		window.window_readSalas();
		window.window_readHorario();
		window.start();
//		salas_iscte.print();
//		List<Sala> salas = salas_iscte.readSalas("CaracterizaçãoDasSalas.csv");
//		System.out.println("Número de salas existentes: " + salas.size());
//		
//		List<Aula> aulas = horario_iscte.readHorario(fileHorario);
//		System.out.println("Número de aulas existentes: " + aulas.size());
	}
}