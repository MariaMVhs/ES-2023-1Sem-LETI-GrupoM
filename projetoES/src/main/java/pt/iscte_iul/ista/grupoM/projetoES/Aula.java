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

	private String curso;
	private String unidadeCurricular;
	private String turno;
	private String turma;
	private int inscritosNoTurno;
	private String diaDaSemana;
	private String horaInicioAula;
	private String horaFimAula;
	private String dataAula;
	private String caracteristicasSalaPedida;
	private String salaAtribuida;
	
	int num_atributos;
	List<String> atributos = new ArrayList<>();
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
