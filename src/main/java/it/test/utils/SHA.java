package it.test.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
	
	public static byte[] generateHash(String value) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(value.getBytes());
			return messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
