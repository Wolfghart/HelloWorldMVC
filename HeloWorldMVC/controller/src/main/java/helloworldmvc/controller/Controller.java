package helloworldmvc.controller;

import helloworldmvc.model.IModel;
import helloworldmvc.vew.IView;

public class Controller {
	private IModel model;
	private IView view;

	public Controller(IModel model, IView view) {
		super();
		this.model = model;
		this.view = view;
	}

	public void run() {
		this.view.display(model.getMessage());
	}

}
