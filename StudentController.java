package de.frauas.web.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.frauas.web.form.StudentForm;
import de.frauas.web.model.Student;

@Controller
public class StudentController {

	private static List<Student> students = new ArrayList<Student>();

	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@Value("${delete.message}")
	private String deleteMessage;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("message", message);
		return "index";
	}

	@RequestMapping(value = { "/listStudents" }, method = RequestMethod.GET)
	public String listStudents(Model model) {
		model.addAttribute("students", students);
		return "listStudents";
	}

	@RequestMapping(value = { "/addStudent" }, method = RequestMethod.GET)
	public String showAddStudentPage(Model model) {
		StudentForm studentForm = new StudentForm();
		model.addAttribute("studentForm", studentForm);
		return "addStudent";
	}

	// Student erstellen
	@RequestMapping(value = { "/addStudent" }, method = RequestMethod.POST)
	public String saveStudent(Model model, @ModelAttribute("studentForm") StudentForm studentForm) {

		String matriculationNo = studentForm.getMatriculationNo();
		String firstName = studentForm.getFirstName();
		String lastName = studentForm.getLastName();

		if (matriculationNo != null && matriculationNo.length() > 0 && firstName != null && firstName.length() > 0
				&& lastName != null && lastName.length() > 0) {
			Student newStudent = new Student(matriculationNo, firstName, lastName);
			students.add(newStudent);
			return "redirect:/listStudents";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "addStudent";
	}

	// Student löschen
	@RequestMapping(value = { "/deleteForm" }, method = RequestMethod.GET)
	public String delStudents(Model model) {
		StudentForm stForm = new StudentForm();
		model.addAttribute("StudentForm", stForm);
		return "deleteForm";
	}
	
	@RequestMapping(value = { "/deleteForm" }, method = RequestMethod.DELETE)
	public String deleteStudent(Model model, @ModelAttribute("studentForm") StudentForm studentForm) {

		String matriculationNo = studentForm.getMatriculationNo();

		for (Student student : students) {
			if (matriculationNo != null && matriculationNo.length() > 0 && matriculationNo.equals(student.getMatriculationNo())) {
				students.remove(student);
				return "redirect:/listStudents";
			}
		}
		model.addAttribute("deleteMessage", deleteMessage);
		return "deleteForm";
	}
}
