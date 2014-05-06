
package rep.data;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于erp数据记录的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepDataVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public RepDataVO() {

	}
	
	public RepDataVO( int sno , Date inputDate , String dataType , int comeNum , int instrestNum , int tryNum , int buyNum , int oldNum , String param1 , String param2 , String param3 ) {
		 this.sno = sno;
		 this.inputDate = inputDate;
		 this.dataType = dataType;
		 this.comeNum = comeNum;
		 this.instrestNum = instrestNum;
		 this.tryNum = tryNum;
		 this.buyNum = buyNum;
		 this.oldNum = oldNum;
		 this.param1 = param1;
		 this.param2 = param2;
		 this.param3 = param3;
	}
	
	public RepDataVO(Date inputDate ,String dataType ,int comeNum ,int instrestNum ,int tryNum ,int buyNum ,int oldNum ,String param1 ,String param2 ,String param3 ) {
			 this.inputDate = inputDate;
			 this.dataType = dataType;
			 this.comeNum = comeNum;
			 this.instrestNum = instrestNum;
			 this.tryNum = tryNum;
			 this.buyNum = buyNum;
			 this.oldNum = oldNum;
			 this.param1 = param1;
			 this.param2 = param2;
			 this.param3 = param3;
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
}
