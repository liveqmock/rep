
package rep.user;

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
 * 关于erp用户表的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepUserAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	RepUserManager pMgr = bf.getManager(BeanManagerKey.repuserManager);
	//业务实体对象
	private RepUser vo;
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
			RepUserImpl repuserImpl = new RepUserImpl(orderNo ,brandName ,brandType ,area ,address ,masterPrice ,workNum ,weekendNum ,phone );
			pMgr.createRepUser(repuserImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeRepUsers(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getRepUser(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			RepUserImpl repuserImpl = new RepUserImpl( sno , orderNo , brandName , brandType , area , address , masterPrice , workNum , weekendNum , phone );
			pMgr.updateRepUser(repuserImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号"),  ORDERNO("用户id"),  BRANDNAME("品牌名称"),  BRANDTYPE("品类"),  AREA("营业面积"),  ADDRESS("店铺地址"),  MASTERPRICE("主力单价"),  WORKNUM("人流量-工作日"),  WEEKENDNUM("人流量-周末"),  PHONE("联系方式");
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
		response.addHeader("Content-Disposition","attachment;filename=RepUserList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<RepUserSearchFields, Object> criterias = getCriterias();

		Collection<RepUser> repuserList = pMgr.searchRepUser(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (RepUser repuser : repuserList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), repuser.getSno()); 
					break;
					case ORDERNO:
						 e.setCell(filed.ordinal(), repuser.getOrderNo()); 
					break;
					case BRANDNAME:
						 e.setCell(filed.ordinal(), repuser.getBrandName()); 
					break;
					case BRANDTYPE:
						 e.setCell(filed.ordinal(), repuser.getBrandType()); 
					break;
					case AREA:
						 e.setCell(filed.ordinal(), repuser.getArea()); 
					break;
					case ADDRESS:
						 e.setCell(filed.ordinal(), repuser.getAddress()); 
					break;
					case MASTERPRICE:
						 e.setCell(filed.ordinal(), repuser.getMasterPrice()); 
					break;
					case WORKNUM:
						 e.setCell(filed.ordinal(), repuser.getWorkNum()); 
					break;
					case WEEKENDNUM:
						 e.setCell(filed.ordinal(), repuser.getWeekendNum()); 
					break;
					case PHONE:
						 e.setCell(filed.ordinal(), repuser.getPhone()); 
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
		Map<RepUserSearchFields, Object> criterias = getCriterias();

		Collection<RepUser> moneyList = pMgr.searchRepUser(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchRepUserNum(criterias);
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

	private Map<RepUserSearchFields, Object> getCriterias() {
		Map<RepUserSearchFields, Object> criterias = new HashMap<RepUserSearchFields, Object>();
			if (getBrandName()!=null&&!"".equals(getBrandName()))
				criterias.put(RepUserSearchFields.BRANDNAME, "%"+getBrandName()+"%"); 
			if (getAddress()!=null&&!"".equals(getAddress()))
				criterias.put(RepUserSearchFields.ADDRESS, "%"+getAddress()+"%"); 
			if (getPhone()!=null&&!"".equals(getPhone()))
			 	criterias.put(RepUserSearchFields.PHONE,  getPhone());
		return criterias;
	}

	public RepUser getVo() {
		return vo;
	}

	public void setVo(RepUser vo) {
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
	private int orderNo; 
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public int getOrderNo(){
 		return orderNo;
 	}
 	
 	/**
 	 * 设置用户id的属性值.
 	 */
 	public void setOrderNo(int orderno){
 		this.orderNo = orderno;
 	}
	private String brandName; 
 	/**
 	 * 获取品牌名称的属性值.
 	 */
 	public String getBrandName(){
 		return brandName;
 	}
 	
 	/**
 	 * 设置品牌名称的属性值.
 	 */
 	public void setBrandName(String brandname){
 		this.brandName = brandname;
 	}
	private String brandType; 
 	/**
 	 * 获取品类的属性值.
 	 */
 	public String getBrandType(){
 		return brandType;
 	}
 	
 	/**
 	 * 设置品类的属性值.
 	 */
 	public void setBrandType(String brandtype){
 		this.brandType = brandtype;
 	}
	private double area; 
 	/**
 	 * 获取营业面积的属性值.
 	 */
 	public double getArea(){
 		return area;
 	}
 	
 	/**
 	 * 设置营业面积的属性值.
 	 */
 	public void setArea(double area){
 		this.area = area;
 	}
	private String address; 
 	/**
 	 * 获取店铺地址的属性值.
 	 */
 	public String getAddress(){
 		return address;
 	}
 	
 	/**
 	 * 设置店铺地址的属性值.
 	 */
 	public void setAddress(String address){
 		this.address = address;
 	}
	private double masterPrice; 
 	/**
 	 * 获取主力单价的属性值.
 	 */
 	public double getMasterPrice(){
 		return masterPrice;
 	}
 	
 	/**
 	 * 设置主力单价的属性值.
 	 */
 	public void setMasterPrice(double masterprice){
 		this.masterPrice = masterprice;
 	}
	private int workNum; 
 	/**
 	 * 获取人流量-工作日的属性值.
 	 */
 	public int getWorkNum(){
 		return workNum;
 	}
 	
 	/**
 	 * 设置人流量-工作日的属性值.
 	 */
 	public void setWorkNum(int worknum){
 		this.workNum = worknum;
 	}
	private int weekendNum; 
 	/**
 	 * 获取人流量-周末的属性值.
 	 */
 	public int getWeekendNum(){
 		return weekendNum;
 	}
 	
 	/**
 	 * 设置人流量-周末的属性值.
 	 */
 	public void setWeekendNum(int weekendnum){
 		this.weekendNum = weekendnum;
 	}
	private String phone; 
 	/**
 	 * 获取联系方式的属性值.
 	 */
 	public String getPhone(){
 		return phone;
 	}
 	
 	/**
 	 * 设置联系方式的属性值.
 	 */
 	public void setPhone(String phone){
 		this.phone = phone;
 	}
}
