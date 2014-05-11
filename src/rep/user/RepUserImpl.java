
package rep.user;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于erp用户表的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepUserImpl implements RepUser {
	private RepUserVO repuserVO = null;
	private static final long serialVersionUID = 1L;

	public RepUserImpl(RepUserVO repuserVO) {
		this.repuserVO = repuserVO;
	}

	public RepUserImpl( int sno , String userId , String brandName , String brandType , double area , String address , double masterPrice , int workNum , String workTime , int weekendNum , String phone , String password , String param1 , String lng_north , String lat_east , String location , String param2 ) {
		this.repuserVO = new RepUserVO( sno , userId , brandName , brandType , area , address , masterPrice , workNum , workTime , weekendNum , phone , password , param1 , lng_north , lat_east , location , param2 );
	} 
	
	public RepUserImpl(String userId ,String brandName ,String brandType ,double area ,String address ,double masterPrice ,int workNum ,String workTime ,int weekendNum ,String phone ,String password ,String param1 ,String lng_north ,String lat_east ,String location ,String param2 ) {
		this.repuserVO = new RepUserVO(userId ,brandName ,brandType ,area ,address ,masterPrice ,workNum ,workTime ,weekendNum ,phone ,password ,param1 ,lng_north ,lat_east ,location ,param2 );
	} 

	public RepUserVO getRepUserVO() {
		return this.repuserVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.repuserVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.repuserVO.getSno();
 	}
 	/**
 	 * 获取用户id的属性值.
 	 */
 	public  String   getUserId(){
 		return this.repuserVO.getUserId();
 	}
 	/**
 	 * 获取品牌名称的属性值.
 	 */
 	public  String   getBrandName(){
 		return this.repuserVO.getBrandName();
 	}
 	/**
 	 * 获取品类的属性值.
 	 */
 	public  String   getBrandType(){
 		return this.repuserVO.getBrandType();
 	}
 	/**
 	 * 获取营业面积的属性值.
 	 */
 	public  double   getArea(){
 		return this.repuserVO.getArea();
 	}
 	/**
 	 * 获取店铺地址的属性值.
 	 */
 	public  String   getAddress(){
 		return this.repuserVO.getAddress();
 	}
 	/**
 	 * 获取主力单价的属性值.
 	 */
 	public  double   getMasterPrice(){
 		return this.repuserVO.getMasterPrice();
 	}
 	/**
 	 * 获取人流量-工作日的属性值.
 	 */
 	public  int   getWorkNum(){
 		return this.repuserVO.getWorkNum();
 	}
 	/**
 	 * 获取营业时间的属性值.
 	 */
 	public  String   getWorkTime(){
 		return this.repuserVO.getWorkTime();
 	}
 	/**
 	 * 获取人流量-周末的属性值.
 	 */
 	public  int   getWeekendNum(){
 		return this.repuserVO.getWeekendNum();
 	}
 	/**
 	 * 获取联系方式的属性值.
 	 */
 	public  String   getPhone(){
 		return this.repuserVO.getPhone();
 	}
 	/**
 	 * 获取密码的属性值.
 	 */
 	public  String   getPassword(){
 		return this.repuserVO.getPassword();
 	}
 	/**
 	 * 获取额外参数1的属性值.
 	 */
 	public  String   getParam1(){
 		return this.repuserVO.getParam1();
 	}
 	/**
 	 * 获取纬度的属性值.
 	 */
 	public  String   getLng_north(){
 		return this.repuserVO.getLng_north();
 	}
 	/**
 	 * 获取经度的属性值.
 	 */
 	public  String   getLat_east(){
 		return this.repuserVO.getLat_east();
 	}
 	/**
 	 * 获取位置的属性值.
 	 */
 	public  String   getLocation(){
 		return this.repuserVO.getLocation();
 	}
 	/**
 	 * 获取额外参数2的属性值.
 	 */
 	public  String   getParam2(){
 		return this.repuserVO.getParam2();
 	}
 
}