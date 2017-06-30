/**
 * 
 */
package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.UserLogin;
import com.example.demo.utils.Constant;

/**
 * @author HP
 *
 */
@Controller
@RequestMapping("/Login")
public class LoginController {
	@Autowired
	@Qualifier("validateLogin")
	private Validator validateLogin;

	@RequestMapping(method = RequestMethod.POST)
	public String validateLogin(ModelMap model, @ModelAttribute UserLogin userLogin, BindingResult bindingResult,
			HttpServletRequest request) {
		
		try {
			model.addAttribute("userLogin", userLogin);
			validateLogin.validate(userLogin, bindingResult);
			HttpSession session = request.getSession();
			if (!bindingResult.hasErrors()) {
				session.setAttribute("loginId", userLogin.getUsername());
				return "redirect:/AllUsers.do";
			}
			return Constant.MH001;
		} catch (CannotCreateTransactionException e) {
			e.printStackTrace();
			return Constant.ERROR_CONNECT;
		} 
	}
	@RequestMapping(method = RequestMethod.GET)
	public String displayLogin(Model model) {
		model.addAttribute("userLogin", new UserLogin());
		return Constant.MH001;
	}

}
