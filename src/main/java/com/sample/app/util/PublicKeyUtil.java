package com.sample.app.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class PublicKeyUtil {
	public static KeyPair getKeyPair(String algorithm) throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
		return keyPairGenerator.generateKeyPair();
	}
}
