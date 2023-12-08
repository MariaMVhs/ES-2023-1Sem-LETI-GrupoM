package pt.iscte_iul.ista.grupoM.projetoES;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de um horario.
 */
public class Horario {
	
	private int num_aulas;
	private List<String> atributos_aulas; // uma lista dos nomes que estão na 1ª linha do csv (file "HorarioDeExemplo")
	private List<Aula> aulas_iscte; // as aulas: atenção é so a linha em causa (sem os nomes) - a partir da 2ª linha do mesmo ficheiro
	
	// controlo pelo indice da lista
	
	
	public Horario() {
		num_aulas = 0;
		atributos_aulas = new ArrayList<>();
		aulas_iscte = new ArrayList<>();
	}
	
	public List<Aula> readHorario(String file) {
		
		List<Aula> aulas = new ArrayList<>();
		
	try {
		
		Aula esta_aula;
		
		// ler a 1ª linha e vai para um vector de strings. São os
		// nomes das colunas
		// a 1ª linha vai para atributos_aulas
		
		BufferedReader buffRead = new BufferedReader(new FileReader(file));
		
		String linha = buffRead.readLine();  // lê a 1ª linha do ficheiro
		String[] atribs = linha.split(";");
		for (String atrib : atribs) {
			atributos_aulas.add(atrib);
		}
		
		while ((linha = buffRead.readLine()) != null) {
			//linha =
			esta_aula = new Aula();
			esta_aula.set_aula(linha);
			aulas_iscte.add(esta_aula);
		}
		
		num_aulas = aulas_iscte.size();
		buffRead.close();

		
		}catch (IOException e) {
			System.out.println("Falha na leitura do arquivo." + e.getMessage());
		}
		return aulas;
	}

	public void printHorario(){ 
		for(Aula aula : aulas_iscte) {
			System.out.println(aula);
		}
	}
	
}