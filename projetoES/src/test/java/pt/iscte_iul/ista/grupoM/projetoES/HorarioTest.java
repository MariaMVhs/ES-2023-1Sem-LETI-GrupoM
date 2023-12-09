package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HorarioTest {
	
	Horario horario;

	@Test
	void testReadHorario() {
		
		horario = new Horario();
        File file = new File("HorarioDeExemplo.csv");
        List<Aula> aulas = horario.readHorario(file);
        assertEquals(26019, aulas.size());
		
        
        //para entrar no catch e dar falha na leitura do CSV
        File file2 = new File("HorarioDeExemlo.csv");
        List<Aula> aulas2 = horario.readHorario(file2);
        assertEquals(26019, aulas2.size());
		

	}

	
	@Test
	void testGetHtmlPath() {
		horario = new Horario();
        String htmlPath = System.getProperty("user.dir") + File.separator + "output.html";
        horario.setHtmlPath(htmlPath);
        assertEquals(htmlPath, horario.getHtmlPath());	
	}
	
	
}
