/**
 * 
 */
package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma aula.
 */
public class Aula {
	

	 /**
     * Número de atributos da aula.
     */
	private int num_atributos;
	
	/**
     * Lista de atributos da aula.
     */
	private List<String> atributos = new ArrayList<>();
	// nota: os nomes desses atributos já estão definidos em Horario

	
	  /**
     * Construtor da classe Aula.
     */
	public Aula() {
		num_atributos = 0;

	}
	
	
	/**
     * Define os atributos da aula.
     * 
     * @param linha String que contém os atributos separados por ponto e vírgula.
     */
	// Objetivo: análise das linhas de texto e extração dos atributos e os seus valores
	
	public void set_aula(String linha) {

		String split_by = ";";
		String[] atribs = linha.split(split_by); //divide a linha de texto num array de strings usando o ";"
		for (String atrib : atribs) { // ciclo que percorre cada string no array atribs
			atributos.add(atrib); // adiciona à string atribs um conjunto de strings atributos
	//		System.out.println(atrib);
		}
		num_atributos = atributos.size(); // atribui o número total de atributos encontrados
										// na linha de texto percorrida
			
	}
	
	
}
