
package rep.pushcontent;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于推送信息表的业务类接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface PushContent extends BusinessObject {  
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno();
 	/**
 	 * 获取推送用户id的属性值.
 	 */
 	public  String   getUserIds();
 	/**
 	 * 获取推送内容的属性值.
 	 */
 	public  String   getContent();
 	/**
 	 * 获取推送时间的属性值.
 	 */
 	public  Date   getPushTime();
 	/**
 	 * 获取推送用户的属性值.
 	 */
 	public  String   getUserNames();
 	/**
 	 * 获取推送机器类型的属性值.
 	 */
 	public  String   getDeviceType();
}
