package com.gis.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PwdUtils {

	public String generatePwd(Integer len) {
		

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random(len, characters );
		return pwd;
	}

}
