package rep.jpush;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import dwz.framework.constants.Constants;
import dwz.present.BaseAction;

/**
 * 
 * 通讯录服务请求. TODO(描述类的职责)
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:130126,date:2014-4-12 上午9:51:02,content:TODO
 * </p>
 * 
 * @author 130126
 * @date 2014-4-12 上午9:51:02
 * @since
 * @version
 */
public class JpushAction extends BaseAction {
	PushService jpushService;
	private String sysCode;
	private String osType;
	private String content;
	private String toUser;
	private String deviceType;
	private String businoId;
	private String iphoneToken;
	private String url;
	private String type;
	private String id;

	protected void writeToPage(HttpServletResponse response, Result result) {
		try {
			response.setContentType("text/html;charset=GBK");
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIphoneToken() {
		return iphoneToken;
	}

	public void setIphoneToken(String iphoneToken) {
		this.iphoneToken = iphoneToken;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getBusinoId() {
		return businoId;
	}

	public void setBusinoId(String businoId) {
		this.businoId = businoId;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public PushService getJpushService() {
		return jpushService;
	}

	public void setJpushService(PushService jpushService) {
		this.jpushService = jpushService;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 推送到一群客户端.
	 * 
	 * @return
	 */
	public String sendMessageToAll() {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
			Result<String> res = new Result<String>();
			HashMap map = new HashMap();
			map.put("content", content);
			if (url != null && !"".equals(url.trim())) {
				map.put("url", url);
			}
			if (type != null && !"".equals(type.trim())) {
				map.put("type", type);
			}
			if (id != null && !"".equals(id.trim())) {
				map.put("id", id);
			}
			res.setData(jpushService.sendNotification(sysCode, content, map,
					osType));
			res.setErrorCode(Constants.SUCCESS);
			// 返回json

		} catch (UnsupportedEncodingException e) {
			Result res = new Result();
			res.setErrorCode(Constants.SERVICE_ERROR);
			res.setErrorMessage("中文转码出现异常");
			writeToPage(response, res);
		}
		return null;
	}

	/**
	 * 推送到指定的客户.
	 * 
	 * @return
	 */
	public String sendMessageToOne() {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
			Result<String> res = new Result<String>();
			HashMap map = new HashMap();
			map.put("content", content);
			if (url != null && !"".equals(url.trim())) {
				map.put("url", url);
			}
			if (type != null && !"".equals(type.trim())) {
				map.put("type", type);
			}
			if (id != null && !"".equals(id.trim())) {
				map.put("id", id);
			}
			res.setData(jpushService.sendMessage(sysCode, toUser, content, map,
					osType));
			res.setErrorCode(Constants.SUCCESS);
			// 返回json
			writeToPage(response, res);

		} catch (UnsupportedEncodingException e) {
			Result res = new Result();
			res.setErrorCode(Constants.SERVICE_ERROR);
			res.setErrorMessage("中文转码出现异常");
			writeToPage(response, res);
		}
		return null;
	}

	/**
	 * 返回别名.
	 * 
	 * @return
	 */
	public String setTagAndAlias() {
		HttpServletResponse response = ServletActionContext.getResponse();
		Result<String> res = new Result<String>();
		String token = jpushService.saveTagAndAlias(businoId, sysCode,
				deviceType, iphoneToken);
		res.setData(token);
		res.setErrorCode(Constants.SUCCESS);
		// 返回json
		writeToPage(response, res);

		return null;
	}

	/**
	 * 删除别名和标签.
	 * 
	 * @return
	 */
	public String deleteTagAndAlias() {
		HttpServletResponse response = ServletActionContext.getResponse();
		Result<String> res = new Result<String>();
		jpushService.deleteTagAndAlias(businoId, sysCode, deviceType);
		res.setData("删除成功.");
		res.setErrorCode(Constants.SUCCESS);
		// 返回json
		writeToPage(response, res);

		return null;
	}
}
