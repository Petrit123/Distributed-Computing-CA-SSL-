package com.Protocol;

public interface iProtocolResponse {
	
String successfulLoginRequest = "801 SUCCESS";
	
	String invalidUserLoginDetailsRequest = "802 DENIED";
	
	String loginRequestUserAlreadyLoggedIn = "808 FAILED";
	
	String successfulSignUpRequest = "701 SUCCESS";
	
	String invalidUserNameSignUpRequest = "702 FAILED";
	
	String successFulUpload = "601 SUCCESS";
	
	String failedUpload = "602 FAILED";
	
	String succesFulDownload = "501 DOWNLOAD SUCCESSFUL";
	
	String failedDownload = "501 DOWNLOAD FAILED";
	
	String successFulLogOut = "401 SUCCESS";
	
	String failedLogOut = "402 FAILED";

}
