package com.ywjc.glsyjs.sys.utilenctry;

import java.io.IOException;



import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class AESCryptonew
{
  public static String Encrypt(String content, String passwd)
  {
    try
    {
      Cipher aesECB = Cipher.getInstance("AES/ECB/PKCS5Padding");
      SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), "AES");
      aesECB.init(1, key);
      byte[] result = aesECB.doFinal(content.getBytes());
      return Base64.encodeBase64String(result);
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchPaddingException e)
    {
      e.printStackTrace();
    }
    catch (InvalidKeyException e)
    {
      e.printStackTrace();
    }
    catch (IllegalBlockSizeException e)
    {
      e.printStackTrace();
    }
    catch (BadPaddingException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String Decrypt(String content, String passwd)
  {
    try
    {
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), "AES");
      cipher.init(2, key);
      byte[] result = Base64.decodeBase64(content);
      return new String(cipher.doFinal(result),"utf-8");
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchPaddingException e)
    {
      e.printStackTrace();
    }
    catch (InvalidKeyException e)
    {
      e.printStackTrace();
    }
    catch (IllegalBlockSizeException e)
    {
      e.printStackTrace();
    }
    catch (BadPaddingException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
