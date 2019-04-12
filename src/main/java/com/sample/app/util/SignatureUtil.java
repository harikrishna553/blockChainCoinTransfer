package com.sample.app.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import com.sample.app.model.Transaction;

public class SignatureUtil {

	public static byte[] getSignature(Transaction transaction, PrivateKey senderPrivateKey, String algorithmToSign)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		/* Get instance of Signature object */
		Signature signature = Signature.getInstance(algorithmToSign);

		/* Initialize Signature object */
		signature.initSign(senderPrivateKey);

		/* Feed Data */
		signature.update(transaction.getDataToBeSigned());

		/* Generate signature */
		byte[] finalSig = signature.sign();

		return finalSig;
	}

	public static boolean verifySignature(Transaction transaction, PublicKey publicKey, String algorithm)
			throws Exception {
		Signature verifySignature = Signature.getInstance(algorithm);
		verifySignature.initVerify(publicKey);

		verifySignature.update(transaction.getDataToBeSigned());
		/* Verify signature */
		return verifySignature.verify(transaction.getDigitalSignature());

	}
}