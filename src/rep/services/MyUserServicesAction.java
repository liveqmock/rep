package rep.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import rep.jpush.Result;

import brightmoon.util.DateUtil;

import com.alibaba.fastjson.JSON;
import common.MyJdbcTool;
import common.base.SpringContextUtil;
import common.util.Coder;

import dwz.present.BaseAction;

/**
 * 直接执行sql的一个Action.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class MyUserServicesAction extends BaseAction {
	/* 修改密码,登陆,忘记密码 */
	private String userId;
	private String password;
	private String oldPassword;
	// 短信验证码.
	private String validCode;

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	/* 注册信息. */
	private double masterPrice;
	private int workNum;
	private int weekendNum;
	private String brandName, brandType, phone, param1, lng_north, lat_east,
			location, worktime = null;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public double getMasterPrice() {
		return masterPrice;
	}

	public void setMasterPrice(double masterPrice) {
		this.masterPrice = masterPrice;
	}

	public int getWorkNum() {
		return workNum;
	}

	public void setWorkNum(int workNum) {
		this.workNum = workNum;
	}

	public int getWeekendNum() {
		return weekendNum;
	}

	public void setWeekendNum(int weekendNum) {
		this.weekendNum = weekendNum;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getLng_north() {
		return lng_north;
	}

	public void setLng_north(String lng_north) {
		this.lng_north = lng_north;
	}

	public String getLat_east() {
		return lat_east;
	}

	public void setLat_east(String lat_east) {
		this.lat_east = lat_east;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	/**
	 * 返回有效的验证码.
	 * 
	 * @param userId
	 * @return
	 */
	private List getValidCode(MyJdbcTool jdbcTool, String userId) {
		long now = System.currentTimeMillis();
		// 得到30分钟以前的时间.
		Date before = new Date(now - 1000 * 60 * 30);
		Object[] args = new Object[] { userId, before };
		// 查询没有失效的验证码
		List ans = jdbcTool
				.queryForList(
						"select validcode from shortmessage_info where mobile=? and createtime>?  and completed!=1 order by createtime desc limit 1",
						args);
		return ans;
	}

	/**
	 * 生成一个验证码并且发送到手机. 1、使用随机函数生成一个6位数字，保存到短信表中
	 * 2、先查询短信表中，有没有未使用过的验证码，并且在半小时以内,就返回旧的验证码 3、如果没有未使用的验证码就生成一个，并发送短信。
	 * 
	 * @return
	 */
	public String generateACode() {
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		long now = System.currentTimeMillis();
		Result<String> r = new Result<String>();
		if (!geneatePublicToken(phone).equals(token)) {
			r.setErrorCode(Result.VALID_WRONG);
			r.setErrorMessage("url验证失败，请传入正确的token");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}

		if (phone == null || "".equals(phone.trim()) || phone.length() != 11) {
			r.setErrorCode(Result.ARGUMENT_ERROR);
			r.setErrorMessage("请输入正确的电话号码");
			r.setCount(0);
			// 发送短信！！！
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		// 查询没有失效的验证码
		List ans = getValidCode(jdbcTool, phone);

		try { // 如果有没有失效的验证码，就直接返回.
			if (ans != null && ans.size() > 0) {
				Map m = (HashMap) ans.get(0);
				r.setErrorCode(Result.SUCCESS);
				r.setData(m.get("validcode") + "");
				r.setCount(0);
				// 发送短信！！！
				writeToPage(response, JSON.toJSONString(r));
				return null;
			} else {
				Random random = new Random();
				int rd = random.nextInt(999999);
				Date start = new Date(now);
				Object[] args2 = new Object[] { phone, rd,
						new java.sql.Timestamp(start.getTime()), "0",
						"发送给您的验证码是:" + rd };
				jdbcTool.updateSql(
						"insert into shortmessage_info(mobile,validcode,createtime,completed,content) values(?,?,?,?,?)",
						args2);
				// 发送短信！！！
				r.setErrorCode(Result.SUCCESS);
				r.setData(rd + "");
				r.setCount(0);
				// 发送短信！！！
				writeToPage(response, JSON.toJSONString(r));
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setErrorCode(Result.SREVER_ERROR);
			r.setErrorMessage("生成验证码失败出现异常");
			r.setCount(0);
			// 发送短信！！！
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
	}

	/**
	 * 注册。
	 * 
	 * @Title: regiest
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String regiest() {

		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		Date d = new Date();
		String dstr = DateUtil.toString(d, "yyyy-MM-dd");
		int yea = DateUtil.getYear(d);
		int mon = DateUtil.getMonth(d);
		int day = DateUtil.getDayOfMonth(d);
		Object[] args = new Object[] { masterPrice, brandName, brandType,
				phone, lng_north, lat_east, worktime, workNum, weekendNum,
				password, location,dstr,yea,mon,day };
		// 查询有效的验证码.
		List ans = getValidCode(jdbcTool, phone);
		int count = 0;
		Result<String> r = new Result<String>();
		if (workNum == 0 || weekendNum == 0 || isEmpty(validCode)) {
			r.setErrorCode(Result.ARGUMENT_ERROR);
			r.setErrorMessage("缺少必填参数!");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		if (!geneatePublicToken(phone).equals(token)) {
			r.setErrorCode(Result.VALID_WRONG);
			r.setErrorMessage("url验证失败，请传入正确的token");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		if (ans != null && ans.size() > 0) {
			Map m = (HashMap) ans.get(0);
			String _valid = m.get("validcode") + "";
			if (validCode == null || !validCode.equals(_valid)) {
				r.setErrorCode(Result.WRONG_TOKEN);
				r.setErrorMessage("验证码不正确");
				r.setCount(0);
				writeToPage(response, JSON.toJSONString(r));
				return null;
			}
			Object[] args2 = new Object[] { validCode, phone };

			// 生成默认密码.
			try {
				if (password == null) {
					password = Coder.toMyCoder("123456");
					args[9] = password;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 查询用户名进行验证.
			try {
				count = jdbcTool.queryForInt(
						"select count(1) from rep_user  where phone =?",
						new Object[] { phone });
				if (count > 0) {
					r.setErrorCode(Result.ARGUMENT_ERROR);
					r.setErrorMessage("用户名已经存在，注册失败");
					r.setCount(0);
					writeToPage(response, JSON.toJSONString(r));
					return null;
				} else {
					if (workNum == 0 || weekendNum == 0) {
						r.setErrorCode(Result.ARGUMENT_ERROR);
						r.setErrorMessage("工作日人数,节假日人数必填，注册失败!");
						r.setCount(0);
						writeToPage(response, JSON.toJSONString(r));
						return null;
					}
					// 更新验证码为失效
					jdbcTool.updateSql(
							"update shortmessage_info set completed=1 where validCode=? and mobile = ?",
							args2);
					// 注册用户数据信息.
					jdbcTool.updateSql(
							"insert into rep_user (price , brandName,"
									+ "brandType, phone, lng_north, lat_east, "
									+ "work_time, people_flownum_work, people_flownum_weekend,password,location,indate,year,month,day ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							args);
					r.setErrorCode(Result.SUCCESS);
					r.setErrorMessage("注册成功");
					r.setCount(0);
					writeToPage(response, JSON.toJSONString(r));
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				r.setErrorCode(Result.SREVER_ERROR);
				r.setErrorMessage("出现异常.");
				r.setCount(0);
				writeToPage(response, JSON.toJSONString(r));
				return null;
			}
		} else {
			r.setErrorCode(Result.NO_VALIDCODE);
			r.setErrorMessage("数据库中没有对应的验证码，请重新生成");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}

	}

	/**
	 * 自定义的登录成功之后返回的校验码.
	 * 
	 * @param s
	 * @return
	 */
	public static String geneateToken(String s) {
		return Coder.encryptMD5Str(Coder.toMyCoder(s));
	}

	/**
	 * 公开的校验码.
	 * 
	 * @param s
	 * @return
	 */
	public static String geneatePublicToken(String s) {
		try {
			String b = Coder.encryptBASE64(s.getBytes());
			return Coder.encryptMD5Str(b.trim());
		} catch (Exception e) {
			e.printStackTrace();
			return s;
		}
	}

	/**
	 * 登陆.
	 * 
	 * @Title: login
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String login() {
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		String[] args = new String[] { userId };
		List pass = jdbcTool.queryForList(
				"select * from rep_user where phone=? ", args);
		Result<String> r = new Result<String>();
		String _p = geneatePublicToken(userId);
		if (!_p.equals(token)) {
			r.setErrorCode(Result.VALID_WRONG);
			r.setErrorMessage("url验证失败，请传入正确的token");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		Result<Map> r2 = new Result<Map>();
		try {
			if (pass == null || pass.size() == 0) {
				r.setErrorCode(Result.NO_USER);
				r.setErrorMessage("用户名不存在!");
				r.setCount(0);
				writeToPage(response, JSON.toJSONString(r));
				return null;
			} else {
				Map m = (HashMap) pass.get(0);
				String p = m.get("password") + "";
				password = Coder.toMyCoder(password);
				if (!p.equals(password)) {
					r.setErrorCode(Result.WRONG_PASSWORD);
					r.setErrorMessage("密码不正确!");
					r.setCount(0);
					writeToPage(response, JSON.toJSONString(r));
					return null;
				} else {
					Map result = new HashMap();
					result.put("phone", m.get("phone"));
					result.put("brandName", m.get("brandname"));
					result.put("brandType", m.get("brandtype"));
					result.put("workNum", m.get("people_flownum_work"));
					result.put("weekendNum", m.get("people_flownum_weekend"));
					result.put("lng_north", m.get("lng_north"));
					result.put("lat_east", m.get("lat_east"));
					result.put("worktime", m.get("work_time"));
					result.put("token", geneateToken("" + m.get("phone")));
					r2.setData(result);
					r2.setErrorCode(Result.SUCCESS);
					r2.setErrorMessage("登陆成功!");
					r2.setCount(0);
					writeToPage(response, JSON.toJSONString(r2));
					return null;
				}
			}
		} catch (Exception e) {
			log.error(e);
			r.setErrorCode(Result.SREVER_ERROR);
			r.setErrorMessage("出现系统异常!");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
	}

	private boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 更新密码.
	 * 
	 * @Title: updatePass
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String updatePass() {
		MyJdbcTool jdbcTool = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		Result<String> r = new Result<String>();
		if (isEmpty(userId) || isEmpty(password) || isEmpty(oldPassword)) {
			r.setErrorCode(Result.ARGUMENT_ERROR);
			r.setErrorMessage("缺少必填参数!");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		if (!geneateToken(userId).equals(token)) {
			r.setErrorCode(Result.VALID_WRONG);
			r.setErrorMessage("url验证失败，请传入正确的token");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}
		String[] args = new String[] { userId };
		List pass = jdbcTool.queryForList(
				"select password from rep_user where phone=? ", args);
		try {
			if (pass == null || pass.size() == 0) {
				r.setErrorCode(Result.NO_USER);
				r.setErrorMessage("用户名不存在!");
				r.setCount(0);
				writeToPage(response, JSON.toJSONString(r));
				return null;
			} else {
				Map m = (HashMap) pass.get(0);
				String p = m.get("password") + "";
				oldPassword = Coder.toMyCoder(oldPassword);
				if (!p.equals(oldPassword)) {
					r.setErrorCode(Result.WRONG_PASSWORD);
					r.setErrorMessage("用户名原密码不正确!");
					r.setCount(0);
					writeToPage(response, JSON.toJSONString(r));
					return null;
				} else {
					password = Coder.toMyCoder(password);
					args = new String[] { password, userId };
					jdbcTool.updateSql(
							"update rep_user set password=? where phone = ?",
							args);
					r.setErrorCode(Result.SUCCESS);
					r.setErrorMessage("修改成功!");
					r.setCount(0);
					writeToPage(response, JSON.toJSONString(r));
					return null;
				}
			}
		} catch (Exception e) {
			log.error(e);
			r.setErrorCode(Result.SREVER_ERROR);
			r.setErrorMessage("出现系统异常!");
			r.setCount(0);
			writeToPage(response, JSON.toJSONString(r));
			return null;
		}

	}

	/**
	 * 忘记密码.
	 * 
	 * @Title: forgetPass
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String forgetPass() {
		return null;
	}
}
