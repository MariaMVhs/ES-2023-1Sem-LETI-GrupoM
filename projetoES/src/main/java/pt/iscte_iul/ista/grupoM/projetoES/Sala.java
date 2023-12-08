package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma sala.
 */
public class Sala {
	
	/**
	 * Número de atributos da sala.
	 */
	private int num_atributos;
	
	/**
	 * Lista de atributos da sala.
	 */
	private List<String> atributos = new ArrayList<>();
	private int num_atributos;
	private List<String> atributos = new ArrayList<>();
	private String nome_sala;
	private String nome_edificio_sala;
	private int capacidade_normal;
	private int capacidade_exame;
	// nota: os nomes desses atributos já estão definidos em Salas
	
	
	/**
	 * Construtor da classe Sala.
	 */
		public Sala() {
			num_atributos = 0;
		}
		
		
		/**
		 * Define os atributos da sala.
		 * @param linha String que contém os atributos separados por ponto e vírgula.
		 */
		public void set_sala(String linha) {

			String split_by = ";";
			String[] atribs = linha.split(split_by);
			for (String atrib : atribs) {
				atributos.add(atrib);
	//			System.out.println(atrib);
			}
			num_atributos = atributos.size();
				
		}
		
		
		public String getNome_edificio_sala() {
			if(!atributos.isEmpty()) {
				return atributos.get(0);
			}
			return null;

		}
		
		
		public String getNome_sala() {
			if(!atributos.isEmpty()) {
				return atributos.get(1);
			}
			return null;
		}
		

//		public int getCapacidade_normal() {
//			if(!atributos.isEmpty()) {
//				return atributos.get(2);
//			}
//			return null;
//		}
//
//		public int getCapacidade_exame() {
//		return capacidade_exame;
//	}

		
}
