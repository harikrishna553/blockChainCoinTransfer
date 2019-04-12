package com.sample.app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.sample.app.model.Block;

public class HashUtil {
	public static final char[] HEXDIGITS = "0123456789ABCDEF".toCharArray();

	private static MessageDigest getMessageDigest() {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			return messageDigest;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}

	public static <T> void setHashCode(Block<T> block) {

		MessageDigest messageDigest = getMessageDigest();

		String hashCode = block.getPreviousBlockHashCode();

		Date date = new Date();
		block.setTimeStamp(date.getTime());
		long time = date.getTime();

		String transactionalData = block.getTransactionalData().toString();

		String finalData = hashCode + transactionalData + time;

		messageDigest.update(finalData.getBytes());

		byte[] digest = messageDigest.digest();

		block.setHashCode(StringUtil.toHexString(digest));

	}

	public static String getHash(String data) {
		MessageDigest messageDigest = getMessageDigest();
		messageDigest.update(data.getBytes());
		byte[] digest = messageDigest.digest();
		return StringUtil.toHexString(digest);
	}
}
