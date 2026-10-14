package com.ywjc.glsyjs.interfaces.plycomresult.service.impl;

import com.ywjc.glsyjs.Policyservices.entity.Policymateriallist;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlyfileEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.repositories.PlyfileEntityRepository;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlyfileEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("plyfileEntityServiceImpl")
public class PlyfileEntityServiceImpl extends BaseServiceImpl<PlyfileEntity, String> implements
        PlyfileEntityService {

    @Resource(name = "plyfileEntityRepository")
    private PlyfileEntityRepository plyfileEntityRepository;

    @Resource(name = "plyfileEntityRepository")
    public void setReposity(PagingAndSortingRepository<PlyfileEntity, String> plyfileEntityRepository) {
        super.setReposity(plyfileEntityRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlyfileEntity> findPlyfileEntityByPldtailid(String pldtailid) {
        return plyfileEntityRepository.findPlyfileEntityByPldtailid(pldtailid);
    }

    @Override
    @Transactional
    public void updateMaterial(List<Policymateriallist> materiallists, List<PolicydetailEntity> details){
        for (PolicydetailEntity detail:details) {
            plyfileEntityRepository.deleteByPldtailid(detail.getPldtailid());
            for (Policymateriallist material:materiallists) {
                PlyfileEntity plyfileEntity=new PlyfileEntity();
                plyfileEntity.setPlyfileid(KeyUtils.getKey());
                plyfileEntity.setPldtailid(detail.getPldtailid());
                plyfileEntity.setPolicycategoryname(detail.getNplcategory());
                plyfileEntity.setPolicycategorynumber(detail.getPlcategoryno());
                plyfileEntity.setPolicycategorysubname(detail.getPlsubclassname());
                plyfileEntity.setPolicysubclassno(detail.getPlsubclassnum());
                plyfileEntity.setPolicyconditions(detail.getPlconditions());
                plyfileEntity.setMaterialname(material.getMaterialname());
                plyfileEntity.setStatus("1");
                plyfileEntity.setSn(material.getSn()+"");
                plyfileEntity.setModelfileid(material.getModelfileid());
                plyfileEntity.setModelfilename(material.getModelfilename());
               save(plyfileEntity);
            }

        }
    }

    @Override
    @Transactional
    public void updatePlyfile(List<Policymateriallist> materiallists,PolicydetailEntity detail){
        plyfileEntityRepository.deleteByPldtailid(detail.getPldtailid());
        for (Policymateriallist material:materiallists) {
            PlyfileEntity plyfileEntity=new PlyfileEntity();
            plyfileEntity.setPlyfileid(KeyUtils.getKey());
            plyfileEntity.setPldtailid(detail.getPldtailid());
            plyfileEntity.setPolicycategoryname(detail.getNplcategory());
            plyfileEntity.setPolicycategorynumber(detail.getPlcategoryno());
            plyfileEntity.setPolicycategorysubname(detail.getPlsubclassname());
            plyfileEntity.setPolicysubclassno(detail.getPlsubclassnum());
            plyfileEntity.setPolicyconditions(detail.getPlconditions());
            plyfileEntity.setMaterialname(material.getMaterialname());
            plyfileEntity.setStatus("1");
            plyfileEntity.setSn(material.getSn()+"");
            plyfileEntity.setModelfileid(material.getModelfileid());
            plyfileEntity.setModelfilename(material.getModelfilename());
            save(plyfileEntity);
        }
    }

}
