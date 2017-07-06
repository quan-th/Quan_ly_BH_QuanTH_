/**
 * 
 */
package com.example.demo.data;

import com.example.demo.entities.DetailUser;
import com.example.demo.entities.TblCompany;
import com.example.demo.entities.TblInsurance;
import com.example.demo.entities.TblUser;

/**
 * @author tranhongquan
 *
 */
public class DataFixture {
	/**
	 * Fixed data for TblUser
	 * @return tblUser
	 */
	public static TblUser getTblUser() {
		TblUser tblUser = new TblUser();
		tblUser.setUserFullName("Tran Hong Quan");
		tblUser.setUserSexDivision("01");
		tblUser.setBirthday("1995-06-17");
		TblInsurance tblInsurance = new TblInsurance();
		tblInsurance.setInsuranceNumber("0123456789");
		tblInsurance.setInsuranceStartDate("2017-06-17");
		tblInsurance.setInsuranceEndDate("2018-06-17");
		tblInsurance.setPlaceOfRegister("Ha noi");
		tblUser.setTblInsurance(tblInsurance);
		TblCompany tblCompany = new TblCompany();
		tblCompany.setCompanyName("Ha Dong");
		tblUser.setTblCompany(tblCompany);
		return tblUser;
	}
	/**
	 * Fixed data for DetailUser
	 * @return detail User
	 */
	public static DetailUser getDetailUser(){
		DetailUser detailUserExpect = new DetailUser();
		detailUserExpect.setUsername("Tran Hong Quan");
		detailUserExpect.setGender("Nam");
		detailUserExpect.setBirthdate("17/06/1995");
		detailUserExpect.setInsuranceNumber("0123456789");
		detailUserExpect.setStartDate("17/06/2017");
		detailUserExpect.setEndDate("17/06/2018");
		detailUserExpect.setPlaceOfRegister("Ha noi");
		detailUserExpect.setCompany("Ha Dong");
		return detailUserExpect;
	}
}
