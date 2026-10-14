package com.ywjc.glsyjs.interfaces.tag.service;


import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccuratetagService extends BaseService<Accuratetag, String> {


    Page<Accuratetag> findPageAndSearch( String tagname,Pageable pageable);


    Accuratetag findTagByTagname(String tagname);

    void deleteByTagid(String tagid);


    List<Accuratetag> findByPid(String pid);

    public List<String> findTagidByPid(String pid);

    public List<String> findTagidByCid(String cid);


    void savetag(List<Accuratetag> tags, String pldtailid, User user);

    void saveCorptag(List<Accuratetag> tags, String companyid, User user);


    public Object[] importInfoExcel(Workbook excelwork, Uploadfile file,User user );

    public List<Object> findtags(String[] pids);

    public List<Object> findTagcount(String[] tagids,String cid,String year);

    public List<Object> findPTagcount(List<String> tagids,String[] pids);

    public List<Object> findcount(List<String> tagids,List<Object> companyids);


}
