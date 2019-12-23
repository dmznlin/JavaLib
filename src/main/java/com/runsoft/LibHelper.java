package com.runsoft;

public class LibHelper {
	/**  基础库全局默认字符集 */
	public static String DefaultCharsetName = "UTF-8";

	/**
	 * 转为16进制字符串
	 * @param nData 字节数组
	 * @return
	 */
	public static String ToHexString(byte[] nData){
		if (nData == null || nData.length == 0){
			return "";
		}

		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < nData.length; i++) {
			int v = nData[i] & 0xFF;
			if (v < 16) {
				buffer.append(0);
			}
			buffer.append(Integer.toHexString(v));
		}

		return buffer.toString();
	}

	/**
	 * 还原数据为字节数组
	 * @param nHexString 16进制字符串
	 * @return
	 */
	public static byte[] RestoreHex(String nHexString){
		if (nHexString == null || nHexString.equals("")){
			return new byte[0];
		}

		int nLen = nHexString.length();
		if (nLen % 2 == 1){
			nLen++;
			nHexString = "0" + nHexString;//奇数长度时,前补0
		}

		byte[] result = new byte[nLen / 2];
		int nIdx = 0;
		for (int i=0; i<nLen; i+=2){
			result[nIdx] = (byte)Integer.parseInt(nHexString.substring(i, i+2),16);
			nIdx++;
		}

		return result;
	}

	/**
	 * 还原数据为字符串
	 * @param nHexString 16进制字符串
	 * @param charsetname 字符集
	 * @return
	 */
	public static String RestoreHex(String nHexString, String charsetname){
		String result;
		try {
			result = new String(RestoreHex(nHexString), charsetname);
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		}

		return result;
	}

	/**
	 * 使用默认字符集还原
	 * @param nHexString 16进制字符串
	 * @return
	 */
	public static String RestoreHexDefault(String nHexString){
		return RestoreHex(nHexString, DefaultCharsetName);
	}
}
