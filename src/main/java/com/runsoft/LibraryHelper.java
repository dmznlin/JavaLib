package com.runsoft;

public class LibraryHelper extends LibraryBase {
	/**
	 * 转为16进制字符串
	 * @param nData 字节数组
	 * @param useblank 使用空格分隔
	 * @param uppercase 大/小写模式
	 * @return
	 */
	public static String ToHexString(byte[] nData, boolean useblank, boolean uppercase){
		if (nData == null || nData.length == 0){
			return "";
		}

		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < nData.length; i++) {
			if (useblank && i > 0){ //补空格
				buffer.append(" ");
			}

			int v = nData[i] & 0xFF;
			if (v < 16) {
				buffer.append(0);
			}

			if (uppercase){
				buffer.append(Integer.toHexString(v).toUpperCase());
			} else {
				buffer.append(Integer.toHexString(v));
			}
		}

		return buffer.toString();
	}

	/**
	 * 转换字符串为16进制格式
	 * @param nStr 待转换字符串
	 * @param charsetname 字符集
	 * @param useblank 使用空格分隔
	 * @return
	 */
	public static String ToHexString(String nStr, String charsetname, boolean useblank){
		String result;
		try {
			result = ToHexString(nStr.getBytes(charsetname), useblank, true);
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		}

		return result;
	}

	/**
	 * 使用默认字符集转换字符串为16进制格式
	 * @param nStr
	 * @return
	 */
	public static String ToHexString(String nStr){
		return ToHexString(nStr, DefaultCharsetName, true);
	}

	/**
	 * 还原数据为字节数组
	 * @param nHexString 16进制字符串
	 * @return
	 */
	public static byte[] RestoreHexStr(String nHexString){
		if (nHexString == null || nHexString.equals("")){
			return new byte[0];
		}

		if (nHexString.indexOf(" ") > -1){ //含有空格则过滤
			nHexString = nHexString.replaceAll(" ", "");
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
			result = new String(RestoreHexStr(nHexString), charsetname);
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
	public static String RestoreHex(String nHexString){
		return RestoreHex(nHexString, DefaultCharsetName);
	}
}
