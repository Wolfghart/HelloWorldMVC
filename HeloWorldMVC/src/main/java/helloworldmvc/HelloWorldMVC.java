package helloworldmvc;

import helloworldmvc.controller.Controller;
import helloworldmvc.model.Model;
import helloworldmvc.vew.View;

public class HelloWorldMVC {

	public static void main(String[] args) {
		Controller c = new Controller(new Model(), new View());
		c.run();

	}

}
