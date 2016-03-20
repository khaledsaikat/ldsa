
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
 * @author Salah Beck
 * @version 1.0
 */

public class GeneratorDemoView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextPane responseDisplay;
	public JButton btnBigDataAnalysis;
	public JButton btnStopAnalysis;
	private String mockData;
	private LinkDataReceiverImpl linkDataLayer;
	private BDController bdController;
	private Timer timer;
	private JTextArea display;
	private boolean running = false;
	private boolean timerStarted = false;

	public static void main(String[] args) {
		GeneratorDemoView frame = new GeneratorDemoView();

	}

	public GeneratorDemoView() {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());

		responseDisplay = new JTextPane();

		display = new JTextArea(16, 58);
		display.setEditable(false);
		JScrollPane scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		middlePanel.add(scroll);
		btnBigDataAnalysis = new JButton("Big Data Analysis Demo");
		btnStopAnalysis = new JButton("Stop Analysis");
		JPanel pnlButtons = new JPanel(new FlowLayout());
		pnlButtons.add(btnBigDataAnalysis);
		pnlButtons.add(btnStopAnalysis);
		middlePanel.add(pnlButtons, BorderLayout.SOUTH);

		JFrame frmGeneratorGui = new JFrame();
		frmGeneratorGui.setTitle("Generator GUI");
		frmGeneratorGui.getContentPane().add(middlePanel);
		frmGeneratorGui.pack();
		frmGeneratorGui.setLocationRelativeTo(null);
		this.setTitle("Generator GUI");
		frmGeneratorGui.setVisible(true);
		frmGeneratorGui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		timer = new Timer(2000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (running && bdController.getResult() != null && bdController.getResult().size() > 0) {
					for (String result : bdController.getResult()) {
						display.append(result + "\n");
					}
				} else {
					display.append("No results available yet" + "\n");
				}
			}
		});
		addActionListeners();

		linkDataLayer = LinkDataReceiverImpl.getInstance();
		linkDataLayer.setOnlineAnalysis(true);
		bdController = new AnalysisController();

		timer.setInitialDelay(2000);
		timer.start();
		timerStarted = true;
	}

	public void addActionListeners() {
		btnBigDataAnalysis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!timerStarted) {
					timer.start();
				} else {
					try {
						display.append("Generating Random Data" + "\n");
						mockData = DataGenerator.giveRandomData();
						System.out.println(mockData);
						linkDataLayer.setComments(mockData);
						bdController.analysis("Real-Time", "BC");
						running = true;
					} catch (UnexpectedJsonStringException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
			}
		});
		btnStopAnalysis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				timerStarted = false;
			}
		});
	}

	public void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public void setOutputUserData(String response) {
		responseDisplay.setText(response);
	}

	private void appendLineToOutput(String line) {
		StyledDocument doc = (StyledDocument) responseDisplay.getDocument();
		try {
			doc.insertString(doc.getLength(), line, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
