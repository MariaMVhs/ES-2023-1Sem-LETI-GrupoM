package pt.iscte_iul.ista.grupoM.projetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import org.apache.commons.csv.CSVParser;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ReadCSVTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetRecords() throws IOException {
		boolean matchingRec = true;
		
		String tempFile = "tempFile.csv";
		String[] header = {"Curso", "Unidade Curricular", "Turno","Turma",
							"Inscritos no turno","Dia da semana", 
							"Hora início da aula", "Hora fim da aula",
							"Características da sala atribuída",
							"Sala atribuída à aula"};
		String[] row = {"ME", "Teoria dos jogos e dos Contratos", "01789TP01",
						"MEA1", "30", "Ter", "13:00:00", "14:30:00", "15/11/2022",
						"Sala Aulas Mestrado", "AA2.25"};
		String[] row2 = {"DF", "Investimentos II", "01074TP01",
						 "DFB1", "3", "Seg", "17:30:00", "19:00:00", "17/10/2022",
						 "Sala/anfiteatro aulas", "D1.07"};
		String[] row3 = {"DCTI", "Métodos de Investigação em Ciências e Tecnologias", "MICTITP01",
						 "DCTI-A-1", "20", "Seg", "18:00:00", "19:30:00", "24/10/2022",
						 "Sala Aulas Mestrado", "C5.09"};
		String[] row4 = {"PIUDHIST", "Seminário de Projeto I (Piudhist)", "SP-I_(Piudhist)S01",
						 "DHMCMG1", "0", "Seg", "19:00:00", "21:00:00", "12/12/2022",
						 "Sala Aulas Mestrado", "AA3.24"};
		
		
		
		try (CSVWriter wr= new CSVWriter(new FileWriter(tempFile))){
				wr.writeNext(header);
				wr.writeNext(row4);
				wr.writeNext(row3);
				wr.writeNext(row2);
				wr.writeNext(row);
		}
		
		List<CSVRecord> accRecs = ReadCSV.getRecords(tempFile);
		
		List<CSVRecord> exptdRecs = new ArrayList<>();
		
		try(CSVParser pars = new CSVParser(new FileReader(tempFile), CSVFormat.DEFAULT.withHeader(header))){
			for(CSVRecord rec : pars) {
				exptdRecs.add(rec);
			}
		}
		
		assertEquals(exptdRecs.size(), accRecs.size());
		
		for(int i = 0; i < exptdRecs.size(); i++) {
		//	assertArrayEquals(exptdRecs.get(i), accRecs.get(i).toArray());
		}
			
		
	}

	@Test
	void testWriteTabulatorHTML() {
		fail("Not yet implemented");
	}

}
