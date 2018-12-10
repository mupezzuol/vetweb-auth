package com.vetweb.auth.configuration;

import org.jboss.security.auth.spi.Util;

public class PasswordGenerator {
	
	public static void main(String[] args) {
		System.out.println(PasswordGenerator.genPassword("vetweb"));
		String pass = Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, "vetweb");
		String passRecepcao = Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, "recepcao");
		System.out.println(pass);
		System.out.println(passRecepcao);
	}
	
	public static String genPassword(String password) {
		try {
			return Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, password);
//			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//			messageDigest.update(password.getBytes());
//			return DatatypeConverter.printHexBinary(messageDigest.digest()).toLowerCase();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

}
