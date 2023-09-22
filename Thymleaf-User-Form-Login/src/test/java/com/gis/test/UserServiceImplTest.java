package com.gis.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.gis.dto.LoginRequest;
import com.gis.dto.UnlockRequest;
import com.gis.dto.UserRequest;
import com.gis.entity.User;
import com.gis.repo.UserRepository;
import com.gis.service.impl.UserServiceImpl;
import com.gis.utils.EmailUtils;
import com.gis.utils.PwdUtils;

@SpringBootTest
public class UserServiceImplTest {
	@Mock
	UserRepository userRepository;

	@Mock
	private PwdUtils pwdUtils;
	@Mock
	private EmailUtils emailUtils;
	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	public void testCreateUserSuccess() {
		UserRequest request = new UserRequest();
		request.setEmail("Ramaa@gmail.com");
		request.setName("Ramaa");

		User existingUser = null;
		when(userRepository.findByEmail(request.getEmail())).thenReturn(existingUser);

		String generatedPassword = "generatedPassword";
		when(pwdUtils.generatePwd(6)).thenReturn(generatedPassword);

		String result = userServiceImpl.createUser(request);

		assertEquals("Mail sent to Ramaa@gmail.com", result);
	}

	@Test
	public void testCreateUserWithEmailAlreadyExists() {
		UserRequest request = new UserRequest();
		request.setEmail("Ramaa@gmail.com");

		User existingUser = new User();
		existingUser.setAccountStatus("Unlocked");

		when(userRepository.findByEmail(request.getEmail())).thenReturn(existingUser);
		String createUser = userServiceImpl.createUser(request);
		assertEquals("Email already exists ,Please login !", createUser);
	}

	@Test
	public void testCreateUserWithLockedAccount() {
		UserRequest request = new UserRequest();
		request.setEmail("Ramaa@gmail.com");

		User lockedUser = new User();
		lockedUser.setAccountStatus("Locked");

		when(userRepository.findByEmail(request.getEmail())).thenReturn(lockedUser);
		assertEquals("Your account need to be unlocked", userServiceImpl.createUser(request));
	}

	@Test
	public void testUnlockAccountWithMatchingPasswords() {
		UnlockRequest form = new UnlockRequest();
		form.setEmail("Ramaa@gmail.com");
		form.setTempPassword("123456");
		form.setNewPassword("567");
		form.setConfirmPassword("567");

		User user = new User();
		user.setEmail("Ramaa@gmail.com");
		user.setPassword("123456");

		when(userRepository.findByEmail(form.getEmail())).thenReturn(user);

		String result = userServiceImpl.unlockAccount(form);

		assertEquals("Account unlocked", result);

	}

	@Test
	public void testUnlockAccountWithNonMatchingPasswords() {
		UnlockRequest form = new UnlockRequest();
		form.setEmail("Ramaa@gmail.com");
		form.setTempPassword("1234567");
		form.setNewPassword("567");
		form.setConfirmPassword("568");

		User user = new User();
		user.setEmail("Ramaa@gmail.com");
		user.setPassword("123457");

		when(userRepository.findByEmail(form.getEmail())).thenReturn(user);

		String result = userServiceImpl.unlockAccount(form);

		assertEquals("Temporary Password is not matching", result);
	}

	@Test
	void testloginUser() {
		LoginRequest form = new LoginRequest();
		User user = new User();
		when(userRepository.findByEmailAndPassword(form.getEmail(), form.getPassword())).thenReturn(user);
		String loginUser = userServiceImpl.loginUser(form);
		assertEquals("success", loginUser);

	}

	@Test
	void testInvalidCredentials() {
		LoginRequest form = new LoginRequest();
		form.setEmail("Siva@gmail.com");
		form.setPassword("123456");
		when(userRepository.findByEmailAndPassword(form.getEmail(), form.getPassword())).thenReturn(null);
		
		assertEquals("Invalid credentials",  userServiceImpl.loginUser(form));

	}
	@Test
	void TestAccountNeedToLocked() {
		LoginRequest form = new LoginRequest();
		form.setEmail("Siva@gmail.com");
		form.setPassword("123456");
		
		User user=new User();
		user.setAccountStatus("Locked");
		when(userRepository.findByEmailAndPassword(form.getEmail(), form.getPassword())).thenReturn(user);
		
		assertEquals("Your account need to be unlocked", userServiceImpl.loginUser(form));

	
		
	}

}
