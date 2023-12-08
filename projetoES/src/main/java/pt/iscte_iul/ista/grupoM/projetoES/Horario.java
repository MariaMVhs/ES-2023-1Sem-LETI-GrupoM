package pt.iscte_iul.ista.grupoM.projetoES;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de um horario.
 */
public class Horario {

	private String htmlPath;
	private int num_aulas;
	private List<String> atributos_aulas; // uma lista dos nomes que estão na 1ª linha do csv (file "HorarioDeExemplo")
	private List<Aula> aulas_iscte; // as aulas: atenção é so a linha em causa (sem os nomes) - a partir da 2ª linha
									// do mesmo ficheiro

	// controlo pelo indice da lista

	/**
	 * Construtor da classe Horario.
	 */
	public Horario() {
		num_aulas = 0;
		atributos_aulas = new ArrayList<>();
		aulas_iscte = new ArrayList<>();
	}

	/**
	 * Retorna o caminho HTML.
	 *
	 * @return String representando o caminho do HTML.
	 */
	public String getHtmlPath() {
		return htmlPath;
	}

	/**
	 * Lê o horário de aulas de um CSV e e adiciona-as à lista de aulas.
	 *
	 * @param file path para CSV.
	 * @return a lista de aulas (horario)lidas do CSV.
	 */
	public List<Aula> readHorario(File file) {

		List<Aula> aulas = new ArrayList<>();

		try {

			Aula esta_aula;

			// ler a 1ª linha e vai para um vector de strings. São os
			// nomes das colunas
			// a 1ª linha vai para atributos_aulas

			BufferedReader buffRead = new BufferedReader(new FileReader(file));

			String linha = buffRead.readLine(); // lê a 1ª linha do ficheiro
			String[] atribs = linha.split(";");
			for (String atrib : atribs) {
				atributos_aulas.add(atrib);
			}

			while ((linha = buffRead.readLine()) != null) {
				// linha =
				esta_aula = new Aula();
				esta_aula.set_aula(linha);
				aulas_iscte.add(esta_aula);
			}

			num_aulas = aulas_iscte.size();
			buffRead.close();
			return aulas_iscte;

		} catch (IOException e) {
			System.out.println("Falha na leitura do arquivo." + e.getMessage());
		}
		return aulas;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath=htmlPath;
	}

	public int getNum_aulas() {
		return num_aulas;
	}

	public Aula getAula(int index) {
		return aulas_iscte.get(index);
	}

	public List<String> getAtributos_aulas() {
		return atributos_aulas;
	}

	public void printHorario() {
		for (Aula aula : aulas_iscte) {
			System.out.println(aula);
		}
	}

}