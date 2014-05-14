
package rep.data;
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
 * 关于erp数据记录的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepDataManagerImpl extends AbstractBusinessObjectManager implements
		RepDataManager {

	private RepDataDao repdatadao = null;

	/**
	 * 构造函数.
	 */
	public RepDataManagerImpl(RepDataDao repdatadao) {
		this.repdatadao = repdatadao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchRepDataNum(Map<RepDataSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.repdatadao.countByQuery(hql,
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
	public Collection<RepData> searchRepData(Map<RepDataSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<RepData> eaList = new ArrayList<RepData>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<RepDataVO> voList = this.repdatadao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		
		for (RepDataVO po : voList) {
			eaList.add(new  RepDataImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<RepDataSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( repdata) "
						: "select  repdata ").append("from RepDataVO as repdata ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<RepDataSearchFields, Object> entry : criterias
					.entrySet()) {
				RepDataSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INPUTDATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.inputDate = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case DATATYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.dataType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case COMENUM:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.comeNum = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INSTRESTNUM:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.instrestNum = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case TRYNUM:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.tryNum = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case BUYNUM:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.buyNum = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OLDNUM:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.oldNum = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.userId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAM1:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.param1 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAM2:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.param2 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAM3:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.param3 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case TIMESPAN:
						sb.append(count == 0 ? " where" : " and").append(
								"  repdata.timeSpan = ? ");
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

		RepDataOrderByFields orderBy = RepDataOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = RepDataOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by repdata.sno");
			break;
			case INPUTDATE:
				 sb.append(" order by repdata.inputDate");
			break;
			case DATATYPE:
				 sb.append(" order by repdata.dataType");
			break;
			case COMENUM:
				 sb.append(" order by repdata.comeNum");
			break;
			case INSTRESTNUM:
				 sb.append(" order by repdata.instrestNum");
			break;
			case TRYNUM:
				 sb.append(" order by repdata.tryNum");
			break;
			case BUYNUM:
				 sb.append(" order by repdata.buyNum");
			break;
			case OLDNUM:
				 sb.append(" order by repdata.oldNum");
			break;
			case USERID:
				 sb.append(" order by repdata.userId");
			break;
			case PARAM1:
				 sb.append(" order by repdata.param1");
			break;
			case PARAM2:
				 sb.append(" order by repdata.param2");
			break;
			case PARAM3:
				 sb.append(" order by repdata.param3");
			break;
			case TIMESPAN:
				 sb.append(" order by repdata.timeSpan");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createRepData(RepData repdata) throws ValidateFieldsException {
		RepDataImpl repdataImpl = (RepDataImpl) repdata;
		this.repdatadao.insert(repdataImpl.getRepDataVO());
	}

	public void removeRepDatas(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			RepDataVO vo = this.repdatadao.findByPrimaryKey(Integer.parseInt(s));
			this.repdatadao.delete(vo);
		}
	}

	public void updateRepData(RepData repdata) throws ValidateFieldsException {
		RepDataImpl repdataImpl = (RepDataImpl) repdata;

		RepDataVO po = repdataImpl.getRepDataVO();
		this.repdatadao.update(po);
	}

	public RepData getRepData(int id) {
		Collection<RepDataVO> repdatas = this.repdatadao.findRecordById(id);

		if (repdatas == null || repdatas.size() < 1)
			return null;

		RepDataVO repdata = repdatas.toArray(new RepDataVO[repdatas.size()])[0];

		return new RepDataImpl(repdata);
	}

}
