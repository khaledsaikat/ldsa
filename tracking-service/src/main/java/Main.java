
public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		Presenter presenter = new Presenter();
		presenter.link(model, view);
	}

}
