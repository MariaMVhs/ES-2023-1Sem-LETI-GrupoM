package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A classe HorarioRater e responsavel por gerir e avaliar metricas sobre um horario,
 * 
 */
public class HorarioRater {

	private Salas salas_iscte;
	private Horario horario_iscte;

	private List<Metrica> metricas;

	/**
     * Construtor da classe HorarioRater 
     *
     * @param salas_iscte  Salas a ser utilizado.
     * @param horario_iscte  Horario a ser utilizado.
     */
	HorarioRater(Salas salas_iscte, Horario horario_iscte) {

		this.salas_iscte = salas_iscte;
		this.horario_iscte = horario_iscte;

		metricas = new ArrayList<Metrica>(); // lista com as metricas guardadas
	}

//	  Cria e guarda a metrica
	 /**
     * Cria e guarda uma nova metrica na lista de metricas.
     *
     * @param name Nome da metrica.
     * @param formula Formula da metrica.
     */
	public void addMetrica(String name, String formula) {
		String new_form = rewrite_formula(formula);
		Metrica metrica = new Metrica(name, new_form, salas_iscte, horario_iscte);
		metricas.add(metrica);
	}
	
//	  Remove uma metrica da lista
	  /**
     * Remove uma metrica da lista.
     *
     * @param name Nome da metrica a ser removida.
     * @return true se a metrica foi removida com sucesso, false caso contrario.
     */
	public boolean removeMetrica(String name){
		for(Metrica metrica : metricas){
			if(metrica.getName().equals(name)){
				metricas.remove(metrica);
				return true;
			}
		}
		return false;
	}
	
//	  Muda o nome de uma Metrica
	  /**
     * Muda o nome de uma Metrica.
     *
     * @param oldName Nome atual da metrica.
     * @param newName Novo nome desejado para a metrica.
     * @return true se o nome foi alterado com sucesso, false caso contrario.
     */
	public boolean renameMetrica(String oldName, String newName){
		for(Metrica metrica : metricas){
			if(metrica.getName().equals(oldName)){
				metrica.setName(newName);
				return true;
			}
		}
		return false;
	}

//	  Verifica se a formula é válida:
//		-acaba com um numero ou atributo
//		-contém pelo menos um atributo de Aula (para ser uma métrica do horario)
//		-não tem dois operadores ou atributos seguidos
//		-se conter um atributo não reconhecido
	/**
     * Verifica se a formula fornecida e valida para o calculo de metricas.
     *
     * @param formula Formula a ser validada.
     * @return true se a formula e valida, false caso contrario.
     */
	public boolean validateFormula(String formula) {

		List<String> fields = new ArrayList<String>(); // lista com os atributos de Sala e Aula
		List<String> operators = new ArrayList<String>(); // lista com os operadores possiveis

		for (int i = 0; i < horario_iscte.getNum_aulas(); i++) {
			fields.add("a." + i);
		}
		for (int i = 0; i < salas_iscte.getNum_salas(); i++) {
			fields.add("s." + i);
		}
		operators.addAll(Arrays.asList("+", "-", "*", "/", "==", "<", ">", "<=", ">="));

		String new_form = rewrite_formula(formula);
		String[] splitForm = new_form.split(" ");

		boolean isField = true;
		boolean hasHorarioField = false;
		for (String s : splitForm) {
			if (isField) {
				if (!(fields.contains(s) || isInteger(s))) {
					return false;
				}
				if (!hasHorarioField && isAula(s)) {
					hasHorarioField = true;
				}
			} else {
				if (!operators.contains(s)) {
					return false;
				}
			}
			isField = !isField;
		}
		if (!(!isField && hasHorarioField)) {
			return false;
		} else {
			return true;
		}
	}

//	  Forma uma matriz com o nome de cada metrica na coluna esquerda e o resultado correspondente na direita
	 /**
     * Forma uma matriz com o nome de cada metrica na coluna esquerda
     * e o resultado correspondente na direita.
     *
     * @return Matriz com informacoes das metricas.
     */
	public String[][] getTableInfo() {

		int numLinhas = metricas.size();
		String[][] data = new String[numLinhas][2];
		for (int i = 0; i < numLinhas; i++) {
			Metrica metrica = metricas.get(i);
			data[i][0] = metrica.getName();
			data[i][1] = String.valueOf(metrica.getResult());
		}
		return data;
	}

//	  Calcula e devolve a avaliação do horário fazendo a média das percentagens de quantas aulas
//	  foram selecionadas pela formula de cada Metrica
	/**
     * Calcula e retorna a avaliacao do horario, fazendo a media das percentagens
     * de aulas selecionadas pela formula de cada metrica.
     *
     * @return Avaliacao do horario.
     */
	public double getRating() {
		double rating = 0;
		double somatorio = 0;
		for (Metrica metrica : metricas) {
			double porcao_aulas = (double)(metrica.getResult()) / horario_iscte.getNum_aulas();
			somatorio += porcao_aulas;
		}
		rating = somatorio / metricas.size();
		return rating;
	}
	
	 /**
     * Retorna o numero de metricas na lista.
     *
     * @return Numero de metricas na lista.
     */
	public int getNum_metricas(){
		return metricas.size();
	}
	
	/**
     * Retorna uma lista com os nomes de todas as metricas na lista.
     *
     * @return Lista de nomes de metricas.
     */
	public List<String> getNome_metricas(){

		List<String> lista_nomes = new ArrayList<>();
		for(Metrica metrica : metricas){
			lista_nomes.add(metrica.getName());
		}
		return lista_nomes;
		
	}

//	  Reescreve a formula para prefixo.index
//		prefixo -> "s" se for um atributo de Sala
//				   "a" se for um atributo de Aula
//	    index -> index desse atributo na lista de atributos correspondente

	private String rewrite_formula(String formula) {

		Map<String, String> replacementMap = new HashMap<>(); // mapa com as substituições para o rewrite_formula

		int n = 0;
		for (String atrib_aula : horario_iscte.getAtributos_aulas()) {
			replacementMap.put(atrib_aula, "a." + n);
			n++;
		}
		n = 0;
		for (String atrib_sala : salas_iscte.getAtributos_salas()) {
			replacementMap.put(atrib_sala, "s." + n);
			n++;
		}

		for (Map.Entry<String, String> entry : replacementMap.entrySet()) {
			formula = formula.replace(entry.getKey(), entry.getValue());
		}
		return formula;
	}

//	  Verifica se o campo inserido pertence a Aula

	private static boolean isAula(String field) {
		return !isInteger(field) && field.startsWith("a.");
	}

//	  Verifica se o campo inserido é um Integer

	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
