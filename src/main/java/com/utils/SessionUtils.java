package com.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {

	public static boolean isUserLogin(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") != null) {
			return true;
		} return false;
	}
	
	public static boolean isAdminLogin(HttpServletRequest request) {
		if(request.getSession().getAttribute("admin") != null) {
			return true;
		} return false;
	}

	public static boolean isEmployeeLogin(HttpServletRequest request) {
		if(request.getSession().getAttribute("employee") != null) {
			return true;
		} return false;
	}

	public static boolean isMerchantLogin(HttpServletRequest request) {
		if(request.getSession().getAttribute("merchant") != null) {
			return true;
		} return false;
	}


}
