
package rep.pushcontent;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于推送信息表的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class PushContentVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PushContentVO() {

	}
	
	public PushContentVO( int sno , String userIds , String content , Date pushTime , String userNames , String deviceType ) {
		 this.sno = sno;
		 this.userIds = userIds;
		 this.content = content;
		 this.pushTime = pushTime;
		 this.userNames = userNames;
		 this.deviceType = deviceType;
	}
	
	public PushContentVO(String userIds ,String content ,Date pushTime ,String userNames ,String deviceType ) {
			 this.userIds = userIds;
			 this.content = content;
			 this.pushTime = pushTime;
			 this.userNames = userNames;
			 this.deviceType = deviceType;
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
	private String userIds; 
 	/**
 	 * 获取推送用户id的属性值.
 	 */
 	public String getUserIds(){
 		return userIds;
 	}
 	
 	/**
 	 * 设置推送用户id的属性值.
 	 */
 	public void setUserIds(String userids){
 		this.userIds = userids;
 	}
	private String content; 
 	/**
 	 * 获取推送内容的属性值.
 	 */
 	public String getContent(){
 		return content;
 	}
 	
 	/**
 	 * 设置推送内容的属性值.
 	 */
 	public void setContent(String content){
 		this.content = content;
 	}
	private Date pushTime; 
 	/**
 	 * 获取推送时间的属性值.
 	 */
 	public Date getPushTime(){
 		return pushTime;
 	}
 	
 	/**
 	 * 设置推送时间的属性值.
 	 */
 	public void setPushTime(Date pushtime){
 		this.pushTime = pushtime;
 	}
	private String userNames; 
 	/**
 	 * 获取推送用户的属性值.
 	 */
 	public String getUserNames(){
 		return userNames;
 	}
 	
 	/**
 	 * 设置推送用户的属性值.
 	 */
 	public void setUserNames(String usernames){
 		this.userNames = usernames;
 	}
	private String deviceType; 
 	/**
 	 * 获取推送机器类型的属性值.
 	 */
 	public String getDeviceType(){
 		return deviceType;
 	}
 	
 	/**
 	 * 设置推送机器类型的属性值.
 	 */
 	public void setDeviceType(String devicetype){
 		this.deviceType = devicetype;
 	}
}
