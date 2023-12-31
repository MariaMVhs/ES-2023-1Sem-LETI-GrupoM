package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma sala.
 */
public class Sala {
	
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
		 * @param linha String que contem os atributos separados por ponto e virgula.
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
		
		
		/**
		 * Devolve o nome do edificio.
		 * @return o nome do edificio.
		 */
		public String getNome_edificio_sala() {
			if(!atributos.isEmpty()) {
				return atributos.get(0);
			}
			return null;

		}
		
		/**
		 * Devolve a sala.
		 * @return a sala.
		 */
		public String getNome_sala() {
			if(!atributos.isEmpty()) {
				return atributos.get(1);
			}
			return null;
		}
		
		/**
		 * Devolve a capacidade normal da sala.
		 * @return a capacidade normal da sala.
		 */
		public int getCapacidade_normal() {
			if(!atributos.isEmpty()) {
				return Integer.parseInt(atributos.get(2));
			}
			return 0;
		}
				
		/**
		 * Devolve a capacidade em exame da sala.
		 * @return a capacidade em exame da sala.
		 */
		public int getCapacidade_exame() {
			if(!atributos.isEmpty()) {
				return Integer.parseInt(atributos.get(3));
			}
			return 0;
		}
	
		/**
		 * Devolve os atributos da Sala.
		 * @return os atributos da Sala.
		 */
		public List<String> getAtributos(){
			return atributos;
		}
	
}
