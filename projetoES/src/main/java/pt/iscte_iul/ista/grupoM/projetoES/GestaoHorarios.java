package pt.iscte_iul.ista.grupoM.projetoES;

import java.io.File;
import java.util.List;

public class GestaoHorarios {

	public static void main(String[] args) {
	
		
		Salas salas_iscte = new Salas();
		Horario horario_iscte = new Horario();
		UserInterface window = new UserInterface();
	
		List<Sala> salas = salas_iscte.readSalas("CaracterizaçãoDasSalas.csv");
		System.out.println("Número de salas existentes: " + salas.size());
		
		List<Aula> aulas = horario_iscte.readHorario("HorarioDeExemplo.csv");
		System.out.println("Número de aulas existentes: " + aulas.size());
	}
	
	
}