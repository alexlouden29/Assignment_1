import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Employee implements Serializable{

	private String name;
	private int salary;
	private LocalDate hireDate;
	
	//Default ctor
	public Employee() {
		this.name = "John Doe";
		this.salary = 0;
		this.hireDate = LocalDate.now();
	}
	
	public Employee( String name, int salary, LocalDate hireDate) {
		//Validate
		if(salary < 0) {
			throw new IllegalArgumentException("Salary cannot be negative.");
		}
		
		//Set fields
		this.name = name;
		this.salary = salary;
		this.hireDate = hireDate;
	}

	
	////////////// Getters and Setters ////////////////
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(int salary) {
		if(salary < 0) {
			throw new IllegalArgumentException("Salary cannot be negative.");
		}
		this.salary = salary;
	}

	/**
	 * @return the hireDate
	 */
	public LocalDate getHireDate() {
		return hireDate;
	}

	/**
	 * @param hireDate the hireDate to set
	 */
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	
	
	
}
