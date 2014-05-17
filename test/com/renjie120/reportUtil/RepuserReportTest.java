package com.renjie120.reportUtil;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportStrGenerate;

@SuppressWarnings("deprecation")
public class RepuserReportTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml" };
		return config;
	}

	@Test
	public void testReportByLocation() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("rep_user").groupBy("location")
				.count().colomns(new String[] { "location" }).build()
				.generateSql();
		System.out.println("查询sql:" + sql);
		String str = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + " ]";
			}
		});
		System.out.println(str);
	}

	@Test
	public void testReportByYearAndMonth() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = new MyReport.Builder("rep_user")
				.groupBy(new String[] { "year", "month" }).count()
				.colomns(new String[] { "year", "month" }).build()
				.generateSql();
		System.out.println("查询sql:" + sql);
		String str = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "','" + objs[2] + "'," + objs[0] + " ]";
			}
		});
		System.out.println(str);
	}

	@Test
	public void testReportRpiByTime() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		Object[] args = new Object[] { "6666666", "2014-1-1", "2014-12-12" };
		String sql = "select indate,rpi from rep_stats where userid=? and indate >? and indate<?";
		System.out.println("查询sql:" + sql);
		String str = util.getReportStr(sql,args, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + " ]";
			}
		});
		System.out.println(str);
	}
	
	@Test
	public void testReportRpiRankByTime() {
		ReportDaoUtil util = (ReportDaoUtil) applicationContext
				.getBean("reportUtil");
		String sql = "select userid,rpi,rank from rep_stats  where indate='2014-5-14' order by rpi desc";
		System.out.println("查询sql:" + sql);
		String str = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + ","+objs[2]+" ]";
			}
		});
		System.out.println(str);
	}
}
