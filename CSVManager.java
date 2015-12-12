package ePages_Calendar;

import java.io.FileWriter;
import java.io.IOException;

public class CSVManager {
	
	private FileWriter fw = null;
	private static final String COMMA = ", ";
	
	public CSVManager(String fileName) throws IOException {
		// create an output file
		fw = new FileWriter(fileName);
		fw.write("MONTH" + COMMA + "RENT DAY" + COMMA + "ENERGY DAY");
		fw.write(System.getProperty("line.separator"));
	}
	
	public void writeLine(String month,  int rentDay, int energyDay) throws IOException {
		fw.write(month + COMMA + Integer.toString(rentDay) + COMMA + Integer.toString(energyDay));
		fw.write(System.getProperty("line.separator"));
	}
	
	public void closeCSV() throws IOException {
		fw.flush();
		fw.close();
	}
}
