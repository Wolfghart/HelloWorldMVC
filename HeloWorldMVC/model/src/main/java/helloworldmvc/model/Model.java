package helloworldmvc.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Model implements IModel {
	@Override
	public String getMessage() {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(Model.class.getResource("message.txt").getFile()))) {

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				contentBuilder.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	};
}
