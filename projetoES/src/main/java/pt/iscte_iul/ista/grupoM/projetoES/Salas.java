package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.List;

public class Salas {
	
	int num_salas;
	List<String> atributos_salas; // uma lista dos nomes que estão na 1ª linha do csv
	List<Sala> salas_iscte;  // as salas: atençºao é so a linha em causa (sem os nomes)
	// controlo pelo indice da lista
	

	/**
	 * 
	 */
	public Salas() {
		num_salas = 0;
	}
	
	int readSalas() {
		
		String split_by = ";";
		
		Sala esta_sala;
		
		// ler a 1ª linha e vai para um vector de strings. São os
		// nomes das colunas
		// a 1ª linha vai para atributos_salas
		
		linha =   // 1ª linha
		String[] atribs = linha.split(split_by);
		for (String atrib : atribs) {
			atributos_salas.add(atrib);
		}
		while (nao chegar ao fim csv) {
			linha =
			esta_sala.set_sala(linha);
			salas_iscte.add(esta_sala);
		}
		
		num_salas = salas_iscte.size();
		

		
	}
	
	public Sala printSalas(){ //fazer o mesmo com o horario
		return salas_iscte;
	}
}
