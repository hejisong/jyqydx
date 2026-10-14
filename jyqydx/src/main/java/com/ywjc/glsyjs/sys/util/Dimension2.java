package com.ywjc.glsyjs.sys.util;




import org.apache.commons.codec.binary.Base64;




/**
 * 
 * @Description TODO
 * @author Administrator
 * @version 1.0
 */

public class Dimension2
{
   
	public static void main(String args[]){
		
		//decryptWithPublickey2("ad2Gylkt8cT++Ky5vU2ygtAhZCkq08VCmoZmraoitLFxIE8L5i8GdCstSIs6/RpFIxt2EL+zERGVhUOXzi/solOU+vXgJs/v2MndW7OqLpeze20LQd6ZEL51meALiRR5EHIBEP2LAL2Hwn6X4SA9F/XF8flonbAAb8ClWFGzByjudg+HLfddXBOVLOmYb4ks4Mtd0TP1TMwIv1r2hXZH1o/kMWGCTTgQuTqo839S6saXL32o2L8lTkV3DMN9nZixFlRMER/aaQeu7TldYyLm6NGLTbTAfDTe5Oc/X+W50DK09/61Jx4UwW3GXZMa+YvMmaHdSsU/kDeIqHhxgVSsvy80VAkisl6XpxWyKRhkhW4=");
		encrityWithPrivatekeyNew("mmmmm","fdssergh678");
	}
    
    
    public static String decryptWithPublickey2(String content){
        
        byte[] pubKey = null;
        //QUVRlCvJEVdnb2tSy9GuwT7IscfqgMLP2QkUTvg93xmxDNool5bY3rfCXyxuUWTZ
        
        pubKey=Base64.decodeBase64("Ok6TL8XCECXlnKwhzdTutAwdvTNhGHS6GqqRC7xTn9i2AF3YErhhsoE65VePXceG6GOtnyeBaANBFJMu1ZxTia46RiKeLd4R1BBpE25N5Cs8Xx3paa/NbI58S+uT/TkDFuBcoOcpng9KrW7OXyG/lBaGc+RuUlkV3lyLNH6bQAhp6yqQx82QHNhvHm1NtbzZIXutKU+f3w9TgLxj6iJ4g4WMrL9i43bfA4FDVO20oSXmSAd7zryMpMYDVVrTyJ123pnNKIcVGTqHzngzmFCsAo/emCiYGXgkBZlNtpauUXQYAjRrK5xrHre3JA3UZs4wdVm8mon85P+OoyELGMWJzA==");
       //	pubKey = Base64.decodeBase64("O3z8Q5VPBl4gpPrXhDufZXJ4ZIMPZ7fqK2/JkS3j2TK1AlFzt9XM/E9ADaLreGUY3TOjWg2WiLRkE3vOplKAiPcYHY2dN3oAWln37w7/w64uamBMQp2QgX/sMisBpDJEnFujPSDTtr/CeMmIlNfp0N7ktxtlMCybEe3PKvhYUo9nidOJAqcFnYGrDkZlQrb6/c4bCthOp5nGN8WR1iQDMVuTBTpQ8BtOR3c1e4QJDdIwMORNFzHtKyoxhLMxuEHtF8JX7ROhLl2K1nwFsUITucqJGQ/KTWqSPsYw0cgQlZv+fVJpWVj9aYkreUoBUe740Edd7wXajKvRaURnYByFAg==");
       
        //pubKey = Base64.decodeBase64("ZWo/It93ybziUMV5QVLbEcivtG3qRdiHTeQNu+Mvj2HfFpL1G9fnenq6OYBYrtlIQbdIQZCCBl2OZfAWqNbxZe+C+sOVXaMNUJ7YGfL3uDhWlYWV3MtHzW5Yrw/FaE/GmMC50COi64PVgHuJxyLs5sdawRzCRTHq2pfN1YD0U4DgZgn0VaVU8XRujwxigiNb52sKBNvRhnGDlf6SLHen/I9iXzlF3VRAgaXKrGRZZojH6NhDER5ZMYD09GS/doiHB23G7codkc9Dfz0wf/Lpz+iaxZ1+ptTujBmDhlEv23wHQscnIw53o6ubzhf/pdkpTQHBvd9IYDmjj5S7KzZyBg==");
        
       	System.out.println("kkkkkkkkmmmm：："+new String(pubKey));
        //pubKey = Base64.decodeBase64("QUVRlCvJEVdnb2tSy9GuwT7IscfqgMLP2QkUTvg93xmxDNool5bY3rfCXyxuUWTZ");
        PKIForWdnjnew pKIForWdnj = new PKIForWdnjnew();
        String result = pKIForWdnj.decryptWithPublickey(content, pubKey);
        
        System.out.println(result);
        return result;
        
   }
    
    
 public static String decryptWithPublickey3(String content){
        
        byte[] pubKey = null;
        //QUVRlCvJEVdnb2tSy9GuwT7IscfqgMLP2QkUTvg93xmxDNool5bY3rfCXyxuUWTZ
        
        pubKey=Base64.decodeBase64("KmJMTzn8vOo1r8DTlzY/mltdnh8BnlOCSGeYSqUXt4pxZnACdgQkwfqMVbiGbx35tv6x8ewPe6x7UNhX8eREmNBGdUhqFVQ2cLWOWEp5BEOFraQJ4M8ErbMMDRjTvtwv6h4H4nXjFI5oJjnGZUx4XA2Qau6sBsbyeFXcbGVwNuwiKYenRi86np+EbubSh3XnrLVSTVC8Tn56oE8UcSXDc5C8H9cL81frdf4Z39/o48iDL1ikBDbCfsH3csV7ztWba5Efr8+89X3/lBgs8s8un0WYID9ILT4YMyYnW/kr1onK8cUX0syAjvRjfc6fYlyzjytQ1L6X81kQGJrSyq+/IA==");
       //	pubKey = Base64.decodeBase64("O3z8Q5VPBl4gpPrXhDufZXJ4ZIMPZ7fqK2/JkS3j2TK1AlFzt9XM/E9ADaLreGUY3TOjWg2WiLRkE3vOplKAiPcYHY2dN3oAWln37w7/w64uamBMQp2QgX/sMisBpDJEnFujPSDTtr/CeMmIlNfp0N7ktxtlMCybEe3PKvhYUo9nidOJAqcFnYGrDkZlQrb6/c4bCthOp5nGN8WR1iQDMVuTBTpQ8BtOR3c1e4QJDdIwMORNFzHtKyoxhLMxuEHtF8JX7ROhLl2K1nwFsUITucqJGQ/KTWqSPsYw0cgQlZv+fVJpWVj9aYkreUoBUe740Edd7wXajKvRaURnYByFAg==");
       
        //pubKey = Base64.decodeBase64("ZWo/It93ybziUMV5QVLbEcivtG3qRdiHTeQNu+Mvj2HfFpL1G9fnenq6OYBYrtlIQbdIQZCCBl2OZfAWqNbxZe+C+sOVXaMNUJ7YGfL3uDhWlYWV3MtHzW5Yrw/FaE/GmMC50COi64PVgHuJxyLs5sdawRzCRTHq2pfN1YD0U4DgZgn0VaVU8XRujwxigiNb52sKBNvRhnGDlf6SLHen/I9iXzlF3VRAgaXKrGRZZojH6NhDER5ZMYD09GS/doiHB23G7codkc9Dfz0wf/Lpz+iaxZ1+ptTujBmDhlEv23wHQscnIw53o6ubzhf/pdkpTQHBvd9IYDmjj5S7KzZyBg==");
        
       	System.out.println("kkkkkkkkmmmm"+pubKey.toString());
        //pubKey = Base64.decodeBase64("QUVRlCvJEVdnb2tSy9GuwT7IscfqgMLP2QkUTvg93xmxDNool5bY3rfCXyxuUWTZ");
        PKIForWdnjnew pKIForWdnj = new PKIForWdnjnew();
        String result = pKIForWdnj.decryptWithPublickey(content, pubKey);
        return result;
        
   }
    
    
    public static String encrityWithPrivatekeyNew(String content,String privatekey){
    	
        PKIForWdnjnew pKIForWdnj = new PKIForWdnjnew();
        String result = pKIForWdnj.encryptWithPrivateKey(content,privatekey).getContent();

        return result;
        
   }
    
    public static String encrityWithPrivatekey(String content){
    	
        PKIForWdnjnew pKIForWdnj = new PKIForWdnjnew();
        String result = pKIForWdnj.encryptWithPrivateKey(content,"1234567kj8yghu123").getContent();
       
        return result;
        
   }
    
    
}
