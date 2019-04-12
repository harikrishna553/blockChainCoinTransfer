package com.sample.app.model;

import java.security.PublicKey;
import java.util.Date;

import com.google.gson.annotations.Expose;
import com.sample.app.util.StringUtil;

public class Transaction {

	@Expose
	private String id;
	private PublicKey sourceAddress;
	private PublicKey destinationAddress;

	@Expose
	private double amountTransferred;
	private byte[] digitalSignature;
	@Expose
	private String signature; // It is string representation of digitalSignature

	@Expose
	private long transactionCreatedTime;

	@Expose
	private String sourceWalletAddress; // String representation of sourceAddress
	@Expose
	private String destinationWalletAddress; // String representation of destinationAddress

	public Transaction() {
		Date date = new Date();
		this.transactionCreatedTime = date.getTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PublicKey getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(PublicKey sourceAddress) {
		this.sourceAddress = sourceAddress;
		this.sourceWalletAddress = StringUtil.toHexString(this.sourceAddress.getEncoded());
	}

	public PublicKey getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(PublicKey destinationAddress) {
		this.destinationAddress = destinationAddress;
		this.destinationWalletAddress = StringUtil.toHexString(this.destinationAddress.getEncoded());
	}

	public double getAmountTransferred() {
		return amountTransferred;
	}

	public void setAmountTransferred(double amountTransferred) {
		this.amountTransferred = amountTransferred;
	}

	public byte[] getDigitalSignature() {
		return digitalSignature;
	}

	public void setDigitalSignature(byte[] digitalSignature) {
		this.digitalSignature = digitalSignature;
		this.signature = StringUtil.toHexString(digitalSignature);
	}

	public long getTransactionCreatedTime() {
		return transactionCreatedTime;
	}

	public void setTransactionCreatedTime(long transactionCreatedTime) {
		this.transactionCreatedTime = transactionCreatedTime;
	}

	public String getSourceWalletAddress() {
		return this.sourceWalletAddress;
	}

	public String getDestinationWalletAddress() {
		return this.destinationWalletAddress;
	}

	public String getSignature() {
		return this.signature;
	}

	public byte[] getDataToBeSigned() {
		StringBuilder builder = new StringBuilder();

		builder.append(StringUtil.toHexString(this.sourceAddress.getEncoded()));
		builder.append(StringUtil.toHexString(this.destinationAddress.getEncoded()));
		builder.append(this.amountTransferred);
		builder.append(this.transactionCreatedTime);
		builder.append(this.id);

		return builder.toString().getBytes();
	}

	public String toString() {
		return new String(getDataToBeSigned());
	}
}
