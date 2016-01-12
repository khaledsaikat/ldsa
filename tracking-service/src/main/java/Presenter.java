import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cedarsoftware.util.io.JsonWriter;

public class Presenter implements ActionListener {
	private Model model;
	private View view;
	
	public Presenter(){
		super();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.buildServiceButton){
			String authorizationUrl;
			authorizationUrl = model.createService(view.getInputClientId(), view.getInputClientSecret(), 
					view.getInputCallbackURL(), view.getInputScope(), 
					view.getInputConnectTimeout(), view.getInputReadTimeout());
			if (authorizationUrl == null){
				view.showMessage("Cannot create Instagram Service", "Something went wrong");
			} else {
				view.accessTokenButton.setEnabled(true);
				view.setOutputAuthorizationURL(authorizationUrl);
			}
		} else if (e.getSource() == view.accessTokenButton) {
			String accessToken = view.getInputAccessToken();
			boolean succeeded = model.createAccessToken(accessToken);
			if (!succeeded){
				view.showMessage("Cannot create AccessToken", "Something went wrong");
			} else {
				view.getInstagramButton.setEnabled(true);
			}
		} else if (e.getSource() == view.getInstagramButton) {
			boolean succeeded = model.createInstagram();
			if(!succeeded){
				view.showMessage("Cannot create Instagram Instance", "Something went wrong");
			} else {
				view.requestUserDataButton.setEnabled(true);
			}
		} else if (e.getSource() == view.requestUserDataButton) {
			String jsonResponse;
			jsonResponse = model.requestUserData();
			String niceJsonResponse = JsonWriter.formatJson(jsonResponse);
			view.setOutputUserData(niceJsonResponse);
		}
	}

	public void link(Model model, View view) {
		this.model = model;
		this.view = view;
		this.view.addActionListener(this);
	}

}
