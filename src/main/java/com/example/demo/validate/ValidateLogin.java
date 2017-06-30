/**
 * Copyright(C) 2016  Luvina
 * ValidateUser.java, Aug 7, 2016 LA-AM
 */
package com.example.demo.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entities.UserLogin;
import com.example.demo.logics.TblUserLogic;
import com.example.demo.utils.Common;

/**
 * @author LA-AM Lớp thực hiện các chức năng liên quan đến validate admin
 */
@Component
@Qualifier("validateLogin")
public class ValidateLogin implements Validator {
	@Autowired
	private TblUserLogic tblUserLogic;
	@Override
	public boolean supports(Class<?> clazz) {
		return UserLogin.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserLogin userLogin = (UserLogin) target;
		if (userLogin.getUsername().length() == 0) {
			errors.rejectValue("username", "NotEmpty.userLogin.username");
			return;
		}
		if (userLogin.getPassword().length() == 0) {
			errors.rejectValue("username", "NotEmpty.userLogin.password");
			return;
		}
		if (tblUserLogic.LoginByUsernameAndPassword(userLogin.getUsername(), Common.convertToMD5(userLogin.getPassword())).size()==0
				&& !errors.hasErrors()) {
			errors.rejectValue("username", "NotExist.userLogin.account");
			return;
		}

	}
}
