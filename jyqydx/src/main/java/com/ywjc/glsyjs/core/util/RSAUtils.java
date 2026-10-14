package com.ywjc.glsyjs.core.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {
	public static final String CHARSET = "UTF-8";
	public static final String RSA_ALGORITHM = "RSA"; // ALGORITHM ['ælgərɪð(ə)m] 算法的意思

	public static Map<String, String> createKeys(int keySize) {
		// 为RSA算法创建一个KeyPairGenerator对象
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
		}

		// 初始化KeyPairGenerator对象,密钥长度
		kpg.initialize(keySize);
		// 生成密匙对
		KeyPair keyPair = kpg.generateKeyPair();
		// 得到公钥
		Key publicKey = keyPair.getPublic();
		String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
		// 得到私钥
		Key privateKey = keyPair.getPrivate();
		String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
		// map装载公钥和私钥
		Map<String, String> keyPairMap = new HashMap<String, String>();
		keyPairMap.put("publicKey", publicKeyStr);
		keyPairMap.put("privateKey", privateKeyStr);
		// 返回map
		return keyPairMap;
	}

	/**
	 * 得到公钥
	 * 
	 * @param publicKey 密钥字符串（经过base64编码）
	 */
	public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 通过X509编码的Key指令获得公钥对象
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
		RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
		return key;
	}

	/**
	 * 得到私钥
	 * 
	 * @param privateKey 密钥字符串（经过base64编码）
	 */
	public static RSAPrivateKey getPrivateKey(String privateKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 通过PKCS#8编码的Key指令获得私钥对象
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
		RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
		return key;
	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 */
	public static String publicEncrypt(String data, RSAPublicKey publicKey) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET),
					publicKey.getModulus().bitLength()));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.err.println("NoSuchAlgorithmException | NoSuchPaddingException Occured");
		} catch (InvalidKeyException e) {
			System.err.println("InvalidKeyException Occured");
		} catch (UnsupportedEncodingException e) {
			System.err.println("UnsupportedEncodingException Occured");
		}
		return null;
	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 */

	public static String privateDecrypt(String data, RSAPrivateKey privateKey) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(RSA_ALGORITHM);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.err.println("NoSuchAlgorithmException | NoSuchPaddingException Occured");
		}
		try {
			if(null!=privateKey&&null!=cipher) {
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
			}
		} catch (InvalidKeyException e) {
			System.err.println("InvalidKeyException Occured");
		}
		try {
			if(null!=data&&null!=privateKey) {
				if(null!=privateKey.getModulus()) {
					byte[] bytes=rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data),
							privateKey.getModulus().bitLength());
					if(null!=bytes&&bytes.length>0) {
						return new String(bytes, CHARSET);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			System.err.println("UnsupportedEncodingException Occured");
		}
		return null;
	}

	/**
	 * 私钥加密
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 */

	public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(RSA_ALGORITHM);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.err.println("NoSuchAlgorithmException | NoSuchPaddingException Occured");
		}
		// 每个Cipher初始化方法使用一个模式参数opmod，并用此模式初始化Cipher对象。此外还有其他参数，包括密钥key、包含密钥的证书certificate、算法参数params和随机源random。
		try {
			if(null!=privateKey&&null!=cipher) {
				cipher.init(Cipher.ENCRYPT_MODE, privateKey);	
			}
			
		} catch (InvalidKeyException e) {
			System.err.println("InvalidKeyException Occured");
		}
		try {
			if(null!=privateKey&&null!=data) {
				if(null!=privateKey.getModulus()) {
					byte[] bytes=rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET),
							privateKey.getModulus().bitLength());
					if(null!=bytes&&bytes.length>0) {
						return Base64.encodeBase64URLSafeString(bytes);
					}
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			System.err.println("UnsupportedEncodingException Occured");
		}
		return null;
	}

	/**
	 * 公钥解密
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 */

	public static String publicDecrypt(String data, RSAPublicKey publicKey) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(RSA_ALGORITHM);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.err.println("NoSuchAlgorithmException | NoSuchPaddingException Occured");
		}
		try {
			if(null!=publicKey&&null!=cipher) {
				cipher.init(Cipher.DECRYPT_MODE, publicKey);
			}
			
		} catch (InvalidKeyException e) {
			System.err.println("InvalidKeyException Occured");
		}
		try {
			if(null!=data&&null!=publicKey) {
				if(null!=publicKey.getModulus()){
					byte[] bytes=rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data),
							publicKey.getModulus().bitLength());
					if(null!=bytes&&bytes.length>0) {
						return new String(bytes, CHARSET);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			System.err.println("UnsupportedEncodingException Occured");
		}
		return null;
	}

	// rsa切割解码 , ENCRYPT_MODE,加密数据 ,DECRYPT_MODE,解密数据
	private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
		int maxBlock = 0; // 最大块
		if (opmode == Cipher.DECRYPT_MODE) {
			maxBlock = keySize / 8;
		} else {
			maxBlock = keySize / 8 - 11;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] buff = null;
		int i = 0;
		while (datas.length > offSet) {
			if (datas.length - offSet > maxBlock) {
				// 可以调用以下的doFinal（）方法完成加密或解密数据：
				try {
					buff = cipher.doFinal(datas, offSet, maxBlock);
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					System.err.println("IllegalBlockSizeException | BadPaddingException Occured");
				}
			} else {
				try {
					buff = cipher.doFinal(datas, offSet, datas.length - offSet);
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					System.err.println("IllegalBlockSizeException | BadPaddingException Occured");
				}
			}
			if(null!=buff&&buff.length>0) {
				out.write(buff, 0, buff.length);
			}
			i++;
			offSet = i * maxBlock;
		}
		byte[] resultDatas = out.toByteArray();
		IOUtils.closeQuietly(out);
		if(null!=out) {
			try {
				out.close();
			} catch (IOException e) {
				System.err.println("IOException Occured");
			}
		}
		return resultDatas;
	}

}