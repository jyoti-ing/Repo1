package com.cjc.Serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cjc.Exception.AgeInvalidException;
import com.cjc.Exception.InvalidEmailException;
import com.cjc.Exception.InvalidPasswordException;
import com.cjc.Exception.InvalidUsernameException;
import com.cjc.Repository.StudentRepo;
import com.cjc.ServiceI.StudentServiceInt;
import com.cjc.model.Student;

@Service
public class StudentServiceimpl implements StudentServiceInt {

@Autowired
StudentRepo sr;
	
@Autowired
JavaMailSender sender;

@Override
	public Student addStudent(Student s) {
	SimpleMailMessage simple = new SimpleMailMessage();
	
	if(s.getEmailid().endsWith("@gmail.com")) {
		simple.setTo(s.getEmailid());
		simple.setText("Hii "+s.getName()+ ",\n "+"your account is created Succfully \n"
		+"Location:"+s.getLocation()+"\n"+"Username:"+s.getUsername()+"\n"+"Password:"+s.getPassword()
		+"\n"+"EmailId:"+s.getEmailid()+"age:"+s.getAge());
}else {
	throw new InvalidEmailException("Email Id should end with \"@gmail.com\"");
}
	
	if(s.getAge()>18) {
		System.out.println(s.getAge());
	}else {
		throw new AgeInvalidException("please enter the age is greater than 18");
	}
	
	if(s.getPassword().length()<8) {
		throw new InvalidPasswordException("Password must be at least 8 characters long.");
}
	 int Count = 0;
     for (char c : s.getPassword().toCharArray()) {
         if (!Character.isLetterOrDigit(c)) {
             Count++;
         }
     }
     if (Count < 2) {
         throw new InvalidPasswordException("Password must contain at least 2 special characters.");
     }
	
     if(s.getEmailid().contains(s.getUsername())) {
			System.out.println(s.getUsername());
		}else {
			throw new InvalidUsernameException("Invalid username");
		}


	sender.send(simple);

		return sr.save(s);
	}

}
