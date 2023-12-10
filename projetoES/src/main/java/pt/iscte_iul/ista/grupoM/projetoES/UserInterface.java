package pt.iscte_iul.ista.grupoM.projetoES;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A UserInterface e responsavel pela interface grafica do sistema de gestao de horario.
 * 
 */
public class UserInterface {

	private Salas salas_iscte;
	private Horario horario_iscte;
	private JFrame frame_main;
	private JPanel pnl_main;
	private CardLayout cardLayout;
	private HorarioRater classificador;

	 /**
     * Construtor UserInterface
     * 
     */
	public UserInterface() {
		salas_iscte = new Salas();
		horario_iscte = new Horario();
		frame_main = new JFrame();
		pnl_main = new JPanel();
		cardLayout = new CardLayout();
	}

//	  Apresenta o primeiro panel ao user
	 /**
     * Inicia a interface grafica
     * 
     */
	public void start() {

		if (horario_iscte.getNum_aulas() > 0 && salas_iscte.getNum_salas() > 0) {
			classificador = new HorarioRater(salas_iscte, horario_iscte);
			createMainPanel();
			frame_main.getContentPane().add(pnl_main);
			frame_main.setVisible(true);
		} else {
			System.exit(0);
		}
	}

//	  Janela para o utilizador dar o ficheiro csv com a informação das Salas
	/**
	 *  Janela para o utilizador dar o ficheiro csv com a informacao das Salass
	 * 
     */
	public void window_readSalas() {

		JFileChooser chooser = new JFileChooser(); // Janela para escolher o ficheiro

		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv"); // Filtro para só mostrar
																							// ficheiros .csv
		chooser.setFileFilter(filter);

		File dir = new File(System.getProperty("user.dir")); // Indica que começa a procura na
		chooser.setCurrentDirectory(dir); // diretoria deste projeto

		int result = chooser.showOpenDialog(pnl_main);
		if (result == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = chooser.getSelectedFile(); // O ficheiro selecionado
			salas_iscte.readSalas(selectedFile);
		}
	}

//	  Janela para o utilizador dar o ficheiro csv com a informacao do Horario
	
	/**
	 * Janela para o utilizador dar o ficheiro csv com a informacao do Horario
	 */
	public void window_readHorario() {

		JFileChooser chooser = new JFileChooser(); // Janela para escolher o ficheiro

		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv"); // Filtro para só mostrar
																							// ficheiros .csv
		chooser.setFileFilter(filter);

		File dir = new File(System.getProperty("user.dir")); // Indica que começa a procura na
		chooser.setCurrentDirectory(dir); // diretoria deste projeto

		int result = chooser.showOpenDialog(pnl_main);
		if (result == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = chooser.getSelectedFile(); // O ficheiro selecionado
			horario_iscte.readHorario(selectedFile);

			String htmlPath;
			try { // Tenta criar o ficheiro html
				htmlPath = HtmlManager.createHtml(horario_iscte);
				horario_iscte.setHtmlPath(htmlPath);
			} catch (FileNotFoundException e1) {
				showErrorMessage("Ficheiro não foi achado");
			}
		}
	}

//	  Para poder usar as Salas do GestaoHorarios
	 /**
     * Define as Salas a serem utilizadas.
     *
     * @param salas_iscte Salas a ser utilizado.
     */
	public void setSalas(Salas salas_iscte) {
		this.salas_iscte = salas_iscte;
	}

//	  Para poder usar o Horario do GestaoHorarios
	/**
     * Define o Horario a ser utilizado.
     *
     * @param horario_iscte Horario a ser utilizado.
     */
	public void setHorario(Horario horario_iscte) {
		this.horario_iscte = horario_iscte;
	}
	
//	  Para poder usar um HorarioRater importado
	/**
   * Define o Classificador de Horario a ser utilizado.
   *
   * @param classificador Horario a ser utilizado.
   */
	public void setHorarioRater(HorarioRater classificador) {
		this.classificador = classificador;
	}

//	  Cria o frame e o panel que vão guardar todos os outros panels

	private void createMainPanel() {

		frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Prepara a janela principal que tem os panels
		frame_main.setSize(750, 250);
		frame_main.setLocationRelativeTo(null);

		pnl_main.setLayout(cardLayout); // Define o layout do panel principal como o CardLayout

		pnl_main.add(createStartPanel(), "start"); // Adiciona os restantes panels ao panel principal
		pnl_main.add(createMudarOrdemPanel(), "ordem");
		pnl_main.add(createQualidadePanel(), "qualidade");
		pnl_main.add(createEditarMetricasPanel(), "editar metricas");
		pnl_main.add(createAddMetricaPanel(), "add metrica");
	}

//	  Primeiro panel que aparece

	private JPanel createStartPanel() {

		JPanel pnl_start = new JPanel(new GridLayout(1, 0));

//        Botão para mostrar o horário em html

		JButton btn_mostrarHorario = new JButton("Ver Horário");
		btn_mostrarHorario.addActionListener(e -> {
			try {
				HtmlManager.openBrowser(horario_iscte.getHtmlPath());
			} catch (IOException e2) {
				showErrorMessage("Erro a ler ficheiro: " + e2.getMessage());
			}
		});
		pnl_start.add(btn_mostrarHorario);

//        Botão para abrir o panel da qualidade do horário

		JButton btn_qualidade = new JButton("Qualidade");
		btn_qualidade.addActionListener(e -> cardLayout.show(pnl_main, "qualidade"));
		pnl_start.add(btn_qualidade);

//        Botão para indicar a ordem os campos do ficheiro csv

		JButton btn_ordem = new JButton("Alterar Ordem");
		btn_ordem.addActionListener(e -> cardLayout.show(pnl_main, "ordem"));
		pnl_start.add(btn_ordem);
		return pnl_start;
	}

//	  Panel para indicar a ordem dos campos no ficheiro csv

	private JPanel createMudarOrdemPanel() {

		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(new JLabel("por implementar")); // a avisar que está incompleto
		JButton btn_return = new JButton("voltar");
		btn_return.addActionListener(e -> cardLayout.show(pnl_main, "start"));
		panel.add(btn_return);
		return panel;
	}

//	  Panel para gerir a qualidade do horario

	private JPanel createQualidadePanel() {

		JPanel panel = new JPanel(new GridLayout(1, 3));

		JButton btn_editarMetricas = new JButton("Editar Metricas"); // Botão que leva ao panel de inserir uma metrica
																		// nova
		JButton btn_mostrarMetricas = new JButton("Ver Metricas");
		JButton btn_return = new JButton("voltar"); // Botão de voltar atrás

		btn_editarMetricas.addActionListener(e -> cardLayout.show(pnl_main, "editar metricas"));
		btn_mostrarMetricas.addActionListener(e -> createVerQualidadeFrame());
		btn_return.addActionListener(e -> cardLayout.show(pnl_main, "start"));

		panel.add(btn_editarMetricas);
		panel.add(btn_mostrarMetricas);
		panel.add(btn_return);

		return panel;
	}
	
//	  Panel com opções para editar as metricas (adicionar / remover / alterar nome)
	
	private JPanel createEditarMetricasPanel(){

		JPanel panel = new JPanel(new GridLayout(1, 4));

		JButton btn_addMetrica = new JButton("Inserir Metrica"); // Botão que leva ao panel de inserir uma metrica
																		// nova
		JButton btn_removerMetrica = new JButton("Remover Metrica"); // Botão que leva ao panel de remover uma metrica

		JButton btn_alterarNomeMetrica = new JButton("Alterar Nome de Metrica"); // Botão que leva ao panel de alterar o nome
																			// de uma metrica
		btn_addMetrica.addActionListener(e -> cardLayout.show(pnl_main, "add metrica"));
		btn_removerMetrica.addActionListener(e -> createRemoveMetricaFrame());
		btn_alterarNomeMetrica.addActionListener(e -> createRenameMetricaFrame());
		panel.add(btn_addMetrica);
		panel.add(btn_removerMetrica);
		panel.add(btn_alterarNomeMetrica);
		
//		  Botão de voltar atrás

		JButton btn_return = new JButton("voltar");
		btn_return.addActionListener(e -> cardLayout.show(pnl_main, "qualidade"));
		panel.add(btn_return);
		
		return panel;
	}

//	  Panel para inserir uma metrica de avaliação

	private JPanel createAddMetricaPanel() {

		JPanel pnl_principal = new JPanel(new GridLayout(4, 1));

//		  Espaço para inserir o nome da formula

		JPanel pnl_upper = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel label_name = new JLabel("Nome da metrica: ");
		JTextField input_name = new JTextField(5);
		input_name.setText("Exemplo");
		pnl_upper.add(label_name);
		pnl_upper.add(input_name);
		pnl_principal.add(pnl_upper);

//        Campo de texto para inserir a formula

		JPanel pnl_middle = new JPanel(new BorderLayout());
		JTextField input_formula = new JTextField(20);
//		input_formula.setText("fieldA - fieldB < 0");
		input_formula.setText("Capacidade Normal - Inscritos no turno < 0");
		pnl_middle.add(input_formula, BorderLayout.CENTER);
		pnl_principal.add(pnl_middle);

		JPanel pnl_bottom = new JPanel(new GridLayout(1, 2));
		JPanel pnl_bottom_right = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JPanel pnl_bottom_left = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnl_bottom.add(pnl_bottom_left);
		pnl_bottom.add(pnl_bottom_right);

//        Lista dropdown com os campos de Sala e Aula

		List<String> atributosAulas = horario_iscte.getAtributos_aulas();
		List<String> atributosSalas = salas_iscte.getAtributos_salas();
		List<String> atributos = new ArrayList<>(atributosAulas);
		atributos.addAll(atributosSalas);
		String[] fieldNames = atributos.toArray(new String[0]);
		JComboBox<String> fieldDropdown = new JComboBox<>(fieldNames);
		pnl_bottom_left.add(fieldDropdown);

//        Botão para adicionar o campo escolhido da lista

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedField = (String) fieldDropdown.getSelectedItem();
				input_formula.setText(input_formula.getText() + selectedField);
			}
		});
		pnl_bottom_left.add(addButton);

//        Botão para submeter a formula

		JButton btn_submit = new JButton("Submit");
		btn_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String formula = input_formula.getText();
				if (classificador.validateFormula(formula)) { // verifica se a formula é válida
					String metricaNome = input_name.getText();
					classificador.addMetrica(metricaNome, formula); // guarda a metrica no HorarioRater
					cardLayout.show(pnl_main, "editar metricas"); // volta ao panel anterior
				} else {
					JOptionPane.showMessageDialog(null, "Invalid formula", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pnl_bottom_right.add(btn_submit);

		pnl_principal.add(pnl_bottom);

//		  Botão de voltar atrás

		JButton btn_return = new JButton("voltar");
		btn_return.addActionListener(e -> cardLayout.show(pnl_main, "editar metricas"));
		pnl_principal.add(btn_return);

		return pnl_principal;
	}
	
//	  Frame para remover uma Metrica
	
	private void createRemoveMetricaFrame(){
		
		//Criar a janela
		JFrame frame = new JFrame("Remover uma metrica");
		frame.setSize(200, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pnl_removerMetrica = new JPanel();
		pnl_removerMetrica.setLayout(new BoxLayout(pnl_removerMetrica, BoxLayout.PAGE_AXIS));
		
		//Texto no topo da janela
		JLabel label_remover = new JLabel("Metricas:");
		pnl_removerMetrica.add(label_remover);
		
		//Lista com os nomes das Metricas criadas
		String[] array_nomes = classificador.getNome_metricas().toArray(new String[0]);
		JList<String> lista_nomes = new JList<>(array_nomes);
		JScrollPane scrollPane = new JScrollPane(lista_nomes);
		pnl_removerMetrica.add(scrollPane);
		
		//Botão para remover a Metrica selecionada na lista
		JButton btn_remover = new JButton("remover");
		btn_remover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                String metrica_selecionada = lista_nomes.getSelectedValue();
				if(classificador.removeMetrica(metrica_selecionada)){
					frame.dispose();
				} else {
					showErrorMessage("Não foi possivel remover Metrica");
				}
			}
		});
		pnl_removerMetrica.add(btn_remover);

		frame.add(pnl_removerMetrica);
		frame.setVisible(true);
	}
	
//	  Frame para alterar o nome a uma Metrica
	
	private void createRenameMetricaFrame(){

		//Criar a janela
		JFrame frame = new JFrame("Alterar nome a metrica");
		frame.setSize(200, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pnl_renameMetrica = new JPanel();
		pnl_renameMetrica.setLayout(new BoxLayout(pnl_renameMetrica, BoxLayout.PAGE_AXIS));

		//Texto no topo da janela
		JLabel label_remover = new JLabel("Metricas:");
		pnl_renameMetrica.add(label_remover);

		//Lista com os nomes das Metricas criadas
		String[] array_nomes = classificador.getNome_metricas().toArray(new String[0]);
		JList<String> lista_nomes = new JList<>(array_nomes);
		JScrollPane scrollPane = new JScrollPane(lista_nomes);
		pnl_renameMetrica.add(scrollPane);
		
		//Secção para inserir o nome
		JPanel pnl_inputNome = new JPanel(new FlowLayout());
		
		JLabel label_newName = new JLabel("Novo Nome:");
		pnl_inputNome.add(label_newName);
		JTextField input_newName = new JTextField(5);
		pnl_inputNome.add(input_newName);
		pnl_renameMetrica.add(pnl_inputNome);
		
		//Botão para efetuar a alteração do nome
		JButton btn_rename = new JButton("Mudar o nome");
		btn_rename.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newName = input_newName.getText();
                String metrica_selecionada = lista_nomes.getSelectedValue();
				if(classificador.renameMetrica(metrica_selecionada, newName)){
					frame.dispose();
				} else {
					showErrorMessage("Não foi possivel mudar o nome da Metrica \"" + newName + "\"");
				}
			}
		});
		pnl_renameMetrica.add(btn_rename);

		frame.add(pnl_renameMetrica);
		frame.setVisible(true);
	}
	
	
//	  Frame com a avaliação do horário e uma tabela das metricas

	private void createVerQualidadeFrame() {

		//Criar a janela
		JFrame frame = new JFrame("Tabela de metricas");
		frame.setSize(200, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pnl_qualidade = new JPanel();
		pnl_qualidade.setLayout(new BoxLayout(pnl_qualidade, BoxLayout.PAGE_AXIS));

		//Texto no topo a dizer:
		//	-a avaliação dada ao horário se já houverem metricas
		//	-que não há metricas se não tiver qualquer metrica
		JLabel label_qualidade = new JLabel();
		if (classificador.getNum_metricas() > 0) {
			label_qualidade.setText("Qualidade do horário: " + (int) Math.round(classificador.getRating() * 100) + "/100");
		} else {
			label_qualidade.setText("Nenhuma metrica disponível");
		}
		pnl_qualidade.add(label_qualidade);
		
		//Tabela com o nome da Metrica à esquerda e o numero de aulas confirmadas pela formula à direita
		String[] nomesColunas = { "Metrica", "Resultado" };
		String[][] data = classificador.getTableInfo();
		JTable tabela = new JTable(data, nomesColunas);
		JScrollPane scrollPane = new JScrollPane(tabela);
		pnl_qualidade.add(scrollPane);

		frame.add(pnl_qualidade);
		frame.setVisible(true);
	}

//	  Pop up a avisar de um erro com a mensagem inserida

	private static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}
