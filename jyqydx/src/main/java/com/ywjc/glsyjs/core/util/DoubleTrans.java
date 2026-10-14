package com.ywjc.glsyjs.core.util;

import java.math.BigDecimal;

public class DoubleTrans {
	
	public static String doubleTrans(double d){
		double preErrorKey=1e-10;
		String result=String.valueOf(d);
		if(Math.round(d)-d <preErrorKey){
		result=String.valueOf((long)d);
		}
		return result;
	}
	
	public static double doubleTrans1(double d){
		 BigDecimal bg = BigDecimal.valueOf(d);
		 double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
}
