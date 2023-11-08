package pt.iscte_iul.ista.grupoM.projetoES;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
		File f;

		public ReadFile(File f) {
			this.f = f;
			checkFile(f);
		}
		
		
	public boolean checkFile(File f) {
		
		try {		
			System.out.println("entrou try");
				Scanner scan = new Scanner(f);
				
				if(scan.hasNextLine()) {
					
					System.out.println("if1");
					
					String line = scan.nextLine();
					String[] col = line.split(";");
					String[] titulos = {"Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno","Dia da semana", 
							"Hora início da aula", "Hora fim da aula","Data da aula", "Características da sala pedida para a aula", 
							"Sala atribuída à aula"};
					if (col.length == titulos.length) {
						for(int i = 0; i<col.length; i++) {
							if (!col[i].equals(titulos[i])) {
								System.out.println("Não funcionou");
								return false;
							}
						}
						System.out.println("Funcionou");
						return true;
					}
				}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Não Funcionou2"); 
		return false;
	}
	
	
	 public static void main(String[] args) {
	        File f = new File("CaracterizaçãoDasSalas.csv");
	        ReadFile rf = new ReadFile(f);
	    }
	
}
