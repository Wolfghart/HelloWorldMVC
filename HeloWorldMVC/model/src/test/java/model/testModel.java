package model;

import org.testng.Assert;
import org.testng.annotations.Test;

import helloworldmvc.model.Model;

public class testModel {

	@Test
	public void testGetMessage() {
		Model model = new Model();
		String m = model.getMessage();
		Assert.assertNotNull(m);
		Assert.assertEquals(m, "Hello World !");
	}
}
