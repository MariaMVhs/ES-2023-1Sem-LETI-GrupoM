/**
 * 
 */
package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Aula {

	private int num_atributos;
	private List<String> atributos = new ArrayList<>();
	// nota: os nomes desses atributos já estão definidos em Horario

	
	public Aula() {
		num_atributos = 0;

	}
	
	public void set_aula(String linha) {

		String split_by = ";";
		String[] atribs = linha.split(split_by);
		for (String atrib : atribs) {
			atributos.add(atrib);
		}
		num_atributos = atributos.size();
			
	}
	
	
}
