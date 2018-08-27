package com.example.nevena.internship.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nevena.internship.domain.Role;
import com.example.nevena.internship.domain.User;
import com.example.nevena.internship.domain.UserRole;
import com.example.nevena.internship.repository.RoleRepository;
import com.example.nevena.internship.repository.UserRepository;
import com.example.nevena.internship.repository.UserRoleRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User createUser(String name, String email, String password, List<Long> userRoleId) {
		User user = new User();
		
		user.setName(name);
		user.setEmail(email);
		user.setActive(true);
		String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
		
		for (Long l: userRoleId) {
			Role role = roleRepository.findOneById(l);
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(role);
			
			userRoleRepository.save(userRole);
		}
		
		return user;
	}
	
	
	public void deleteUser(Long id) {
		User user = userRepository.findOneById(id);
		user.setActive(false);
		userRepository.save(user);
	}
	
	
	public User findOne(Long id) {
		User user = userRepository.findOneById(id);
		return user;
	}
	
	public User editUser(Long id, String name, String password, String email) {
		User user = userRepository.findOneById(id);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		return userRepository.save(user);
		
	}
	
	public List<User> findAll() {
		List<User> lista= userRepository.findAll();
		List<User> empty = new ArrayList<>();
		
		for (User u : lista) {
			if (u.isActive()) 
				empty.add(u);
		}
		
		return empty;
	}
}
