package misc;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;

public class CSVFile {
	
	String path;
	
	StringBuilder builder;
	
	FileWriter writer;
	
	public CSVFile(String path) {
		this.path = path;
		builder = new StringBuilder();
		
		try {
			writer = new FileWriter(path);
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public void writeLine(String[] data) {
		for(String string: data) {
			builder.append(string);
			builder.append(";");
		}
		builder.append("\n");
	}
	
	public void save() {
		try {
			writer.write(builder.toString());
			writer.flush();
			writer.close();
		} catch(IOException e) {}
	}
}
