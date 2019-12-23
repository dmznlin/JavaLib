package com.runsoft;

import java.security.MessageDigest;
import java.util.Base64;

public class EncodeHelper {
	/**
	 * base64编码
	 * @param nData 待编码数据
	 * @return
	 */
	public static String EncodeBase64(byte[] nData){
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(nData);
	}

	/**
	 * 对字符串编码
	 * @param nStr 待编码字符串
	 * @param charsetname 字符集
	 * @return
	 */
	public static String EncodeBase64(String nStr, String charsetname){
		String result = "";
		Base64.Encoder encoder = Base64.getEncoder();
		try {
			result = encoder.encodeToString(nStr.getBytes(charsetname));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return  result;
	}

	/**
	 * 按默认字符集编码
	 * @param nStr 待编码字符串
	 * @return
	 */
	public static String EncodeBase64(String nStr){
		return EncodeBase64(nStr, LibHelper.DefaultCharsetName);
	}

	/**
	 * base64解码
	 * @param nData 待解码数据
	 * @return
	 */
	public static byte[] DecodeBase64(byte[] nData){
		Base64.Decoder decoder = Base64.getDecoder();
	 	return decoder.decode(nData);
	}

	/**
	 * 解码字符串
	 * @param nStr 待解码字符串
	 * @param charsetname 字符集
	 * @return
	 */
	public static String DecodeBase64(String nStr, String charsetname){
		String result = "";
		Base64.Decoder decoder = Base64.getDecoder();
		try {
			result = new String(decoder.decode(nStr), charsetname);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 按默认字符集解码
	 * @param nStr
	 * @return
	 */
	public static String DecodeBase64(String nStr){
		return DecodeBase64(nStr, LibHelper.DefaultCharsetName);
	}

	/**
	 * md5加密
	 * @param nData 待加密数据
	 * @return
	 */
	public static String EncodeMD5(byte[] nData){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(nData);
			return LibHelper.ToHexString(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 对字符串加密
	 * @param nStr 待加密数据
	 * @param charsetname 字符集
	 * @return
	 */
	public static String EncodeMD5(String nStr, String charsetname){
		String result;
		try {
			result = EncodeMD5(nStr.getBytes(charsetname));
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		}

		return result;
	}

	/**
	 * 按默认字符集加密
	 * @param nStr
	 * @return
	 */
	public static String EncodeMD5(String nStr){
		return EncodeMD5(nStr, LibHelper.DefaultCharsetName);
	}
}
