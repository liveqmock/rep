
package rep.user;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于erp用户表的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface RepUser extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public  int   getOrderNo();
 	/**
 	 * 获取品牌名称的属性值.
 	 */
 	public  String   getBrandName();
 	/**
 	 * 获取品类的属性值.
 	 */
 	public  String   getBrandType();
 	/**
 	 * 获取营业面积的属性值.
 	 */
 	public  double   getArea();
 	/**
 	 * 获取店铺地址的属性值.
 	 */
 	public  String   getAddress();
 	/**
 	 * 获取主力单价的属性值.
 	 */
 	public  double   getMasterPrice();
 	/**
 	 * 获取人流量-工作日的属性值.
 	 */
 	public  int   getWorkNum();
 	/**
 	 * 获取人流量-周末的属性值.
 	 */
 	public  int   getWeekendNum();
 	/**
 	 * 获取联系方式的属性值.
 	 */
 	public  String   getPhone();
}
