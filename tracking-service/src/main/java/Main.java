/**
 * Mainclass creates Model, View, Presenter 
 * and links model and view to the presenter
 * 
 * @author Vincent Nelius
 * @version 1.0
 *
 */
public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		Presenter presenter = new Presenter();
		presenter.link(model, view);
	}

}
