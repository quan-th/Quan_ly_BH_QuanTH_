/**
 * Copyright(C) 2016  Luvina
 * UpdateController.java,Jan 9, 2017,HP
 */
package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Company;
import com.example.demo.entities.InsuranceInfo;
import com.example.demo.logics.TblCompanyLogic;
import com.example.demo.logics.TblInsuranceLogic;
import com.example.demo.logics.TblUserLogic;
import com.example.demo.utils.Common;
import com.example.demo.utils.Constant;
import com.example.demo.validate.ValidateInsurance;

/**
 * @author HP
 *
 *         UpdateController
 */
@Controller
public class UpdateController {
	@Autowired
	private SmartValidator validator;
	@Autowired
	private TblCompanyLogic tblCompanyLogic;
	@Autowired
	private TblUserLogic tblUserLogic;
	@Autowired
	private TblInsuranceLogic tblInsuranceLogic;
	@Autowired
	private ValidateInsurance insurance;

	/**
	 * G�?i khi ấn cập nhật từ MH003
	 * 
	 * @param model
	 *            model
	 * @param insuranceInfo
	 *            thông tin bảo hiểm
	 * @return màn hình 004
	 */

	@RequestMapping(value = "/Update.do", method = RequestMethod.GET)
	public String updateInsurance(ModelMap model, HttpServletRequest request) {
		String sessionId = request.getParameter("SessionId");
		try {
			Integer userId = Integer.parseInt(request.getParameter("UserId"));
			model.addAttribute("SessionId", sessionId);
			if (!tblUserLogic.checkUserExist(userId)) {
				return Constant.ERROR;
			}
			InsuranceInfo insuranceInfo = tblInsuranceLogic.getInsuranceInfo(userId);
			model.addAttribute("insuranceInfo", insuranceInfo);
			model.addAttribute("action", Constant.ACTION_UPDATE);
			return Constant.MH004;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return Constant.ERROR;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return Constant.ERROR;
		}
	}
//
//	@RequestMapping(value = "/Update.do", method = RequestMethod.POST)
//	public String updateInsurance(ModelMap model, @ModelAttribute InsuranceInfo insuranceInfo,
//			HttpServletRequest httpServletRequest, BindingResult infoResult) {
//		if (!tblUserLogic.checkUserExist(insuranceInfo.getUserId())) {
//			String sessionId = httpServletRequest.getParameter("SessionId");
//			model.addAttribute("SessionId", sessionId);
//			return Constant.ERROR;
//		}
//		insuranceInfo.setChoseCompany(Common.normalizeChoseCompany(insuranceInfo.getChoseCompany()));
//		if (insuranceInfo.getChoseCompany().equals(Constant.ALREADY_HAVE)) {
//			validator.validate(insuranceInfo, infoResult, Default.class);
//		} else if (insuranceInfo.getChoseCompany().equals(Constant.ADD_NEW_COMPANY)) {
//			validator.validate(insuranceInfo, infoResult, InsuranceInfo.ValidateForCompany.class);
//		}
//
//		insurance.validate(insuranceInfo, infoResult);
//		if (!infoResult.hasErrors()) {
//			if (tblInsuranceLogic.insertOrUpdateInsurance(insuranceInfo)) {
//				return "redirect:/AllUsers.do";
//			} else {
//				String sessionId = httpServletRequest.getParameter("SessionId");
//				model.addAttribute("SessionId", sessionId);
//				return Constant.ERROR;
//			}
//		}
//		model.addAttribute("insuranceInfo", insuranceInfo);
//		model.addAttribute("action", Constant.ACTION_UPDATE);
//		return Constant.MH004;
//	}

	/**
	 * �?ược g�?i khi ngư�?i dùng ch�?n công ty
	 * 
	 * @param model
	 *            model
	 * @param companyId
	 *            Id của công ty ch�?n
	 * @return detail công ty Json
	 */
	@RequestMapping(value = "/Update.do/loadCompany", method = RequestMethod.POST)
	@ResponseBody
	public String detailsCompany(ModelMap model, @RequestParam Integer companyId) {
		return tblCompanyLogic.getCompanyById(companyId);
	}

	/**
	 * load thông tin mặc định
	 * 
	 * @param model
	 *            model
	 */
	@ModelAttribute("companies")
	private List<Company> loadDefaultValues(ModelMap model) {
		return (tblCompanyLogic.getAllCompany());
	}
}
