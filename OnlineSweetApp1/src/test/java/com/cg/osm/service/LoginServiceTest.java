package com.cg.osm.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.osm.entity.User;
import com.cg.osm.repository.LoginRepository;
//import com.cg.osm.service.LoginServiceImpl;

@SpringBootTest
public class LoginServiceTest {

	@InjectMocks
	LoginServiceImpl loginServiceImpl;
	@Mock
	LoginRepository loginRepo;
	@Test
	public void addUserMockTest() {
		User user = new User();
		user.setUserId("Sandeep");
		user.setPassword("password");
		user.setRole("User Plus");

		Mockito.when(loginRepo.save(user)).thenReturn(user);
		assertThat(loginServiceImpl.addUser(user)).isEqualTo(user);
	}
	@Test
	public void removeUserMockTest()
	{
		User user = new User();
		user.setUserId("Sandeep");
		user.setPassword("password");
		user.setRole("User Plus");
		
		Mockito.when(loginRepo.findById(user.getUserId())).thenReturn(Optional.of(user));
	    Mockito.when(loginRepo.existsById(user.getUserId())).thenReturn(false);
	    assertFalse(loginRepo.existsById(user.getUserId()));
	}
	
	@Test
	public void validateUserMockTest() {
		User user = new User();
		user.setUserId("Sandeep");
		user.setPassword("password");
		user.setRole("User Plus");

		Mockito.when(loginRepo.findValidateUser(user.getUserId(), user.getPassword())).thenReturn(user);
		Mockito.when(loginRepo.existsById(user.getUserId())).thenReturn(true);
		Mockito.when(loginRepo.getPassword(user.getUserId())).thenReturn(user.getPassword());
		assertThat(loginServiceImpl.validateUser(user.getUserId())).isEqualTo(user);

	}

}
