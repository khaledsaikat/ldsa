import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;

/**
 * GUI of the tracking service
 * 
 * @author Vincent Nelius
 * @version 1.0
 */

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private Container contentPane;
	private Container inputPane;
	private Container outputPane;
	
	private JTextField inputClientId; 
	private JTextField inputClientSecret;
	private JTextField inputCallbackURL;
	private JTextField inputScope;
	private JTextField inputConnectTimeout;
	private JTextField inputReadTimeout;
	private JTextField inputVerifier;
	private JTextField inputAccessToken;
	
	private JLabel clientIdLabel;
	private JLabel clientSecretLabel;
	private JLabel callbackURLLabel;
	private JLabel scopeLabel;
	private JLabel connectTimeoutLabel;
	private JLabel readTimeoutLabel;
	private JLabel accessTokenLabel;

	
	public JButton buildServiceButton;
	public JButton accessTokenButton;
	public JButton getInstagramButton;
	public JButton requestUserDataButton;
	
	private JLabel authorizationURLLabel;
	private JTextField outputAuthorizationURL;
	private JTextPane outputUserData;
	
	public View(){
		super("Tracking Service BDW-41 Test GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension d = new Dimension(400,20);
		
		contentPane = this.getContentPane();
		contentPane.setLayout(new MigLayout("wrap 2"));
		
		inputPane = new Container();
		inputPane.setLayout(new MigLayout("wrap 2"));
		outputPane = new Container();
		outputPane.setLayout(new MigLayout("wrap 1"));
		
		inputClientId = new JTextField("68b960897e8540c7ba0d4218fd0f64bf");
		inputClientId.setPreferredSize(d);
		inputClientSecret = new JTextField("8465494e5e7e4d719dbe00459f7fd985");
		inputClientSecret.setPreferredSize(d);
		inputCallbackURL = new JTextField("http://localhost");
		inputCallbackURL.setPreferredSize(d);
		inputScope = new JTextField("basic");
		inputScope.setPreferredSize(d);
		inputConnectTimeout = new JTextField("1000");
		inputConnectTimeout.setPreferredSize(d);
		inputReadTimeout = new JTextField("1000");
		inputReadTimeout.setPreferredSize(d);
		inputAccessToken = new JTextField("2282338605.68b9608.a42f7d98a9604ad785a6c89477bdd5bb");
		inputAccessToken.setPreferredSize(d);
		
		clientIdLabel = new JLabel("Client ID: ");
		clientSecretLabel = new JLabel("Client Secret: ");
		callbackURLLabel = new JLabel("Callback URL: ");
		scopeLabel = new JLabel("Scope: ");
		connectTimeoutLabel = new JLabel("Connection Timeout: ");
		readTimeoutLabel = new JLabel("Reading Timeout: ");
		accessTokenLabel = new JLabel("AccessToken");
		
		buildServiceButton = new JButton("Build Instagram Service");
		buildServiceButton.setPreferredSize(d);
		accessTokenButton = new JButton("Create Access Token");
		accessTokenButton.setPreferredSize(d);
		accessTokenButton.setEnabled(false);
		getInstagramButton = new JButton("Create Instagram Instance");
		getInstagramButton.setPreferredSize(d);
		getInstagramButton.setEnabled(false);
		requestUserDataButton = new JButton("Request User Data");
		requestUserDataButton.setPreferredSize(d);
		requestUserDataButton.setEnabled(false);
		
		inputPane.add(clientIdLabel);
		inputPane.add(inputClientId);
		inputPane.add(clientSecretLabel);
		inputPane.add(inputClientSecret);
		inputPane.add(callbackURLLabel);
		inputPane.add(inputCallbackURL);
		inputPane.add(scopeLabel);
		inputPane.add(inputScope);
		inputPane.add(connectTimeoutLabel);
		inputPane.add(inputConnectTimeout);
		inputPane.add(readTimeoutLabel);
		inputPane.add(inputReadTimeout);
		
		inputPane.add(buildServiceButton, "span");
		inputPane.add(accessTokenLabel);
		inputPane.add(inputAccessToken);
		inputPane.add(accessTokenButton, "span");
		inputPane.add(getInstagramButton, "span");
		inputPane.add(requestUserDataButton, "span");
		
		authorizationURLLabel = new JLabel("Authorization URL");
		outputAuthorizationURL = new JTextField();
		outputAuthorizationURL.setPreferredSize(d);
		outputAuthorizationURL.setEditable(false);
		outputUserData = new JTextPane();
		outputUserData.setBorder(BorderFactory.createTitledBorder("JSON Response"));
		outputUserData.setPreferredSize(new Dimension(400,800));
		outputUserData.setEditable(false);
		outputPane.add(authorizationURLLabel);
		outputPane.add(outputAuthorizationURL);
		outputPane.add(outputUserData);
		
		contentPane.add(inputPane);
		contentPane.add(outputPane);
		
		this.pack();
		this.setVisible(true);
	}

	public void addActionListener(ActionListener actionListener) {
		buildServiceButton.addActionListener(actionListener);
		accessTokenButton.addActionListener(actionListener);
		getInstagramButton.addActionListener(actionListener);
		requestUserDataButton.addActionListener(actionListener);
	}

	public String getInputClientId() {
		return inputClientId.getText();
	}

	public String getInputClientSecret() {
		return inputClientSecret.getText();
	}

	public String getInputCallbackURL() {
		return inputCallbackURL.getText();
	}

	public String getInputScope() {
		return inputScope.getText();
	}

	public Integer getInputConnectTimeout() {
		return Integer.parseInt(inputConnectTimeout.getText());
	}

	public Integer getInputReadTimeout() {
		return Integer.parseInt(inputReadTimeout.getText());
	}

	public String getInputVerifier() {
		return inputVerifier.getText();
	}

	public void setOutputAuthorizationURL(String response){
		this.outputAuthorizationURL.setText(response);
	}
	
	public void setInputAccessToken(String response){
		this.inputAccessToken.setText(response);
	}
	
	public void setOutputUserData(String response) {
		this.outputUserData.setText(response);
	}
	
	public String getInputAccessToken(){
		return this.inputAccessToken.getText();
	}
	
	public void showMessage(String message, String title){
		JOptionPane.showMessageDialog(this, message, title, ERROR);
	}
}
