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
		String sql = "select userid,rpi,rank from rep_stats  where indate=? order by rpi desc";

		String str = util.getReportStr(sql, args, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0]+ " ]";
			}
		});
		writeToPage(response, str);
		return null;
	}
}
