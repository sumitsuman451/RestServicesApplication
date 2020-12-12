package com.example.restful.webservices.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	@Query(value="select * from student_management",nativeQuery = true)
	List<Student> getAllStudents();
	
	@Query(value="select * from student_management s where s.first_name = :firstName" , nativeQuery = true)
	Student findStudentByFirstname(@Param("firstName") String firstName); 
	
	//Named Parameters :- pass method parameters to the query using named parameters
	@Query(value="select * from student_management s where s.id = id" , nativeQuery = true)
	Student findStudentByNamedId(Integer id);
	
	//Indexed Query Parameters :-pass method parameters to the query in the same order they appear in the method declaration
	@Query(value="select * from student_management s where s.id = ?1" , nativeQuery = true)
	Student findStudentByIndexedId(Integer id);
	
	Student findByfirstName(String firstName);
}
