package pt.iscte_iul.ista.grupoM.projetoES;

import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.stream.Collectors;

public class Storage {

	private List<String[]> horario;
	private List<String[]> salas;
	
	public Storage(List<String[]> horario, List<CSVRecord> salas) {
		this.horario = horario;
		this.salas = convertFile(salas);
	}

	private static List<String[]> convertFile(List<CSVRecord> file) {
		return file.stream().map(Storage::convertCSVToString).collect(Collectors.toList());
	}

	private static String[] convertCSVToString(CSVRecord rec) {
		int size = rec.size();
		String[] stringArray = new String[size];
		for (int i = 0; i < size; i++) {
			stringArray[i] = rec.get(i);
		}
		return stringArray;
	}
	
	public List<String[]> getHorario(){
		return horario;
	}
	public List<String[]> getSalas(){
		return salas;
	}

}
