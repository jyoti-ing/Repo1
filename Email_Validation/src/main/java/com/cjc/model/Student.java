package com.cjc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Student {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String name;
 private String location;
 private String username;
 private String password;
 private String emailid;
 private int age;
}
