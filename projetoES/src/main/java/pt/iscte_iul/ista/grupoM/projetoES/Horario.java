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
	private List<Aula> aulas_iscte; // as aulas: atenção é so a linha em causa (sem os nomes) - a partir da 2ª linha do mesmo ficheiro
	
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
		
		List<Aula> aulas = new ArrayList<>(); // Cria nova lista vazia do tipo Aula
		
	try { // try-catch para lidar com possíveis exceções que possam ocorrer durante a 
		  // leitura do ficheiro fornecido
		
		Aula esta_aula;
		
		// ler a 1ª linha e vai para um vector de strings. São os
		// nomes das colunas
		// a 1ª linha vai para atributos_aulas
		
		BufferedReader buffRead = new BufferedReader(new FileReader(file)); // leitura do ficheiro
		
		String linha = buffRead.readLine();  // lê a 1ª linha do ficheiro e armazena em "linha"
		String[] atribs = linha.split(";"); // depois da leitura da 1ª linha do file
											//faz a divisão num array de strings que representam
											// os nomes das colunas.
		for (String atrib : atribs) { // percorre cada string existente no array atribs
			atributos_aulas.add(atrib); // adiciona à string atrib  o conjunto de strings atributos_aulas
		}
		
		while ((linha = buffRead.readLine()) != null) { // ciclo while para ler cada uma das seguintes linhas do file
														// e armazena em linha, até que se chegue ao final do file
			esta_aula = new Aula();
			esta_aula.set_aula(linha); // chama set_aula da classe Aula e passa a linha tual como argumento
			aulas_iscte.add(esta_aula); // adiciona esta_aula à lista aulas_iscte
		}
		
		num_aulas = aulas_iscte.size(); // atribuição do número total de aulas lidas do arquivo ao num_aulas.
		buffRead.close();
		return aulas_iscte;
		
	}catch (IOException e) {
			System.out.println("Falha na leitura do arquivo." + e.getMessage());
	}
		return aulas;
	}

	
	
}