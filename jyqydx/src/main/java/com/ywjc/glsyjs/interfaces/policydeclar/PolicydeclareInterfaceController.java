package com.ywjc.glsyjs.interfaces.policydeclar;

import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import com.ywjc.glsyjs.Policyservices.service.PolicydeclareService;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/interface/policydeclar")
public class PolicydeclareInterfaceController {
    @Resource(name = "policydeclareServiceImpl")
    private PolicydeclareService policydeclareServiceImpl;
    @Resource(name = "policydetailServiceImpl")
    private PolicydetailService policydetailService;
    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;

    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public MessageUtil queryList(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
                                 @RequestParam(required = false) String declarename,
                                 @RequestParam(required = false) Date startdate,
                                 @RequestParam(required = false) Date enddate) {
        declarename = StringUtils.isNotBlank(declarename) ? "%" + declarename + "%" : "%%";
        MessageUtil messageUtil = new MessageUtil();
        String state = "2";//已经发布
        Page<Policydeclare> policydeclares = policydeclareServiceImpl.queryPageinfo(declarename, startdate, enddate, state, pageable);
        messageUtil.setErrorMsg("请求列表成功");
        messageUtil.setResultObj(policydeclares);
        messageUtil.setResult(true);
        return messageUtil;
    }

    @RequestMapping(value = "/queryDetailByPolicyFile", method = RequestMethod.GET)
    public MessageUtil queryDetailByPolicyFile(@RequestParam("plfids") String plfids,
                                               @RequestParam(value = "search", required = false) String search, @RequestParam String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        List<PolicydetailEntity> result = new ArrayList<>();
        String[] split = plfids.split(",");
        for (String plfid : split) {
            String policycategory = null;
            String policysubclass = null;
            if (StringUtils.isNotBlank(search)) {
                policycategory = search.split("-")[0] == null ? "" : search.split("-")[0];
                if (search.split("-").length > 1) {
                    policysubclass = search.split("-")[1] == null ? "" : search.split("-")[1];
                }
            }
            List<PolicydetailEntity> policyFiles = policydetailService.findPolicyFiles(plfid, policycategory, policysubclass);
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            for (PolicydetailEntity policydetail : policyFiles) {
                PlycomresultdetailEntity plycomresultdetail = plycomresultdetailEntityService.findPlycomresult(companyid, policydetail.getPldtailid(), year);
                if (plycomresultdetail != null) {
                    policydetail.setSbstate("1");
                }
            }
            result.addAll(policyFiles);
        }
        messageUtil.setErrorMsg("请求列表成功");
        messageUtil.setResultObj(result);
        messageUtil.setResult(true);
        return messageUtil;
    }

    @RequestMapping(value = "/queryPolicyCategory", method = RequestMethod.GET)
    public MessageUtil queryPolicyCategory() {
        MessageUtil messageUtil = new MessageUtil();
        List<String> result = policydetailService.findPolicyCategory();
        messageUtil.setErrorMsg("请求列表成功");
        messageUtil.setResultObj(result);
        messageUtil.setResult(true);
        return messageUtil;
    }


}
