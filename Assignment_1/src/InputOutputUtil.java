import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class that can read and write employees using different data streams, if provided the writer or reader.
 * @author alexl
 *
 */
public class InputOutputUtil {
	
	//Delimiter for text IO.
	public static final String DELIMITER = "|";

	/**
	 * Write employees to stream as text.
	 * @param emps Array of employee objects to write.
	 * @param pw PrintWriter to write to.
	 */
	public static void writeText(Employee[] emps, PrintWriter pw) {
		for( Employee e : emps) {
			pw.println( e.getName() + DELIMITER + e.getSalary() + DELIMITER + e.getHireDate());
		}
	}
	
	/**
	 * Read employees from stream as text.
	 * @param sc Scanner to read with.
	 * @return
	 */
	public static List<Employee> readText(Scanner sc) {
		List<Employee> emps = new ArrayList<Employee>();
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] tokens = line.split("\\|");
			
			//Parse line
			Employee e = new Employee(tokens[0], Integer.parseInt(tokens[1]), LocalDate.parse(tokens[2]));
			emps.add(e);
		}
		return emps;
	}
	
	/**
	 * Write employee objects to stream using a binary data stream.
	 * @param emps Array of employees to write.
	 * @param out DataOutputStream to write to.
	 * @throws Exception
	 */
	public static void writeBinary(Employee[] emps, DataOutputStream out) throws IOException {
		for(Employee e : emps) {
			out.writeUTF(e.getName());
			out.writeInt(e.getSalary());
			out.writeUTF(e.getHireDate().toString());
		}
	}
	
	/**
	 * Read employees from stream using a binary data stream.
	 * @param in DataInputStream to read with.
	 * @return List of employees
	 * @throws IOException
	 */
	public static List<Employee> readBinary(DataInputStream in) throws IOException {
		List<Employee> emps = new ArrayList<Employee>();
		while(in.available() > 1) {
			Employee e = new Employee(in.readUTF(), in.readInt(), LocalDate.parse(in.readUTF()));
			emps.add(e);
		}
		return emps;
	}
	
	/**
	 * Write employees to stream as objects.
	 * @param emps Array of employees to write.
	 * @param out ObjectOutputStream to write to.
	 * @throws IOException
	 */
	public static void writeObject(Employee[] emps, ObjectOutputStream out) throws IOException {
		out.writeObject(emps);
	}
	
	/**
	 * Reads employees from steam as objects.
	 * @param in ObjectInputStream to read from.
	 * @return an array of employees.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static List<Employee> readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		return Arrays.asList((Employee []) in.readObject());
		
	}
	
}
