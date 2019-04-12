package com.sample.app.model;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.sample.app.constants.Constants;
import com.sample.app.generators.BlockChainGenerator;
import com.sample.app.generators.TransactionGenerator;
import com.sample.app.util.SignatureUtil;

/**
 * Wallet represents a virtual wallet where user can store crypto coins.
 * 
 * publicKey represents the wallet address, if User X wants to send N coins to
 * User Y, then X send coins to Y wallet address.
 * 
 * privateKey is used to sign the transactions. Do not share wallet private key.
 * 
 *
 */
public class Wallet {
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private BlockChainGenerator blockChainGenerator;

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public BlockChainGenerator getBlockChainGenerator() {
		return blockChainGenerator;
	}

	public void setBlockChainGenerator(BlockChainGenerator blockChainGenerator) {
		this.blockChainGenerator = blockChainGenerator;
	}

	public boolean sendMoney(PublicKey destinationAddress, double amount) throws Exception {

		Transaction transaction = TransactionGenerator.createTransaction(this.publicKey, destinationAddress, amount);

		byte[] signature = SignatureUtil.getSignature(transaction, this.privateKey, Constants.ALGORITHM_TO_SIGN);
		transaction.setDigitalSignature(signature);

		blockChainGenerator.submitTransaction(transaction, Constants.ALGORITHM_TO_SIGN);

		return true;
	}

}


