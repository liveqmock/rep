
package rep.user;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.ParamSelect;
import common.base.AllSelect;
import common.base.SpringContextUtil;
import dwz.constants.BeanManagerKey;
import common.base.AllSelect;
import common.base.AllSelectContants;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于erp用户表的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepUserManagerImpl extends AbstractBusinessObjectManager implements
		RepUserManager {

	private RepUserDao repuserdao = null;

	/**
	 * 构造函数.
	 */
	public RepUserManagerImpl(RepUserDao repuserdao) {
		this.repuserdao = repuserdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchRepUserNum(Map<RepUserSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.repuserdao.countByQuery(hql,
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
	public Collection<RepUser> searchRepUser(Map<RepUserSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<RepUser> eaList = new ArrayList<RepUser>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<RepUserVO> voList = this.repuserdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		
		for (RepUserVO po : voList) {
			eaList.add(new  RepUserImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<RepUserSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( repuser) "
						: "select  repuser ").append("from RepUserVO as repuser ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<RepUserSearchFields, Object> entry : criterias
					.entrySet()) {
				RepUserSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.userId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case BRANDNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.brandName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case BRANDTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.brandType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case AREA:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.area = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ADDRESS:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.address like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case MASTERPRICE:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.masterPrice = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case WORKNUM:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.workNum = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case WORKTIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.workTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case WORKTIMENUM:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.workTimeNum = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case WEEKENDNUM:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.weekendNum = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PHONE:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.phone = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PASSWORD:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.password = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAM1:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.param1 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case LNG_NORTH:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.lng_north = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INDATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.inDate = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case LAT_EAST:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.lat_east = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case LOCATION:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.location = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAM2:
						sb.append(count == 0 ? " where" : " and").append(
								"  repuser.param2 = ? ");
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

		RepUserOrderByFields orderBy = RepUserOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = RepUserOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by repuser.sno");
			break;
			case USERID:
				 sb.append(" order by repuser.userId");
			break;
			case BRANDNAME:
				 sb.append(" order by repuser.brandName");
			break;
			case BRANDTYPE:
				 sb.append(" order by repuser.brandType");
			break;
			case AREA:
				 sb.append(" order by repuser.area");
			break;
			case ADDRESS:
				 sb.append(" order by repuser.address");
			break;
			case MASTERPRICE:
				 sb.append(" order by repuser.masterPrice");
			break;
			case WORKNUM:
				 sb.append(" order by repuser.workNum");
			break;
			case WORKTIME:
				 sb.append(" order by repuser.workTime");
			break;
			case WORKTIMENUM:
				 sb.append(" order by repuser.workTimeNum");
			break;
			case WEEKENDNUM:
				 sb.append(" order by repuser.weekendNum");
			break;
			case PHONE:
				 sb.append(" order by repuser.phone");
			break;
			case PASSWORD:
				 sb.append(" order by repuser.password");
			break;
			case PARAM1:
				 sb.append(" order by repuser.param1");
			break;
			case LNG_NORTH:
				 sb.append(" order by repuser.lng_north");
			break;
			case INDATE:
				 sb.append(" order by repuser.inDate");
			break;
			case LAT_EAST:
				 sb.append(" order by repuser.lat_east");
			break;
			case LOCATION:
				 sb.append(" order by repuser.location");
			break;
			case PARAM2:
				 sb.append(" order by repuser.param2");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createRepUser(RepUser repuser) throws ValidateFieldsException {
		RepUserImpl repuserImpl = (RepUserImpl) repuser;
		this.repuserdao.insert(repuserImpl.getRepUserVO());
	}

	public void removeRepUsers(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			RepUserVO vo = this.repuserdao.findByPrimaryKey(Integer.parseInt(s));
			this.repuserdao.delete(vo);
		}
	}

	public void updateRepUser(RepUser repuser) throws ValidateFieldsException {
		RepUserImpl repuserImpl = (RepUserImpl) repuser;

		RepUserVO po = repuserImpl.getRepUserVO();
		this.repuserdao.update(po);
	}

	public RepUser getRepUser(int id) {
		Collection<RepUserVO> repusers = this.repuserdao.findRecordById(id);

		if (repusers == null || repusers.size() < 1)
			return null;

		RepUserVO repuser = repusers.toArray(new RepUserVO[repusers.size()])[0];

		return new RepUserImpl(repuser);
	}

}
