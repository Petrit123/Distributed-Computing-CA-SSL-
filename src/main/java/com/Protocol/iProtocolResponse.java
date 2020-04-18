package com.Protocol;

public interface iProtocolResponse {
	
String successfulLoginRequest = "801 LOGIN SUCCESSFUL";
	
	String invalidUserLoginDetailsRequest = "802 LOGIN DENIED";
	
	String loginRequestUserAlreadyLoggedIn = "808 FAILED USER ALREADY LOGGED IN";
	
	String successfulSignUpRequest = "701 SIGNUP SUCCESSFUL";
	
	String invalidUserNameSignUpRequest = "702 SIGNUP UNSUCCESSFUL";
	
	String successFulUpload = "601 UPLOAD SUCCESSFUL";
	
	String failedUpload = "602 UPLOAD UNSUCCESSFUL";
	
	String succesFulDownload = "501 DOWNLOAD SUCCESSFUL";
	
	String failedDownload = "501 DOWNLOAD UNSUCCESSFUL";
	
	String successFulLogOut = "401 LOGOFF SUCCESSFUl";
	
	String failedLogOut = "402 LOGOFF FAILED";

}
