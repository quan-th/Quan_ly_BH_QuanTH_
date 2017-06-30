/**
 * Copyright(C) 2016  Luvina
 * DetailInsuranceController.java,Dec 23, 2016,HP
 */
package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.DetailUser;
import com.example.demo.logics.TblUserLogic;
import com.example.demo.utils.Constant;

/**
 * @author HP
 *
 *         DetailInsuranceController
 */
@Controller
public class DetailInsuranceController {
	@Autowired
	private TblUserLogic tblUserLogic;

//	@RequestMapping(value = "/Details.do", method = RequestMethod.GET)
//	public String detailsUser(HttpServletRequest request, ModelMap model) {
//		String sessionId = request.getParameter("SessionId");
//		model.addAttribute("SessionId", sessionId);
//		try {
//			int id = Integer.parseInt(request.getParameter("UserId"));
//			if (tblUserLogic.checkUserExist(id)) {
//				DetailUser detailUser = tblUserLogic.getDetailUser(id);
//				model.addAttribute("detailUser", detailUser);
//				return Constant.MH003;
//			} else {
//				return Constant.ERROR;
//			}
//		} catch (NumberFormatException e) {
//			return Constant.ERROR;
//		} catch (NullPointerException e) {
//			return Constant.ERROR;
//		}
//	}
}
