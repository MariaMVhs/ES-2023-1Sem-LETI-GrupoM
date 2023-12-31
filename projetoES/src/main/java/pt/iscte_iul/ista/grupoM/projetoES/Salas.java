package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.List;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Esta classe representa um conjunto de salas.
 */
public class Salas {
	
	private int num_salas;
	private List<String> atributos_salas = new ArrayList<>(); // uma lista dos nomes que estão na 1ª linha do csv (file "Caracterizacao das salas")
	private List<Sala> salas_iscte = new ArrayList<>();  // as salas: atenção é so a linha em causa (sem os nomes) - a partir da 2ª linha do mesmo ficheiro
	
	// controlo pelo indice da lista
	

	/**
     * Construtor da classe Salas.
     */
	public Salas() {
		num_salas = 0;
	}
	
	
	/**
     * Esta funcao le um ficheiro e cria uma lista de objetos do tipo Sala.
     * Cada linha do ficheiro representa uma sala com os valores separados por ";"
     * @param file path para CSV
     * @return a lista de salas lidas do CSV.
     */
	public List<Sala> readSalas(File file) {
		
			List<Sala> salas = new ArrayList<>(); // cria uma lista do tipo Sala vazia
			
		try { // try-catch para lidar com possíveis exceções que possam ocorrer durante a 
			  // leitura do ficheiro fornecido
			
			Sala esta_sala;
			
			// ler a 1ª linha e vai para um vector de strings. São os
			// nomes das colunas
			// a 1ª linha vai para atributos_salas
			
			BufferedReader buffRead = new BufferedReader(new FileReader(file));
			
			String linha = buffRead.readLine();  // lê a 1ª linha do ficheiro
			String[] atribs = linha.split(";"); // depois da leitura da 1ª linha do file
												//faz a divisão num array de strings que representam
												// os nomes das colunas.
			for (String atrib : atribs) { // para cada string existente em atribs,
				atributos_salas.add(atrib); //  adiciona à lista atributos_sala
			}
			
			while ((linha = buffRead.readLine()) != null) { //ciclo while para leitura das restantes linhas
				esta_sala = new Sala(); // criação de uma nova sala
				esta_sala.set_sala(linha); // atribuição dos dados da linha atual
				salas_iscte.add(esta_sala); // adiciona os dados da nova sala à lista de salas do iscte
			}
			
			num_salas = salas_iscte.size();
			buffRead.close();
			return salas_iscte;

			
		}catch (IOException e) {
			System.out.println("Falha na leitura do arquivo." + e.getMessage());
		}
		return null;
	}
	
	/**
     * Imprime as salas.
     */
	public void printSalas(){
		for(Sala sala : salas_iscte) {
			System.out.println(sala);
		}
	}
	
	/**
     * Devolve a lista de salas.
     * @return a lista de salas.
     */
	public List<Sala> getSalas(){
		return salas_iscte;
	}

	/**
     * Devolve o numero de salas.
     * @return o numero de salas.
     */
	public int getNum_salas(){
		return num_salas;
	}

	/**
     * Devolve a lista dos atributos de Salas.
     * @return a lista dos atributos de Salas.
     */
	public List<String> getAtributos_salas(){
		return atributos_salas;
	}
}
