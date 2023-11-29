package pt.iscte_iul.ista.grupoM.projetoES;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class UserInterface {

	private JFrame pathWindow;
	private JFrame fieldsWindow;
	private JButton carregarHorario;
	private JButton quit;
	private Horario reader;
	
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
		setupCarregarHorario();
		setupQuit();
		pathWindow.add(carregarHorario);
		pathWindow.add(quit);
	}
	
	/*private void setupCarregarHorario() {
		carregarHorario = new JButton("Importar horário");
		carregarHorario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                String path = pathInput.getText();
                //openFieldsWindow();
                usePath(path);
			}
			
		});
	}*/
	
	private void setupCarregarHorario() {
	    carregarHorario = new JButton("Importar horário");
	    carregarHorario.addActionListener(new ActionListener() {
	        
	    	 @Override
	         public void actionPerformed(ActionEvent e) {
	             try {
					showPathInputDialog();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
	         
	     });
	 }

	 private void showPathInputDialog() throws Exception {
		 // Show an input dialog to get the file path from the user
		 String path = JOptionPane.showInputDialog(UserInterface.this, "Enter file path:");
		     
		 // Check if the user entered a path
		 if (path != null && !path.isEmpty()) {
	        usePath(path);
	     } else {
	    // Handle the case where the user canceled or entered an empty path
	    // You can show a message or take appropriate action
        System.out.println("User canceled or entered an empty path");
	   }
		        
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
	
	private void usePath(String path) throws Exception {
		reader = new Horario(path);
	}
}
