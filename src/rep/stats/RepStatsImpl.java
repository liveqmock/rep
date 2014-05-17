
package rep.stats;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于erp统计记录的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RepStatsImpl implements RepStats {
	private RepStatsVO repstatsVO = null;
	private static final long serialVersionUID = 1L;

	public RepStatsImpl(RepStatsVO repstatsVO) {
		this.repstatsVO = repstatsVO;
	}

	public RepStatsImpl( int sno , Date inputDate , double statis1 , double statis2 , double statis3 , double statis4 , double statis5 , double statis6 , int userId , double rpi , double rank , String problem , String param2 , String param1 ) {
		this.repstatsVO = new RepStatsVO( sno , inputDate , statis1 , statis2 , statis3 , statis4 , statis5 , statis6 , userId , rpi , rank , problem , param2 , param1 );
	} 
	
	public RepStatsImpl(Date inputDate ,double statis1 ,double statis2 ,double statis3 ,double statis4 ,double statis5 ,double statis6 ,int userId ,double rpi ,double rank ,String problem ,String param2 ,String param1 ) {
		this.repstatsVO = new RepStatsVO(inputDate ,statis1 ,statis2 ,statis3 ,statis4 ,statis5 ,statis6 ,userId ,rpi ,rank ,problem ,param2 ,param1 );
	} 

	public RepStatsVO getRepStatsVO() {
		return this.repstatsVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.repstatsVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.repstatsVO.getSno();
 	}
 	/**
 	 * 获取输入日期的属性值.
 	 */
 	public  Date   getInputDate(){
 		return this.repstatsVO.getInputDate();
 	}
 	/**
 	 * 获取统计数据1的属性值.
 	 */
 	public  double   getStatis1(){
 		return this.repstatsVO.getStatis1();
 	}
 	/**
 	 * 获取统计数据2的属性值.
 	 */
 	public  double   getStatis2(){
 		return this.repstatsVO.getStatis2();
 	}
 	/**
 	 * 获取统计数据3的属性值.
 	 */
 	public  double   getStatis3(){
 		return this.repstatsVO.getStatis3();
 	}
 	/**
 	 * 获取统计数据4的属性值.
 	 */
 	public  double   getStatis4(){
 		return this.repstatsVO.getStatis4();
 	}
 	/**
 	 * 获取统计数据5的属性值.
 	 */
 	public  double   getStatis5(){
 		return this.repstatsVO.getStatis5();
 	}
 	/**
 	 * 获取统计数据6的属性值.
 	 */
 	public  double   getStatis6(){
 		return this.repstatsVO.getStatis6();
 	}
 	/**
 	 * 获取统计用户的属性值.
 	 */
 	public  int   getUserId(){
 		return this.repstatsVO.getUserId();
 	}
 	/**
 	 * 获取rpi数值的属性值.
 	 */
 	public  double   getRpi(){
 		return this.repstatsVO.getRpi();
 	}
 	/**
 	 * 获取排名的属性值.
 	 */
 	public  double   getRank(){
 		return this.repstatsVO.getRank();
 	}
 	/**
 	 * 获取问题的属性值.
 	 */
 	public  String   getProblem(){
 		return this.repstatsVO.getProblem();
 	}
 	/**
 	 * 获取额外参数2的属性值.
 	 */
 	public  String   getParam2(){
 		return this.repstatsVO.getParam2();
 	}
 	/**
 	 * 获取额外参数1的属性值.
 	 */
 	public  String   getParam1(){
 		return this.repstatsVO.getParam1();
 	}
 
}