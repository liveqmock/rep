
package rep.data;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于erp数据记录的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface RepData extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取输入日期的属性值.
 	 */
 	public  Date   getInputDate();
 	/**
 	 * 获取收集方式的属性值.
 	 */
 	public  String   getDataType();
 	/**
 	 * 获取进店人数的属性值.
 	 */
 	public  int   getComeNum();
 	/**
 	 * 获取感兴趣人数的属性值.
 	 */
 	public  int   getInstrestNum();
 	/**
 	 * 获取试衣人数的属性值.
 	 */
 	public  int   getTryNum();
 	/**
 	 * 获取购买人数的属性值.
 	 */
 	public  int   getBuyNum();
 	/**
 	 * 获取老顾客人数的属性值.
 	 */
 	public  int   getOldNum();
 	/**
 	 * 获取统计用户的属性值.
 	 */
 	public  int   getUserId();
 	/**
 	 * 获取额外参数1的属性值.
 	 */
 	public  String   getParam1();
 	/**
 	 * 获取额外参数2的属性值.
 	 */
 	public  String   getParam2();
 	/**
 	 * 获取额外参数3的属性值.
 	 */
 	public  String   getParam3();
}
