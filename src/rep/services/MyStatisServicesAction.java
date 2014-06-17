package rep.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rep.jpush.Result;

import com.alibaba.fastjson.JSON;
import common.MyJdbcTool;
import common.base.SpringContextUtil;
import common.util.CommonUtil;

import dwz.present.BaseAction;

/**
 * 直接执行sql的一个Action.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class MyStatisServicesAction extends BaseAction {
	// 日期
	private String indate;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	// 用户id
	private String userId;

	/**
	 * 返回统计了时间的日期
	 * 
	 * @Title: getStatisDates
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getStatisDates() {
		Object[] args = new Object[] { userId };
		Result<Object> r = new Result<Object>();
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		String tk = MyUserServicesAction.geneateToken(userId);
		// 验证失败.
		if (!tk.equals(token)) {
			r.setErrorCode(Result.VALID_WRONG);
			r.setErrorMessage("url验证失败，请传入正确的token");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		List<String> s = new ArrayList<String>();
		try {
			List result = jdbcTool.queryForList(
					"select distinct indate from rep_stats where userid=?",
					args);
			if (result != null && result.size() > 0) {
				r.setErrorCode(Result.SUCCESS);
				for (Object o : result) {
					LinkedHashMap m = (LinkedHashMap) o;
					s.add(m.get("indate") + "");
				}
				r.setData(s);
				r.setCount(1);
				writeToPage(response, JSON.toJSONString(r));
			} else {
				r.setErrorCode(Result.NO_USER);
				r.setErrorMessage("没有找到合适的数据");
				r.setCount(0);
				writeToPage(response, JSON.toJSONString(r));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			r.setErrorCode(Result.SREVER_ERROR);
			r.setErrorMessage("出现异常.");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
	}

	/**
	 * 查询某一个人的某一天的统计数据.
	 * 
	 * @Title: getResult
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param _date
	 * @param @param _user
	 * @param @return
	 * @return Result<Object>
	 * @throws
	 */
	private Result<Object> getResult(String _date, String _user) {
		Object[] args = new Object[] { _date, _user };
		Object[] args2 = new Object[] { _date };
		Result<Object> r = null;
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		// 查询统计数据.
		List result = jdbcTool
				.queryForList(
						"select id as sno,g1 as statis1,g2 as statis2,g3 as statis3, g4 as statis4,"
								+ "g5 as statis5,g6 as statis6,problem,userId,rpi,rank from rep_stats where indate=? and userid=?",
						args);
		// 计算排名
		int allCount = jdbcTool.queryForInt(
				"select count(1) from rep_stats where indate = ?", args2);
		if (result != null && result.size() > 0) {
			r = new Result<Object>();
			Map m = (HashMap) result.get(0);
			double rank = Double.parseDouble(m.get("rank") + "");
			m.put("rank",
					CommonUtil.multiply(
							CommonUtil.divide(
									(CommonUtil.subtract(allCount, rank)),
									allCount, 4), 100)
							+ "%");
			m.put("inputDate", indate);
			r.setErrorCode(Result.SUCCESS);
			r.setData(result.get(0));
			r.setCount(1);
		}
		return r;
	}

	/**
	 * 手动触发计算统计数据！
	 * 
	 * @Title: goStatis
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String goStatis() {// 验证失败.
		String tk = MyUserServicesAction.geneateToken(userId);
		Result<Object> r = new Result<Object>();
		if (!tk.equals(token)) {
			r.setErrorCode(Result.VALID_WRONG);
			r.setErrorMessage("url验证失败，请传入正确的token");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		new Thread(new StatisUtil()).start();
		 
		return null;
	}

	/**
	 * 返回具体某一天的统计数据.
	 * 
	 * @Title: getData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getData() {
		String tk = MyUserServicesAction.geneateToken(userId);
		Result<Object> r = new Result<Object>();
		// 验证失败.
		if (!tk.equals(token)) {
			r.setErrorCode(Result.VALID_WRONG);
			r.setErrorMessage("url验证失败，请传入正确的token");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		try {
			r = getResult(indate, userId);
			if (r != null)
				writeToPage(response, JSON.toJSONString(r));
			else {
				r.setErrorCode(Result.NO_USER);
				r.setErrorMessage("没有找到合适的数据");
				r.setCount(0);
				writeToPage(response, JSON.toJSONString(r));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			r.setErrorCode(Result.SREVER_ERROR);
			r.setErrorMessage("出现异常.");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
	}

	/**
	 * 返回最近一条的统计数据
	 * 
	 * @Title: getLastData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getLastData() {
		Object[] args = new Object[] { userId };
		Result<Object> r = new Result<Object>();
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		String tk = MyUserServicesAction.geneateToken(userId);
		// 验证失败.
		if (!tk.equals(token)) {
			r.setErrorCode(Result.VALID_WRONG);
			r.setErrorMessage("url验证失败，请传入正确的token");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		try {
			List result = jdbcTool
					.queryForList(
							"select indate from rep_stats where userid = ? order by indate desc limit 1",
							args);
			if (result != null && result.size() > 0) {
				Map m = (HashMap) result.get(0);
				String _indate = m.get("indate") + "";
				System.out.println("最新的日期是：" + _indate);
				r = getResult(_indate, userId);
				if (r != null) {
					writeToPage(response, JSON.toJSONString(r));
					return null;
				}
			}
			r.setErrorCode(Result.NO_USER);
			r.setErrorMessage("没有找到合适的数据");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			r.setErrorCode(Result.SREVER_ERROR);
			r.setErrorMessage("出现异常.");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
	}
}
