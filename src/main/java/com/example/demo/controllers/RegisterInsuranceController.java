/**
 * Copyright(C) 2016  Luvina
 * RegisterInsuranceController.java,Dec 28, 2016,HP
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
import com.example.demo.entities.SearchingInfo;
import com.example.demo.logics.TblCompanyLogic;
import com.example.demo.logics.TblInsuranceLogic;
import com.example.demo.utils.Common;
import com.example.demo.utils.Constant;
import com.example.demo.validate.ValidateInsurance;

/**
 * @author HP
 *
 *         RegisterInsuranceController
 */
@Controller
public class RegisterInsuranceController {
	@Autowired
	private SmartValidator validator;
	@Autowired
	private TblCompanyLogic tblCompanyLogic;
	@Autowired
	private TblInsuranceLogic tblInsuranceLogic;
	@Autowired
	private ValidateInsurance insurance;

	/**
	 * G�?i khi ấn đăng kí từ MH002
	 * 
	 * @param model
	 *            model
	 * @param insuranceInfo
	 *            thông tin bảo hiểm
	 * @return màn hình 004
	 */

	@RequestMapping(value = "/Register.do", method = RequestMethod.GET)
	public String insertInsurance(ModelMap model, HttpServletRequest request) {
		String sessionId = request.getParameter("SessionId");
		model.addAttribute("SessionId", sessionId);
		SearchingInfo searchingInfo = null;

		searchingInfo = request.getSession().getAttribute(sessionId) != null
				? (SearchingInfo) request.getSession().getAttribute(sessionId) : new SearchingInfo();
		InsuranceInfo insuranceInfo = new InsuranceInfo();
		insuranceInfo.setCompanyId(searchingInfo.getCompanyId());
		model.addAttribute("insuranceInfo", insuranceInfo);
		model.addAttribute("action", Constant.ACTION_REGISTER);
		return Constant.MH004;

	}

	/**
	 * G�?i khi ấn đăng kí từ MH004
	 * 
	 * @param model
	 *            model
	 * @param insuranceInfo
	 *            thông tin bảo hiểm
	 * @return màn hình 004
	 */
	@RequestMapping(value = "/Register.do", method = RequestMethod.POST)
	public String insertInsurance(ModelMap model, @ModelAttribute InsuranceInfo insuranceInfo, BindingResult infoResult,
			HttpServletRequest httpServletRequest) {
		insuranceInfo.setChoseCompany(Common.normalizeChoseCompany(insuranceInfo.getChoseCompany()));
		if (insuranceInfo.getChoseCompany().equals(Constant.ALREADY_HAVE)) {
			validator.validate(insuranceInfo, infoResult, Default.class);
		} else if (insuranceInfo.getChoseCompany().equals(Constant.ADD_NEW_COMPANY)) {
			validator.validate(insuranceInfo, infoResult, InsuranceInfo.ValidateForCompany.class);
		}
		insurance.validate(insuranceInfo, infoResult);

		if (!infoResult.hasErrors()) {
			if (tblInsuranceLogic.insertOrUpdateInsurance(insuranceInfo)) {
				return "redirect:/AllUsers.do";
			} else {
				String sessionId = httpServletRequest.getParameter("SessionId");
				model.addAttribute("SessionId", sessionId);
				return Constant.ERROR;
			}
		}
		model.addAttribute("action",Constant.ACTION_REGISTER);
		return Constant.MH004;
	}

	/**
	 * �?ược g�?i khi ngư�?i dùng ch�?n công ty
	 * 
	 * @param model
	 *            model
	 * @param companyId
	 *            Id của công ty ch�?n
	 * @return detail công ty Json
	 */
	@RequestMapping(value = "/Register.do/loadCompany", method = RequestMethod.POST)
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