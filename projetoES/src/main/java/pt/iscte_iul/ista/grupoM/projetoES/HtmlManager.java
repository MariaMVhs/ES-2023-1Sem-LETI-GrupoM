package pt.iscte_iul.ista.grupoM.projetoES;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HtmlManager {

	public static String createHtml(Horario aulas_iscte) {
		String htmlPath = "";
		int pageSize = 20;
		PrintWriter writer;
		try {
			writer = new PrintWriter("new_output.html");
			writer.println("<!DOCTYPE html>");
			writer.println("<html>");
			writer.println("<head>");
			// link para utilizaçao do tabulator
			writer.println(
					"<link href=\"https://unpkg.com/tabulator-tables@5.0.3/dist/css/tabulator.min.css\" rel=\"stylesheet\">");
			writer.println(
					"<script src=\"https://unpkg.com/tabulator-tables@5.0.3/dist/js/tabulator.min.js\"></script>");
			writer.println("<title>Horario ISCTE-IUL</title>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<h1>Horario ISCTE-IUL</h1>");
			writer.println("<div id=\"csv-table\"></div>");
			// writer.println("<button onclick=\"prevPage()\">Previous Page</button>");
			// writer.println("<button onclick=\"nextPage()\">Next Page</button>");
			writer.println("<script>");

			writer.println("var pageSize = " + pageSize + ";");

			// pega na lista de CSVRecords e escreve em HTML, de modo a ser interpretado
			// pelo tabulator ( modo: {col:val} )
			writer.println("var data = [");

			// percorre a lista e escreve no modo indicado
			for (int i = 1; i < aulas_iscte.getNum_aulas(); i++) {
				writer.println("{");
				Aula aula = aulas_iscte.getAula(i);
				List<String> record = aula.getAtributos();
				for (int j = 0; j < aula.getNum_atributos(); j++) {
					String header = aulas_iscte.getAtributos_aulas().get(j);
					String value = record.get(j);
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

			// escreve o nome das colunas, que vai buscar a primeira linha do CSV, neste
			// caso ao index 0 da lista de CSVRecords
			writer.println("columns: [");
			for (String header : aulas_iscte.getAtributos_aulas()) {
				writer.println("{ title: \"" + header + "\", field: \"" + header + "\" },");
			}

			writer.println("],");
			writer.println("});");

			// termina o HTML
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");

			htmlPath = System.getProperty("user.dir") + File.separator + "output.html";

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return htmlPath;
	}

	public static void openBrowser(String htmlPath) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(new File(htmlPath).toURI());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
