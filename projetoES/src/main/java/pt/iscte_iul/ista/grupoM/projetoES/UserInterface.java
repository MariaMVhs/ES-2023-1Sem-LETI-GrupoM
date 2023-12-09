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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserInterface {

	private Salas salas_iscte;
	private Horario horario_iscte;
	private JFrame frame_main;
	private JPanel pnl_main;
	private CardLayout cardLayout;

	public UserInterface() {
		salas_iscte = new Salas();
		horario_iscte = new Horario();
		frame_main = new JFrame();
		pnl_main = new JPanel();
		cardLayout = new CardLayout();
	}

//	  Apresenta o primeiro panel ao user

	public void start() {
		
		if (horario_iscte.getNum_aulas() > 0 && salas_iscte.getNum_salas() > 0) {
			createMainPanel();
			frame_main.getContentPane().add(pnl_main);
			frame_main.setVisible(true);
		} else{
			System.exit(0);
		}
	}

//	  Janela para o utilizador dar o ficheiro csv com a informação das Salas

	public void window_readSalas() {
		
		JFileChooser chooser = new JFileChooser();											//Janela para escolher o ficheiro
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");	//Filtro para só mostrar ficheiros .csv
		chooser.setFileFilter(filter);
		
		File dir = new File(System.getProperty("user.dir"));								//Indica que começa a procura na 
		chooser.setCurrentDirectory(dir);													//diretoria deste projeto
		
		int result = chooser.showOpenDialog(pnl_main);
		if (result == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = chooser.getSelectedFile();							//O ficheiro selecionado
			salas_iscte.readSalas(selectedFile);
		}
	}

//	  Janela para o utilizador dar o ficheiro csv com a informação do Horario

	public void window_readHorario() {
		
		JFileChooser chooser = new JFileChooser();											//Janela para escolher o ficheiro
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");	//Filtro para só mostrar ficheiros .csv
		chooser.setFileFilter(filter);
		
		File dir = new File(System.getProperty("user.dir"));								//Indica que começa a procura na 
		chooser.setCurrentDirectory(dir);													//diretoria deste projeto
		
		int result = chooser.showOpenDialog(pnl_main);
		if (result == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = chooser.getSelectedFile();							//O ficheiro selecionado
			horario_iscte.readHorario(selectedFile);
			
			String htmlPath;
			try {																			//Tenta criar o ficheiro html
				htmlPath = HtmlManager.createHtml(horario_iscte);
				horario_iscte.setHtmlPath(htmlPath);
			} catch (FileNotFoundException e1) {
				showErrorMessage("Ficheiro não foi achado");
			}
		}
	}

//	  Para poder usar as Salas do GestaoHorarios

	public void setSalas(Salas salas_iscte) {
		this.salas_iscte = salas_iscte;
	}

//	  Para poder usar o Horario do GestaoHorarios

	public void setHorario(Horario horario_iscte) {
		this.horario_iscte = horario_iscte;
	}

//	  Cria o frame e o panel que vão guardar todos os outros panels

	private void createMainPanel() {
		
		frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Prepara a janela principal que tem os panels
		frame_main.setSize(750, 250);
		frame_main.setLocationRelativeTo(null);
		
		pnl_main.setLayout(cardLayout);									//Define o layout do panel principal como o CardLayout
		
		pnl_main.add(createStartPanel(), "start");						//Adiciona os restantes panels ao panel principal
		pnl_main.add(createMudarOrdemPanel(), "ordem");
		pnl_main.add(createMostrarQualidadePanel(), "qualidade");
		pnl_main.add(createInserirMetricaPanel(), "inserir metrica");
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
		
		JButton btn_qualidade = new JButton("Ver Qualidade");
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
		panel.add(new JLabel("por implementar"));								//a avisar que está incompleto
		JButton btn_return = new JButton("voltar");
		btn_return.addActionListener(e -> cardLayout.show(pnl_main, "start"));
		panel.add(btn_return);
		return panel;
	}

//	  Panel para mostrar a qualidade do horario

	private JPanel createMostrarQualidadePanel() {
		
		JPanel panel = new JPanel(new GridLayout(3, 1));
		JButton btn_inserirMetrica = new JButton("Inserir Metrica");			//Botão que leva ao panel de inserir uma metrica nova
		btn_inserirMetrica.addActionListener(e -> cardLayout.show(pnl_main, "inserir metrica"));
		
		panel.add(new JLabel("por implementar"));								//a avisar que está incompleto
		panel.add(btn_inserirMetrica);
		
		JButton btn_return = new JButton("voltar");								//Botão de voltar atrás
		btn_return.addActionListener(e -> cardLayout.show(pnl_main, "start"));
		panel.add(btn_return);
		
		return panel;
	}

//	  Panel para inserir uma metrica de avaliação

	private JPanel createInserirMetricaPanel() {
		
		JPanel pnl_principal = new JPanel(new GridLayout(4, 1));

//		  Espaço para inserir o nome da formula
		
		JPanel pnl_upper = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel label_name = new JLabel("Nome da metrica: ");
		JTextField input_name = new JTextField(5);
		pnl_upper.add(label_name);
		pnl_upper.add(input_name);
		pnl_principal.add(pnl_upper);

//        Campo de texto para inserir a formula
		
		JPanel pnl_middle = new JPanel(new BorderLayout());
		JTextField input_formula = new JTextField(20);
		input_formula.setText("fieldA - fieldB < 0");
//		input_formula.setText("Capacidade Normal - Inscritos no turno < 0");
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
		
		HorarioRater rater=new HorarioRater(salas_iscte, horario_iscte);
		
//        Botão para submeter a formula
		
		JButton btn_submit = new JButton("Submit");
		btn_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String formula = input_formula.getText();
				if(rater.validateFormula(formula)){							//verifica se a formula é válida
					String metricaNome=input_name.getText();
					rater.addMetrica(metricaNome, formula);					//guarda a metrica no HorarioRater
					cardLayout.show(pnl_main, "qualidade");					//volta ao panel anterior
				} else {
					JOptionPane.showMessageDialog(null, "Invalid formula", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pnl_bottom_right.add(btn_submit);

		pnl_principal.add(pnl_bottom);

//		  Botão de voltar atrás
		
		JButton btn_return = new JButton("voltar");
		btn_return.addActionListener(e -> cardLayout.show(pnl_main, "qualidade"));
		pnl_principal.add(btn_return);

		return pnl_principal;
	}
	
//	  Pop up a avisar de um erro com a mensagem inserida

	private static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
