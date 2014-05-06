
package rep.data;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于erp数据记录的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepDataImpl implements RepData {
	private RepDataVO repdataVO = null;
	private static final long serialVersionUID = 1L;

	public RepDataImpl(RepDataVO repdataVO) {
		this.repdataVO = repdataVO;
	}

	public RepDataImpl( int sno , Date inputDate , String dataType , int comeNum , int instrestNum , int tryNum , int buyNum , int oldNum , String param1 , String param2 , String param3 ) {
		this.repdataVO = new RepDataVO( sno , inputDate , dataType , comeNum , instrestNum , tryNum , buyNum , oldNum , param1 , param2 , param3 );
	} 
	
	public RepDataImpl(Date inputDate ,String dataType ,int comeNum ,int instrestNum ,int tryNum ,int buyNum ,int oldNum ,String param1 ,String param2 ,String param3 ) {
		this.repdataVO = new RepDataVO(inputDate ,dataType ,comeNum ,instrestNum ,tryNum ,buyNum ,oldNum ,param1 ,param2 ,param3 );
	} 

	public RepDataVO getRepDataVO() {
		return this.repdataVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.repdataVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.repdataVO.getSno();
 	}
 	/**
 	 * 获取输入日期的属性值.
 	 */
 	public  Date   getInputDate(){
 		return this.repdataVO.getInputDate();
 	}
 	/**
 	 * 获取收集方式的属性值.
 	 */
 	public  String   getDataType(){
 		return this.repdataVO.getDataType();
 	}
 	/**
 	 * 获取进店人数的属性值.
 	 */
 	public  int   getComeNum(){
 		return this.repdataVO.getComeNum();
 	}
 	/**
 	 * 获取感兴趣人数的属性值.
 	 */
 	public  int   getInstrestNum(){
 		return this.repdataVO.getInstrestNum();
 	}
 	/**
 	 * 获取试衣人数的属性值.
 	 */
 	public  int   getTryNum(){
 		return this.repdataVO.getTryNum();
 	}
 	/**
 	 * 获取购买人数的属性值.
 	 */
 	public  int   getBuyNum(){
 		return this.repdataVO.getBuyNum();
 	}
 	/**
 	 * 获取老顾客人数的属性值.
 	 */
 	public  int   getOldNum(){
 		return this.repdataVO.getOldNum();
 	}
 	/**
 	 * 获取额外参数1的属性值.
 	 */
 	public  String   getParam1(){
 		return this.repdataVO.getParam1();
 	}
 	/**
 	 * 获取额外参数2的属性值.
 	 */
 	public  String   getParam2(){
 		return this.repdataVO.getParam2();
 	}
 	/**
 	 * 获取额外参数3的属性值.
 	 */
 	public  String   getParam3(){
 		return this.repdataVO.getParam3();
 	}
 
}