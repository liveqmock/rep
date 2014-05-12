
package rep.pushcontent;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于推送信息表的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class PushContentImpl implements PushContent {
	private PushContentVO pushcontentVO = null;
	private static final long serialVersionUID = 1L;

	public PushContentImpl(PushContentVO pushcontentVO) {
		this.pushcontentVO = pushcontentVO;
	}

	public PushContentImpl( int sno , String userIds , String content , Date pushTime , String userNames , String deviceType ) {
		this.pushcontentVO = new PushContentVO( sno , userIds , content , pushTime , userNames , deviceType );
	} 
	
	public PushContentImpl(String userIds ,String content ,Date pushTime ,String userNames ,String deviceType ) {
		this.pushcontentVO = new PushContentVO(userIds ,content ,pushTime ,userNames ,deviceType );
	} 

	public PushContentVO getPushContentVO() {
		return this.pushcontentVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.pushcontentVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.pushcontentVO.getSno();
 	}
 	/**
 	 * 获取推送用户id的属性值.
 	 */
 	public  String   getUserIds(){
 		return this.pushcontentVO.getUserIds();
 	}
 	/**
 	 * 获取推送内容的属性值.
 	 */
 	public  String   getContent(){
 		return this.pushcontentVO.getContent();
 	}
 	/**
 	 * 获取推送时间的属性值.
 	 */
 	public  Date   getPushTime(){
 		return this.pushcontentVO.getPushTime();
 	}
 	/**
 	 * 获取推送用户的属性值.
 	 */
 	public  String   getUserNames(){
 		return this.pushcontentVO.getUserNames();
 	}
 	/**
 	 * 获取推送机器类型的属性值.
 	 */
 	public  String   getDeviceType(){
 		return this.pushcontentVO.getDeviceType();
 	}
 
}