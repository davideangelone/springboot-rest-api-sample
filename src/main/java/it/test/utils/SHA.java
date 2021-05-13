package it.test.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
	
	public static String generateHash(String value) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(value.getBytes());
			return new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
