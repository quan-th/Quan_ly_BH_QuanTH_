/**
 * Copyright(C) 2016  Luvina
 * LogoutController.java,Jan 11, 2017,HP
 */
package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author HP 
 * LogoutController
 */
@Controller
@RequestMapping("/Logout.do")
public class LogoutController {
	@RequestMapping(method = RequestMethod.GET)
	public String validateLogin(ModelMap model,HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/Login";
	}
}
