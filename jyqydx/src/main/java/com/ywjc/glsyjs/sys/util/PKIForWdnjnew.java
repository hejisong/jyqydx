package com.ywjc.glsyjs.sys.util;


import java.io.File;

import com.ywjc.glsyjs.sys.utilenctry.AESCryptonew;
import com.ywjc.glsyjs.sys.utilenctry.Base64;
import com.ywjc.glsyjs.sys.utilenctry.Ciphertextnew;
import com.ywjc.glsyjs.sys.utilenctry.RSACryptonew;
import com.ywjc.glsyjs.sys.utilenctry.ReadPFXnew;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import com.ywjc.glsyjs.sys.utilenctry.StringUtilnew;


public class PKIForWdnjnew
{
  private String keystorefile = "/www.yunsign.com.pfx";
  private String keypasswd = "2016MYnjHTTPSkey";
 // private String keypasswd = "Zhangcheng123";
  private byte[] publicKey = null;
  private byte[] privateKey = null;
  
  public byte[] getpriKey()
  {
    return this.publicKey;
  }
  
  public byte[] getpubKey()
  {
    return this.privateKey;
  }
  
  public Ciphertextnew encryptWithPublickey(String content, String key)
  {
    try
    {
      key = StringUtilnew.ExpandString(key);
      
      this.publicKey = pubKeyGenerator(key);
      
      Ciphertextnew ciphertext = new Ciphertextnew();
      ciphertext.setContent(AESCryptonew.Encrypt(content, key));
      
      return ciphertext;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public String decryptWithPrivateKey(String content, byte[] pubKey)
  {
    try
    {
      ReadPFXnew reader = new ReadPFXnew();
      
      reader.ParsePvkformPfx(this.keystorefile, this.keypasswd);
      byte[] bPubKey = RSACryptonew.Decrypt(reader.getPrikey(), pubKey);
      
      return AESCryptonew.Decrypt(content, new String(bPubKey));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public Ciphertextnew encryptWithPrivateKey(String content, String key)
  {
    try
    {
      key = StringUtil.ExpandString(key);
      
     // System.out.println("kkkkkk:"+key);
      this.privateKey = priKeyGenerator(key);
      
      System.out.println("key:::"+Base64.encode(this.privateKey));
      Ciphertextnew ciphertext = new Ciphertextnew();
      ciphertext.setContent(AESCryptonew.Encrypt(content, key));
      
      return ciphertext;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public String decryptWithPublickey(String content, byte[] key)
  {
    try
    {
      ReadPFXnew reader = new ReadPFXnew();
      File f = new File(getClass().getResource("").getPath());
      reader.ParsePvkformPfx(f.toString() + this.keystorefile, this.keypasswd);
      
     // System.out.println("llllll"+reader.getPubkey());
      byte[] bRealKey = RSACryptonew.Decrypt(reader.getPubkey(), key);
      
      return AESCryptonew.Decrypt(content, new String(bRealKey));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public byte[] pubKeyGenerator(String key)
  {
    try
    {
      ReadPFXnew reader = new ReadPFXnew();
      
      File f = new File(getClass().getResource("").getPath());
      reader.ParsePvkformPfx(f.toString()+this.keystorefile, this.keypasswd);
      
      return RSACryptonew.Encrypt(reader.getPubkey(), key.getBytes());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  private byte[] priKeyGenerator(String key)
  {
    try
    {
      ReadPFXnew reader = new ReadPFXnew();
      File f = new File(getClass().getResource("").getPath());
      reader.ParsePvkformPfx(f.toString()+this.keystorefile, this.keypasswd);
      
     // System.out.println("kkkkkkkk"+reader.getPrikey().getEncoded());
      
      return RSACryptonew.Encrypt(reader.getPrikey(), key.getBytes());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
