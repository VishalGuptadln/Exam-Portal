package com.exam.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepo;
import com.exam.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepo roleRepo;

	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User userDb = this.userRepo.findByUserName(user.getUserName());
		if (userDb != null) {
			System.out.println("User is already there !!");
			throw new Exception("User already present !!");
		} else {
			for (UserRole ur : userRoles) {
				roleRepo.save(ur.getRole());
			}
			user.setUserRole(userRoles);
			userDb = this.userRepo.save(user);
			//Role rl = new Role();
		}
		return userDb;
	}
	
	public User getUser(String userName)
	{
		return this.userRepo.findByUserName(userName);
	}

}
