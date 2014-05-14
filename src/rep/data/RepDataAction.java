
package rep.data;

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
 * 关于erp数据记录的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepDataAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	RepDataManager pMgr = bf.getManager(BeanManagerKey.repdataManager);
	//业务实体对象
	private RepData vo;
	//当前页数
	private int page = 1;
	//每页显示数量
	private int pageSize = 50;
	//总页数
	private long count;
	
	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			RepDataImpl repdataImpl = new RepDataImpl(inputDate ,dataType ,comeNum ,instrestNum ,tryNum ,buyNum ,oldNum ,userId ,param1 ,param2 ,param3 ,timeSpan );
			pMgr.createRepData(repdataImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeRepDatas(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getRepData(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			RepDataImpl repdataImpl = new RepDataImpl( sno , inputDate , dataType , comeNum , instrestNum , tryNum , buyNum , oldNum , userId , param1 , param2 , param3 , timeSpan );
			pMgr.updateRepData(repdataImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号"),  INPUTDATE("输入日期"),  DATATYPE("收集方式"),  COMENUM("进店人数"),  INSTRESTNUM("感兴趣人数"),  TRYNUM("试衣人数"),  BUYNUM("购买人数"),  OLDNUM("老顾客人数"),  USERID("统计用户"),  PARAM1("额外参数1"),  PARAM2("额外参数2"),  PARAM3("额外参数3"),  TIMESPAN("营业时间间隔");
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
		response.addHeader("Content-Disposition","attachment;filename=RepDataList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<RepDataSearchFields, Object> criterias = getCriterias();

		Collection<RepData> repdataList = pMgr.searchRepData(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (RepData repdata : repdataList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), repdata.getSno()); 
					break;
					case INPUTDATE:
						 e.setCell(filed.ordinal(), repdata.getInputDate()); 
					break;
					case DATATYPE:
						 e.setCell(filed.ordinal(), repdata.getDataType()); 
					break;
					case COMENUM:
						 e.setCell(filed.ordinal(), repdata.getComeNum()); 
					break;
					case INSTRESTNUM:
						 e.setCell(filed.ordinal(), repdata.getInstrestNum()); 
					break;
					case TRYNUM:
						 e.setCell(filed.ordinal(), repdata.getTryNum()); 
					break;
					case BUYNUM:
						 e.setCell(filed.ordinal(), repdata.getBuyNum()); 
					break;
					case OLDNUM:
						 e.setCell(filed.ordinal(), repdata.getOldNum()); 
					break;
					case USERID:
						 e.setCell(filed.ordinal(), repdata.getUserId()); 
					break;
					case PARAM1:
						 e.setCell(filed.ordinal(), repdata.getParam1()); 
					break;
					case PARAM2:
						 e.setCell(filed.ordinal(), repdata.getParam2()); 
					break;
					case PARAM3:
						 e.setCell(filed.ordinal(), repdata.getParam3()); 
					break;
					case TIMESPAN:
						 e.setCell(filed.ordinal(), repdata.getTimeSpan()); 
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
		Map<RepDataSearchFields, Object> criterias = getCriterias();

		Collection<RepData> moneyList = pMgr.searchRepData(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchRepDataNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
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

	private Map<RepDataSearchFields, Object> getCriterias() {
		Map<RepDataSearchFields, Object> criterias = new HashMap<RepDataSearchFields, Object>();
			if (getDataType()!=null&&!"".equals(getDataType()))
			 	criterias.put(RepDataSearchFields.DATATYPE,  getDataType());
		return criterias;
	}

	public RepData getVo() {
		return vo;
	}

	public void setVo(RepData vo) {
		this.vo = vo;
	} 
  
	private Integer sno; 
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public Integer getSno(){
 		return sno;
 	}
 	
 	/**
 	 * 设置流水号的属性值.
 	 */
 	public void setSno(Integer sno){
 		this.sno = sno;
 	}
	private Date inputDate; 
 	/**
 	 * 获取输入日期的属性值.
 	 */
 	public Date getInputDate(){
 		return inputDate;
 	}
 	
 	/**
 	 * 设置输入日期的属性值.
 	 */
 	public void setInputDate(Date inputdate){
 		this.inputDate = inputdate;
 	}
	private String dataType; 
 	/**
 	 * 获取收集方式的属性值.
 	 */
 	public String getDataType(){
 		return dataType;
 	}
 	
 	/**
 	 * 设置收集方式的属性值.
 	 */
 	public void setDataType(String datatype){
 		this.dataType = datatype;
 	}
	private int comeNum; 
 	/**
 	 * 获取进店人数的属性值.
 	 */
 	public int getComeNum(){
 		return comeNum;
 	}
 	
 	/**
 	 * 设置进店人数的属性值.
 	 */
 	public void setComeNum(int comenum){
 		this.comeNum = comenum;
 	}
	private int instrestNum; 
 	/**
 	 * 获取感兴趣人数的属性值.
 	 */
 	public int getInstrestNum(){
 		return instrestNum;
 	}
 	
 	/**
 	 * 设置感兴趣人数的属性值.
 	 */
 	public void setInstrestNum(int instrestnum){
 		this.instrestNum = instrestnum;
 	}
	private int tryNum; 
 	/**
 	 * 获取试衣人数的属性值.
 	 */
 	public int getTryNum(){
 		return tryNum;
 	}
 	
 	/**
 	 * 设置试衣人数的属性值.
 	 */
 	public void setTryNum(int trynum){
 		this.tryNum = trynum;
 	}
	private int buyNum; 
 	/**
 	 * 获取购买人数的属性值.
 	 */
 	public int getBuyNum(){
 		return buyNum;
 	}
 	
 	/**
 	 * 设置购买人数的属性值.
 	 */
 	public void setBuyNum(int buynum){
 		this.buyNum = buynum;
 	}
	private int oldNum; 
 	/**
 	 * 获取老顾客人数的属性值.
 	 */
 	public int getOldNum(){
 		return oldNum;
 	}
 	
 	/**
 	 * 设置老顾客人数的属性值.
 	 */
 	public void setOldNum(int oldnum){
 		this.oldNum = oldnum;
 	}
	private int userId; 
 	/**
 	 * 获取统计用户的属性值.
 	 */
 	public int getUserId(){
 		return userId;
 	}
 	
 	/**
 	 * 设置统计用户的属性值.
 	 */
 	public void setUserId(int userid){
 		this.userId = userid;
 	}
	private String param1; 
 	/**
 	 * 获取额外参数1的属性值.
 	 */
 	public String getParam1(){
 		return param1;
 	}
 	
 	/**
 	 * 设置额外参数1的属性值.
 	 */
 	public void setParam1(String param1){
 		this.param1 = param1;
 	}
	private String param2; 
 	/**
 	 * 获取额外参数2的属性值.
 	 */
 	public String getParam2(){
 		return param2;
 	}
 	
 	/**
 	 * 设置额外参数2的属性值.
 	 */
 	public void setParam2(String param2){
 		this.param2 = param2;
 	}
	private String param3; 
 	/**
 	 * 获取额外参数3的属性值.
 	 */
 	public String getParam3(){
 		return param3;
 	}
 	
 	/**
 	 * 设置额外参数3的属性值.
 	 */
 	public void setParam3(String param3){
 		this.param3 = param3;
 	}
	private String timeSpan; 
 	/**
 	 * 获取营业时间间隔的属性值.
 	 */
 	public String getTimeSpan(){
 		return timeSpan;
 	}
 	
 	/**
 	 * 设置营业时间间隔的属性值.
 	 */
 	public void setTimeSpan(String timespan){
 		this.timeSpan = timespan;
 	}
}
