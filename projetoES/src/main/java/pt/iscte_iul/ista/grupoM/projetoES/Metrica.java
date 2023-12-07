package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.DoubleBinaryOperator;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

public class Metrica {

	private List<String[]> salas; // trazer as listas do Storage
	private List<String[]> horario;

	private String name;
	private String formula;
	private int result;
	private JexlEngine jexl;

//	No input da formula os fields vao ser escritos como s.(index do field nas salas) ou h.(index do field em horario)

	public Metrica(String name, String formula, Storage storage) {
		jexl = new JexlBuilder().create();
		this.name = name;
		this.formula = formula;
		salas = storage.getSalas();
		horario = storage.getHorario();
		calcMetrica();
	}

	private void calcMetrica() {
		result = (int) horario.stream().filter(aula -> evaluate(aula)).count();
	}

	private boolean evaluate(String[] aula) {
		JexlExpression expression = jexl.createExpression(formula);
		JexlContext context = new MapContext();
		String[] fields = formula.split(" ");
		for (int i = 0; i < fields.length; i += 2) {
			try {
				Integer.parseInt(fields[i]);
			} catch (NumberFormatException e) {
				String[] fieldSplit = fields[i].split("\\.");
				switch (fieldSplit[0]) {
				case "s":
					context.set(fields[i], aula[Integer.parseInt(fieldSplit[1])]);
					break;
				case "h":
					Optional<String[]> matchingSala = salas.stream().filter(sala -> sala[1].equals(aula[10]))
							.findFirst();
					int value = Integer
							.parseInt(matchingSala.map(sala -> sala[Integer.parseInt(fieldSplit[1])]).orElse(null));
					context.set(fields[i], value);
					break;
				default:
					System.out.println("field não reconhecido.");
				}
			}
		}
		return (boolean) expression.evaluate(context);
	}

	public String getName() {
		return name;
	}

	public double getResult() {
		return result;
	}

}
