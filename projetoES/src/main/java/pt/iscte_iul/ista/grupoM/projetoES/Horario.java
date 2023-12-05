package pt.iscte_iul.ista.grupoM.projetoES;

//importa bibliotecas para ler o ficheiro CSV
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

//funcao para ler o ficheiro, guarda o ficheiro numa lista de CSVRecords 

/**
 * Classe de um horario.
 */
public class Horario {
	
	private List<Integer> fieldOrder;
	private String htmlPath;
	private LocalTime t;
	private String time;
	
	/**
     * Construtor da classe Horario.
     *
     * @param path Caminho para o CSV.
     * @param fieldOrder Ordem dos campos no arquivo CSV.
     */
	
	public Horario() {
		LocalTime t;
	}
	
	public Horario(String time) {
		LocalTime t;
	}
	

	public Horario(String path, List<Integer> fieldOrder) {
		this.fieldOrder=fieldOrder;
        List<CSVRecord> records;
        
		try {
			records = readCSV(path);
	        writeTabulatorHTML(reorderFields(records),20);
	        htmlPath = System.getProperty("user.dir") + File.separator + "output.html";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public LocalTime horario(String time) {
		LocalTime localTime;
		
		try {
		localTime = LocalTime.parse(time);
		} catch (Exception e) {
			System.out.println("Hora inválida: " + time);
			return null;
		}
		
		
		
		if(!validTime(localTime)) {
			System.out.println("Hora inválida: " + time);
			return null;
		}
		
		return localTime;
		
	}
	
	public static boolean validTime(LocalTime t) {
		if(t.equals(LocalTime.MIN) || t.equals(LocalTime.MAX)) {
			return true;
		}else if(t.equals(LocalTime.of(24, 0, 0))){
			return false;
		}
		return true;
		
	}
	
	/**
     * Retorna o caminho para o HTML gerado.
     *
     * @return O caminho para o HTML gerado.
     */
    public String getPath() {
        return htmlPath;
    }

    /**
     * Reordena os campos de um CSV.
     *
     * @param rec lista de registos CSV.
     * @return lista de registos CSV com os campos reordenados.
     */
    private List<String[]> reorderFields(List<CSVRecord> rec){
    	
        List<String[]> reorderedFile = new ArrayList<String[]>();
        for(int i = 0; i<rec.size(); i++) {
            String[] line = new String[fieldOrder.size()];
            for(int j=0; j<fieldOrder.size(); j++){
                line[j]=rec.get(i).get(fieldOrder.get(j));
            }
            reorderedFile.add(line);
        }
        return reorderedFile;
    }
    
    /**
     * Le um CSV e retorna uma lista de registos CSV.
     *
     * @param source e o caminho do CSV.
     * @return uma lista de registos CSV.
     * @throws IOException se ocorrer um erro ao ler o arquivo CSV.
     */
    public List<CSVRecord> readCSV(String source) throws IOException {
    	
    	//variavel que guarda o PATH do ficheiro
        Reader reader;
        
        //verifica se é um link para o gitHUB, NAO TESTADO
        if (source.startsWith("http")) {
            URL url = new URL(source);
            reader = new InputStreamReader(url.openStream());
        //verifica se é um PATH para a diretoria do ficheiro, NAO TESTADO
        } else if (source.equals("user")) {
            reader = new InputStreamReader(System.in);
        //assume que o ficheiro esta na diretoria do projeto, A FUNCIONAR
        } else {
            reader = new FileReader(source);
        }
        
        //cria a lista de CSVRecords
        try (CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';'))) {
            return parser.getRecords();
        }
    }
    	
    /**
     * Escreve uma tabela HTML a partir de uma lista de registos CSV.
     *
     * @param records lista de registos CSV.
     * @param pageSize tamanho da pagina HTML.
     * @throws IOException se ocorrer um erro ao escrever a tabela HTML.
     */
    public static void writeTabulatorHTML(List<String[]> records, int pageSize) throws IOException {
    	//inicia HTML
        try (PrintWriter writer = new PrintWriter("output.html")) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            //link para utilizaçao do tabulator
            writer.println("<link href=\"https://unpkg.com/tabulator-tables@5.0.3/dist/css/tabulator.min.css\" rel=\"stylesheet\">");
            writer.println("<script src=\"https://unpkg.com/tabulator-tables@5.0.3/dist/js/tabulator.min.js\"></script>");
            writer.println("<title>Horario ISCTE-IUL</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>Horario ISCTE-IUL</h1>");
            writer.println("<div id=\"csv-table\"></div>");
            //writer.println("<button onclick=\"prevPage()\">Previous Page</button>");
            //writer.println("<button onclick=\"nextPage()\">Next Page</button>");
            writer.println("<script>");
                       
            writer.println("var pageSize = " + pageSize + ";");
            
            //pega na lista de CSVRecords e escreve em HTML, de modo a ser interpretado pelo tabulator ( modo: {col:val} )
            writer.println("var data = [");

            //percorre a lista e escreve no modo indicado
            for (int i = 1; i < records.size(); i++) {
                writer.println("{");
                String[] record = records.get(i);
                for ( int j = 0 ; j < record.length; j++) {
                    String header = records.get(0)[j];
                    String value = record[j];
                    writer.println("\"" + header + "\": \"" + value + "\",");
                }
                writer.println("},");
            }

            writer.println("];");

           
            writer.println("table = new Tabulator(\"#csv-table\", {");
            writer.println("data: data,");
            writer.println("layout: \"fitDataFill\",");
            writer.println("pagination: \"local\",");
            writer.println("paginationSize: pageSize,");
            
            //escreve o nome das colunas, que vai buscar a primeira linha do CSV, neste caso ao index 0 da lista de CSVRecords
            writer.println("columns: [");
            for (String header : records.get(0)) {
                writer.println("{ title: \"" + header + "\", field: \"" + header + "\" },");
            }
            
            writer.println("],");
            writer.println("});");
            
            
            //termina o HTML
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");
        }

        System.out.println("HTML done");
    }
}