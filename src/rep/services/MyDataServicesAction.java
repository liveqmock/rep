package rep.services;

import rep.jpush.Result;

import com.alibaba.fastjson.JSON;
import common.MyJdbcTool;
import common.base.SpringContextUtil;

import dwz.present.BaseAction;

/**
 * 直接执行sql的一个Action.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class MyDataServicesAction extends BaseAction {
	// 日期
	private String indate;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	// 数据收集方式
	private String dataType;

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getComeNum() {
		return comeNum;
	}

	public void setComeNum(int comeNum) {
		this.comeNum = comeNum;
	}

	public int getIntrestNum() {
		return intrestNum;
	}

	public void setIntrestNum(int intrestNum) {
		this.intrestNum = intrestNum;
	}

	public int getTryNum() {
		return tryNum;
	}

	public void setTryNum(int tryNum) {
		this.tryNum = tryNum;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public int getOldNum() {
		return oldNum;
	}

	public void setOldNum(int oldNum) {
		this.oldNum = oldNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	// 进店人数
	private int comeNum;
	// 感兴趣人数
	private int intrestNum;
	// 试衣人数
	private int tryNum;
	// 购买人数
	private int buyNum;
	// 回头客
	private int oldNum;
	// 时间段
	private String timeSpan;

	public String getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(String timeSpan) {
		this.timeSpan = timeSpan;
	}

	// 用户id
	private String userId;

	/**
	 * 添加数据.
	 * 
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String addData() {
		Object[] args = new Object[] { indate, dataType, comeNum, intrestNum,
				tryNum, buyNum, oldNum, userId, timeSpan };
		Result<String> r = new Result<String>();
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		String tk = MyUserServicesAction.geneateToken(userId);
		System.out.println("tk=" + tk);
		// 验证失败.
		if (!tk.equals(token)) {
			r.setErrorCode(Result.VALID_WRONG);
			r.setErrorMessage("url验证失败，请传入正确的token");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		try {
			jdbcTool.updateSql("insert into rep_data (indate , datatype,"
					+ "come_num, intrest_num, try_num, buy_num, "
					+ "old_num, userid,timespan ) values(?,?,?,?,?,?,?,?,?)",
					args);
			r.setErrorCode(Result.SUCCESS);
			r.setErrorMessage("添加成功");
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
