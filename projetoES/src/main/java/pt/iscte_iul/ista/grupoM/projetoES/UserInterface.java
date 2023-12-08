package pt.iscte_iul.ista.grupoM.projetoES;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserInterface {

	private Salas salas_iscte;
	private Horario horario_iscte;
	private JFrame frame_main;
	private JPanel pnl_main;
	private CardLayout cardLayout;

//	TO DO: meter os botoes de return

	public UserInterface() {
		salas_iscte = new Salas();
		horario_iscte = new Horario();
		frame_main = new JFrame();
		pnl_main = new JPanel();
		cardLayout = new CardLayout();
		createMainPanel();
	}

//	  Apresenta o primeiro panel ao user

	public void start() {
		frame_main.getContentPane().add(pnl_main);
		frame_main.setVisible(true);
	}

//	  Janela para o utilizador dar o ficheiro csv com a informação das Salas

	public void window_readSalas() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(pnl_main);
		if (result == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = chooser.getSelectedFile();
			salas_iscte.readSalas(selectedFile);
		} else {
			System.out.println("Ficheiro n foi selecionado");
//			o ficheiro n foi selecionado
		}
	}

//	  Janela para o utilizador dar o ficheiro csv com a informação do Horario

	public void window_readHorario() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(pnl_main);
		if (result == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = chooser.getSelectedFile();
			horario_iscte.readHorario(selectedFile);
			String htmlPath=HtmlManager.createHtml(horario_iscte);
			horario_iscte.setHtmlPath(htmlPath);
		} else {
			System.out.println("Ficheiro n foi selecionado");
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
		frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_main.setSize(600, 200);
		frame_main.setLocationRelativeTo(null);
		pnl_main.setLayout(cardLayout);
		pnl_main.add(createStartPanel(), "start");
		pnl_main.add(createMudarOrdemPanel(), "ordem");
		pnl_main.add(createMostrarQualidadePanel(), "qualidade");
		pnl_main.add(createInserirMetricaPanel(), "inserir metrica");
	}

//	  Primeiro panel que aparece
//	  TO DO: ir buscar o path do html à class que o criou, ou dizer a essa mesma class para abrir o html

	private JPanel createStartPanel() {
		JPanel pnl_start = new JPanel(new GridLayout(1, 0));

//        Botão para mostrar o horário em html
		JButton btn_mostrarHorario = new JButton("Ver Horário");
		btn_mostrarHorario
				.addActionListener(e -> HtmlManager.openBrowser(horario_iscte.getHtmlPath()));
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
		panel.add(new JLabel("por implementar"));
		JButton btn_return = new JButton("voltar");
		btn_return.addActionListener(e -> cardLayout.show(pnl_main, "start"));
		panel.add(btn_return);
		return panel;
	}

//	  Panel para mostrar a qualidade do horario

	private JPanel createMostrarQualidadePanel() {
		JPanel panel = new JPanel(new GridLayout(3, 1));
		JButton btn_inserirMetrica = new JButton("Inserir Metrica");
		btn_inserirMetrica.addActionListener(e -> cardLayout.show(pnl_main, "inserir metrica"));
		panel.add(new JLabel("por implementar"));
		panel.add(btn_inserirMetrica);
		JButton btn_return = new JButton("voltar");
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
		pnl_middle.add(input_formula, BorderLayout.CENTER);
		pnl_principal.add(pnl_middle);

		JPanel pnl_bottom = new JPanel(new GridLayout(1, 2));
		JPanel pnl_bottom_right = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JPanel pnl_bottom_left = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnl_bottom.add(pnl_bottom_left);
		pnl_bottom.add(pnl_bottom_right);

//        Lista dropdown com os campos de Sala e Aula
		String[] fieldNames = { "fieldA", "fieldB", "fieldC" }; // Adicionar os nomes dos fields
		JComboBox fieldDropdown = new JComboBox<>(fieldNames);
		pnl_bottom_left.add(fieldDropdown);

//        Botão para adicionar o campo escolhido da lista
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Append selected field to the formula text box
				String selectedField = (String) fieldDropdown.getSelectedItem();
				input_formula.setText(input_formula.getText() + selectedField);
			}
		});
		pnl_bottom_left.add(addButton);

//        Botão para submeter a formula
//		  TO DO: Enviar formula para o HorarioRater.validadeFormula()
		JButton btn_submit = new JButton("Submit");
		btn_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String formula = input_formula.getText();
//				if(horarioRater.validateFormula()){
//					metricaNome=input_name.getText();
//					Metrica input_metrica=new Metrica(metricaNome, formula);
//					horarioRater.add(input_metrica);
//				} else {
//					mensagem a avisar user que a formula é inválida
				System.out.println("Submitted Formula: " + formula);
			}
		});
		pnl_bottom_right.add(btn_submit);

		pnl_principal.add(pnl_bottom);

		JButton btn_return = new JButton("voltar");
		btn_return.addActionListener(e -> cardLayout.show(pnl_main, "qualidade"));
		pnl_principal.add(btn_return);

		return pnl_principal;
	}

//	  Abrir o browser pela path do html criado

	private void openBrowser(String htmlPath) {

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
