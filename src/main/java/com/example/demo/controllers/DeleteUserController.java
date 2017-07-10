/**
 * Copyright(C) 2016  Luvina
 * DeleteUserController.java,Dec 28, 2016,HP
 */
package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.logics.TblInsuranceLogic;
import com.example.demo.logics.TblUserLogic;
import com.example.demo.utils.Constant;

/**
 * @author HP
 * DeleteUserController
 */
@Controller
public class DeleteUserController {
	@Autowired
	private TblUserLogic tblUserLogic;
	@Autowired
	private TblInsuranceLogic tblInsuranceLogic;
	@RequestMapping(value = "/Delete.do", method = RequestMethod.GET)
	public String detailsUser(ModelMap model, HttpServletRequest request) {
		try {
			int id = Integer.parseInt(request.getParameter("userId"));
			if (tblUserLogic.checkUserExist(id)) {
				if (tblInsuranceLogic.deleteInsurance(id)) {
					return "redirect:/AllUsers.do";
				}
			}
			return Constant.ERROR;
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			return Constant.ERROR;
		}
	}
}
