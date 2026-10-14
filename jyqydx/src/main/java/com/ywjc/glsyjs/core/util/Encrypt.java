package com.ywjc.glsyjs.core.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * �ַ������߼���
 * 
 * @author szj
 */
public class Encrypt {

	private static Logger log = Logger.getLogger(Encrypt.class);
	private final static String DES = "DES";

	/**
	 * 加密
	 * 
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks;
		byte[] rst = null;
		try {
			dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
			// 现在，获取数据并加密
			// 正式执行加密操作
			rst = cipher.doFinal(src);
		} catch (InvalidKeyException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (InvalidKeySpecException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (NoSuchPaddingException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (IllegalBlockSizeException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (BadPaddingException e) {
			log.error(e.getMessage());
			log.info(e);
		}

		return rst;
	}

	/**
	 * 解密
	 * 
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks;
		byte[] rst = null;
		try {
			dks = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			// 现在，获取数据并解密
			// 正式执行解密操作
			rst = cipher.doFinal(src);
		} catch (InvalidKeyException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (InvalidKeySpecException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (NoSuchPaddingException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (IllegalBlockSizeException e) {
			log.error(e.getMessage());
			log.info(e);
		} catch (BadPaddingException e) {
			log.error(e.getMessage());
			log.info(e);
		}

		return rst;
	}

	/**
	 * 密码解密
	 * 
	 * @param data
	 * @return
	 */
	public final static String decrypt(String data, String key) {
		return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()));

//		return null;
	}

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String password, String key) {
		String strc = password;
		try {
			strc = new String(strc.getBytes("GB2312"));
			if(StringUtils.isNotBlank(password)&&StringUtils.isNotBlank(key)) {
				if(null!=password.getBytes()&&password.getBytes().length>0
						&&null!=key.getBytes()&&key.getBytes().length>0) {
					byte[] byts=encrypt(password.getBytes(), key.getBytes());
					if(null!=byts&&byts.length>0) {
						return byte2hex(byts);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}

		return null;
	}


	public static String byte2hex(byte[] b) {
		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {

			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1)

				hs = hs + "0" + stmp;

			else

				hs = hs + stmp;

		}

		return hs.toUpperCase();

	}

	public static byte[] hex2byte(byte[] b) {

		if ((b.length % 2) != 0)

			throw new IllegalArgumentException("长度不是偶数");

		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {

			String item = new String(b, n, 2);

			b2[n / 2] = (byte) Integer.parseInt(item, 16);

		}

		return b2;
	}

	public static String getKey() {
		String key = UUID.randomUUID().toString();
		key = key.replace("-", "");
		return key;
	}
}
