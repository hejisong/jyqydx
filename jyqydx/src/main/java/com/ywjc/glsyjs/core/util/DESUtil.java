package com.ywjc.glsyjs.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DESUtil {
	private static Logger log = Logger.getLogger(DESUtil.class);
	private static ScriptEngine engine = null;

	static {
		// 得到一个ScriptEngine对象
		ScriptEngineManager maneger = new ScriptEngineManager();
		engine = maneger.getEngineByName("JavaScript");

		// 读js文件
		InputStream is = DESUtil.class.getClassLoader().getResourceAsStream("Des.js");
		Reader scriptReader = null;
		try {
			scriptReader = new InputStreamReader(is, "utf-8");
			engine.eval(scriptReader);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		} catch (ScriptException e) {
			log.error(e.getMessage());
		} finally {
			if (scriptReader != null) {
				try {
					scriptReader.close();
				} catch (IOException e) {
					System.err.println("IOException Occured");
				}
			}

		}
	}

	public static String entry(String data, String salt) {

		String result = null;

		if (engine instanceof Invocable) {
			// 调用JS方法
			Invocable invocable = (Invocable) engine;
			try {
				result = (String) invocable.invokeFunction("strDec", new Object[] { data, salt });
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage());
			} catch (ScriptException e) {
				log.error(e.getMessage());
			}
		}
		return result;
	}

}
