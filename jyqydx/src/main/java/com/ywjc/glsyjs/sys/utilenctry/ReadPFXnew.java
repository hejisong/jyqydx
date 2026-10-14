package com.ywjc.glsyjs.sys.utilenctry;

import java.io.FileInputStream;



import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Enumeration;


import org.apache.commons.codec.binary.Base64;

public class ReadPFXnew
{
  private PrivateKey prikey;
  private PublicKey pubkey;
  
  public void ParsePvkformPfx(String strPfx, String strPassword)
  {
    try
    {
      KeyStore ks = KeyStore.getInstance("PKCS12");
      FileInputStream fis = new FileInputStream(strPfx);
      
      char[] nPassword = null;
      if ((strPassword == null) || (strPassword.trim().equals(""))) {
        nPassword = null;
      } else {
        nPassword = strPassword.toCharArray();
      }
      ks.load(fis, nPassword);
      fis.close();
      Enumeration<String> enumas = ks.aliases();
      String keyAlias = null;
      if (enumas.hasMoreElements())
      {
        keyAlias = (String)enumas.nextElement();
        System.out.println("alias=[" + keyAlias + "]");
      }
      this.prikey = ((PrivateKey)ks.getKey(keyAlias, nPassword));
      Certificate cert = ks.getCertificate(keyAlias);
      this.pubkey = cert.getPublicKey();
      
     /* for(int i=0;i<this.pubkey.getEncoded().length;i++){
    	  System.out.println(this.pubkey.getEncoded()[i]);
      }*/
      //System.out.println("public keymmmm:"+Base64.encodeBase64(this.pubkey.t));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public PrivateKey getPrikey()
  {
    return this.prikey;
  }
  
  public void setPrikey(PrivateKey prikey)
  {
    this.prikey = prikey;
  }
  
  public PublicKey getPubkey()
  {
    return this.pubkey;
  }
  
  public void setPubkey(PublicKey pubkey)
  {
    this.pubkey = pubkey;
  }
}
