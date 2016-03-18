
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

/**
 * GUI of the tracking services generator and analysis results
 * 
 * @author  Salah Beck
 * @version 1.0
 */

public class GeneratorDemoView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextPane responseDisplay;
	public JButton btnBigDataAnalysis;

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
}
