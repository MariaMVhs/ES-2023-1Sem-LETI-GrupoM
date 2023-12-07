package pt.iscte_iul.ista.grupoM.projetoES;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class HorarioRater {

	private List<CSVRecord> salas;
	private List<String[]> horario;
	private Storage storage;
	private List<Metrica> metricas;
	private List<String> fields;
	private List<String> operators;

	HorarioRater(Horario horario) {
//		this.horario=horario.getList();
		readSalas();
		metricas = new ArrayList<Metrica>();
		storage = new Storage(this.horario, salas);
	}

	//unsure how we're supposed to get the file with the classrooms' info
	private void readSalas() {
		Reader reader;
		try {
			reader = new FileReader(System.getProperty("user.dir") + File.separator + "CaracterizaçãoDasSalas");
			CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';'));
			salas = parser.getRecords();
			parser.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addMetrica(String name, String formula) {
		if (validateFormula(formula)) {
			Metrica metrica = new Metrica(name, formula, storage);
			metricas.add(metrica);
		} else {
			System.out.println("invalid formula"); // change to tell UserInterface to tell user this
		}
	}

	private boolean validateFormula(String formula) {
		String[] splitForm = formula.split(" ");
		boolean isField = true;
		boolean hasHorarioField = false;
		for (String s : splitForm) {
			if (isField) {
				if (!(fields.contains(s) || isInteger(s))) {
					return false;
				}
				if (!hasHorarioField && isHorarioField(s)) {
					hasHorarioField=true;
				}
			} else {
				if (!operators.contains(s)) {
					return false;
				}
			}
			isField = !isField;
		}
		if (!(isField && hasHorarioField)) {
			return false;
		} else {
			return true;
		}
	}
	
	private static boolean isHorarioField(String field) {
	    return !isInteger(field) && field.startsWith("h.");
	}

	private static boolean isInteger(String s) {
	    try {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	// Adicionar uma janela para o user adicionar as metricas

	// Tipos de operações possiveis:
	// No meio:
	// +
	// -
	// *
	// /
	// No fim:
	// =
	// ≠
	// >
	// <
	// ≥
	// ≤

	// Tipos de variaveis possiveis nas formulas:
	// int
	// field de salas
	// field de horario

//	Lista de fields de salas:
//		1.Edificio
//		2.Nome Sala
//		3.Capacidade Normal
//		4.Capacidade Exame
//		5.Nº Caracteristicas
//		6.As Caracteristicas - 31 booleans
//	
//	Lista de fields de horario:
//		1.Curso
//		2.Unidade Curricular
//		3.Turno
//		4.Turma
//		5.Inscritos no Turno
//		6.Dia de Semana
//		7.Hora Inicio de Aula
//		8.Hora Fim de Aula
//		9.Data de Aula
//		10.Caracteristicas Pedidas
//		11.Sala

//	public int aulasSobrelotadasNum() {
//		int n = 0;
//		for (int i = 0; i < salas.size(); i++) {
//			final int j=i;
//			int capacity = Integer.valueOf(salas.get(j).get(2));
//			n += (int) horario.stream().filter(name -> salas.get(j).get(0)==name[1])
//				 .filter(number -> Integer.valueOf(number[4])<capacity).count(); // unsure if "(int)" is needed"
//		}
//		return n;
//	}
//	
//	public int aulasSobrelotadasExtra() {
//		int n = 0;
//		for (int i = 0; i < salas.size(); i++) {
//			final int j=i;
//			int capacity = Integer.valueOf(salas.get(j).get(2));
//			n += (int) horario.stream().filter(name -> salas.get(j).get(0)==name[1])
//				 .filter(number -> Integer.valueOf(number[4])<capacity).count(); // unsure if "(int)" is needed"
//		}
//		return n;
//	}

}
