package com.sample.app.util;

public class StringUtil {

	public static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 3);
		for (int b : bytes) {
			b &= 0xff;
			sb.append(HashUtil.HEXDIGITS[b >> 4]);
			sb.append(HashUtil.HEXDIGITS[b & 15]);
			// sb.append(' ');
		}
		return sb.toString();
	}

	
}
