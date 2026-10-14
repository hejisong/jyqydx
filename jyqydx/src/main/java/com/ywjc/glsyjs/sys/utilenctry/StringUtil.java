package com.ywjc.glsyjs.sys.utilenctry;

public class StringUtil
{
  private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  
  public static String byteArrayToString(byte[] data)
  {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < data.length; i++)
    {
      stringBuilder.append(HEX_CHAR[((data[i] & 0xF0) >>> 4)]);
      
      stringBuilder.append(HEX_CHAR[(data[i] & 0xF)]);
      if (i < data.length - 1) {
        stringBuilder.append(' ');
      }
    }
    return stringBuilder.toString();
  }
  
  public static String ExpandString(String srcStr)
  {
    String expStr = "1";
    if (16 < srcStr.length()) {
      return srcStr.substring(0, 16);
    }
    int deslen = 16 - srcStr.length();
    for (int i = 0; i < deslen; i++) {
      srcStr = srcStr + expStr;
    }
    return srcStr;
  }
}
