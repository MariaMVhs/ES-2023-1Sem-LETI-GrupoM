package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.List;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class Salas {
	
	private int num_salas;
	private List<String> atributos_salas = new ArrayList<>(); // uma lista dos nomes que estão na 1ª linha do csv (file "Caracterizacao das salas")
	private List<Sala> salas_iscte = new ArrayList<>();  // as salas: atenção é so a linha em causa (sem os nomes) - a partir da 2ª linha do mesmo ficheiro
	
	// controlo pelo indice da lista
	

	/**
	 * 
	 */
	public Salas() {
		num_salas = 0;
	}
	
	// Esta função lê um ficheiro e cria uma lista de objetos do tipo Sala 
	// Cada linha do ficheiro representa uma sala com os valores separados por ";"
	public List<Sala> readSalas(String file) {
		
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
	
}
