/**
 * Copyright(C) 2016  Luvina
 * ValidateInsurance.java,Jan 4, 2017,HP
 */
package com.example.demo.validate;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entities.InsuranceInfo;
import com.example.demo.utils.Common;

/**
 * @author HP
 *
 *         ValidateInsurance
 */
@Component
public class ValidateInsurance implements Validator {
//	@Autowired
//	private TblInsuranceLogic tblInsuranceLogic;
	/**
	 * So sánh ngày bắt đầu và ngày kết thúc
	 * 
	 * @param startDate
	 *            ngày bắt đầu
	 * @param endDate
	 *            ngày kết thúc
	 * @return true nếu ngày bắt đầu trước ngày kết thúc, false nếu ngày bắt đầu
	 *         sau hoặc bằng ngày kết thúc
	 */
	public boolean compareStartDateAndEndDate(String startDate, String endDate) {
		try {
			String[] arrEndDate = endDate.split("/");
			String[] arrStartDate = startDate.split("/");
			Calendar calEndDate = new GregorianCalendar(Integer.parseInt(arrEndDate[2]),
					Integer.parseInt(arrEndDate[1]), Integer.parseInt(arrEndDate[0]));
			Calendar calStartDate = new GregorianCalendar(Integer.parseInt(arrStartDate[2]),
					Integer.parseInt(arrStartDate[1]), Integer.parseInt(arrStartDate[0]));
			int compareDate = calEndDate.compareTo(calStartDate);
			if (compareDate <= 0) {
				return false;
			} else {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
		} catch (NumberFormatException e) {
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}

	}

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
		if (!errors.hasErrors()) {

//			if (tblInsuranceLogic.checkExist(insuranceInfo.getInsuranceNumber())) {
//				errors.rejectValue("insuranceNumber", "Existed.insuranceInfo.insuranceNumber");
//			}
			if (!compareStartDateAndEndDate(insuranceInfo.getStartDate(), insuranceInfo.getEndDate())) {
				errors.rejectValue("endDate", "Invalid.insuranceInfo.endDate");
			}
		}
	}

}
