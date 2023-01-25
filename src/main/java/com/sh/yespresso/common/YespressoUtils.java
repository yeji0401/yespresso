package com.sh.yespresso.common;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class YespressoUtils {
	
	public static String getEncryptedPassword(String rawPassword, String salt) {
		String encryptedPassword = null;
		
		try {
			// 1. 암호화 MessageDigest
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] _salt = salt.getBytes("utf-8");
			byte[] _rawPassword = rawPassword.getBytes("utf-8");
			md.update(_salt);
			byte[] _encryptedPassword = md.digest(_rawPassword);
			
			System.out.println(new String(_encryptedPassword));
			
			// 2. 인코딩 Base64Encoder (영문자, 숫자, +, /) padding =
			Encoder encoder = Base64.getEncoder();
			encryptedPassword =  encoder.encodeToString(_encryptedPassword);
			
			System.out.println(encryptedPassword);
		} catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptedPassword;
	}
}
