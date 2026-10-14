package com.ywjc.glsyjs.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class SocketIoService {
	private static final Logger log = LoggerFactory
			.getLogger(SocketIoService.class);
	
	private SocketIOServer server;
	private static Map<String, SocketIOClient> clientsMap = new HashMap<String, SocketIOClient>();

	public void startServer() throws InterruptedException {
		log.info("startServer启动socket");
		Configuration config = new Configuration();
		// 服务器主机ip
//		config.setHostname("192.168.65.13");
//		config.setHostname("10.23.49.15");
		config.setHostname(LocalIp.LOCALIP);
		// 端口
		config.setPort(9092);
		config.setMaxFramePayloadLength(1024 * 1024);
		config.setMaxHttpContentLength(1024 * 1024);
		server = new SocketIOServer(config);

		/**
		 * 监听其他事件
		 */

		// 添加客户端连接事件
		server.addConnectListener(new ConnectListener() {
			@Override
			public void onConnect(SocketIOClient client) {
				// TODO Auto-generated method stub
				String sa = client.getRemoteAddress().toString();
				String clientIp = sa.substring(1, sa.indexOf(":"));// 获取设备ip
				clientsMap.put(clientIp, client);
				log.info(clientIp + "-------------------------" + "客户端已连接");
			}
		});
		// 添加客户端断开连接事件
		server.addDisconnectListener(new DisconnectListener() {
			@Override
			public void onDisconnect(SocketIOClient client) {
				// TODO Auto-generated method stub
				String sa = client.getRemoteAddress().toString();
				String clientIp = sa.substring(1, sa.indexOf(":"));// 获取设备ip
				log.info(clientIp + "-------------------------" + "客户端已断开连接");
				clientsMap.remove(clientIp);
			}
		});
		
		server.start();

	}

	public void stopServer() {
		if (server != null) {
			server.stop();
			server = null;
		}
	}

	/**
	 * 给所有连接客户端推送消息
	 * 
	 * @param eventType
	 *            推送的事件类型
	 * @param message
	 *            推送的内容
	 */
	public void sendMessageToAllClient(String eventType, String message) {
		Collection<SocketIOClient> clients = server.getAllClients();
		for (SocketIOClient client : clients) {
			client.sendEvent(eventType, message);
		}
	}

	/**
	 * 给具体的客户端推送消息
	 * 
	 *            设备类型
	 * @param message
	 *            推送的消息内容
	 */
	public void sendMessageToOneClient(String uuid, String eventType,
			String message) {
		try {
			if (uuid != null && !"".equals(uuid)) {
				SocketIOClient client = (SocketIOClient) clientsMap.get(uuid);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SocketIOServer getServer() {
		return server;
	}
}