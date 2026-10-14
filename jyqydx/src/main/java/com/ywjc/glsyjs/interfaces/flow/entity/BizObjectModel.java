package com.ywjc.glsyjs.interfaces.flow.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class BizObjectModel {

    private static final Logger log = Logger.getLogger(BizObjectModel.class);

    private static String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"};

    private boolean loadedFromDb = false;

    private String schemaCode;

    private String formCode;

    private String status;

    private Map<String, Object> data = new HashMap();
}
