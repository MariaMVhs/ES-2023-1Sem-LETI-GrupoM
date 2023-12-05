package pt.iscte_iul.ista.grupoM.projetoES;

public class Main {

	public static void main(String[] args) {
	
	UserInterface window = new UserInterface();
	Horario h1 = new Horario("00:00:00");
	Horario h2 = new Horario("23:59:59");
	Horario h3 = new Horario("24:00:00"); // its supposed to throw an exception (invalid time)
	}
}