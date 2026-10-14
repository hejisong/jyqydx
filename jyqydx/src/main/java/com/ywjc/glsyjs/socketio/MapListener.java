package com.ywjc.glsyjs.socketio;

import com.alibaba.fastjson.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component("MapCacheServer")
public class MapListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = Logger.getLogger(MapListener.class);

	private ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(1);

	@Autowired
	private SocketIoService service;

//	@Resource(name = "illnoticeServiceImpl")
//	private IllnoticeService illnoticeService;
//	@Resource
//	private ManschikService manschikSerivce;
//
//	@Resource(name = "susShipServiceImpl")
//	private SusShipService susShipService;
//	@Resource(name = "shipIllegalRecordServiceImpl")
//	private ShipIllegalRecordService shipIllegalRecordService;
//	@Resource
//	private FileInfoService fileInfoService;
//	@Resource(name = "gateofferServiceImpl")
//	private GateofferService gateofferService;
//	@Resource(name = "usesiteinforServiceImpl")
//	private UsesiteinforService usesiteinforService;
//
//	@Resource(name = "punishbasicServiceImpl")
//	private PunishbasicService punishbasicService;
//
//	@Resource(name = "sstxcaseinfoServiceImpl")
//	private SstxcaseinfoService sstxcaseinfoService;


	// 执行周期
	private static Integer period = 30000;

	// 延迟时间，时间单位为毫秒,可自行设定，不得小于等于0
	private static Integer delay = 2000;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

			 //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
			
				 log.info("开始启动websocket"+sdf.format(new Date()));
					if (service.getServer() == null) {
						try {
							service.startServer();
						} catch (InterruptedException e) {
							log.error(e.getMessage());
						}
					}
					
					
					// 定时向客户端推送信息，每30秒钟推送一次
					executorService.scheduleWithFixedDelay(new Thread() {
						public void run() {
							log.info("开始推送"+sdf.format(new Date()));
							try {
								List<Object[]> list1 = new ArrayList<Object[]>();
								List<Object[]> list2 = new ArrayList<Object[]>();
								List<Object[]> list3 = new ArrayList<Object[]>();
								List<Object[]> list4 = new ArrayList<Object[]>();
								List<Object[]> list5 = new ArrayList<Object[]>();
								List<Object[]> list6 = new ArrayList<Object[]>();
								List<Object[]> list7 = new ArrayList<Object[]>();
								List<Object[]> list8 = new ArrayList<Object[]>();
								List<Object[]> list9 = new ArrayList<Object[]>();
								////////////////////////////////////////////////////////////////////////////////////////////////////////////
//								//法制科待审核的案件
//								//法制科审核的节点Id
//								List<Object[]> fzkdshlists = sstxcaseinfoService.findBydzxcasebynodeid("402809a26f7f1bcd016f7f31f1860000");
//								//查询法制科待审核的案件(提示法制科)
//								list1 = sstxcaseinfoService.findByfzkNobynodeandstate("402809a26f7f1bcd016f7f31f1860000","0");
//								if(fzkdshlists!=null&&fzkdshlists.size()!=list1.size()){
//									for(int i=0;i<fzkdshlists.size();i++){
//										String id=fzkdshlists.get(i)[0].toString();
//										Sstxcaseinfo sstxcaseinfo=sstxcaseinfoService.find(id);
//										if(sstxcaseinfo==null){
//											Sstxcaseinfo sstx=new Sstxcaseinfo();
//											sstx.setPunishid(id);
//											sstx.setWfinstid(fzkdshlists.get(i)[1].toString());
//											sstx.setWfnodeid(fzkdshlists.get(i)[2].toString());
//											sstx.setFirstclerkid(fzkdshlists.get(i)[3].toString());
//											sstx.setSecondclerkid(fzkdshlists.get(i)[4].toString());
//											sstx.setOrganid(fzkdshlists.get(i)[5].toString());
//											sstx.setOrganname(fzkdshlists.get(i)[6].toString());
//											sstx.setAllnames(fzkdshlists.get(i)[7].toString());
//											sstx.setRemindstate("0");
//											sstx.setType("fzkdshcase");
//											sstx.setCreatetime(new Date());
//											sstx.setUpdatetime(new Date());
//											sstxcaseinfoService.save(sstx);
//										}
//									}
//									list1 = sstxcaseinfoService.findByfzkNobynodeandstate("402809a26f7f1bcd016f7f31f1860000","0");
//								}
//								//法制科已审核的案件
//								//机构负责人审核的节点Id(注:是重大案件)
//								List<Object[]> fzkyshlists = sstxcaseinfoService.findBydzxcaseendbynodeid("4028048e4bd83f7c014bd85f2bf40003");
//								//查询法制科已经审核的案件(提示法制科)
//								list2 = sstxcaseinfoService.findByfzkNobynodeandstate("4028048e4bd83f7c014bd85f2bf40003","0");
//								if(fzkyshlists!=null&&fzkyshlists.size()!=list2.size()){
//									for(int i=0;i<fzkyshlists.size();i++){
//										String id=fzkyshlists.get(i)[0].toString();
//										Sstxcaseinfo sstxcaseinfo=sstxcaseinfoService.find(id);
//										if(sstxcaseinfo!=null&&!sstxcaseinfo.getType().equals("fzkshendcase")){
//											sstxcaseinfo.setWfnodeid(fzkyshlists.get(i)[2].toString());
//											sstxcaseinfo.setRemindstate("0");
//											sstxcaseinfo.setType("fzkshendcase");
//											sstxcaseinfo.setUpdatetime(new Date());
//											sstxcaseinfoService.update(sstxcaseinfo);
//										}
//									}
//									list2 = sstxcaseinfoService.findByfzkNobynodeandstate("4028048e4bd83f7c014bd85f2bf40003","0");
//
//								}
//
//								//处置大队待审核的案件
//								//处置大队审核的节点Id
//								List<Object[]> czdddshlists = sstxcaseinfoService.findBydzxcasebynodeid("402809c26ef464cd016ef472e0a70000");
//								//查询处置大队待审核的案件(提示)
//								list3 = sstxcaseinfoService.findByfzkNobynodeandstate("402809c26ef464cd016ef472e0a70000","0");
//								if(czdddshlists!=null&&czdddshlists.size()!=list3.size()){
//									for(int i=0;i<czdddshlists.size();i++){
//										String id=czdddshlists.get(i)[0].toString();
//										Sstxcaseinfo sstxcaseinfo=sstxcaseinfoService.find(id);
//										if(sstxcaseinfo==null){
//											Sstxcaseinfo sstx=new Sstxcaseinfo();
//											sstx.setPunishid(id);
//											sstx.setWfinstid(czdddshlists.get(i)[1].toString());
//											sstx.setWfnodeid(czdddshlists.get(i)[2].toString());
//											sstx.setFirstclerkid(czdddshlists.get(i)[3].toString());
//											sstx.setSecondclerkid(czdddshlists.get(i)[4].toString());
//											sstx.setOrganid(czdddshlists.get(i)[5].toString());
//											sstx.setOrganname(czdddshlists.get(i)[6].toString());
//											sstx.setAllnames(czdddshlists.get(i)[7]==null?"":czdddshlists.get(i)[7].toString());
//											sstx.setRemindstate("0");
//											sstx.setType("czdddshcase");
//											sstx.setCreatetime(new Date());
//											sstx.setUpdatetime(new Date());
//											sstxcaseinfoService.save(sstx);
//										}
//									}
//									list3 = sstxcaseinfoService.findByfzkNobynodeandstate("402809c26ef464cd016ef472e0a70000","0");
//
//								}
////
//								//处置大队以审核的案件
//								//支队分管领导的节点id(注:不是水上大队的案件)
//								List<Object[]> czddshendlists = sstxcaseinfoService.findByccddcaseendbynodeid("402809a26f9d906b016f9e4967930028");
//								//查询处置大队以审核的案件(提示)
//								list4 = sstxcaseinfoService.findByfzkNobynodeandstate("402809a26f9d906b016f9e4967930028","0");
//								if(czddshendlists!=null&&czddshendlists.size()!=list4.size()){
//									for(int i=0;i<czddshendlists.size();i++){
//										String id=czddshendlists.get(i)[0].toString();
//										Sstxcaseinfo sstxcaseinfo=sstxcaseinfoService.find(id);
//										if(sstxcaseinfo!=null&&!sstxcaseinfo.getType().equals("czddshendcase")){
//											sstxcaseinfo.setWfnodeid(czddshendlists.get(i)[2].toString());
//											sstxcaseinfo.setRemindstate("0");
//											sstxcaseinfo.setType("czddshendcase");
//											sstxcaseinfo.setUpdatetime(new Date());
//											sstxcaseinfoService.update(sstxcaseinfo);
//										}
//									}
//									list4 = sstxcaseinfoService.findByfzkNobynodeandstate("402809a26f9d906b016f9e4967930028","0");
//								}
//								/////////////////////////////////////////////////////////////////////////////////////////////////////////
////老的提醒以注释
////								list1 = illnoticeService.findByState("1");//违章行为签字确认
////								list2 = manschikSerivce.findShipnameAnd();//查询上传过配员证书的船
////								list3 = susShipService.findByState("2");//确认超限的数据
////								list4 = shipIllegalRecordService.findByState("0");//违章未处理
////								list5 = illnoticeService.findByPayState("1");//罚没款已缴费
////								list6 = fileInfoService.findByFiletype("三合一签字");//三个文书签字确认后（一般程序案件办理）
////								list7 = fileInfoService.findByFiletype("处罚决定书签字");//处罚决定书签字
////								list9 = fileInfoService.findByFiletype("结案签字");//剩余三个签字确认后（违法行为通知书送达回证、处罚决定书、处罚决定书送达回证）
//								list8 = gateofferService.findByState("4");//安全检查

								list8.addAll(list9);
								list7.addAll(list8);
								list6.addAll(list7);
								list5.addAll(list6);
								list4.addAll(list5);
								list3.addAll(list4);
								list2.addAll(list3);
								list1.addAll(list2);
								Object[] points = list1.toArray(new Object[list1.size()]);
								service.sendMessageToAllClient("notices", JSONArray.toJSONString(points));
							} catch (Throwable e) {
								e.printStackTrace();
								log.error("信息推送线程发生错误："+e);
							}
							
							log.info("推送结束"+sdf.format(new Date()));
							
						}
					}, delay, period, TimeUnit.MILLISECONDS); 
					log.info("当前容器:"+event.getApplicationContext().getDisplayName()+"时间戳:"+sdf.format(new Date()));

	}
}
