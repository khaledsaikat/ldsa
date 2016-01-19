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
		if (e.getSource() == view.requestButton){
			String authorizationUrl;
			authorizationUrl = model.createService(TestConfig.CLIENT_ID, TestConfig.CLIENT_SECRET, TestConfig.CALLBACK_URL, TestConfig.SCOPE, TestConfig.CONNECT_TIMEOUT, TestConfig.READ_TIMEOUT);
			if (authorizationUrl == null){
				view.showMessage("Cannot create Instagram Service", "Something went wrong");
				return;
			}
			String accessToken = TestConfig.ACCESS_TOKEN;
			boolean succeeded = model.createAccessToken(accessToken);
			if (!succeeded){
				view.showMessage("Cannot create AccessToken", "Something went wrong");
				return;
			}
<<<<<<< Updated upstream
		} else if (e.getSource() == view.getInstagramButton) {
			boolean succeeded = model.createInstagram();
			if(!succeeded){
				view.showMessage("Cannot create Instagram Instance", "Something went wrong");
			} else {
				view.requestUserDataButton.setEnabled(true);
			}
		} else if (e.getSource() == view.requestUserDataButton) {
=======
			model.createInstagram();
>>>>>>> Stashed changes
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
