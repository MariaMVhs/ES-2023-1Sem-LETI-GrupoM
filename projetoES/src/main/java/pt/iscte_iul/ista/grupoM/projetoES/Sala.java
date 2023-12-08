package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.ArrayList;
import java.util.List;

public class Sala {
	
	private int num_atributos;
	private List<String> atributos = new ArrayList<>();
	// nota: os nomes desses atributos já estão definidos em Salas
	
	
	/**
	 * Construtor da classe Sala.
	 */
		public Sala() {
			num_atributos = 0;
		}
		
		
		/**
		 * Método que define os atributos da sala.
		 * @param linha String que contém os atributos separados por ponto e vírgula.
		 */
		public void set_sala(String linha) {

			String split_by = ";";
			String[] atribs = linha.split(split_by);
			for (String atrib : atribs) {
				atributos.add(atrib);
			}
			num_atributos = atributos.size();
				
		}
}
