/**
 * Copyright(C) 2016  Luvina
 * ValidateInsurance.java,Jan 4, 2017,HP
 */
package com.example.demo.validate;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entities.InsuranceInfo;
import com.example.demo.logics.TblInsuranceLogic;
import com.example.demo.utils.Common;

/**
 * @author HP ValidateInsurance
 */
@Component
public class ValidateInsurance implements Validator {
	@Autowired
	private TblInsuranceLogic tblInsuranceLogic;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return InsuranceInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		InsuranceInfo insuranceInfo = (InsuranceInfo) target;
		if ("".equals(insuranceInfo.getBirthdate())) {
			errors.rejectValue("birthdate", "NotEmpty.insuranceInfo.birthdate");
		} else if (!Common.checkValidDate(insuranceInfo.getBirthdate())) {
			errors.rejectValue("birthdate", "NotExist.insuranceInfo.birthdate");
		}
		if ("".equals(insuranceInfo.getStartDate())) {
			errors.rejectValue("startDate", "NotEmpty.insuranceInfo.startDate");
		} else if (!Common.checkValidDate(insuranceInfo.getStartDate())) {
			errors.rejectValue("startDate", "NotExist.insuranceInfo.startDate");
		}
		if ("".equals(insuranceInfo.getEndDate())) {
			errors.rejectValue("endDate", "NotEmpty.insuranceInfo.endDate");
		} else if (!Common.checkValidDate(insuranceInfo.getEndDate())) {
			errors.rejectValue("endDate", "NotExist.insuranceInfo.endDate");
		}
		if (errors.hasErrors() == false) {
			if (insuranceInfo.getUserId() == 0) {
				if (tblInsuranceLogic.checkExist(insuranceInfo.getInsuranceNumber())) {
					errors.rejectValue("insuranceNumber", "Existed.insuranceInfo.insuranceNumber");
				}
			} else {
				if (tblInsuranceLogic.checkValidInsuranceForUpdate(insuranceInfo) == false) {
					errors.rejectValue("insuranceNumber", "Existed.insuranceInfo.insuranceNumber");
				}
			}
			if (Common.compareStartDateAndEndDate(insuranceInfo.getStartDate(), insuranceInfo.getEndDate()) == false) {
				errors.rejectValue("endDate", "Invalid.insuranceInfo.endDate");
			}
		}
	}

}
