package common.util;

import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.DeviceEnum;
import cn.jpush.api.push.CustomMessageParams;
import cn.jpush.api.push.MessageResult;
import cn.jpush.api.push.NotificationParams;
import cn.jpush.api.push.ReceiverTypeEnum;

/**
 * jpush推送的api封装.
 * 
 * @author 130126
 * 
 */
public class JpushUtil {
	// master secret
	private static String masterSecret = null;
	// app key
	private static String appKey = null;
	// 从消息推送时起，保存离线的时长。秒为单位。最多支持10天
	private static int timeToLive = 0;
	static {
		Resource resource = new ClassPathResource("jpush.properties");
		try {
			Properties properties = PropertiesLoaderUtils
					.loadProperties(resource);
			masterSecret = properties.getProperty("MasterSecret");
			appKey = properties.getProperty("AppKey");
			// 从消息推送时起，保存离线的时长。秒为单位。最多支持10天
			timeToLive = Integer.parseInt(properties.getProperty("TimeToLive"));
			System.out.println("masterSecret="+masterSecret+",appKey="+appKey+",timeToLive="+timeToLive);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送通知到全部的系统
	 * 
	 * @param content
	 * @return
	 */
	public static MessageResult sendNotificationAll(String content) {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				timeToLive, DeviceEnum.Android, false);
		return jpushClient.sendNotificationAll(content);
	}

	/**
	 * 对指定tag的设备发送通知.
	 * 
	 * @param tag
	 * @param content
	 * @param extras
	 * @return
	 */
	public static MessageResult sendNotificationByTag(String tag,
			String content, Map<String, Object> extras) {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				timeToLive, DeviceEnum.Android, false);
		NotificationParams params = new NotificationParams();
		params.setReceiverType(ReceiverTypeEnum.TAG);
		params.setReceiverValue(tag);
		return jpushClient.sendNotification(content, params, extras);
	}

	/**
	 * 根据别名进行通知发送.
	 * 
	 * @param alias
	 * @param content
	 * @param extras
	 * @return
	 */
	public static MessageResult sendNotificationByAlias(String alias,
			String content, Map<String, Object> extras) {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				timeToLive, DeviceEnum.Android, false);
		NotificationParams params = new NotificationParams();
		params.setReceiverType(ReceiverTypeEnum.ALIAS);
		params.setReceiverValue(alias);
		return jpushClient.sendNotification(content, params, extras);
	}

	/**
	 * 根据标签发送推送.
	 * 
	 * @param tag
	 * @param title
	 * @param content
	 * @param extras
	 *            携带的额外参数信息.
	 * @return
	 */
	public static MessageResult sendCustomerByTag(String tag, String title,
			String content, Map<String, Object> extras) {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				timeToLive, DeviceEnum.Android, false);
		CustomMessageParams params = new CustomMessageParams();
		params.setReceiverType(ReceiverTypeEnum.TAG);
		params.setReceiverValue(tag);
		return jpushClient.sendCustomMessage(title, content, params, extras);
	}

	/**
	 * 按照别名进行发送消息.
	 * 
	 * @param tag
	 * @param title
	 * @param content
	 * @return
	 */
	public static MessageResult sendCustomerByAlias(String alias, String title,
			String content, Map<String, Object> extras) {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey,
				timeToLive, DeviceEnum.Android, false);
		CustomMessageParams params = new CustomMessageParams();
		params.setReceiverType(ReceiverTypeEnum.ALIAS);
		params.setReceiverValue(alias);
		return jpushClient.sendCustomMessage(title, content, params, extras);
	}
}
