import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;



/**
 * GUI of the tracking service
 * 
 * @author Vincent Nelius, Salah Beck, Florian Wenzel
 * @version 1.0
 */

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private Container contentPane;
	private JLabel responseLabel;
	private JTextPane responseDisplay;
	private JScrollPane scrollPane;
	public JButton requestButton;
	
	public View(){
		super("Tracking Service BDW-41 Polished GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		contentPane = this.getContentPane();
		
		
		Dimension smallDimension = new Dimension(600,50);
		Dimension bigDimension	= new Dimension(600,400);
		responseLabel = new JLabel("Query Response");
		responseLabel.setPreferredSize(smallDimension);
		responseDisplay = new JTextPane();
		responseDisplay.setEditable(false);
		responseDisplay.setPreferredSize(bigDimension);
		requestButton = new JButton("Request User Data");
		requestButton.setPreferredSize(smallDimension);
		
		scrollPane = new JScrollPane(responseDisplay);
		contentPane.add(responseLabel);
		contentPane.add(scrollPane);
		contentPane.add(requestButton);
		
		this.pack();
		this.setVisible(true);
	}

	public void addActionListener(ActionListener actionListener) {
		requestButton.addActionListener(actionListener);
	}
	
	public void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public void setOutputUserData(String response) {
		responseDisplay.setText(response);
	}
}
