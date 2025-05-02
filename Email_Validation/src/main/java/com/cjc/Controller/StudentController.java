package com.cjc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.Exception.AgeInvalidException;
import com.cjc.Exception.InvalidEmailException;
import com.cjc.Exception.InvalidPasswordException;
import com.cjc.Exception.InvalidUsernameException;
import com.cjc.ServiceI.StudentServiceInt;
import com.cjc.model.Student;

@RestController
public class StudentController {
 @Autowired
 StudentServiceInt ssi;
 
 @PostMapping("/addStudent")
 public ResponseEntity<Student> addStudent(@RequestBody Student s){
 Student stu	= ssi.addStudent(s);
	return new ResponseEntity<Student>(stu,HttpStatus.CREATED);
}
 
 @ExceptionHandler(InvalidEmailException.class)
 public ResponseEntity<String> invalidemailException(InvalidEmailException email){
	String str =email.getMessage();
	return new ResponseEntity<String>(str,HttpStatus.NOT_FOUND) ;
 }
 
 @ExceptionHandler(AgeInvalidException.class)
 public ResponseEntity<String> ageinvalid(AgeInvalidException age){
	String str= age.getMessage();
	return new ResponseEntity<String>(str,HttpStatus.NOT_FOUND);
 }
 
 @ExceptionHandler(InvalidPasswordException.class)
 public ResponseEntity<String>invalidpassword(InvalidPasswordException pass){
String str	= pass.getMessage();
return new ResponseEntity<String>(str,HttpStatus.NOT_FOUND);
 }
 
 @ExceptionHandler(InvalidUsernameException.class)
 public ResponseEntity<String>invalidusername(InvalidUsernameException pass){
String str	= pass.getMessage();
return new ResponseEntity<String>(str,HttpStatus.NOT_FOUND);
 }
 
}
