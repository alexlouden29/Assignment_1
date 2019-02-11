import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class Lesson1IOStream {
	
	//Test data
	private static Employee[] testEmployees = {
			new Employee("Mr. Worker", 10000, LocalDate.of(2018, 1, 1)),
			new Employee("Mrs. Worker", 15000, LocalDate.of(2018, 2, 2))
	};
	
	public static void main(String[] args) {

		//Get command line argument
		String arg = null;
		if(args.length > 0) {
			arg = args[0];
		}
		
		//Print help info for the confused
		if(args.length != 1 || arg.equals("--help")) {
			printHelpInfo();
		}
		
		/*************** Handle text option *****************/
		else if(arg.equals("--text")) {
			System.out.println("Reading and Writing using text format.");
			PrintWriter pw = null;
			Scanner sc = null;
			try {
				//Create the file
				File file = createFile();
			
				//Write data to file
				pw = new PrintWriter(file, "UTF-8");
				InputOutputUtil.writeText(testEmployees, pw);
				pw.flush();
				
				//Read and display data from file
				sc = new Scanner(file, "UTF-8");
				List<Employee> readEmployees = InputOutputUtil.readText(sc);
				printEmployees(readEmployees);
			}
			catch(Exception e) {
				//Quick and awful exception handling, since it's not the point of this assignment
				e.printStackTrace();
				System.out.println("try again");
			}
			finally {
				if(pw != null) { pw.close(); }
				if(sc != null) { sc.close(); }
			}
		}
		
		/***************** Handle binary option *****************/
		else if(arg.equals("--binary")) {
			System.out.println("Reading and Writing using binary format.");
			DataOutputStream out = null;
			DataInputStream in = null;
			try {
				File file = createFile();
				
				//Write Data to file
				out = new DataOutputStream(new FileOutputStream(file));
				InputOutputUtil.writeBinary(testEmployees, out);
				out.flush();
				
				//Read data from that file
				in = new DataInputStream(new FileInputStream(file));
				List<Employee> readEmployees = InputOutputUtil.readBinary(in);
				printEmployees(readEmployees);
				
			}
			catch(Exception e) {
				//Quick and awful exception handling, since it's not the point of this assignment
				e.printStackTrace();
				System.out.println("try again");
			}
			finally {
				try {
					if(out != null) { out.close(); }
					if(in != null) { in.close(); }
				} catch (Exception e) {/*Do Nothing*/}
			}
		}
		
		/************Handle object option******************/
		else if(arg.contentEquals("--object")) {
			System.out.println("Reading and Writing using object format.");
			ObjectOutputStream out = null;
			ObjectInputStream in = null;
			try {
				File file = createFile();
				
				//Write to file as object stream.
				out = new ObjectOutputStream(new FileOutputStream(file));
				InputOutputUtil.writeObject(testEmployees, out);
				out.flush();
				
				//Read from file
				in = new ObjectInputStream(new FileInputStream(file));
				List<Employee> emps = InputOutputUtil.readObject(in);
				printEmployees(emps);
			}
			catch(Exception e) {
				//Quick and awful exception handling, since it's not the point of this assignment
				e.printStackTrace();
				System.out.println("try again");
			}
			finally {
				try {
					if(out != null) { out.close(); }
					if(in != null) { in.close(); }
				} catch (Exception e) {/*Do Nothing*/}
			}
		}
		else {
			printHelpInfo();
		}
		
	}
	
	/**
	 * Helper method prints help info when --help flag is used, or no flag is used
	 */
	public static void printHelpInfo() {
		System.out.println(
				"Usage: \n" +
				"--help - show these instructions\n" +
				"--text - read and write a file using text format\n" +
				"--binary - read and write a file using binary format\n" +
				"--object - read and write a file as an object stream\n"
				);
	}
	
	/**
	 * Helper method that creates a data file if it does not already exist.
	 * @return
	 * @throws Exception
	 */
	private static File createFile() throws Exception {
		File file = new File("data/employee.dat");
		file.getParentFile().mkdirs(); // Will create parent directories if not exists
		file.createNewFile(); //Does nothing if the file exists
		return file;
	}
	
	/**
	 * Helper method that prints all employees to the console.
	 * @param emps
	 */
	private static void printEmployees(List<Employee> emps) {
		for(Employee e : emps) {
			System.out.println( e.getName() + InputOutputUtil.DELIMITER + e.getSalary() + InputOutputUtil.DELIMITER + e.getHireDate());
		}
	}
	
}
