package com.cs.rest.services;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.Constants.ApplicationConstants;
import com.cs.Constants.Status;
import com.cs.mongo.model.LoginStatus;
import com.cs.request.models.LoginParams;
import com.cs.request.models.LoginResponse;
import com.cs.utility.ColdStorageCrypter;
import com.cs.utility.DateUtility;

@Service
public class SessionManagementService {
	
	@Autowired
	private UserServices userServices;
	@Autowired
	private LoginStatusServices loginStatusServices;

	public String getNewSession(LoginParams loginParams) {
		
		String userName = loginParams.getUsername();
		String password = loginParams.getPassword();
		String encodingKey = ApplicationConstants.CIPHER_ENCODED_0264_KEY;
		String initializationkey = ApplicationConstants.CIPHER_FEEDBACK;
		byte[] cipherdUserName = null;
		byte[] cipherKey = encodingKey.getBytes();
		byte[] initializationVector = initializationkey.getBytes();
		ColdStorageCrypter cipher = new ColdStorageCrypter(cipherKey, initializationVector);
		
		try {
			cipherdUserName = cipher.encrypt(userName+password);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| ShortBufferException | BadPaddingException | IOException e) {
			e.printStackTrace();
		}
		
		
		
		String x = new String(cipherdUserName + ApplicationConstants.CIPHER_MIDDLE_TEXT);
		StringBuilder encrypuserName = new  StringBuilder(x);
		StringBuilder encryptedString  = removeBlankSpace(encrypuserName);
		encryptedString.append("@"+userName);
		return encryptedString.toString();
	}
	
	static StringBuilder removeBlankSpace(StringBuilder sb) {
		  int j = 0;
		  for(int i = 0; i < sb.length(); i++) {
		    if (!Character.isWhitespace(sb.charAt(i))) {
		       sb.setCharAt(j++, sb.charAt(i));
		    }
		  }
		  sb.delete(j, sb.length());
		  return sb;
	}
	
	public LoginResponse validateUser(LoginParams loginParams) {
		LoginResponse response = new LoginResponse();
		String userName = loginParams.getUsername();
		String password = loginParams.getPassword();
		
		response.setMessage("Invalid UserName or Password");
		response.setSession_id("null");
		
		if(userServices.validateUser(userName, password))
		{
			response.setMessage("Successfully Logged In");
			StringBuilder encryptedsession =  new StringBuilder(getNewSession(loginParams));
			response.setSession_id(encryptedsession.toString());
			
			//Add User To the session
			loginStatusServices.addNewSession(loginParams,encryptedsession);
			
		}
		return response;

	}
	
	public String validateSession(String session_id){
		
		/*
		 *1- Delete when Time passes 
		 *2- if not then return False Message
		 *3- if yes then update the session 
		 */
		
		LoginStatus loginSession = loginStatusServices.getAllSession(session_id);
		
		//Check Session Exists
		if(StringUtils.isEmpty(session_id) || !session_id.contains("@"))
			return Status.sessionInvalid;
		
		
		// Checking Time Pass
		long timediff = DateUtility.getTimeDiff(loginSession.getCreatedDate());
			//if Greater then 5 min logout
		if(timediff>2){
			logoutAndDelete(session_id);
			return Status.sessionInvalid;
		}else if(!loginSession.getSession_id().equals(session_id)){
			logoutAndDelete(session_id);
			return Status.sessionInvalid;
		}
		else{
			loginStatusServices.updateSession(session_id);
			return "sessionUpdated";
		}
	}

	private void logoutAndDelete(String session_id) {
		loginStatusServices.removeSession(session_id);
		
	}

}
