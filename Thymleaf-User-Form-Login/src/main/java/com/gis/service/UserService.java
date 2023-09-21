package com.gis.service;

import com.gis.dto.LoginRequest;
import com.gis.dto.UnlockRequest;
import com.gis.dto.UserRequest;

public interface UserService {
	
	String createUser(UserRequest form);

	String unlockAccount(UnlockRequest form);


	String loginUser(LoginRequest form);

}
