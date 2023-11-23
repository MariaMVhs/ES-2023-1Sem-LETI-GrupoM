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

//		Check why lines 161 - 163 are in comment

//funcao para ler o ficheiro, guarda o ficheiro numa lista de CSVRecords 
public class ReadFile {
	
	private List<Integer> fieldOrder;
	private String htmlPath;
	
	public ReadFile(String path, List<Integer> fieldOrder) {
        this.fieldOrder= fieldOrder;
        List<CSVRecord> records;
		try {
			records = readCSV(path);
	        writeTabulatorHTML(reorderFields(records));
	        htmlPath = System.getProperty("user.dir") + File.separator + "output.html";
//	        try {
//				openBrowser(new File(htmlPath).toURI());
//			} catch (URISyntaxException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPath() {
		return htmlPath;
	}
	
	private List<String[]> reorderFields(List<CSVRecord> rec){
		List<String[]> reorderedFile = new ArrayList<String[]>();
		for(int i = 0; i<rec.size(); i++) {
			String[] line = new String[fieldOrder.size()];
			for(int j = 0; j<fieldOrder.size(); j++) {
				line[j]=rec.get(i).get(fieldOrder.get(j));
			}
			reorderedFile.add(line);
		}
		return reorderedFile;
	}
	
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

    
//    public static void main(String[] args) throws IOException {
//    	//pede a localização do ficheiro
//        System.out.print("Enter CSV source (URL, 'user', or local file path): ");
//        //le os parametros passados
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String source = reader.readLine();
//        //chama a funcao readCSV com os parametros lidos
//        List<CSVRecord> records = readCSV(source);
//        //chama a funcao writeTabulator com a lista lida do readCSV, lista de CSVRecords
//        writeTabulatorHTML(records);
//    }
    

    public void writeTabulatorHTML(List<String[]> records) throws IOException {
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
            writer.println("<script>");
            
            //pega na lista de CSVRecords e escreve em HTML, de modo a ser interpretado pelo tabulator ( modo: {col:val} )
            writer.println("var data = [");

            //paginaçao, pois nao carregava os dados todos de uma vez, AINDA SEM SUCESSO
            int pageSize = 10;
            int currentPage = 1;
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, records.size());

            //percorre a lista e escreve no modo indicado
            for (int i = startIndex; i < endIndex; i++) {
                writer.println("{");
                String[] record = records.get(i);
                for (int j = 0; j < record.length; j++) {
                    String header = records.get(0)[j];
                    String value = record[j];
                    writer.println("\"" + header + "\": \"" + value + "\",");
                }
                writer.println("},");
            }

            writer.println("];");

            //configuraçoes do tabulator
            writer.println("var table = new Tabulator(\"#csv-table\", {");
            writer.println("data: data,");
            writer.println("layout: \"fitDataFill\",");
            writer.println("pagination: \"local\", // Enable local pagination");
            //paginacao
            writer.println("paginationSize: " + pageSize + ",");
            writer.println("paginationInitialPage: " + currentPage + ",");
            
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