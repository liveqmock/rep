
package rep.pushcontent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于推送信息表的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class PushContentManagerImpl extends AbstractBusinessObjectManager implements
		PushContentManager {

	private PushContentDao pushcontentdao = null;

	/**
	 * 构造函数.
	 */
	public PushContentManagerImpl(PushContentDao pushcontentdao) {
		this.pushcontentdao = pushcontentdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchPushContentNum(Map<PushContentSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.pushcontentdao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<PushContent> searchPushContent(Map<PushContentSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<PushContent> eaList = new ArrayList<PushContent>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<PushContentVO> voList = this.pushcontentdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_deviceType = allSelect
				.getParamsByType(AllSelectContants.DEVICETYPE.getName());
		
		for (PushContentVO po : voList) {
			po.setDeviceType(select_deviceType.getName("" + po.getDeviceType())); 
			eaList.add(new  PushContentImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<PushContentSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( pushcontent) "
						: "select  pushcontent ").append("from PushContentVO as pushcontent ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<PushContentSearchFields, Object> entry : criterias
					.entrySet()) {
				PushContentSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  pushcontent.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERIDS:
						sb.append(count == 0 ? " where" : " and").append(
								"  pushcontent.userIds = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CONTENT:
						sb.append(count == 0 ? " where" : " and").append(
								"  pushcontent.content like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case PUSHTIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  pushcontent.pushTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERNAMES:
						sb.append(count == 0 ? " where" : " and").append(
								"  pushcontent.userNames = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case DEVICETYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  pushcontent.deviceType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		PushContentOrderByFields orderBy = PushContentOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = PushContentOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by pushcontent.sno");
			break;
			case USERIDS:
				 sb.append(" order by pushcontent.userIds");
			break;
			case CONTENT:
				 sb.append(" order by pushcontent.content");
			break;
			case PUSHTIME:
				 sb.append(" order by pushcontent.pushTime");
			break;
			case USERNAMES:
				 sb.append(" order by pushcontent.userNames");
			break;
			case DEVICETYPE:
				 sb.append(" order by pushcontent.deviceType");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createPushContent(PushContent pushcontent) throws ValidateFieldsException {
		PushContentImpl pushcontentImpl = (PushContentImpl) pushcontent;
		this.pushcontentdao.insert(pushcontentImpl.getPushContentVO());
	}

	public void removePushContents(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			PushContentVO vo = this.pushcontentdao.findByPrimaryKey(Integer.parseInt(s));
			this.pushcontentdao.delete(vo);
		}
	}

	public void updatePushContent(PushContent pushcontent) throws ValidateFieldsException {
		PushContentImpl pushcontentImpl = (PushContentImpl) pushcontent;

		PushContentVO po = pushcontentImpl.getPushContentVO();
		this.pushcontentdao.update(po);
	}

	public PushContent getPushContent(int id) {
		Collection<PushContentVO> pushcontents = this.pushcontentdao.findRecordById(id);

		if (pushcontents == null || pushcontents.size() < 1)
			return null;

		PushContentVO pushcontent = pushcontents.toArray(new PushContentVO[pushcontents.size()])[0];

		return new PushContentImpl(pushcontent);
	}

}
