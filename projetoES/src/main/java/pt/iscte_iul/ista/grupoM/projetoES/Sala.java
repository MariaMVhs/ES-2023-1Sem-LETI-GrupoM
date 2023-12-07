package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.List;

public class Sala {
	
	int num_atributos;
	List<String> atributos;
	// nota: os nomes desses atributos já estºao definidos em Salas
	
	
		/**
		 * 
		 */
		public Sala() {
			num_atributos = 0;
		}
		
		public void set_sala(String linha) {

			String split_by = ";";
			String[] atribs = linha.split(split_by);
			for (String atrib : atribs) {
				atributos.add(atrib);
			}
			num_atributos = atributos.size();
				
		}
}
