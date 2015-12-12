# PaymentAssistant
A Java application to help you rack energy and rent payment by indicating you the right days for the upcoming year.

# Usage
- The program should receive a file name that should be the output of calculation. 
- The output file is will be a CSV file containing information for the next one year. 
  Example: If today is 15.10.2015, it will calculate from October 2015 until September 2016.
- The CSV will have 3 columns, Name of the Month, day of the month the rent should be paid and day the of the month the energy should be pay.

# Rules to determine payment days
- The rent is paid monthly, at the last day of the month, unless that day is a weekend (Saturday or Sunday).
  In this case, the rent is paid before the weekend.
- The Energy is paid monthly too, but at the 10th, unless it is a weekend. 
  In this case, it is paid on the following Tuesday after the weekend.
