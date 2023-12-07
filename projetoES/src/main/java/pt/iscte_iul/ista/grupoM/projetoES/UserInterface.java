package pt.iscte_iul.ista.grupoM.projetoES;

import java.awt.CardLayout;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserInterface {

//	private Salas salas_iscte;
//	private Horario horario_iscte;
	private JFrame window_main;

	public UserInterface() {
		window_main = new JFrame();
	}

	public void window_setSalas() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(window_main);
		if (result == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = chooser.getSelectedFile();
//			salas_iscte.carregar_salas(selectedFile);
		} else {
			System.out.println("Ficheiro n foi selecionado");
//			o ficheiro n foi selecionado
		}
	}

	public void window_setHorario() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(window_main);
		if (result == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = chooser.getSelectedFile();
//			horario_iscte.carregar_salas(selectedFile);
		} else {
			System.out.println("Ficheiro n foi selecionado");
//			o ficheiro n foi selecionado
		}
	}

}
