package com.cs.rest.services;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

import org.springframework.stereotype.Service;

import com.cs.Constants.ApplicationConstants;
import com.cs.request.models.LoginParams;
import com.cs.utility.ColdStorageCrypter;

@Service
public class SessionManagementService {

	public String getNewSession(LoginParams loginParams) {
		
		String userName = loginParams.getUserName();
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
			// TODO Auto-generated catch block
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

}
