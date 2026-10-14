package com.ywjc.glsyjs.task;

import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import com.ywjc.glsyjs.interfaces.flow.service.FlowProcessEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component("flowcreatetask")
//@EnableAsync
public class FlowCreatetask {
    private static Logger log = Logger.getLogger(FlowCreatetask.class);
    private static final LinkedBlockingQueue<String> applyidQueue= new LinkedBlockingQueue<>();

    public static ArrayBlockingQueue<String> dataApplyid = new ArrayBlockingQueue<String>(50);

    @Resource(name = "plycomresultdetailEntityServiceImpl")
    private PlycomresultdetailEntityService plycomresultdetailEntityService;

    @Resource(name = "flowProcessEntityServiceImpl")
    private FlowProcessEntityService flowProcessEntityService;

    public static void putApplyid(String applyid) {
        synchronized (dataApplyid) {
            while(dataApplyid.size()==50) {
                try {
                    dataApplyid.wait();
                } catch (InterruptedException e) {
                    System.out.println("punishbasic生产者线程挂起异常："+e.getMessage());
                }
            }
            dataApplyid.add(applyid);
            dataApplyid.notifyAll();
        }
    }

    public static String getApplyid() {
        synchronized (dataApplyid) {
            while(dataApplyid.isEmpty()) {
                try {
                    dataApplyid.wait();
                } catch (InterruptedException e) {
                    System.out.println("punishbasic消费者线程挂起异常："+e.getMessage());
                }
            }
            String applyid = dataApplyid.remove();
            dataApplyid.notifyAll();
            return applyid;
        }
    }
    public static void addApplyid(String applyid){
        applyidQueue.add(applyid);
    }

    public void addApplyid(){
        boolean flag = true;
        while(flag) {
            try{
                if(dataApplyid.size()<50){
                    List<PlycomresultdetailEntity> list=plycomresultdetailEntityService.findlistByIsflow("0");
                    if(list!=null&&list.size()>0){
                        for(PlycomresultdetailEntity entity:list){
                            if(dataApplyid.size()<50){
                                String oldapplyid=entity.getPlycomresultdetailid();
                                dataApplyid.put(oldapplyid);
                                entity.setIsflow("1");
                                plycomresultdetailEntityService.update(entity);
                            }else{
                                break;
                            }
                        }
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

//    @Async
    public void addFolwProcess(){
        boolean flag = true;
        while(flag) {
            try{
                if(dataApplyid.size()>0){
                    String applyid=dataApplyid.take();
                    PlycomresultdetailEntity entity=plycomresultdetailEntityService.find(applyid);
                    if(entity!=null){
                        List<FlowProcessEntity> flowprocesslist=flowProcessEntityService.queryListByapplyid(entity.getPlycomresultdetailid());
                        if(flowprocesslist!=null&&flowprocesslist.size()>0) {
                            log.info("流程id:"+applyid+"已生成流程数据无需重复生成！");
                            break;
                        }else{
                            String message=flowProcessEntityService.createflowprocessbyapplyidintask(entity.getPlycomresultdetailid());
                            log.info("流程id:"+applyid+"创建成功"+message);
                        }
                    }
                }else{
                    break;
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
//        //查询所有已匹配的政策数据
////        List<PlycomresultdetailEntity> plylist=plycomresultdetailEntityService.findAll();
////        if(plylist!=null&&plylist.size()>0){
////            for(PlycomresultdetailEntity entity:plylist){
//                PlycomresultdetailEntity entity=plycomresultdetailEntityService.find("d59efee87c1411ebb138b06ebf39a80b");
//                List<FlowProcessEntity> flowprocesslist=flowProcessEntityService.queryListByapplyid(entity.getPlycomresultdetailid());
//                if(flowprocesslist==null||flowprocesslist.size()==0){
//                    String message=flowProcessEntityService.createflowprocessbyapplyidintask(entity.getPlycomresultdetailid());
//                    log.info(message);
//                }
////            }
////        }


    }
}
