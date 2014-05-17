package rep.services;

import java.util.HashMap;
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

	public String getData() {
		Object[] args = new Object[] { indate, userId };
		Object[] args2 = new Object[] { indate };
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
							"select id as sno,g1 as statis1,g2 as statis2,g3 as statis3, g4 as statis4,"
									+ "g5 as statis5,g6 as statis6,problem,userId,rpi,rank from rep_stats where indate=? and userid=?",
							args);
			int allCount = jdbcTool.queryForInt(
					"select count(1) from rep_stats where indate = ?", args2);
			if (result != null && result.size() > 0) {
				Map m = (HashMap) result.get(0);
				double rank = Double.parseDouble(m.get("rank") + "");
				m.put("rank",
						CommonUtil.multiply(CommonUtil.divide(
								(CommonUtil.subtract(allCount, rank)),
								allCount, 4), 100)
								+ "%");
				m.put("inputDate", indate);
				r.setErrorCode(Result.SUCCESS);
				r.setData(result.get(0));
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
}
