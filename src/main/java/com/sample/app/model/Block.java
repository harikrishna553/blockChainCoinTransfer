package com.sample.app.model;

import com.google.gson.annotations.Expose;

public class Block<T> {

	@Expose
	private String hashCode;
	@Expose
	private String previousBlockHashCode;
	@Expose
	private T transactionalData;
	@Expose
	private long timeStamp;

	@Expose
	private Block<T> nextBlock;

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getPreviousBlockHashCode() {
		return previousBlockHashCode;
	}

	public void setPreviousBlockHashCode(String previousBlockHashCode) {
		this.previousBlockHashCode = previousBlockHashCode;
	}

	public T getTransactionalData() {
		return transactionalData;
	}

	public void setTransactionalData(T transactionalData) {
		this.transactionalData = transactionalData;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Block<T> getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(Block<T> nextBlock) {
		this.nextBlock = nextBlock;
	}

}
