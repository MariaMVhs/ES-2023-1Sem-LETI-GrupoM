package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.Optional;
import java.text.Normalizer;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

public class Metrica {

	private Salas salas_iscte;
	private Horario horario_iscte;

	private String name;
	private String formula;
	private int result;
	private JexlEngine jexl;

//	No input da formula os fields vao ser escritos como s.(index do field nas salas) ou h.(index do field em horario)

	public Metrica(String name, String formula, Salas salas_iscte, Horario horario_iscte) {
		jexl = new JexlBuilder().create();
		this.name = name;
		this.formula = formula;
		this.salas_iscte = salas_iscte;
		this.horario_iscte = horario_iscte;
		calcMetrica();
	}

//	Faz a contagem das aulas que verificam a formula

	private void calcMetrica() {
		result = (int) horario_iscte.getAulas().stream().filter(aula -> useFormula(aula)).count();
	}

//	Avalia uma aula 

	private boolean useFormula(Aula aula) {
		JexlExpression expression = jexl.createExpression(formula);
		JexlContext context = new MapContext();
		String[] fields = formula.split(" ");
		for (int i = 0; i < fields.length; i += 2) {
			try { // Verifica se é um numero int
				Integer.parseInt(fields[i]);

			} catch (NumberFormatException e1) {
				String[] fieldSplit = fields[i].split("\\.");
				switch (fieldSplit[0]) {

				// Vai buscar o valor se for um campo de Sala
				case "s":
					if (!aula.getAula_sala_atribuida().equals("Sem sala")) {
						Optional<Sala> matchingSala = salas_iscte.getSalas().stream().filter(
								sala -> normaliza(sala.getNome_sala()).equals(normaliza(aula.getAula_sala_atribuida())))
								.findFirst();
						try {
							int value = Integer.parseInt(
									matchingSala.map(sala -> sala.getAtributos().get(Integer.parseInt(fieldSplit[1])))
											.orElse(null));
							context.set(fields[i], value);
						} catch (NumberFormatException e2) {
							return false;
						}
					} else {
						return false;
					}
					break;

				// Vai buscar o valor se for um campo de Aula
				case "a":
					context.set(fields[i], aula.getAtributos().get(Integer.parseInt(fieldSplit[1])));
					break;

				default:
					System.out.println("field não reconhecido.");
				}
			}
		}
		return (boolean) expression.evaluate(context);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getResult() {
		return result;
	}

//	  Para normalizar a String inserida, deixando só os caracteres a-z, A-Z, e 0-9

	private static String normaliza(String input) {
		String output = input.replaceAll("[^a-zA-Z0-9]", "");
		java.text.Normalizer.normalize(input, Normalizer.Form.NFD); // remove caracteres desconhecidos
		return output;

	}

}
