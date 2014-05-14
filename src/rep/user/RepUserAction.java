
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
			RepUserImpl repuserImpl = new RepUserImpl(userId ,brandName ,brandType ,area ,address ,masterPrice ,workNum ,workTime ,workTimeNum ,weekendNum ,phone ,password ,param1 ,lng_north ,inDate ,lat_east ,location ,param2 );
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
			RepUserImpl repuserImpl = new RepUserImpl( sno , userId , brandName , brandType , area , address , masterPrice , workNum , workTime , workTimeNum , weekendNum , phone , password , param1 , lng_north , inDate , lat_east , location , param2 );
			pMgr.updateRepUser(repuserImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号"),  USERID("用户id"),  BRANDNAME("品牌名称"),  BRANDTYPE("品类"),  AREA("营业面积"),  ADDRESS("店铺地址"),  MASTERPRICE("主力单价"),  WORKNUM("人流量-工作日"),  WORKTIME("营业时间"),  WORKTIMENUM("营业时间"),  WEEKENDNUM("人流量-周末"),  PHONE("联系方式"),  PASSWORD("密码"),  PARAM1("额外参数1"),  LNG_NORTH("纬度"),  INDATE("注册时间"),  LAT_EAST("经度"),  LOCATION("位置"),  PARAM2("额外参数2");
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
					case USERID:
						 e.setCell(filed.ordinal(), repuser.getUserId()); 
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
					case WORKTIME:
						 e.setCell(filed.ordinal(), repuser.getWorkTime()); 
					break;
					case WORKTIMENUM:
						 e.setCell(filed.ordinal(), repuser.getWorkTimeNum()); 
					break;
					case WEEKENDNUM:
						 e.setCell(filed.ordinal(), repuser.getWeekendNum()); 
					break;
					case PHONE:
						 e.setCell(filed.ordinal(), repuser.getPhone()); 
					break;
					case PASSWORD:
						 e.setCell(filed.ordinal(), repuser.getPassword()); 
					break;
					case PARAM1:
						 e.setCell(filed.ordinal(), repuser.getParam1()); 
					break;
					case LNG_NORTH:
						 e.setCell(filed.ordinal(), repuser.getLng_north()); 
					break;
					case INDATE:
						 e.setCell(filed.ordinal(), repuser.getInDate()); 
					break;
					case LAT_EAST:
						 e.setCell(filed.ordinal(), repuser.getLat_east()); 
					break;
					case LOCATION:
						 e.setCell(filed.ordinal(), repuser.getLocation()); 
					break;
					case PARAM2:
						 e.setCell(filed.ordinal(), repuser.getParam2()); 
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
	private String userId; 
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public String getUserId(){
 		return userId;
 	}
 	
 	/**
 	 * 设置用户id的属性值.
 	 */
 	public void setUserId(String userid){
 		this.userId = userid;
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
	private String workTime; 
 	/**
 	 * 获取营业时间的属性值.
 	 */
 	public String getWorkTime(){
 		return workTime;
 	}
 	
 	/**
 	 * 设置营业时间的属性值.
 	 */
 	public void setWorkTime(String worktime){
 		this.workTime = worktime;
 	}
	private String workTimeNum; 
 	/**
 	 * 获取营业时间的属性值.
 	 */
 	public String getWorkTimeNum(){
 		return workTimeNum;
 	}
 	
 	/**
 	 * 设置营业时间的属性值.
 	 */
 	public void setWorkTimeNum(String worktimenum){
 		this.workTimeNum = worktimenum;
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
	private String password; 
 	/**
 	 * 获取密码的属性值.
 	 */
 	public String getPassword(){
 		return password;
 	}
 	
 	/**
 	 * 设置密码的属性值.
 	 */
 	public void setPassword(String password){
 		this.password = password;
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
	private String lng_north; 
 	/**
 	 * 获取纬度的属性值.
 	 */
 	public String getLng_north(){
 		return lng_north;
 	}
 	
 	/**
 	 * 设置纬度的属性值.
 	 */
 	public void setLng_north(String lng_north){
 		this.lng_north = lng_north;
 	}
	private Date inDate; 
 	/**
 	 * 获取注册时间的属性值.
 	 */
 	public Date getInDate(){
 		return inDate;
 	}
 	
 	/**
 	 * 设置注册时间的属性值.
 	 */
 	public void setInDate(Date indate){
 		this.inDate = indate;
 	}
	private String lat_east; 
 	/**
 	 * 获取经度的属性值.
 	 */
 	public String getLat_east(){
 		return lat_east;
 	}
 	
 	/**
 	 * 设置经度的属性值.
 	 */
 	public void setLat_east(String lat_east){
 		this.lat_east = lat_east;
 	}
	private String location; 
 	/**
 	 * 获取位置的属性值.
 	 */
 	public String getLocation(){
 		return location;
 	}
 	
 	/**
 	 * 设置位置的属性值.
 	 */
 	public void setLocation(String location){
 		this.location = location;
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
}
