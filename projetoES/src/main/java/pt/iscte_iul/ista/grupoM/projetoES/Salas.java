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
	
	public List<Sala> readSalas(String file) {
		
			List<Sala> salas = new ArrayList<>();
			
		try {
			
			Sala esta_sala;
			
			// ler a 1ª linha e vai para um vector de strings. São os
			// nomes das colunas
			// a 1ª linha vai para atributos_salas
			
			BufferedReader buffRead = new BufferedReader(new FileReader("CaracterizaçãoDasSalas.csv"));
			
			String linha = buffRead.readLine();  // lê a 1ª linha do ficheiro
			String[] atribs = linha.split(";");
			for (String atrib : atribs) {
				atributos_salas.add(atrib);
			}
			
			while ((linha = buffRead.readLine()) != null) {
				//linha =
				esta_sala = new Sala();
				esta_sala.set_sala(linha);
				salas_iscte.add(esta_sala);
			}
			
			num_salas = salas_iscte.size();
			buffRead.close();
			return salas_iscte;

			
		}catch (IOException e) {
			System.out.println("Falha na leitura do arquivo." + e.getMessage());
		}
		return null;
	}
	
	public void printSalas(){
		for(Sala sala : salas_iscte) {
			System.out.println(sala);
		}
	}
}
