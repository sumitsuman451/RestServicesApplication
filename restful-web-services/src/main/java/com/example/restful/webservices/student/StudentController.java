package com.example.restful.webservices.student;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restful.webservices.user.UserNotFoundException;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@GetMapping("/students")
	public List<Student> retrieveAllStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/students/firstpage")
	public List<Student> retrieveFirstPage(){
		PageRequest pageRequest=PageRequest.of(0, 4);
		Page<Student> firstPage=studentRepository.findAll(pageRequest);
		return firstPage.getContent();
	}
	
//	Pageable secondPageable=(Pageable) firstPage.nextPageable(); 
//	Page<Student> secondPage=studentRepository.findAll(secondPageable);
//	return secondPage.getContent();

	
	@GetMapping("/students/page/{pagenumber}")
	public List<Student> retrieveAnyPage(@PathVariable int pagenumber){
		PageRequest pageRequest=PageRequest.of(pagenumber, 4);
		Page<Student> anyPage=studentRepository.findAll(pageRequest);
		return anyPage.getContent();
	}
	
	@GetMapping("/students/page/sort/{pagenumber}")
	public List<Student> retrieveSortPage(@PathVariable int pagenumber){
		PageRequest pageRequest=PageRequest.of(pagenumber, 4, Sort.DEFAULT_DIRECTION);
		Page<Student> anyPage=studentRepository.findAll(pageRequest);
		return anyPage.getContent();
	}
	
	@GetMapping("/students/{id}")
	public Optional<Student> retrieveStudent(@PathVariable int id){
		Optional<Student> studentOptional= studentRepository.findById(id);
		if(!studentOptional.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		return studentOptional;
	}
	
	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable int id) {
	 Optional<Student> studentOptional=studentRepository.findById(id);
	 if(!studentOptional.isPresent()) {
		 throw new UserNotFoundException("id-"+id);
	 }
	 studentRepository.deleteById(id);	
	}
	
	@PostMapping("/students")
	public void createStudent(@RequestBody Student student){
		studentRepository.save(student);
	}
	
	@GetMapping("/teachers")
	public List<Teacher> retrieveAllTeachers(){
		return teacherRepository.findAll(); 
	}
	
	@GetMapping("/teachers/{id}")
	public Optional<Teacher> retrieveTeacher(@PathVariable int id) {
		Optional<Teacher> teacherOptional= teacherRepository.findById(id);
		if(!teacherOptional.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		return teacherOptional;
	}
	
	@GetMapping("/teachers/{id}/students")
	public ResponseEntity<Object> retrieveAllStudentsOfParticularTeacher(@PathVariable int id, @RequestBody Student student){
		Optional<Teacher> teacherOptional= teacherRepository.findById(id);
		if(!teacherOptional.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		Teacher teacher=teacherOptional.get();
		student.setTeacher(teacher);
		studentRepository.save(student);
		
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(teacher.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("student/value/{name}")
	public Student retrieveStudentName(@PathVariable String name) {
		return studentRepository.findByfirstName(name);
	}
	
	@GetMapping("/student/data")
	public List<Student> StudentData(){
		return studentRepository.getAllStudents();
	}
	
	@GetMapping("students/data/{name}")
	public Student findStudentByName(@PathVariable String name) {
		Student s1=studentRepository.findStudentByFirstname(name);
		System.out.println(s1);
		return s1;
	}
	
	@GetMapping("students/named/{sumitid}")
	public Student findStudentByName(@PathVariable int sumitid) {
		return studentRepository.findStudentByNamedId(sumitid);
	}
	
	@GetMapping("students/indexed/{sumitid}")
	public Student findStudentByIndexedId(@PathVariable int sumitid) {
		Student s1=studentRepository.findStudentByIndexedId(sumitid);
		System.out.println(s1);
		return s1;
	}
}
