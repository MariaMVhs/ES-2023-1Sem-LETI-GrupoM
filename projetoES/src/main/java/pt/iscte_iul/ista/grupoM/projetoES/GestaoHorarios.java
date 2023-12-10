package pt.iscte_iul.ista.grupoM.projetoES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A classe GestaoHorarios é a classe principal que contem o metodo main para
 * iniciar a aplicacao. A execucao inicia a interface para interacao com o
 * sistema
 * 
 */
public class GestaoHorarios {

	/**
	 * Metodo principal que inicia a aplicacao.
	 *
	 * @param args Argumentos de linha de comando (nao utilizados).
	 */
	public static void main(String[] args) {

		iniciarPorTxt("exemplo_leitura.txt");
//		Salas salas_iscte = new Salas();
//		Horario horario_iscte = new Horario();
//		UserInterface window = new UserInterface();
//		window.setSalas(salas_iscte);
//		window.setHorario(horario_iscte);
//		window.window_readSalas();
//		window.window_readHorario();
//		window.start();
	}

	/**
	 * Metodo para carregar a informação necessária de um ficheiro txt.
	 *
	 * @param filePath Caminho do ficheiro txt.
	 */
	public static void iniciarPorTxt(String filePath) {

		try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
			
			String salasFilePath = buffer.readLine();
			String horarioFilePath = buffer.readLine();
			
			Salas salas_iscte = new Salas();
			Horario horario_iscte = new Horario();
			UserInterface window = new UserInterface();
			
			salas_iscte.readSalas(new File(salasFilePath));
			horario_iscte.readHorario(new File(horarioFilePath));
			
			String htmlPath = HtmlManager.createHtml(horario_iscte);
			horario_iscte.setHtmlPath(htmlPath);
			
			HorarioRater classificador = new HorarioRater(salas_iscte, horario_iscte);
			String metrica;
			
			while ((metrica = buffer.readLine()) != null) {
				String[] parametros = metrica.split("\\|");
				String nome = parametros[0];
				String formula = parametros[1];
				classificador.addMetrica(nome, formula);
			}
			
			window.setSalas(salas_iscte);
			window.setHorario(horario_iscte);
			window.start();
			window.setHorarioRater(classificador);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}