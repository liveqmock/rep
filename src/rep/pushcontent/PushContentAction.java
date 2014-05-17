package rep.pushcontent;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import rep.jpush.PushService;

import com.opensymphony.xwork2.ActionContext;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于推送信息表的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class PushContentAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 业务接口对象.
	PushContentManager pMgr = bf.getManager(BeanManagerKey.pushcontentManager);
	// 业务实体对象
	private PushContent vo;
	// 当前页数
	private int page = 1;
	// 每页显示数量
	private int pageSize = 50;
	// 总页数
	private long count;

	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			PushService jpushService = (PushService) SpringContextUtil
					.getBean("jpushService");

			AllSelect allSelect = (AllSelect) SpringContextUtil
					.getBean(BeanManagerKey.allSelectManager.toString());
			ParamSelect select_deviceType = allSelect
					.getParamsByType(AllSelectContants.DEVICETYPE.getName());
			String deviceTp = select_deviceType.getName("" + deviceType);
			System.out.println("deviceType==" + deviceType + ",,,," + deviceTp);
			// 如果没有选择人员，就通知全部的人员
			if (userIds == null || "".equals(userIds.trim()))
				jpushService.sendNotification("dpm", content, null, deviceTp);
			else {
				//推送到制定的人员
				String[] ids = userIds.split(",");
				for (String s : ids)
					jpushService.sendMessage("dpm", s, content, null, deviceTp);
			}
			// 推送的时间.
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
			Date d = new Date(System.currentTimeMillis());
			// String t = sdf.format(d);
			PushContentImpl pushcontentImpl = new PushContentImpl(userIds,
					content, d, userNames, deviceType);
			pMgr.createPushContent(pushcontentImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removePushContents(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getPushContent(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			PushContentImpl pushcontentImpl = new PushContentImpl(sno, userIds,
					content, pushTime, userNames, deviceType);
			pMgr.updatePushContent(pushcontentImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public enum ExportFiled {
		SNO("流水号"), USERIDS("推送用户id"), CONTENT("推送内容"), PUSHTIME("推送时间"), USERNAMES(
				"推送用户"), DEVICETYPE("推送机器类型");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}

	public String beforeQuery() {
		return "query";
	}

	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=PushContentList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<PushContentSearchFields, Object> criterias = getCriterias();

		Collection<PushContent> pushcontentList = pMgr.searchPushContent(
				criterias, realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (PushContent pushcontent : pushcontentList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case SNO:
					e.setCell(filed.ordinal(), pushcontent.getSno());
					break;
				case USERIDS:
					e.setCell(filed.ordinal(), pushcontent.getUserIds());
					break;
				case CONTENT:
					e.setCell(filed.ordinal(), pushcontent.getContent());
					break;
				case PUSHTIME:
					e.setCell(filed.ordinal(), pushcontent.getPushTime());
					break;
				case USERNAMES:
					e.setCell(filed.ordinal(), pushcontent.getUserNames());
					break;
				case DEVICETYPE:
					e.setCell(filed.ordinal(), pushcontent.getDeviceType());
					break;
				default:
					break;
				}

			}
		}

		e.exportXls(response);
		return null;
	}

	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<PushContentSearchFields, Object> criterias = getCriterias();

		Collection<PushContent> moneyList = pMgr.searchPushContent(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchPushContentNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount", count);
		return "list";
	}

	public String reQuery() {
		return "list";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private Map<PushContentSearchFields, Object> getCriterias() {
		Map<PushContentSearchFields, Object> criterias = new HashMap<PushContentSearchFields, Object>();
		if (getContent() != null && !"".equals(getContent()))
			criterias.put(PushContentSearchFields.CONTENT, "%" + getContent()
					+ "%");
		if (getDeviceType() != null && !"".equals(getDeviceType())
				&& !"-1".equals(getDeviceType())
				&& !"-2".equals(getDeviceType()))
			criterias.put(PushContentSearchFields.DEVICETYPE, getDeviceType());
		return criterias;
	}

	public PushContent getVo() {
		return vo;
	}

	public void setVo(PushContent vo) {
		this.vo = vo;
	}

	private Integer sno;

	/**
	 * 获取流水号的属性值.
	 */
	public Integer getSno() {
		return sno;
	}

	/**
	 * 设置流水号的属性值.
	 */
	public void setSno(Integer sno) {
		this.sno = sno;
	}

	private String userIds;

	/**
	 * 获取推送用户id的属性值.
	 */
	public String getUserIds() {
		return userIds;
	}

	/**
	 * 设置推送用户id的属性值.
	 */
	public void setUserIds(String userids) {
		this.userIds = userids;
	}

	private String content;

	/**
	 * 获取推送内容的属性值.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置推送内容的属性值.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	private Date pushTime;

	/**
	 * 获取推送时间的属性值.
	 */
	public Date getPushTime() {
		return pushTime;
	}

	/**
	 * 设置推送时间的属性值.
	 */
	public void setPushTime(Date pushtime) {
		this.pushTime = pushtime;
	}

	private String userNames;

	/**
	 * 获取推送用户的属性值.
	 */
	public String getUserNames() {
		return userNames;
	}

	/**
	 * 设置推送用户的属性值.
	 */
	public void setUserNames(String usernames) {
		this.userNames = usernames;
	}

	private String deviceType;

	/**
	 * 获取推送机器类型的属性值.
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 设置推送机器类型的属性值.
	 */
	public void setDeviceType(String devicetype) {
		this.deviceType = devicetype;
	}
}
