package rep.stats;

import java.io.Serializable;
import java.util.Date;

/**
 * 关于erp统计记录的实体bean.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class RepStatsVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public RepStatsVO() {

	}

	public RepStatsVO(int sno, Date inputDate, double statis1, double statis2,
			double statis3, double statis4, double statis5, double statis6,
			int userId, double rpi, double rank, String problem, String param2,
			String param1) {
		this.sno = sno;
		this.inputDate = inputDate;
		this.statis1 = statis1;
		this.statis2 = statis2;
		this.statis3 = statis3;
		this.statis4 = statis4;
		this.statis5 = statis5;
		this.statis6 = statis6;
		this.userId = userId;
		this.rpi = rpi;
		this.rank = rank;
		this.problem = problem;
		this.param2 = param2;
		this.param1 = param1;
	}

	public RepStatsVO(Date inputDate, double statis1, double statis2,
			double statis3, double statis4, double statis5, double statis6,
			int userId, double rpi, double rank, String problem, String param2,
			String param1) {
		this.inputDate = inputDate;
		this.statis1 = statis1;
		this.statis2 = statis2;
		this.statis3 = statis3;
		this.statis4 = statis4;
		this.statis5 = statis5;
		this.statis6 = statis6;
		this.userId = userId;
		this.rpi = rpi;
		this.rank = rank;
		this.problem = problem;
		this.param2 = param2;
		this.param1 = param1;
	}

	private Integer sno;

	/**
	 * 获取流水号的属性值.
	 */
	public Integer getSno() {
		return sno;
	}

	/**
	 * 设置流水号的属性值.
	 */
	public void setSno(Integer sno) {
		this.sno = sno;
	}

	private Date inputDate;

	/**
	 * 获取输入日期的属性值.
	 */
	public Date getInputDate() {
		return inputDate;
	}

	/**
	 * 设置输入日期的属性值.
	 */
	public void setInputDate(Date inputdate) {
		this.inputDate = inputdate;
	}

	private double statis1;

	/**
	 * 获取统计数据1的属性值.
	 */
	public double getStatis1() {
		return statis1;
	}

	/**
	 * 设置统计数据1的属性值.
	 */
	public void setStatis1(double statis1) {
		this.statis1 = statis1;
	}

	private double statis2;

	/**
	 * 获取统计数据2的属性值.
	 */
	public double getStatis2() {
		return statis2;
	}

	/**
	 * 设置统计数据2的属性值.
	 */
	public void setStatis2(double statis2) {
		this.statis2 = statis2;
	}

	private double statis3;

	/**
	 * 获取统计数据3的属性值.
	 */
	public double getStatis3() {
		return statis3;
	}

	/**
	 * 设置统计数据3的属性值.
	 */
	public void setStatis3(double statis3) {
		this.statis3 = statis3;
	}

	private double statis4;

	/**
	 * 获取统计数据4的属性值.
	 */
	public double getStatis4() {
		return statis4;
	}

	/**
	 * 设置统计数据4的属性值.
	 */
	public void setStatis4(double statis4) {
		this.statis4 = statis4;
	}

	private double statis5;

	/**
	 * 获取统计数据5的属性值.
	 */
	public double getStatis5() {
		return statis5;
	}

	/**
	 * 设置统计数据5的属性值.
	 */
	public void setStatis5(double statis5) {
		this.statis5 = statis5;
	}

	private Double statis6;

	/**
	 * 获取统计数据6的属性值.
	 */
	public Double getStatis6() {
		return statis6;
	}

	/**
	 * 设置统计数据6的属性值.
	 */
	public void setStatis6(Double statis6) {
		this.statis6 = statis6;
	}

	private Integer userId;

	/**
	 * 获取统计用户的属性值.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置统计用户的属性值.
	 */
	public void setUserId(Integer userid) {
		this.userId = userid;
	}

	private Double rpi;

	/**
	 * 获取rpi数值的属性值.
	 */
	public Double getRpi() {
		return rpi;
	}

	/**
	 * 设置rpi数值的属性值.
	 */
	public void setRpi(Double rpi) {
		this.rpi = rpi;
	}

	private Double rank;

	/**
	 * 获取排名的属性值.
	 */
	public Double getRank() {
		return rank;
	}

	/**
	 * 设置排名的属性值.
	 */
	public void setRank(Double rank) {
		this.rank = rank;
	}

	private String problem;

	/**
	 * 获取问题的属性值.
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * 设置问题的属性值.
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}

	private String param2;

	/**
	 * 获取额外参数2的属性值.
	 */
	public String getParam2() {
		return param2;
	}

	/**
	 * 设置额外参数2的属性值.
	 */
	public void setParam2(String param2) {
		this.param2 = param2;
	}

	private String param1;

	/**
	 * 获取额外参数1的属性值.
	 */
	public String getParam1() {
		return param1;
	}

	/**
	 * 设置额外参数1的属性值.
	 */
	public void setParam1(String param1) {
		this.param1 = param1;
	}
}
