package pt.iscte_iul.ista.grupoM.projetoES;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class UserInterface {

	private JFrame pathWindow;
	private JFrame fieldsWindow;
	private JFrame changeFieldsWindow;
	private JButton carregarHorario;
	private JButton quit;
	private JButton fieldsManter;
	private JButton fieldsAlterar;
	private ReadFile reader;
	private String htmlPath;
	private String csvPath;
	private List<Integer> fieldOrder;

	public UserInterface() {
		pathWindow = new JFrame("main");
		pathWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pathWindow.setSize(200, 400);
		pathWindow.setLocation(100, 50);
		createButtons();
		pathWindow.setVisible(true);
	}

	private void createButtons() {
		pathWindow.setLayout(new GridLayout(0, 1));
		setupCarregarHorario();
		setupQuit();
		pathWindow.add(carregarHorario);
		pathWindow.add(quit);
	}

	private void openFieldsWindow() {
		pathWindow.setVisible(false);
		fieldsWindow = new JFrame("Order of fields");
		fieldsWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fieldsWindow.setSize(200, 400);
		fieldsWindow.setLocation(100, 50);
		fieldsWindow.setLayout(new GridLayout(0, 1));
		setupFieldsOpen();
		setupFieldsAlterar();
		fieldsWindow.add(fieldsManter);
		fieldsWindow.add(fieldsAlterar);
		fieldsWindow.add(quit);
		fieldsWindow.setVisible(true);
	}

	private void openChangeFieldsWindow() {
		changeFieldsWindow = new JFrame("Change order of fields");
		changeFieldsWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		changeFieldsWindow.setSize(200, 400);
		changeFieldsWindow.setLocation(100, 50);
		changeFieldsWindow.setLayout(new GridLayout(0, 1));
		JTextField defaultOrderDisplay = new JTextField("Default order: Curso, Unidade Curricular, Turno, Turma,"
				+ " Inscritos no turno, Dia da semana, Hora início da aula," + " Hora fim da aula, Data da aula,"
				+ " Características da sala pedida para a aula, Sala atribuída à aula.");
		changeFieldsWindow.add(defaultOrderDisplay);
		setupChangeFieldsButtons();
		changeFieldsWindow.setVisible(true);

	}

	private void useCsvPath(String path) {
		reader = new ReadFile(path, fieldOrder);
		htmlPath = reader.getPath();
		try {
			openBrowser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void openBrowser() throws IOException, URISyntaxException {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			desktop.browse(new File(htmlPath).toURI());
		}
	}

//	Buttons

	//Abre um pop up para inserir o path do fichero
	private void setupCarregarHorario() {
		//Carrega um botao
	    carregarHorario = new JButton("Importar horário");
	    carregarHorario.addActionListener(new ActionListener() {
	        
	    	 @Override
	         public void actionPerformed(ActionEvent e) {
	             try {
	            	//funcao que executa a acao de clicar no botao
					showPathInputDialog();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
	         
	     });
	 }
	
	//funcao para carregar o path do ficherio fornecido
	 private void showPathInputDialog() throws Exception {
		 //Abre o pop up para inserir o path
		 String path = JOptionPane.showInputDialog(UserInterface.this, "Enter file path:");
		     
		 //Verifica se está vazio
		 if (path != null && !path.isEmpty()) {
	        csvPath = path;
	        openFieldsWindow();
	     } else {
	    	System.out.println("User canceled or entered an empty path");
	   }
		        
	 }
	
	
	private void setupFieldsOpen() {
		fieldsManter = new JButton("Abrir horário");
		fieldsManter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fieldOrder == null) {
					fieldOrder = new ArrayList<Integer>();
					Integer[] defaultOrder = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
					fieldOrder.addAll(Arrays.asList(defaultOrder));
				} else if (fieldOrder.size() != 11) {
					fieldOrder.clear();
					Integer[] defaultOrder = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
					fieldOrder.addAll(Arrays.asList(defaultOrder));
				}
				useCsvPath(csvPath);
				fieldsWindow.setVisible(false);
			}

		});
	}

	private void setupFieldsAlterar() {
		fieldsAlterar = new JButton("Alterar");
		fieldsAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openChangeFieldsWindow();
			}

		});
	}

	// Default order:
	// Curso, Unidade Curricular, Turno, Turma, Inscritos no turno, Dia da semana,
	// Hora início da aula, Hora fim da aula, Data
	// da aula, Características da sala pedida para a aula, Sala atribuída à aula.

	private void setupChangeFieldsButtons() {
		
		//JButton[] buttons = new JButton[];	
		fieldOrder = new ArrayList<Integer>();
		
		JButton curso = new JButton("Curso");
		JButton unidadeCurricular = new JButton("Unidade Curricular");
		JButton turno = new JButton("Turno");
		JButton turma = new JButton("Turma");
		JButton inscritos = new JButton("Inscritos no turno");
		JButton diaSemana = new JButton("Dia da semana");
		JButton horaInicio = new JButton("Hora início da aula");
		JButton horaFim = new JButton("Hora fim da aula");
		JButton data = new JButton("Data da aula");
		JButton caracteristicas = new JButton("Características da sala pedida para a aula");
		JButton sala = new JButton("Sala atribuída à aula");

		curso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(0);
				curso.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		unidadeCurricular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(1);
				unidadeCurricular.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		turno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(2);
				turno.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		turma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(3);
				turma.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		inscritos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(4);
				inscritos.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		diaSemana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(5);
				diaSemana.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		horaInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(6);
				horaInicio.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		horaFim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(7);
				horaFim.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		data.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(8);
				data.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		caracteristicas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(9);
				caracteristicas.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		sala.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldOrder.add(10);
				sala.setVisible(false);
				if (fieldOrder.size() == 11) {
					changeFieldsWindow.setVisible(false);
				}
			}

		});

		changeFieldsWindow.add(curso);
		changeFieldsWindow.add(unidadeCurricular);
		changeFieldsWindow.add(turno);
		changeFieldsWindow.add(turma);
		changeFieldsWindow.add(inscritos);
		changeFieldsWindow.add(diaSemana);
		changeFieldsWindow.add(horaInicio);
		changeFieldsWindow.add(horaFim);
		changeFieldsWindow.add(data);
		changeFieldsWindow.add(caracteristicas);
		changeFieldsWindow.add(sala);

	}

	private void setupQuit() {
		quit = new JButton("Cancel");
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
	}
}
