package rep.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import brightmoon.util.DateUtil;

import common.MyJdbcTool;
import common.base.SpringContextUtil;

public class StatisService {

	public void work() {
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		Date d = new Date();
		// 当前统计的时间
		String inDate = DateUtil.toString(d, "yyyy-MM-dd");
		// 得到星期几.
		int dayInThisWeek = DateUtil.getDayOfWeek(d);
		int count = jdbcTool.queryForInt(
				"select count(1) from rep_stats where indate = ?",
				new Object[] { inDate });
		// 如果是星期一， 就开始计算之前的天数
		if (dayInThisWeek == dayInThisWeek && count < 1) {
			// 得到要进行统计的开始时间
			String start = DateUtil.toString(DateUtil.afterAnyDay(d, -8),
					"yyyy-MM-dd");
			// 得到要统计的结束时间.
			String end = DateUtil.toString(DateUtil.afterAnyDay(d, -1),
					"yyyy-MM-dd");
			System.out.println("准备统计的时间段是:start=" + start + ",,,,end=" + end);
			List sumData = statis(jdbcTool, start, end);
			if (sumData != null && sumData.size() > 0) {
				// 循环处理数据
				for (Object o : sumData) {
					Map m = (Map) o;
					int comeNum = Integer.parseInt(m.get("come_num") + "");
					int intreNum = Integer.parseInt(m.get("intrest_num") + "");
					int tryNum = Integer.parseInt(m.get("try_num") + "");
					int buyNum = Integer.parseInt(m.get("buy_num") + "");
					int oldNum = Integer.parseInt(m.get("old_num") + "");
					int userId = Integer.parseInt(m.get("userid") + "");
					//添加统计数据.
					addData(jdbcTool, 1000, comeNum, intreNum, tryNum, buyNum,
							oldNum, userId, inDate);
				}
			}
			
			//更新名次.
			updateRank(jdbcTool,inDate);
		} else {
			System.out.println("已经统计过当天数据了.");
		}
		System.out.println("Quartz的任务调度！！！" + new Date());
	}

	/**
	 * 计算时间间隔之类的数据之和进行统计.
	 * 
	 * @Title: statis
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jdbcTool
	 * @param @param startTime
	 * @param @param endTime
	 * @param @param calcTime
	 * @param @return
	 * @return List
	 * @throws
	 */
	private List statis(MyJdbcTool jdbcTool, String startTime, String endTime) {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT sum(a.come_num) come_num, ");
		buf.append(" sum(a.intrest_num) intrest_num, ");
		buf.append(" sum(a.try_num) try_num, ");
		buf.append(" sum(a.buy_num) buy_num, ");
		buf.append(" sum(a.old_num) old_num, ");
		buf.append(" a.userid ");
		buf.append("FROM ");
		buf.append(" (SELECT come_num, intrest_num, try_num, buy_num, old_num, userid ");
		buf.append(" FROM rep_data ");
		buf.append(" WHERE indate >=? ");
		buf.append(" AND indate<?) AS a ");
		buf.append("GROUP BY a.userid ");
		Object[] args = new Object[] { startTime, endTime };
		List ans = jdbcTool.queryForList(buf.toString(), args);
		return ans;
	}

	/**
	 * 查询店外人数.
	* @Title: getOutNum
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param jdbcTool
	* @param @param userId
	* @param @param indate
	* @param @return
	* @return int
	* @throws
	 */
	public int getOutNum(MyJdbcTool jdbcTool,   int userId,
			String indate) {
		int outNum = 0;
		return outNum;
	}
	/**
	 * 添加统计数据到统计表中.
	 * 
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jdbcTool
	 * @param @param outNum
	 * @param @param comeNum
	 * @param @param intrestNum
	 * @param @param tryNum
	 * @param @param buyNum
	 * @param @param oldNum
	 * @param @param userId
	 * @param @param indate
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String addData(MyJdbcTool jdbcTool, int outNum, int comeNum,
			int intrestNum, int tryNum, int buyNum, int oldNum, int userId,
			String indate) {
		CalcStatis statis = CalcStatis.getInstance();
		double g1 = statis.getG1(new int[] { outNum, comeNum });
		double g2 = statis.getG2(new int[] { outNum, comeNum, intrestNum });
		double g3 = statis.getG3(new int[] { outNum, intrestNum, tryNum });
		double g4 = statis.getG4(new int[] { outNum, tryNum, buyNum });
		double g5 = statis.getG5(new int[] { outNum, buyNum });
		double g6 = statis.getG6(new int[] { outNum, oldNum });
		double rpi = statis.getRpi(new double[] { g1, g2, g3, g4, g5, g6 });
		Object[] args = new Object[] { indate, g1, g2, g3, g4, g5, g6, userId,
				rpi };
		jdbcTool.updateSql("insert into rep_stats (indate , g1,"
				+ "g2, g3, g4, g5, "
				+ "g6, userid,rpi ) values(?,?,?,?,?,?,?,?,?)", args);

		return null;
	}

	public void updateRank(MyJdbcTool jdbcTool, String date) {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT id,rank ");
		buf.append("FROM ");
		buf.append(" (SELECT tmp.id, tmp.rpi, @rank := @rank + 1 AS rank ");
		buf.append(" FROM ");
		buf.append(" (SELECT id, ");
		buf.append(" rpi ");
		buf.append(" FROM rep_stats ");
		buf.append(" WHERE indate=? ");
		buf.append(" ORDER BY rpi DESC) tmp, ");
		buf.append(" (SELECT @rank := 0) a) RESULT");
		Object[] args = new Object[] { date };
		List ans = jdbcTool.queryForList(buf.toString(), args);
		if (ans != null && ans.size() > 0) {
			// 循环处理数据
			for (Object o : ans) {
				Map m = (Map) o;
				//得到名字排名..
				Object id = m.get("id");
				Object rank = m.get("rank");
				Object[] args2 = new Object[]{rank,id};
				jdbcTool.updateSql("update rep_stats set rank = ? where id=?", args2);
			}
		}
	}

}
