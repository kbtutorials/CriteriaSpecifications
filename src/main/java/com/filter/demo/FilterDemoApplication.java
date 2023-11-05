package com.filter.demo;

import com.filter.demo.model.Address;
import com.filter.demo.model.Employee;
import com.filter.demo.model.Student;
import com.filter.demo.model.Subject;
import com.filter.demo.repo.AddressRepo;
import com.filter.demo.repo.EmployeeRepo;
import com.filter.demo.repo.StudentRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class FilterDemoApplication implements CommandLineRunner {

	private final StudentRepo studentRepo;
	private final EmployeeRepo employeeRepo;
	private final AddressRepo addressRepo;

	public static void main(String[] args) {
		SpringApplication.run(FilterDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Started student entity saving");
		Student student = new Student();
		student.setName("ramu");
		student.setAddress("hyderabad");
		student.setHobby("cricket");
		studentRepo.save(student);

		Student student1 = new Student();
		student1.setName("pranj");
		student1.setAddress("pune");
		student1.setHobby("Tabletennis");
		studentRepo.save(student1);

		Student student2 = new Student();
		student2.setName("parameshwar");
		student2.setAddress("Vizag");
		student2.setHobby("swimming");
		studentRepo.save(student2);

		Student student3 = new Student();
		student3.setName("arun");
		student3.setAddress("warangal");
		student3.setHobby("cricket");
		studentRepo.save(student3);

		Student student4 = new Student();
		student4.setName("laxmi");
		student4.setAddress("guntur");
		student4.setHobby("chess");
		studentRepo.save(student4);

		Student student5 = new Student();
		student5.setName("anvesh");
		student5.setAddress("miryalaguda");
		student5.setHobby("cards");
		studentRepo.save(student5);

		Student student6 = new Student();
		student6.setName("mani");
		student6.setAddress("vanaparthi");
		student6.setHobby("dancing");
		studentRepo.save(student6);

		Student student7 = new Student();
		student7.setName("teja");
		student7.setAddress("ananthapur");
		student7.setHobby("chatting");
		studentRepo.save(student7);

		Student student8 = new Student();
		student8.setName("sai");
		student8.setAddress("tirupathi");
		student8.setHobby("learning");
		studentRepo.save(student8);

		Student student9 = new Student();
		student9.setName("lasya");
		student9.setAddress("hyderabad");
		student9.setHobby("acting");
		studentRepo.save(student9);

		Student student10 = new Student();
		student10.setName("vikram");
		student10.setAddress("karimnagar");
		student10.setHobby("pool");
		studentRepo.save(student10);

		Student student11 = new Student();
		student11.setName("naaraj");
		student11.setAddress("haryana");
		student11.setHobby("javeline");
		studentRepo.save(student11);

		Student student12 = new Student();
		student12.setName("sania mirza");
		student12.setAddress("hyderabad");
		student12.setHobby("tennis");
		studentRepo.save(student12);

		Student student13 = new Student();
		student13.setName("anand");
		student13.setAddress("chennai");
		student13.setHobby("chess");
		studentRepo.save(student13);

		log.info("Student Saved");

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Address ad = new Address();
		ad.setCity("Hyderabad");
		addressRepo.save(ad);

		Address ad1 = new Address();
		ad1.setCity("Pune");
		addressRepo.save(ad1);

		Address ad2 = new Address();
		ad2.setCity("Mumbai");
		addressRepo.save(ad2);

		Address ad3 = new Address();
		ad3.setCity("Koachi");
		addressRepo.save(ad3);

		Address ad4 = new Address();
		ad4.setCity("Kolkata");
		addressRepo.save(ad4);


		Employee em = new Employee();
		em.setName("sainath");
		em.setSalary(30000);
		em.setSkill(Subject.ENGLISH);
		em.setDoj(formatter.parse("10/12/2015"));
		em.setAddress(ad);
		employeeRepo.save(em);

		Employee em1 = new Employee();
		em1.setName("rama");
		em1.setSalary(50000);
		em1.setSkill(Subject.HINDI);
		em1.setAddress(ad1);
		em1.setDoj(formatter.parse("4/06/2016"));
		employeeRepo.save(em1);


		Employee em2 = new Employee();
		em2.setName("karthik");
		em2.setSalary(50000);
		em2.setSkill(Subject.MATHS);
		em2.setAddress(ad2);
		em2.setDoj(formatter.parse("03/04/2018"));
		employeeRepo.save(em2);

		Employee em3 = new Employee();
		em3.setName("naresh");
		em3.setSalary(10000);
		em3.setSkill(Subject.ENGLISH);
		em3.setAddress(ad3);
		em3.setDoj(formatter.parse("10/12/2016"));
		employeeRepo.save(em3);

		Employee em4 = new Employee();
		em4.setName("rambabu");
		em4.setSalary(40000);
		em4.setSkill(Subject.TELUGU);
		em4.setAddress(ad4);
		em4.setDoj(formatter.parse("10/12/2015"));
		employeeRepo.save(em4);



	}
}
