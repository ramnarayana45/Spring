package com.cg.osm.service;

import com.cg.osm.entity.User;


public interface LoginService {
	
	public User addUser(User user);
	public User removeUser(String userId);
	public User validateUser(String userId);
	public User userLogin(String userId,String password);
}
