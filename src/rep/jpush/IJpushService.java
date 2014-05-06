package rep.jpush;

import java.util.List;
import java.util.Map;

/**
 * 推送消息提供的接口. TODO(描述类的职责)
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:130126,date:2014-4-10 下午3:50:39,content:TODO
 * </p>
 * 
 * @author 130126
 * @date 2014-4-10 下午3:50:39
 * @since
 * @version
 */
public interface IJpushService {
	/**
	 * 对指定系统的全部用户进行通知. 实际是按照标签（syscode）发送自定义的客户通知。
	 * 
	 * @param sysCode
	 * @param content
	 * @param osType 操作系统名(android,iphone，没有的话就通知两个)
	 * @return
	 */
	public String sendNotification(String sysCode, String content,
			Map<String, Object> extras,String osType);

	/**
	 * 对指定用户和执行系统进行发送点对点消息. 实际是按照别名(syscode+":"+userid)发送自定义的客户通知。
	 * 
	 * @param sysCode
	 * @param userId
	 * @param content
	 * @param osType 操作系统名(android,iphone，没有的话就通知两个)
	 * @return
	 */
	public String sendMessage(String sysCode, String userId, String content,
			Map<String, Object> extras,String osType);

	/**
	 * 保存用户标签和别名.
	 * 
	 * @param userId
	 *            用户id
	 * @param sysCode
	 * @param deviceType
	 * @param token
	 * @return
	 */
	public String saveTagAndAlias(String userId, String sysCode,
			String deviceType, String token);

	/**
	 * 删除用户标签和别名.
	 * 
	 * @param userId
	 * @param sysCode
	 * @param deviceType
	 */
	public void deleteTagAndAlias(String userId, String sysCode,
			String deviceType);

	/**
	 * 查询推送配置映射信息.
	 * 
	 * @param userId
	 * @param sysCode
	 * @param deviceType
	 * @return
	 */
	public List<JpushVO> queryTagAndAlias(String userId, String sysCode,
			String deviceType);
}
