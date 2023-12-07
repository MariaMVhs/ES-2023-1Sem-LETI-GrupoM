package pt.iscte_iul.ista.grupoM.projetoES;

import java.util.List;

public class GestaoHorarios {

	public static void main(String[] args) {
	
		
		Salas salas_iscte = new Salas();
		Horario_old horario_iscte = new Horario_old();
		UserInterface window = new UserInterface();
	
		List<Sala> salas = salas_iscte.readSalas("CaracterizaçãoDasSalas.csv");
		System.out.println("Número de salas existentes: " + salas.size());
	}
	
	
}