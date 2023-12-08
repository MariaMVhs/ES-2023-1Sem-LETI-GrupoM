package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.ArrayList;
import java.util.List;

public class Sala {
	
	private int num_atributos;
	private List<String> atributos = new ArrayList<>();
	private String nome_sala;
	private String nome_edificio_sala;
	private int capacidade_normal;
	private int capacidade_exame;
	// nota: os nomes desses atributos já estão definidos em Salas
	
	
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
		

		public int getCapacidade_normal() {
			if(!atributos.isEmpty()) {
				return Integer.parseInt(atributos.get(2));
			}
			return 0;
		}

		public int getCapacidade_exame() {
			if(!atributos.isEmpty()) {
				return Integer.parseInt(atributos.get(3));
			}
			return 0;
	}

		
}
