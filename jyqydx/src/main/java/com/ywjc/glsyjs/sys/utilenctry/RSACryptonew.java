package com.ywjc.glsyjs.sys.utilenctry;

import java.security.InvalidKeyException;


import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSACryptonew
{
  public static byte[] Encrypt(Key publicKey, byte[] plainTextData)
    throws Exception
  {
	  
	//  System.out.println(publicKey.getEncoded());
    if (publicKey == null) {
      throw new Exception("加密公钥为空，请设置");
    }
    Cipher cipher = null;
    try
    {
      cipher = Cipher.getInstance("RSA");
      cipher.init(1, publicKey);
      return cipher.doFinal(plainTextData);
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new Exception("无此加密算法");
    }
    catch (NoSuchPaddingException e)
    {
      e.printStackTrace();
      return null;
    }
    catch (InvalidKeyException e)
    {
      throw new Exception("加密公钥非法，请检查");
    }
    catch (IllegalBlockSizeException e)
    {
      throw new Exception("明文长度非法");
    }
    catch (BadPaddingException e)
    {
      throw new Exception("明文数据已损坏");
    }
  }
  
  public static byte[] Decrypt(Key privateKey, byte[] cipherData)
    throws Exception
  {
    if (privateKey == null) {
      throw new Exception("解密私钥为空，请设置");
    }
    Cipher cipher = null;
    try
    {
      cipher = Cipher.getInstance("RSA");
      cipher.init(2, privateKey);
      return cipher.doFinal(cipherData);
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new Exception("无此解密算法");
    }
    catch (NoSuchPaddingException e)
    {
      e.printStackTrace();
      return null;
    }
    catch (InvalidKeyException e)
    {
      throw new Exception("解密私钥非法，请检查");
    }
    catch (IllegalBlockSizeException e)
    {
      throw new Exception("密文长度非法");
    }
    catch (BadPaddingException e)
    {
      throw new Exception("密文数据已损坏");
    }
  }
}
