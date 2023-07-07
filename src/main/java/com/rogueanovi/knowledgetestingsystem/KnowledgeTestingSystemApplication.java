package com.rogueanovi.knowledgetestingsystem;

import com.rogueanovi.knowledgetestingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class KnowledgeTestingSystemApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeTestingSystemApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

//		User user = new User();
//		user.setFirstName("Ovidio");
//		user.setLastName("Romero");
//		user.setUsername("rogueanovi");
//		user.setPhoneNumber("3004041809");
//		user.setEmail("ovidioromero66@gmail.com");
//      user.setPassword(passwordEncoder.encode("o@Rg940703"));
//		user.setProfile("profile_picture.png");
//
//		Role role = new Role();
//		role.setRoleId(1L);
//		role.setRolename("ADMIN");
//
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//
//		Set<UserRole> userRoles = new HashSet<>();
//		userRoles.add(userRole);
//
//		User userSaved = userService.registerUser(user, userRoles);
//
//		System.out.println(userSaved.getUsername());
	}
}
