package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HorarioRater {

	private Salas salas_iscte;
	private Horario horario_iscte;
	private List<Metrica> metricas;
	private List<String> fields;
	private List<String> operators;
	private Map<String, String> replacementMap;

	HorarioRater(Salas salas_iscte, Horario horario_iscte) {
		
		this.salas_iscte = salas_iscte;
		this.horario_iscte = horario_iscte;
		
		metricas = new ArrayList<Metrica>();	//lista com as metricas guardadas
		fields = new ArrayList<String>();		//lista com os atributos de Sala e Aula
		operators = new ArrayList<String>();	//lista com os operadores possiveis
        replacementMap = new HashMap<>();		//mapa com as substituições para o rewrite_formula
        
        int n=0;
        for(String atrib_aula : horario_iscte.getAtributos_aulas()){
        	fields.add("a." + n);
        	replacementMap.put(atrib_aula, "a." + n);
        	n++;
        }
        n=0;
        for(String atrib_sala : salas_iscte.getAtributos_salas()){
        	fields.add("s." + n);
        	replacementMap.put(atrib_sala, "s." + n);
        	n++;
        }
        operators.addAll(Arrays.asList("+", "-", "*", "/", "==", "<", ">", "<=", ">="));
	}
	
//	  Cria e guarda a metrica

	public void addMetrica(String name, String formula) {
		String new_form=rewrite_formula(formula, replacementMap);
		Metrica metrica = new Metrica(name, new_form, salas_iscte, horario_iscte);
		metricas.add(metrica);
	}
	
//	  Verifica se a formula é válida:
//		-acaba com um numero ou atributo
//		-contém pelo menos um atributo de Aula (para ser uma métrica do horario)
//		-não tem dois operadores ou atributos seguidos
//		-se conter um atributo não reconhecido

	public boolean validateFormula(String formula) {
		
		String new_form=rewrite_formula(formula, replacementMap);
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
	
//	  Reescreve a formula para prefixo.index
//		prefixo -> "s" se for um atributo de Sala
//				   "a" se for um atributo de Aula
//	    index -> index desse atributo na lista de atributos correspondente
	
	private static String rewrite_formula(String formula, Map<String, String> replacementMap){
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
