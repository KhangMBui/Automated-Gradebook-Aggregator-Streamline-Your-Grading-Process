import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.List;

public class PA2Main {
	/**
	 * 
	 * @param fileName The name of the file to be read
	 * @param data The hashtable of students with key being their IDs, and values are the students
	 */
	public static void readAndMergeCSVData(String fileName, Hashtable<String, Student> data) {
		//Read files stored directly in projectory folder:
	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	    	//Skip first line (header row):
	        String line;
	        boolean isFirstLine = true;  // Skip the header row by checking. If first line is true then skip, setting it to false
	        while ((line = reader.readLine()) != null) {
	            if (isFirstLine) {
	                isFirstLine = false; //Set isFirstLine to False
	                continue; // Skip the header row
	            }
	            //Separate the lines in .cvs file into different strings
	            //In .cvs file, cells are separated by a comma
	            String[] fields = line.split(","); 
	            //Get ID as it's the first cell
	            String id = fields[0];  
	            //If ID is already in the hashtable:
	            if (data.containsKey(id)) {
	            	//If it's the overall section (empty ID)
	            	if (id == "") {
	            		//If it's homework_1, exam_1, or quizzes_1, then we getTotal and grade
	            		//Otherwise, we do nothing, as if we do, the overAll row in the output files
	            		//will have redundant values
	            		if (fileName!= "homework_2.cvs" && fileName!= "exams_2.csv" && fileName!= "quizzes_2.csv") {
	            			//add the total cell. It's in [2] because the word "OVERALL" in second cell
	            			//is not separated by a comma
	            			data.get(id).getTotal().add(fields[2]);
	            			//Get grades and add it to the gradeList
	            			for (int i = 3; i<fields.length; i++) {
	    	            		data.get(id).getGrade().add(fields[i]);
	    	            	}
	            		}
	            	}
	            	//If it's not the overall section
	            	else {
	            		//We get total in [3] now because the name of student is 
	            		//separated by the comma, which leads to the student's name being
	            		//stored in 2 cells, 1 for first name and 1 for last name.
	            		data.get(id).getTotal().add(fields[3]);
	            		//For the same reason, we get grade with i = 4, and add it to the gradeList
	            		for (int i = 4; i<fields.length; i++) {
		            		data.get(id).getGrade().add(fields[i]);
		            	}
	            	}
	            //If ID is not found in the hash table:	
	            } else {
	                // ID does not exist, create a new entry
	            	data.put(id, new Student());
	            	String name;
	            	//If it's not the overall section (id not empty)
	                if (!id.isEmpty()) {
	                	//Name is separated into 2 cells, so we bind them together
	                	name = fields[1] + "," + fields[2];
	                	//get total
	                	data.get(id).getTotal().add(fields[3]);
	                	//add grades into gradeList
	                	for (int i = 4; i < fields.length; i++) {
		                    data.get(id).getGrade().add(fields[i]);
		                }
	                }
	                //If it's the overall section
	                else {
	                	//Name is only one cell: OVERALL
	                	name = fields[1];
	                }
	                //Set name and set id
	                data.get(id).setName(name);
	                data.get(id).setID(id);
	            }
	        }
	    //Catch error in case file is not found,...
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Function used to calculated final grades of students
	 * @param s The student to calculate the final grade for
	 * @return The String of the final grade
	 */
	public static String calculateFinal(Student s) {
		//Initialize sum = 0
	    int sum = 0;
	    //For each total, parse it into Integer and add it to Sum
	    for (String total : s.getTotal()) {
	        sum += Integer.parseInt(total);
	    }
	    //Divide the sum / 15, while making it a double 
	    double result = (double) sum / 15;
	    // Format the result with two decimal places
	    DecimalFormat df = new DecimalFormat("0.00");
	    //Format it:
	    String formattedResult = df.format(result);
	    return formattedResult;
	}

	/**
	 * Function used to output details of students' grades
	 * @param fileName The name of the file to output to
	 * @param data The Hashtable of students
	 */
	public static void outputDetails(String fileName, Hashtable<String, Student> data) {
		//If file name doesn't end with .csv, add the end to it
		if (!fileName.endsWith(".csv")) {
			fileName += ".csv";
		}
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter( fileName))) {
	        // Write the header row
	    	writer.write("ID, Name, HW1, HW2, HW3, HW4, HW5, HW6,"
	    			+ " HW7, Quiz1, Quiz2, Quiz3, Quiz4, E1, E2, E3");
	        writer.newLine();
	        // Loop through the data in the Hashtable and write it to the CSV file
	        //Write Overall score line
	        writer.write(data.get("").getID() + ",");
	        writer.write(data.get("").getName() + ",");
	        for (String overAllData:  data.get("").getGrade()) {
	        	writer.write(overAllData + ",");
	        }
	        writer.newLine();
	        //Start writing student info with ID, name, and grades
	        //Traverse through the keySet of the hash table
	        for (String id : data.keySet()) {
	        	//If we meet the overall section again, simply ignore it
	        	if (id == "") {
	        		continue;
	        	}
	        	//For each student in the hash table, write his or her ID, name, and grades
	            Student studentData = data.get(id);
	            writer.write(studentData.getID() + ",");
	            writer.write(studentData.getName() + ",");
	            for (String grade: data.get(id).getGrade()) {
	            	writer.write(grade + ",");
	            }
	            writer.newLine();
	        }
	    }
	    //Catch error
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Function used to output the summary of students' grades
	 * @param fileName The name of the file to output to
	 * @param data The Hashtable of students
	 */
	public static void outputSummary(String fileName, Hashtable<String, Student> data) {
		//If the file doesn't end with .csv, add to it:
		if (!fileName.endsWith(".csv")) {
			fileName += ".csv";
		}
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter( fileName))) {
	    	//Write the header row:
	    	writer.write("#ID, Name, Final Grade, Homework, Quizzes, Exams");
			writer.newLine();
			//Write Overall score line
	        writer.write(data.get("").getID() + ",");
	        writer.write(data.get("").getName() + ",");
	        writer.write(",");
	        //Write overAll row:
	        for (String overAllData:  data.get("").getTotal()) {
	        	writer.write(overAllData + ",");
	        }
	        writer.newLine();
	        //Write the students' lines, traverse the keySet of hashtable
	        for (String id : data.keySet()) {
	        	//If we meet the overall section again, simply ignore it
	        	if (id == "") {
	        		continue;
	        	}
	        	//For each student, write ID, name, final grades, and total grades
	            Student studentData = data.get(id);
	            writer.write(studentData.getID() + ",");
	            writer.write(studentData.getName() + ",");
	            writer.write(calculateFinal(studentData) + ",");
	            for (String total: data.get(id).getTotal()) {
	            	writer.write(total + ",");
	            }
	            writer.newLine();
	        }
			
	    }
	    //Catch error
	    catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	/**
	 * Check if it's a valid .csv file
	 * @param fileName The name of the file to be checked
	 * @return True if name is valid, false otherwise.
	 */
	private static boolean isValidCSVFileName(String fileName) {
	    // Allow file names like "exams_1.csv", "exams_2.csv", etc.
	    return fileName.matches("^[a-zA-Z0-9_]+_[0-9]+\\.csv$");
	}
	/**
	 * Main function used to process files into details and summary output files
	 * @param args
	 */
	public static void main(String[] args) {
		//Create a hash table
		Hashtable<String, Student> data = new Hashtable<>();
		//List of files to be processed:
		String[] csvFiles = {"homework_1.csv", "homework_2.csv", "quizzes_1.csv", "quizzes_2.csv", "exams_1.csv", "exams_2.csv"};
		args = csvFiles;
		//If no files are provided
		if (args.length == 0) {
			System.out.println("No CSV files are provided");
			return;
		}
		//Else:
		for (String s: args) {
			//If valid file name, print a notification and read the file
			if (isValidCSVFileName(s)) {
				System.out.println("Processing " + s + ".csv");
				readAndMergeCSVData(s, data);
				
			} else {
				//If not, notify invalid file name.
				System.out.println("Invalid CSV file name: " + s);
			}
		}
		//Output detail file:
		outputDetails("output-details", data);
		//Output summary file:
		outputSummary("output-summary", data);
	}
}
