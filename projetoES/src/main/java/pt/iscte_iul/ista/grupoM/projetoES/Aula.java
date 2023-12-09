/**
 * 
 */
package pt.iscte_iul.ista.grupoM.projetoES;

import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

/**
 * Classe que representa uma aula.
 */
public class Aula {
	
	private int num_atributos;
	private List<String> atributos;
	private String aula_curso;
	private String aula_UC;
	private String aula_turno;
	private String aula_turma;
	private String aula_iscritosTurno;
	private String aula_diaSemana;
	private LocalTime aula_horaInicio;
	private LocalTime aula_horaFim;
	private String aula_data;
	private String aula_carateristicas_sala;
	private String aula_sala_atribuida;
	
	// nota: os nomes desses atributos já estão definidos em Horario

	
	  /**
     * Construtor da classe Aula.
     */
	public Aula() {
		num_atributos = 0;
		atributos = new ArrayList<>();

	}
	
	// Objetivo: análise das linhas de texto e extração dos atributos e os seus valores

	
	/**
     * Define os atributos da aula.
     * 
     * @param linha String que contém os atributos separados por ponto e vírgula.
     */	
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

	/**
     * Devolve o curso.
     * 
     * @return o curso.
     */
	public String getAula_curso() {
		if(!atributos.isEmpty()){
			return atributos.get(0);
		}
		return null;
	}
	
	/**
     * Devolve a unidade curricular.
     * 
     * @return a unidade curricular.
     */
	public String getAula_UC() {
		if(!atributos.isEmpty()) {
			return atributos.get(1);
		}
		return null;
	}

	/**
     * Devolve o turno.
     * 
     * @return o turno.
     */
	public String getAula_turno() {
		if(!atributos.isEmpty()) {
			return atributos.get(2);
		}
		return null;
	}

	/**
     * Devolve a turma.
     * 
     * @return a turma.
     */
	public String getAula_turma() {
		if(!atributos.isEmpty()) {
			return atributos.get(3);
		}
		return null;
	}

	/**
     * Devolve os inscritos no turno.
     * 
     * @return os inscritos no turno.
     */
	public String getAula_inscritosTurno() {
		if(!atributos.isEmpty()) {
			return atributos.get(4);
		}
		return null;
	}

	/**
     * Devolve o dia da semana em que decorre a aula.
     * 
     * @return o dia da semana.
     */
	public String getAula_diaSemana() {
		if(!atributos.isEmpty()) {
			return atributos.get(5);
		}
		return null;
	}

	/**
     * Devolve a hora em que a aula começa.
     * 
     * @return hora de inicio de aula.
     */
	public LocalTime getAula_horaInicio() {
		if(!atributos.isEmpty()) {
			return LocalTime.parse(atributos.get(6));
		}
		return null;
	}

	/**
     * Devolve a hora em que a aula termina.
     * 
     * @return hora de fim de aula.
     */
	public LocalTime getAula_horaFim() {
		if(!atributos.isEmpty()) {
			return LocalTime.parse(atributos.get(7));
		}
		return null;
	}

	/**
     * Devolve data da aula.
     * 
     * @return data da aula.
     */
	public String getAula_data() {
		if(!atributos.isEmpty()) {
			return atributos.get(8);
		}
		return null;
	}

	/**
     * Devolve caracteristicas da sala em que decorre a aula.
     * 
     * @return caracteristicas da sala.
     */
	public String getAula_carateristicas_sala() {
		if(!atributos.isEmpty()) {
			return atributos.get(9);
		}
		return null;
	}

	/**
     * Devolve a sala que foi atribuida a aula.
     * 
     * @return a sala atribuida.
     */
	public String getAula_sala_atribuida() {
		if(!atributos.isEmpty()) {
			return atributos.get(10);
		}
		return null;
	}

	/**
     * Devolve o numero de atributos da aulas.
     * 
     * @return o numero de atributos.
     */
	public int getNum_atributos(){
		return num_atributos;
	}

	/**
     * Devolve a lista dos atributos da Aula.
     * 
     * @return a lista dos atributos da Aula.
     */
	public List<String> getAtributos(){
		return atributos;
	}
	
}