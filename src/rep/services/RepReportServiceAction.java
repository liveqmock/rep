package rep.services;

import common.base.SpringContextUtil;
import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportStrGenerate;

import dwz.present.BaseAction;

/**
 * repuser报表.
 * 
 * @ClassName: RepReportServiceAction
 * @Description: TODO
 * @date 2014-5-15 下午04:35:55
 * 
 */
public class RepReportServiceAction extends BaseAction {
	/**
	 * 根据区域显示报表.
	 * 
	 * @Title: reportUserByLocation
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String reportUserByLocation() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("rep_user").groupBy("location")
				.count().colomns(new String[] { "location" }).build()
				.generateSql();
		String str = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + " ]";
			}
		});
		str="[['上海',211 ],['北京',323 ],['天津',41 ],['四川',323 ],['湖北',42 ],['湖南',33 ],['江苏',34 ],['广东',213 ],['广西',234 ]]";
		writeToPage(response, str);
		return null;
	}

	/**
	 * 显示年份月份的用户报表.
	 * 
	 * @Title: reportUserByYearAndMonth
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String reportUserByYearAndMonth() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("rep_user")
				.groupBy(new String[] { "year", "month" }).count()
				.colomns(new String[] { "year", "month" }).build()
				.generateSql();
		String str = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "','" + objs[2] + "'," + objs[0] + " ]";
			}
		});
		str="[['2013','1',100 ],['2013','2',231 ],['2013','3',134 ],['2013','4',112 ],['2013','5',231 ],['2013','6',131 ],['2013','7',311 ],['2013','8',213 ],['2013','9',71 ],['2013','10',512 ],['2013','11',123 ],['2013','12',100 ],['2014','1',123 ],['2014','2',12 ],['2014','3',212 ],['2014','4',43 ],['2014','5',131 ],['2014','6',133 ],['2014','7',121 ],['2014','8',177 ],['2014','9',211 ],['2014','10',81 ],['2014','11',311 ],['2014','12',271 ]]";
		writeToPage(response, str);
		return null;
	}

	private String userId;
	private String inDate;

	public String getInDateBig() {
		return inDateBig;
	}

	public void setInDateBig(String inDateBig) {
		this.inDateBig = inDateBig;
	}

	public String getInDateSmall() {
		return inDateSmall;
	}

	public void setInDateSmall(String inDateSmall) {
		this.inDateSmall = inDateSmall;
	}

	private String inDateBig;
	private String inDateSmall;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	/**
	 * 根据时间显示rpi视图报表.
	 * 
	 * @Title: reportRpiByTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String reportRpiByTime() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String[] userids = userId.split(",");

		Object args[] = new Object[userids.length + 2];
		int i = 0;
		StringBuffer buf = new StringBuffer();
		for (String s : userids) {
			args[i++] = s;
			buf.append("?,");
		}
		buf = buf.deleteCharAt(buf.lastIndexOf(","));
		args[userids.length] = inDateSmall;
		args[userids.length + 1] = inDateBig;
		// { userId, inDateSmall, inDateBig };

		String sql = "select indate,rpi,userid from rep_stats where userid in(" + buf
				+ ") " + "and indate >=? and indate<?";
		String str = util.getReportStr(sql, args, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[2] + "','" + objs[0] +"','"+objs[1]+ "' ]";
			}
		});
		str="[['阿迪达斯','2014-05-06','34.0' ],['阿迪达斯','2014-05-14','32.9' ],['阿迪达斯','2014-05-20','23.0' ],['阿迪达斯','2014-05-27','38.9' ],['阿迪达斯','2014-04-30','64.0' ],['耐克','2014-05-06','23.0' ],['耐克','2014-05-14','32.9' ],['耐克','2014-05-20','45.0' ],['耐克','2014-05-27','21.9' ],['耐克','2014-04-30','88.0' ] ]";
	writeToPage(response, str);
		return null;
	}

	/**
	 * 显示指定时间段的rpi排名.
	 * 
	 * @Title: reportRpiRankByTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String reportRpiRankByTime() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		Object[] args = new Object[] { inDate };
	String sql = "select u.brandname,s.rpi,s.rank from rep_stats   s,rep_user  u  where s.userid=u.id and s.indate=?   order by s.rpi desc";

		String str = util.getReportStr(sql, args, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {

				return "['" + objs[1] + "','" + objs[0]+ "' ]";
			}
		});
		writeToPage(response, str);
		return null;
	}
}
