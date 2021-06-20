package com.cg.osm.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.User;

import com.cg.osm.error.UserNotFoundException;

import com.cg.osm.repository.LoginRepository;
@Service
public class LoginServiceImpl implements LoginService {

	/*
	 * Injecting Login Repository into Service Layer
	 */
	
	@Autowired
	LoginRepository loginRepo;
	Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


	@Override
	@Transactional
	public User addUser(User user) throws UserNotFoundException{
		logger.info("User addUser()");
		if(user == null)
			throw new UserNotFoundException("NotFound");
		else {
			loginRepo.save(user);
			return user;
		}
	}

	@Override
	@Transactional
	public User removeUser(String userId) throws UserNotFoundException {
		logger.info("User removeUser()");
		Optional<User> users = loginRepo.findById(userId);
		if(!users.isPresent())
			throw new UserNotFoundException("NotFound");
		else {
			loginRepo.deleteById(userId);
			return users.get();
		}
	}

	@Override
	@Transactional
	public User validateUser(String userId) throws UserNotFoundException{
		logger.info("User validateUser()");
		String pass = loginRepo.getPassword(userId);
		User u = loginRepo.findValidateUser(userId,pass);
		if(u == null)
			throw new UserNotFoundException("NotFound");
		else
			return u;	
		}


	@Override
	@Transactional
	public User userLogin(String userId, String password) throws UserNotFoundException {
		
		logger.info("User userLogin()");
		
		User user=loginRepo.findValidateUser(userId,password) ;
		if(user==null) {
			throw new UserNotFoundException("NotFound");
		}
		else
		return user;	
	
	}
}
	