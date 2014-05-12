package rep.jpush;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import cn.jpush.api.push.MessageResult;

import com.alibaba.fastjson.JSON;
import common.util.IphonePushUtil;
import common.util.JpushUtil;

import dwz.framework.constants.Constants;

/**
 * 提供的接口. TODO(描述类的职责)
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
public class PushService implements IJpushService {
	private IJPushDao dao;

	public IJPushDao getDao() {
		return dao;
	}

	public void setDao(IJPushDao dao) {
		this.dao = dao;
	}

	/**
	 * 发送到苹果.
	 * 
	 * @param userId
	 *            用户id
	 * @param sysCode
	 *            系统名
	 * @param content
	 *            发送的内容
	 * @param extras
	 *            附带的数据信息
	 */
	private void sendToIphone(String userId, String sysCode, String content,
			Map<String, Object> extras) {
		// 发送到苹果手机
		List<JpushVO> vos = queryTagAndAlias(userId, sysCode, Constants.PINGGUO);
		if (vos != null && vos.size() > 0) {
			List<String> tokens = new ArrayList<String>();
			for (JpushVO v : vos) {
				tokens.add(v.getToken());
			}
			IphonePushUtil.sendMessage(tokens, content, extras);
		}
	}

	@Override
	public String sendNotification(String sysCode, String content,
			Map<String, Object> extras, String osType) {
		if (osType == null) {
			// 发送到android
			JpushUtil.sendNotificationByTag(sysCode, content, extras);
			// 发送到苹果手机
			sendToIphone(null, sysCode, content, extras);
			return "ok";
		} else if (Constants.ANDROID.equals(osType.trim())) {
			MessageResult result = JpushUtil.sendNotificationByTag(sysCode,
					content, extras);
			if (result.isResultOK())
				return JSON.toJSONString(result);
		} else if (Constants.PINGGUO.equals(osType.trim())) {
			// 发送到苹果手机
			sendToIphone(null, sysCode, content, extras);
			return "ok";
		}
		return null;
	}

	@Override
	public String sendMessage(String sysCode, String userId, String content,
			Map<String, Object> extras, String osType) {
		if (osType == null) {
			// 发送到安卓.
			List<JpushVO> vos = queryTagAndAlias(userId, sysCode,
					Constants.ANDROID);
			if (vos!=null&&vos.size() > 0) { 
				MessageResult result = JpushUtil.sendNotificationByAlias(vos
						.get(0).getToken(), content, extras);
				if (result.isResultOK())
					return JSON.toJSONString(result);
			}
			// 发送到苹果手机
			sendToIphone(userId, sysCode, content, extras);
			return "ok";
		} else if (Constants.ANDROID.equals(osType.trim())) {
			List<JpushVO> vos = queryTagAndAlias(userId, sysCode,
					Constants.ANDROID);
			if (vos!=null&&vos.size() > 0) { 
				MessageResult result = JpushUtil.sendNotificationByAlias(vos
						.get(0).getToken(), content, extras);
				if (result.isResultOK())
					return JSON.toJSONString(result);
			}
		} else if (Constants.PINGGUO.equals(osType.trim())) {
			sendToIphone(userId, sysCode, content, extras);
		}
		return null;
	}

	public static String encodeMD5Hex(String data) {
		// 执行消息摘要
		return DigestUtils.md5Hex(data);
	}

	private String saveJpushVo(String deviceType, String token, String sysCode,
			String userId) {
		JpushVO vo = new JpushVO();
		// 生成的别名是这样计算出来的.
		if (Constants.ANDROID.equals(deviceType))
			token = encodeMD5Hex(sysCode + userId + deviceType
					+ System.currentTimeMillis());
		else if (Constants.PINGGUO.equals(deviceType)) {
			// 苹果是直接上传的token，不是生成的。
		} else {
			throw new RuntimeException("不支持的终端类型.");
		}
		vo.setUserId(userId);
		vo.setSysCode(sysCode);
		vo.setDeviceType(deviceType);
		vo.setToken(token);// 再插入新的数据
		dao.savePushUser(vo);
		return token;
	}

	@Override
	public String saveTagAndAlias(String userId, String sysCode,
			String deviceType, String token) {
		List<JpushVO> ans = queryTagAndAlias(userId, sysCode, deviceType);
		// 如果找不到以前的记录，就插入新的数据
		if (ans==null||ans.size() == 0) {
			token = saveJpushVo(deviceType, token, sysCode, userId);
		}
		// 否则就返回以前的token.
		else {
			String db_token = ans.get(0).getToken();
			// 如果是苹果上传，如果数据库里面的和上传的token不一样，就更新.
			if (db_token != null && token != null && !db_token.equals(token)
					&& Constants.PINGGUO.equals(deviceType)) {
				JpushVO vo = new JpushVO();
				vo.setUserId(userId);
				vo.setSysCode(sysCode);
				vo.setDeviceType(deviceType);
				vo.setToken(token);
				dao.updatePushUser(vo);
			}

			// 如果是android，以前没有保存token，就添加新的数据
			if (db_token == null && Constants.ANDROID.equals(deviceType)) {
				dao.deletePushUser(userId, sysCode, deviceType);
				token = saveJpushVo(deviceType, token, sysCode, userId);
			}
			// 如果是android， 以前保存了token，就返回以前的值.
			else if (Constants.ANDROID.equals(deviceType) && db_token != null) {
				return db_token;
			}
		}
		return token;
	}

	@Override
	public void deleteTagAndAlias(String userId, String sysCode,
			String deviceType) {
		dao.deletePushUser(userId, sysCode, deviceType);
	}

	@Override
	public List<JpushVO> queryTagAndAlias(String userId, String sysCode,
			String deviceType) {
		JpushVO vo = new JpushVO();
		vo.setUserId(userId);
		vo.setSysCode(sysCode);
		vo.setDeviceType(deviceType);
		return dao.selectPushUser(vo);
	}

}
