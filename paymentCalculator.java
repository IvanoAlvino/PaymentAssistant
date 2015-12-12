package ePages_Calendar;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class paymentCalculator {
	
	private final static String[] months = {"January", "February", "March", "April", "May", "June", "July", 
			"August", "September", "October", "November", "December"};

	public static void main(String[] args) throws IOException {
		int dayOfWeek;
		Calendar c, rentDay, energyDay;
		
		// check if file name is passed from command line
		if ( args.length < 1 ) {
			System.out.println("Error, you must indicate the output CSV file name");
			return;
		}
		
		// Initializations
		c = new GregorianCalendar();
		rentDay = new GregorianCalendar();
		energyDay = new GregorianCalendar();
		
		// create CSV file
		CSVManager csv = new CSVManager(args[0]);
		
		for( int i=0; i<12; i++ ) {
			/** calculate energy payment day **/
			c.set(Calendar.DAY_OF_MONTH, 10);
			energyDay = (Calendar) c.clone();
			dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			// if weekend, set energyDay to next Tuesday
			if( dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY ) {
				while( dayOfWeek != Calendar.TUESDAY ) {
					energyDay.add(Calendar.DAY_OF_MONTH, 1);
					dayOfWeek = energyDay.get(Calendar.DAY_OF_WEEK);
				}
			}
			
			/** calculate rent payment day **/
			c.set( Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH) );
			rentDay = (Calendar) c.clone();
			dayOfWeek = rentDay.get(Calendar.DAY_OF_WEEK);
			// if weekend, set rentDay to Friday before weekend
			while( dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY ) {
				rentDay.add(Calendar.DAY_OF_MONTH, -1);
				dayOfWeek = rentDay.get(Calendar.DAY_OF_WEEK);
			}
			
			/** print the new line into CSV **/
			csv.writeLine(months[c.get(Calendar.MONTH)], 
					      rentDay.get(Calendar.DAY_OF_MONTH),
					      energyDay.get(Calendar.DAY_OF_MONTH));
			
			/** go to next month **/
			c.add(Calendar.MONTH, 1);
		}
		
		csv.closeCSV();
		System.out.println("File " + args[0] + " has been succesfully created.");
	}
}
