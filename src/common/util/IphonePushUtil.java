/**
 * MainSend.java
 * 版权所有(C) 2012 
 * 创建:cuiran 2012-07-24 11:31:35
 */
package common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * TODO
 * 
 * @author cuiran
 * @version TODO
 */
public class IphonePushUtil {
	private static Log log = LogFactory.getLog(IphonePushUtil.class.getName());
	private static String host;
	private static String pass;
	private static String file;
	private static int port;
	static {
		Resource resource = new ClassPathResource("jpush.properties");
		try {
			Properties properties = PropertiesLoaderUtils
					.loadProperties(resource);
			host = properties.getProperty("iphone_host");
			file = properties.getProperty("iphone_file");
			pass = properties.getProperty("iphone_password");
			// 从消息推送时起，保存离线的时长。秒为单位。最多支持10天
			port = Integer.parseInt(properties.getProperty("iphone_port"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendMessage(String token, String content,
			Map<String, String> extras) {
		try {
			System.out.println("Push Start deviceToken:" + token);
			// 定义消息模式
			PayLoad payLoad = new PayLoad();
			payLoad.addAlert(content);
			payLoad.addBadge(1);
			payLoad.addSound("default");
			if (extras != null) {
				Set<Entry<String, String>> s = extras.entrySet();
				Iterator<Entry<String, String>> it = s.iterator();
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					payLoad.addCustomDictionary(e.getKey(), e.getValue());
				}
			}
			// 注册deviceToken
			PushNotificationManager pushManager = PushNotificationManager
					.getInstance();
			pushManager.initializeConnection(host, port, file, pass,
					SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
			// pushManager.initializeConnection();
			pushManager.addDevice("iPhone", token);
			pushManager.addDevice("iPhone2", token);
			// 发送推送
			Device client = pushManager.getDevice("iPhone");
			pushManager.sendNotification(client, payLoad);

			Device client2 = pushManager.getDevice("iPhone2");
			pushManager.sendNotification(client2, payLoad);
			// 停止连接APNS
			pushManager.stopConnection();
			// 删除deviceToken
			pushManager.removeDevice("iPhone");
			pushManager.removeDevice("iPhone2");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void sendMessage(List<String> tokens, String content,
			Map<String, Object> extras) {
		try {
			// 定义消息模式
			PayLoad payLoad = new PayLoad();
			payLoad.addAlert(content);
			payLoad.addBadge(1);
			payLoad.addSound("default");
			if (extras != null) {
				Set<Entry<String, Object>> s = extras.entrySet();
				Iterator<Entry<String, Object>> it = s.iterator();
				while (it.hasNext()) {
					Entry<String, Object> e = it.next();
					payLoad.addCustomDictionary(e.getKey(), e.getValue()+"");
				}
			}
			// 注册deviceToken
			PushNotificationManager pushManager = PushNotificationManager
					.getInstance();
			pushManager.initializeConnection(host, port, file, pass,
					SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
			// pushManager.initializeConnection();
			int i = 0;
			for (String t : tokens) {
				String deviceId = "iPhone" + (i++);
				pushManager.addDevice(deviceId, t);
				Device client = pushManager.getDevice(deviceId);
				pushManager.sendNotification(client, payLoad);
			}
			// 停止连接APNS
			pushManager.stopConnection();
			for (; i > 0; i--) {
				// 删除deviceToken
				pushManager.removeDevice("iPhone" + (i - 1));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/************************************************
	 * 测试推送服务器地址：gateway.sandbox.push.apple.com /2195
	 * 产品推送服务器地址：gateway.push.apple.com / 2195
	 * 
	 * 需要javaPNS_2.2.jar包
	 ***************************************************/
	public static void main(String[] args) throws Exception {

		try {
			// 从客户端获取的deviceToken
			String token = "d31b0f21cce48a7d843bd1b47610bb55930b391a4d661aa5e1388c2d10817be6";
			List<String> tt = new ArrayList<String>();
			tt.add(token);
 tt.add(token);
			IphonePushUtil.sendMessage(tt, "测试发送的推送信息...", null);
			 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
