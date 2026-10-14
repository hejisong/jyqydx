package com.ywjc.glsyjs.businesshare.service;


import com.ywjc.glsyjs.businesshare.entity.Suggestreply;
import com.ywjc.glsyjs.core.service.BaseService;

import java.util.List;

public interface SuggestreplyService extends BaseService<Suggestreply, String> {

    public List<Suggestreply> findBySuggest(String suggestid);

}
