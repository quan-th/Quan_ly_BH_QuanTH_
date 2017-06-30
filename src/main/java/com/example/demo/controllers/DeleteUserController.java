/**
 * Copyright(C) 2016  Luvina
 * DeleteUserController.java,Dec 28, 2016,HP
 */
package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.logics.TblInsuranceLogic;
import com.example.demo.logics.TblUserLogic;

/**
 * @author HP
 *
 *         DeleteUserController
 */
@Controller
public class DeleteUserController {
	@Autowired
	private TblUserLogic tblUserLogic;
	@Autowired
	private TblInsuranceLogic tblInsuranceLogic;

//	@RequestMapping(value = "/Delete.do", method = RequestMethod.GET)
//	public String detailsUser(ModelMap model, HttpServletRequest request) {
//		try {
//			int id = Integer.parseInt(request.getParameter("userId"));
//			if (tblUserLogic.checkUserExist(id)) {
//				if (tblInsuranceLogic.deleteInsurance(id)) {
//					return "redirect:/AllUsers.do";
//				} else {
//					return Constant.ERROR;
//				}
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
