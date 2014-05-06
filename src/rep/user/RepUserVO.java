
package rep.user;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于erp用户表的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepUserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public RepUserVO() {

	}
	
	public RepUserVO( int sno , int orderNo , String brandName , String brandType , double area , String address , double masterPrice , int workNum , int weekendNum , String phone ) {
		 this.sno = sno;
		 this.orderNo = orderNo;
		 this.brandName = brandName;
		 this.brandType = brandType;
		 this.area = area;
		 this.address = address;
		 this.masterPrice = masterPrice;
		 this.workNum = workNum;
		 this.weekendNum = weekendNum;
		 this.phone = phone;
	}
	
	public RepUserVO(int orderNo ,String brandName ,String brandType ,double area ,String address ,double masterPrice ,int workNum ,int weekendNum ,String phone ) {
			 this.orderNo = orderNo;
			 this.brandName = brandName;
			 this.brandType = brandType;
			 this.area = area;
			 this.address = address;
			 this.masterPrice = masterPrice;
			 this.workNum = workNum;
			 this.weekendNum = weekendNum;
			 this.phone = phone;
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
