package de.frauas.web.model;

public class Student {

	private String matriculationNo;
	private String lastName;
	private String firstName;

	public Student(String matriculationNo, String lastName, String firstName) {
		this.matriculationNo = matriculationNo;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public String getMatriculationNo() {
		return matriculationNo;
	}

	public void setMatriculationNo(String matriculationNo) {
		this.matriculationNo = matriculationNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
