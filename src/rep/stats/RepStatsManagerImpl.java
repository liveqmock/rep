
package rep.stats;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于erp统计记录的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepStatsManagerImpl extends AbstractBusinessObjectManager implements
		RepStatsManager {

	private RepStatsDao repstatsdao = null;

	/**
	 * 构造函数.
	 */
	public RepStatsManagerImpl(RepStatsDao repstatsdao) {
		this.repstatsdao = repstatsdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchRepStatsNum(Map<RepStatsSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.repstatsdao.countByQuery(hql,
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
	public Collection<RepStats> searchRepStats(Map<RepStatsSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<RepStats> eaList = new ArrayList<RepStats>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<RepStatsVO> voList = this.repstatsdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		
		for (RepStatsVO po : voList) {
			eaList.add(new  RepStatsImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<RepStatsSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( repstats) "
						: "select  repstats ").append("from RepStatsVO as repstats ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<RepStatsSearchFields, Object> entry : criterias
					.entrySet()) {
				RepStatsSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case INPUTDATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.inputDate = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STATIS1:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.statis1 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STATIS2:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.statis2 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STATIS3:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.statis3 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STATIS4:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.statis4 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STATIS5:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.statis5 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STATIS6:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.statis6 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PROBLEM:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.problem like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case PARAM2:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.param2 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case PARAM1:
						sb.append(count == 0 ? " where" : " and").append(
								"  repstats.param1 = ? ");
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

		RepStatsOrderByFields orderBy = RepStatsOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = RepStatsOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by repstats.sno");
			break;
			case INPUTDATE:
				 sb.append(" order by repstats.inputDate");
			break;
			case STATIS1:
				 sb.append(" order by repstats.statis1");
			break;
			case STATIS2:
				 sb.append(" order by repstats.statis2");
			break;
			case STATIS3:
				 sb.append(" order by repstats.statis3");
			break;
			case STATIS4:
				 sb.append(" order by repstats.statis4");
			break;
			case STATIS5:
				 sb.append(" order by repstats.statis5");
			break;
			case STATIS6:
				 sb.append(" order by repstats.statis6");
			break;
			case PROBLEM:
				 sb.append(" order by repstats.problem");
			break;
			case PARAM2:
				 sb.append(" order by repstats.param2");
			break;
			case PARAM1:
				 sb.append(" order by repstats.param1");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createRepStats(RepStats repstats) throws ValidateFieldsException {
		RepStatsImpl repstatsImpl = (RepStatsImpl) repstats;
		this.repstatsdao.insert(repstatsImpl.getRepStatsVO());
	}

	public void removeRepStatss(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			RepStatsVO vo = this.repstatsdao.findByPrimaryKey(Integer.parseInt(s));
			this.repstatsdao.delete(vo);
		}
	}

	public void updateRepStats(RepStats repstats) throws ValidateFieldsException {
		RepStatsImpl repstatsImpl = (RepStatsImpl) repstats;

		RepStatsVO po = repstatsImpl.getRepStatsVO();
		this.repstatsdao.update(po);
	}

	public RepStats getRepStats(int id) {
		Collection<RepStatsVO> repstatss = this.repstatsdao.findRecordById(id);

		if (repstatss == null || repstatss.size() < 1)
			return null;

		RepStatsVO repstats = repstatss.toArray(new RepStatsVO[repstatss.size()])[0];

		return new RepStatsImpl(repstats);
	}

}
