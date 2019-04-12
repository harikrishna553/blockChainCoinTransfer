package com.sample.app.generators;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import com.sample.app.model.Wallet;
import com.sample.app.util.PublicKeyUtil;

public class WalletGenerator {

	public static Wallet newWallet(String algorithm) throws NoSuchAlgorithmException {
		Wallet wallet = new Wallet();

		KeyPair keyPair = PublicKeyUtil.getKeyPair(algorithm);
		

		wallet.setPrivateKey(keyPair.getPrivate());
		wallet.setPublicKey(keyPair.getPublic());

		return wallet;

	}

}
