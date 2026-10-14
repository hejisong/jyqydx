package com.ywjc.glsyjs.sys.repositories;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.util.Constantval;
import org.springframework.stereotype.Component;

import com.ywjc.glsyjs.core.service.QueryService;


@Component("indexRepository")
public class IndexRepository {

	@Resource(name = "queryCacheServiceImpl")
	private QueryService queryService;
	

	

}
