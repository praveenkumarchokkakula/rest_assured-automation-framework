package com.restfulbooker.apitest.businessLogics;

import org.testng.annotations.Test;

import com.restfulbooker.apitest.actions.ValidatorOperation;
import com.restfulbooker.apitest.baseAPI.Auth;
import com.restfulbooker.apitest.listeners.ExtentTestManager;
import com.restfulbooker.apitest.listeners.ExtentBase;
import com.relevantcodes.extentreports.LogStatus;
import java.lang.reflect.Method;



public class LoginTest extends ExtentBase {



  /**
   * reference API Doc: https://restful-booker.herokuapp.com/apidoc/index.html
   * 
   * */

  @Test
  public void validLoginTest(Method method) {
	  
	  Auth auth = new Auth();
	  auth.getLoginToken("admin", "password123");

	  try {
		  // log URL if needed: ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + responseUrl);
		  auth.assertIt(200);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response code");

		  auth.assertIt("token",null,ValidatorOperation.NOT_EMPTY);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value not empty case");
		  
		  auth.assertIt("token",null,ValidatorOperation.NOT_NULL);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value not null case");
	  }
	  catch(AssertionError e){
		  ExtentTestManager.getTest().log(LogStatus.FAIL,"Assertion Failure: " +e.getMessage());
	  }
	  
	 }
  
  @Test
  public void invalidLoginTest(Method method) {
	  
	  Auth auth = new Auth();
	  auth.getLoginToken("dummy", "dummypassword123");

	  try {
		// log URL if needed: ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + responseUrl);
		  auth.assertIt(200);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response code");
		  
		  auth.assertIt("reason","Bad credentials",ValidatorOperation.EQUALS);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value == Bad credentials");

	  }
	  catch(AssertionError e){
		  ExtentTestManager.getTest().log(LogStatus.FAIL,"Assertion Failure: " +e.getMessage());
	  }

	 }

}
