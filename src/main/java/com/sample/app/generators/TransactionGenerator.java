package com.sample.app.generators;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;

import com.sample.app.model.Transaction;
import com.sample.app.model.Wallet;
import com.sample.app.util.HashUtil;
import com.sample.app.util.SignatureUtil;
import com.sample.app.util.StringUtil;

public class TransactionGenerator {

	public static Transaction createTransaction(PublicKey sourceAddress, PublicKey destinationAddress,
			double amountToTransfer) {
		Transaction transaction = new Transaction();

		transaction.setSourceAddress(sourceAddress);
		transaction.setDestinationAddress(destinationAddress);
		transaction.setAmountTransferred(amountToTransfer);

		String sourceAddrStr = StringUtil.toHexString(sourceAddress.getEncoded());
		String destAddrStr = StringUtil.toHexString(destinationAddress.getEncoded());

		String hashStr = sourceAddrStr + destAddrStr + transaction.getTransactionCreatedTime() + amountToTransfer;

		String id = HashUtil.getHash(hashStr);

		transaction.setId(id);

		return transaction;

	}

	public static Transaction createTransaction(Wallet senderWallet, PublicKey destinationAddress,
			double amountToTransfer, String algorithmToSign)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		Transaction transaction = createTransaction(senderWallet.getPublicKey(), destinationAddress, amountToTransfer);
		byte[] signature = SignatureUtil.getSignature(transaction, senderWallet.getPrivateKey(), algorithmToSign);
		transaction.setDigitalSignature(signature);
		return transaction;

	}

	public static Transaction canTransferBukaCoins(Wallet senderWallet, PublicKey destinationAddress,
			double coinsToTransfer, String algorithmToSign) throws Exception {
		Transaction transaction = createTransaction(senderWallet, destinationAddress, coinsToTransfer, algorithmToSign);

		boolean isvalid = SignatureUtil.verifySignature(transaction, senderWallet.getPublicKey(), algorithmToSign);

		if (isvalid) {
			return transaction;
		}

		return null;

	}
}
