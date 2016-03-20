
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.util.*;

import DataGenerator.DataGenerator;
import de.due.ldsa.bd.AnalysisController;
import de.due.ldsa.bd.BDController;
import de.due.ldsa.ld.LinkDataReceiverImpl;
import de.due.ldsa.ld.exceptions.UnexpectedJsonStringException;

/**
 * GUI of the tracking services generator and analysis results
 * 
 * @author  Salah Beck
 * @version 1.0
 */

public class GeneratorDemoView extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextPane responseDisplay;
	public JButton btnBigDataAnalysis;
	private String mockData;	
	private LinkDataReceiverImpl linkDataLayer; 
	private BDController bdController;
	
	public static void main(String[] args) {
		GeneratorDemoView frame = new GeneratorDemoView();
		
	}

	public GeneratorDemoView() {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());

		JTextArea display = new JTextArea(16, 58);
		display.setEditable(false);
		JScrollPane scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		middlePanel.add(scroll);
		btnBigDataAnalysis = new JButton("Big Data Analysis Demo");
		middlePanel.add(btnBigDataAnalysis, BorderLayout.SOUTH);

		JFrame frmGeneratorGui = new JFrame();
		frmGeneratorGui.setTitle("Generator GUI");
		frmGeneratorGui.getContentPane().add(middlePanel);
		frmGeneratorGui.pack();
		frmGeneratorGui.setLocationRelativeTo(null);
		this.setTitle("Generator GUI");
		frmGeneratorGui.setVisible(true);
		frmGeneratorGui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addActionListener(this);
		
		linkDataLayer = LinkDataReceiverImpl.getInstance();
		linkDataLayer.setOnlineAnalysis(true);
		bdController = new AnalysisController();
	}

	public void addActionListener(ActionListener actionListener) {
		btnBigDataAnalysis.addActionListener(actionListener);
	}

	public void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public void setOutputUserData(String response) {
		responseDisplay.setText(response);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			mockData = DataGenerator.giveRandomData();
			System.out.println(mockData);
			linkDataLayer.setComments(mockData);
			bdController.analysis("Real-Time", "BC");
		} catch (UnexpectedJsonStringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void appendLineToOutput(String line){
		StyledDocument doc = (StyledDocument) responseDisplay.getDocument();
		try {
			doc.insertString(doc.getLength(), line, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
