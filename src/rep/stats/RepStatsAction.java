package rep.stats;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于erp统计记录的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class RepStatsAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 业务接口对象.
	RepStatsManager pMgr = bf.getManager(BeanManagerKey.repstatsManager);
	// 业务实体对象
	private RepStats vo;
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
			RepStatsImpl repstatsImpl = new RepStatsImpl(inputDate, statis1,
					statis2, statis3, statis4, statis5, statis6, problem,
					param2, param1);
			pMgr.createRepStats(repstatsImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeRepStatss(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getRepStats(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			RepStatsImpl repstatsImpl = new RepStatsImpl(sno, inputDate,
					statis1, statis2, statis3, statis4, statis5, statis6,
					problem, param2, param1);
			pMgr.updateRepStats(repstatsImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public enum ExportFiled {
		SNO("流水号"), INPUTDATE("输入日期"), STATIS1("统计数据1"), STATIS2("统计数据2"), STATIS3(
				"统计数据3"), STATIS4("统计数据4"), STATIS5("统计数据5"), STATIS6("统计数据6"), PROBLEM(
				"问题"), PARAM2("额外参数2"), PARAM1("额外参数1");
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
				"attachment;filename=RepStatsList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<RepStatsSearchFields, Object> criterias = getCriterias();

		Collection<RepStats> repstatsList = pMgr.searchRepStats(criterias,
				realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (RepStats repstats : repstatsList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case SNO:
					e.setCell(filed.ordinal(), repstats.getSno());
					break;
				case INPUTDATE:
					e.setCell(filed.ordinal(), repstats.getInputDate());
					break;
				case STATIS1:
					e.setCell(filed.ordinal(), repstats.getStatis1());
					break;
				case STATIS2:
					e.setCell(filed.ordinal(), repstats.getStatis2());
					break;
				case STATIS3:
					e.setCell(filed.ordinal(), repstats.getStatis3());
					break;
				case STATIS4:
					e.setCell(filed.ordinal(), repstats.getStatis4());
					break;
				case STATIS5:
					e.setCell(filed.ordinal(), repstats.getStatis5());
					break;
				case STATIS6:
					e.setCell(filed.ordinal(), repstats.getStatis6());
					break;
				case PROBLEM:
					e.setCell(filed.ordinal(), repstats.getProblem());
					break;
				case PARAM2:
					e.setCell(filed.ordinal(), repstats.getParam2());
					break;
				case PARAM1:
					e.setCell(filed.ordinal(), repstats.getParam1());
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
		Map<RepStatsSearchFields, Object> criterias = getCriterias();

		Collection<RepStats> moneyList = pMgr.searchRepStats(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchRepStatsNum(criterias);
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

	private Map<RepStatsSearchFields, Object> getCriterias() {
		Map<RepStatsSearchFields, Object> criterias = new HashMap<RepStatsSearchFields, Object>();
		if (getProblem() != null && !"".equals(getProblem()))
			criterias.put(RepStatsSearchFields.PROBLEM, "%" + getProblem()
					+ "%");
		return criterias;
	}

	public RepStats getVo() {
		return vo;
	}

	public void setVo(RepStats vo) {
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

	private Date inputDate;

	/**
	 * 获取输入日期的属性值.
	 */
	public Date getInputDate() {
		return inputDate;
	}

	/**
	 * 设置输入日期的属性值.
	 */
	public void setInputDate(Date inputdate) {
		this.inputDate = inputdate;
	}

	private double statis1;

	/**
	 * 获取统计数据1的属性值.
	 */
	public double getStatis1() {
		return statis1;
	}

	/**
	 * 设置统计数据1的属性值.
	 */
	public void setStatis1(double statis1) {
		this.statis1 = statis1;
	}

	private double statis2;

	/**
	 * 获取统计数据2的属性值.
	 */
	public double getStatis2() {
		return statis2;
	}

	/**
	 * 设置统计数据2的属性值.
	 */
	public void setStatis2(double statis2) {
		this.statis2 = statis2;
	}

	private double statis3;

	/**
	 * 获取统计数据3的属性值.
	 */
	public double getStatis3() {
		return statis3;
	}

	/**
	 * 设置统计数据3的属性值.
	 */
	public void setStatis3(double statis3) {
		this.statis3 = statis3;
	}

	private double statis4;

	/**
	 * 获取统计数据4的属性值.
	 */
	public double getStatis4() {
		return statis4;
	}

	/**
	 * 设置统计数据4的属性值.
	 */
	public void setStatis4(double statis4) {
		this.statis4 = statis4;
	}

	private double statis5;

	/**
	 * 获取统计数据5的属性值.
	 */
	public double getStatis5() {
		return statis5;
	}

	/**
	 * 设置统计数据5的属性值.
	 */
	public void setStatis5(double statis5) {
		this.statis5 = statis5;
	}

	private double statis6;

	/**
	 * 获取统计数据6的属性值.
	 */
	public double getStatis6() {
		return statis6;
	}

	/**
	 * 设置统计数据6的属性值.
	 */
	public void setStatis6(double statis6) {
		this.statis6 = statis6;
	}

	private String problem;

	/**
	 * 获取问题的属性值.
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * 设置问题的属性值.
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}

	private String param2;

	/**
	 * 获取额外参数2的属性值.
	 */
	public String getParam2() {
		return param2;
	}

	/**
	 * 设置额外参数2的属性值.
	 */
	public void setParam2(String param2) {
		this.param2 = param2;
	}

	private String param1;

	/**
	 * 获取额外参数1的属性值.
	 */
	public String getParam1() {
		return param1;
	}

	/**
	 * 设置额外参数1的属性值.
	 */
	public void setParam1(String param1) {
		this.param1 = param1;
	}
}
