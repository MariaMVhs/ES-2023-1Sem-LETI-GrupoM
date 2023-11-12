package pt.iscte_iul.ista.grupoM.projetoES;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class UserInterface {

	private JFrame pathWindow;
	private JFrame fieldsWindow;
	private JButton carregarHorario;
	private JButton quit;
	private JTextField pathInput;
	private ReadFile reader;
	
	public UserInterface() {
		pathWindow=new JFrame("main");
		pathWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pathWindow.setSize(200,400);
		pathWindow.setLocation(100,50);
		createButtons();
		pathWindow.setVisible(true);
	}
	
	private void createButtons() {
		pathWindow.setLayout(new GridLayout(0, 1));
		pathInput = new JTextField();
		setupCarregarHorario();
		setupQuit();
		pathWindow.add(carregarHorario);
		pathWindow.add(quit);
		pathWindow.add(pathInput);
	}
	
	private void setupCarregarHorario() {
		carregarHorario = new JButton("Importar horário");
		carregarHorario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                String path = pathInput.getText();
//                openFieldsWindow();
                usePath(path);
			}
			
		});
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
	
//	fieldsWindow will be used for the user to input the field order. WIP
	
	private void openFieldsWindow() {
		fieldsWindow=new JFrame("Select order of fields");
		fieldsWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fieldsWindow.setSize(200,400);
		fieldsWindow.setLocation(100,50);
		fieldsWindow.setLayout(new GridLayout(0, 1));
		fieldsWindow.add(quit);
		fieldsWindow.setVisible(true);
	}
	
	private void usePath(String path) {
		reader = new ReadFile(path);
	}
}
